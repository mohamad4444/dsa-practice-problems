#!/usr/bin/env python3
"""
LeetCode Problem Importer
Auto-generates packages, Solution.java (or Solution_2.java), Main.java test harness,
and README.md problem documentation from LeetCode clipboard snippets via xclip.
"""

import sys
import os
import re
import json
import html
import subprocess
import urllib.request
from pathlib import Path

def find_project_root():
    """Locate the repository's top-level LeetCode Maven project."""
    current = Path.cwd().resolve()
    importer_dir = Path(__file__).resolve().parent
    repository_root = importer_dir.parent
    search_paths = [
        repository_root / "leetcode",  # Canonical destination.
        current,
        current / "leetcode",
        current.parent,
        current.parent / "leetcode",
    ]

    for p in search_paths:
        pom = p / "pom.xml"
        src_java = p / "src" / "main" / "java"
        if pom.exists() and src_java.is_dir():
            return p.resolve(), src_java.resolve()

    src_java = current / "src" / "main" / "java"
    if src_java.is_dir():
        return current.resolve(), src_java.resolve()

    raise FileNotFoundError(
        "Could not find the top-level LeetCode Maven project at "
        f"{repository_root / 'leetcode'}."
    )

def find_base_package(src_java_path):
    """Find the base package (e.g. com.github.mohamad4444)."""
    default_pkg = "com.github.mohamad4444"
    if not src_java_path.exists():
        return default_pkg

    known_path = src_java_path / "com" / "github" / "mohamad4444"
    if known_path.exists():
        return "com.github.mohamad4444"

    curr = src_java_path
    pkg_parts = []
    while curr.is_dir():
        dirs = [d for d in curr.iterdir() if d.is_dir() and not d.name.startswith(".")]
        if len(dirs) == 1 and not dirs[0].name.startswith("problem"):
            pkg_parts.append(dirs[0].name)
            curr = dirs[0]
        else:
            break

    if pkg_parts:
        return ".".join(pkg_parts)
    return default_pkg

def parse_problem_arg(arg):
    """
    Parse problem argument supporting numbers and solution variants:
    - '168'    -> problem 168, Solution, Main
    - '168-2'  -> problem 168, Solution_2, Main_2
    - '168_2'  -> problem 168, Solution_2, Main_2
    - '8'      -> problem08, Solution, Main
    - '8-2'    -> problem08, Solution_2, Main_2
    - '08-2'   -> problem08, Solution_2, Main_2
    """
    cleaned = str(arg).strip()
    match = re.match(r'^(?:problem)?(\d+)(?:[-_](\d+))?$', cleaned, re.IGNORECASE)
    if not match:
        raise ValueError(
            f"Invalid problem format '{arg}'. Expected format like '168' or '168-2' (or '8-2')."
        )

    num = int(match.group(1))
    variant = int(match.group(2)) if match.group(2) else None

    # Zero pad numbers < 10 (e.g. problem08) matching repository convention
    pkg_name = f"problem{num:02d}" if num < 10 else f"problem{num}"
    sol_class_name = f"Solution_{variant}" if variant else "Solution"
    main_class_name = f"Main_{variant}" if variant else "Main"

    return {
        "num": num,
        "variant": variant,
        "pkg_name": pkg_name,
        "sol_class_name": sol_class_name,
        "main_class_name": main_class_name,
    }

def read_clipboard_xclip():
    """
    Read clipboard directly using xclip (with fallback to wl-paste if Wayland).
    """
    # 1. Try xclip directly
    try:
        res = subprocess.run(
            ["xclip", "-selection", "clipboard", "-o"],
            capture_output=True,
            text=True,
            timeout=2
        )
        if res.returncode == 0 and res.stdout:
            return res.stdout
    except FileNotFoundError:
        pass
    except Exception:
        pass

    # 2. Try wl-paste fallback (for Wayland sessions)
    try:
        res = subprocess.run(
            ["wl-paste"],
            capture_output=True,
            text=True,
            timeout=2
        )
        if res.returncode == 0 and res.stdout:
            return res.stdout
    except FileNotFoundError:
        pass
    except Exception:
        pass

    # 3. Try tkinter fallback
    try:
        import tkinter as tk
        root = tk.Tk()
        root.withdraw()
        content = root.clipboard_get()
        root.destroy()
        if content:
            return content
    except Exception:
        pass

    return None

def fetch_leetcode_problem(prob_num):
    """
    Fetch problem details (title, difficulty, content, test cases) from LeetCode public GraphQL API.
    Returns a dict with metadata, or None if offline/error.
    """
    url = "https://leetcode.com/graphql"
    headers = {
        "Content-Type": "application/json",
        "User-Agent": "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36",
        "Referer": "https://leetcode.com/problemset/all/",
    }

    # 1. Search for titleSlug using frontend ID
    search_payload = {
        "query": """
        query problemsetQuestionList($categorySlug: String, $limit: Int, $skip: Int, $filters: QuestionListFilterInput) {
          problemsetQuestionList: questionList(
            categorySlug: $categorySlug
            limit: $limit
            skip: $skip
            filters: $filters
          ) {
            questions: data {
              frontendQuestionId: questionFrontendId
              title
              titleSlug
              difficulty
            }
          }
        }
        """,
        "variables": {
            "categorySlug": "",
            "skip": 0,
            "limit": 50,
            "filters": {
                "searchKeywords": str(prob_num)
            }
        }
    }

    try:
        req = urllib.request.Request(
            url,
            data=json.dumps(search_payload).encode("utf-8"),
            headers=headers,
            method="POST"
        )
        with urllib.request.urlopen(req, timeout=4) as resp:
            data = json.loads(resp.read().decode("utf-8"))

        questions = data.get("data", {}).get("problemsetQuestionList", {}).get("questions", [])
        matched = None
        for q in questions:
            if str(q.get("frontendQuestionId")) == str(prob_num):
                matched = q
                break

        if not matched:
            return None

        title_slug = matched["titleSlug"]

        # 2. Query full question details by titleSlug
        detail_payload = {
            "query": """
            query questionData($titleSlug: String!) {
              question(titleSlug: $titleSlug) {
                questionFrontendId
                title
                titleSlug
                difficulty
                content
                exampleTestcaseList
                topicTags {
                  name
                }
              }
            }
            """,
            "variables": {"titleSlug": title_slug}
        }

        req2 = urllib.request.Request(
            url,
            data=json.dumps(detail_payload).encode("utf-8"),
            headers=headers,
            method="POST"
        )
        with urllib.request.urlopen(req2, timeout=4) as resp2:
            data2 = json.loads(resp2.read().decode("utf-8"))

        return data2.get("data", {}).get("question")

    except Exception:
        return None

def html_to_markdown(html_str):
    """Convert LeetCode HTML problem description into clean GitHub Flavored Markdown."""
    if not html_str:
        return ""

    text = html_str
    # Replace headers
    text = re.sub(r'<h[1-6][^>]*>(.*?)</h[1-6]>', r'\n### \1\n', text, flags=re.DOTALL)
    # Replace preformatted code blocks
    text = re.sub(r'<pre><code>(.*?)</code></pre>', r'\n```\n\1\n```\n', text, flags=re.DOTALL)
    text = re.sub(r'<pre>(.*?)</pre>', r'\n```\n\1\n```\n', text, flags=re.DOTALL)
    text = re.sub(r'<code[^>]*>(.*?)</code>', r'`\1`', text, flags=re.DOTALL)
    # Replace strong / bold
    text = re.sub(r'<(?:strong|b)[^>]*>(.*?)</(?:strong|b)>', r'**\1**', text, flags=re.DOTALL)
    # Replace italics / em
    text = re.sub(r'<(?:em|i)[^>]*>(.*?)</(?:em|i)>', r'*\1*', text, flags=re.DOTALL)
    # Replace lists
    text = re.sub(r'<li[^>]*>(.*?)</li>', r'- \1', text, flags=re.DOTALL)
    # Replace paragraphs and line breaks
    text = re.sub(r'<p[^>]*>', r'\n\n', text)
    text = re.sub(r'</p>', r'', text)
    text = re.sub(r'<br\s*/?>', r'\n', text)
    # Strip remaining HTML tags
    text = re.sub(r'<[^>]+>', '', text)
    # Unescape HTML entities
    text = html.unescape(text)
    # Normalize excess blank lines
    text = re.sub(r'\n{3,}', '\n\n', text)
    return text.strip()

def generate_readme_content(prob_info, leetcode_data=None):
    """Generate Markdown content for problem README.md."""
    num = prob_info["num"]
    title = f"Problem {num}"
    difficulty = "Unknown"
    slug = ""
    tags = []
    description = "No online description available."

    if leetcode_data:
        q_id = leetcode_data.get("questionFrontendId", num)
        q_title = leetcode_data.get("title", f"Problem {num}")
        title = f"{q_id}. {q_title}"
        difficulty = leetcode_data.get("difficulty", "Unknown")
        slug = leetcode_data.get("titleSlug", "")
        tags = [t["name"] for t in leetcode_data.get("topicTags", []) if "name" in t]
        raw_content = leetcode_data.get("content", "")
        if raw_content:
            description = html_to_markdown(raw_content)

    url_line = f"- **LeetCode URL:** [LeetCode #{num}](https://leetcode.com/problems/{slug}/)" if slug else ""
    tags_line = f"- **Tags:** {', '.join(tags)}" if tags else ""

    md = f"""# {title}

- **Difficulty:** {difficulty}
{url_line}
{tags_line}

---

## 📝 Problem Description

{description}

---

## 💡 Approach & Intuition

### Approach 1
- **Intuition:** 
- **Algorithm:**
- **Complexity:**
  - Time Complexity: $\\mathcal{{O}}(...)$
  - Space Complexity: $\\mathcal{{O}}(...)$

---

## 🧪 Run & Test
Execute `Main.java` (or click **Run | Debug** above `main()` in VS Code) to verify solutions against test cases.
"""
    return md

def split_params(params_str):
    """Split parameter list by commas, respecting generic brackets <...>."""
    params = []
    current = []
    depth = 0
    for char in params_str:
        if char == '<':
            depth += 1
            current.append(char)
        elif char == '>':
            depth = max(0, depth - 1)
            current.append(char)
        elif char == ',' and depth == 0:
            p = "".join(current).strip()
            if p:
                params.append(p)
            current = []
        else:
            current.append(char)
    p = "".join(current).strip()
    if p:
        params.append(p)
    return params

def parse_solution_method(code_str, sol_class_name):
    """Extract the main LeetCode method from the Solution class."""
    method_pattern = re.compile(
        r'public\s+(?:<[^>]+>\s+)?([A-Za-z0-9_<>, \[\]]+?)\s+([a-zA-Z0-9_]+)\s*\(([^)]*)\)\s*(?:throws\s+[A-Za-z0-9_,\s]+)?\s*\{',
        re.MULTILINE
    )

    matches = method_pattern.findall(code_str)
    for return_type, method_name, params_str in matches:
        return_type = return_type.strip()
        method_name = method_name.strip()

        # Skip main method or constructor
        if method_name in ("main", "Solution", sol_class_name):
            continue

        params = []
        raw_params = split_params(params_str)
        for p in raw_params:
            parts = p.rsplit(maxsplit=1)
            if len(parts) == 2:
                p_type, p_name = parts[0].strip(), parts[1].strip()
                params.append((p_type, p_name))
            else:
                params.append(("Object", parts[0]))

        return {
            "method_name": method_name,
            "return_type": return_type,
            "params": params
        }

    return None

def ensure_default_return(code_str, method_info):
    """
    If method body is empty or lacks a return statement (and return_type is not void),
    insert an appropriate default return (null, 0, false, etc.) so Java compiles cleanly.
    """
    if not method_info:
        return code_str

    return_type = method_info["return_type"].strip()
    if return_type == "void":
        return code_str

    # Determine default return value based on return type
    if return_type in ("boolean", "Boolean"):
        ret_val = "false"
    elif return_type in ("int", "Integer", "short", "Short", "byte", "Byte"):
        ret_val = "0"
    elif return_type in ("long", "Long"):
        ret_val = "0L"
    elif return_type in ("double", "Double"):
        ret_val = "0.0"
    elif return_type in ("float", "Float"):
        ret_val = "0.0f"
    elif return_type in ("char", "Character"):
        ret_val = "' '"
    else:
        # Objects, String, List, int[], ListNode, TreeNode, etc.
        ret_val = "null"

    method_name = method_info["method_name"]
    pattern = re.compile(
        rf'(public\s+(?:<[^>]+>\s+)?{re.escape(return_type)}\s+{re.escape(method_name)}\s*\([^)]*\)\s*(?:throws\s+[A-Za-z0-9_,\s]+)?\s*\{{)',
        re.MULTILINE
    )

    match = pattern.search(code_str)
    if not match:
        return code_str

    start_brace = match.end() - 1
    depth = 0
    end_brace = -1
    for i in range(start_brace, len(code_str)):
        if code_str[i] == '{':
            depth += 1
        elif code_str[i] == '}':
            depth -= 1
            if depth == 0:
                end_brace = i
                break

    if end_brace == -1:
        return code_str

    body = code_str[start_brace + 1:end_brace]
    # If the body already contains a return statement, leave it intact
    if re.search(r'\breturn\b', body):
        return code_str

    # Insert default return statement
    indent = "        "
    inserted = f"\n{indent}return {ret_val};\n    "
    new_code = code_str[:start_brace + 1] + inserted + code_str[end_brace:]
    return new_code

def clean_solution_code(code_str, sol_class_name):
    """Clean code and replace 'class Solution' with 'public class <sol_class_name>'."""
    code = code_str.strip()
    # Remove markdown code block markers
    code = re.sub(r'^```[a-zA-Z]*\s*\n', '', code)
    code = re.sub(r'\n```\s*$', '', code)
    code = code.strip()

    # Remove any existing package declarations
    lines = code.split('\n')
    cleaned_lines = []
    for line in lines:
        if line.strip().startswith("package ") and line.strip().endswith(";"):
            continue
        cleaned_lines.append(line)
    code = '\n'.join(cleaned_lines).strip()

    # Replace 'class Solution' or 'public class Solution' with 'public class <sol_class_name>'
    code = re.sub(
        r'(?:public\s+)?class\s+Solution\b',
        f'public class {sol_class_name}',
        code,
        count=1
    )

    return code

def generate_pseudo_param_value(param_type, param_name):
    """Generate sensible pseudo/dummy values for testing parameters."""
    ptype = param_type.strip()
    pname = param_name.lower()

    if ptype in ("int", "Integer"):
        if "column" in pname:
            return "28"
        elif "target" in pname:
            return "9"
        elif pname in ("k", "val", "x"):
            return "3"
        elif pname in ("n", "num"):
            return "5"
        return "0"

    elif ptype in ("long", "Long"):
        return "100L"

    elif ptype in ("double", "Double"):
        return "3.14"

    elif ptype in ("float", "Float"):
        return "3.14f"

    elif ptype in ("boolean", "Boolean"):
        return "true"

    elif ptype in ("char", "Character"):
        return "'a'"

    elif ptype == "String":
        if "word" in pname or "title" in pname:
            return '"example"'
        elif "s" in pname:
            return '"hello"'
        return '""'

    elif ptype == "int[]":
        if pname in ("nums", "height", "arr"):
            return "new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}"
        return "new int[]{1, 2, 3}"

    elif ptype == "int[][]":
        return "new int[][]{{1, 2}, {3, 4}}"

    elif ptype == "char[]":
        return "new char[]{'h', 'e', 'l', 'l', 'o'}"

    elif ptype == "char[][]":
        return "new char[][]{{'a', 'b'}, {'c', 'd'}}"

    elif ptype == "String[]":
        return 'new String[]{"apple", "banana", "cherry"}'

    elif ptype == "ListNode":
        return "ListNode.of(1, 2, 3, 4, 5)"

    elif ptype == "TreeNode":
        return "TreeNode.of(1, 2, 3, 4, 5)"

    elif ptype == "Node":
        return "Node.fromAdjList(new int[][]{{2, 4}, {1, 3}, {2, 4}, {1, 3}})"

    elif ptype.startswith("List<Integer>"):
        return "new ArrayList<>(List.of(1, 2, 3))"

    elif ptype.startswith("List<String>"):
        return 'new ArrayList<>(List.of("a", "b", "c"))'

    elif ptype.startswith("List<"):
        return "new ArrayList<>()"

    elif ptype.startswith("Map<"):
        return "new HashMap<>()"

    elif ptype.startswith("Set<"):
        return "new HashSet<>()"

    return "null"

def generate_solution_file_content(full_package, code, base_package):
    """Generate formatted content for Solution.java or Solution_X.java."""
    needs_list_node = "ListNode" in code
    needs_tree_node = "TreeNode" in code
    needs_graph_node = bool(re.search(r'\bNode\b', code))

    imports = []
    if "import java.util." not in code:
        imports.append("import java.util.*;")
    if needs_list_node and f"import {base_package}.common.ListNode" not in code:
        imports.append(f"import {base_package}.common.ListNode;")
    if needs_tree_node and f"import {base_package}.common.TreeNode" not in code:
        imports.append(f"import {base_package}.common.TreeNode;")
    if needs_graph_node and f"import {base_package}.common.Node" not in code:
        imports.append(f"import {base_package}.common.Node;")

    imports_block = "\n".join(imports) + "\n" if imports else ""

    return f"""package {full_package};

{imports_block}
{code}
"""

def generate_main_file_content(full_package, base_package, prob_info, method_info, raw_code, leetcode_data=None):
    """Generate formatted content for Main.java / Main_X.java test runner."""
    needs_list_node = "ListNode" in raw_code
    needs_tree_node = "TreeNode" in raw_code
    needs_graph_node = bool(re.search(r'\bNode\b', raw_code))

    imports = ["import java.util.*;"]
    if needs_list_node:
        imports.append(f"import {base_package}.common.ListNode;")
    if needs_tree_node:
        imports.append(f"import {base_package}.common.TreeNode;")
    if needs_graph_node:
        imports.append(f"import {base_package}.common.Node;")

    imports_block = "\n".join(imports)

    num = prob_info["num"]
    variant = prob_info["variant"]
    sol_class = prob_info["sol_class_name"]
    main_class = prob_info["main_class_name"]
    header_suffix = f" (Solution {variant})" if variant else ""

    if not method_info:
        return f"""package {full_package};

{imports_block}

public class {main_class} {{
    public static void main(String[] args) {{
        {sol_class} solution = new {sol_class}();

        System.out.println("==================================================");
        System.out.println("Running Problem {num}{header_suffix}");
        System.out.println("==================================================");

        // TODO: Call your {sol_class} methods here
    }}
}}
"""

    method_name = method_info["method_name"]
    return_type = method_info["return_type"]
    params = method_info["params"]

    param_decls = []
    param_names = []
    print_inputs = []

    for p_type, p_name in params:
        val = generate_pseudo_param_value(p_type, p_name)
        param_decls.append(f"        {p_type} {p_name} = {val};")
        param_names.append(p_name)

        if "[]" in p_type:
            if "[][]" in p_type:
                print_inputs.append(f'        System.out.println("Input: {p_name} = " + Arrays.deepToString({p_name}));')
            else:
                print_inputs.append(f'        System.out.println("Input: {p_name} = " + Arrays.toString({p_name}));')
        elif p_type == "Node":
            print_inputs.append(f'        System.out.println("Input: {p_name} = " + Node.toAdjListString({p_name}));')
        else:
            print_inputs.append(f'        System.out.println("Input: {p_name} = " + {p_name});')

    param_decls_str = "\n".join(param_decls) if param_decls else "        // No arguments required"
    print_inputs_str = "\n".join(print_inputs)
    call_args_str = ", ".join(param_names)

    # Output execution logic
    if return_type == "void":
        exec_str = f"        solution.{method_name}({call_args_str});"
        if params and "[]" in params[0][0]:
            first_param = params[0][1]
            if "[][]" in params[0][0]:
                print_output = f'        System.out.println("Output (modified): " + Arrays.deepToString({first_param}));'
            else:
                print_output = f'        System.out.println("Output (modified): " + Arrays.toString({first_param}));'
        else:
            print_output = '        System.out.println("Execution completed (void).");'
    elif return_type == "Node":
        exec_str = f"        var result = solution.{method_name}({call_args_str});"
        print_output = (
            '        System.out.println("Output (adjList): " + Node.toAdjListString(result));\n'
            + (f'        System.out.println("Deep Copy Validated: " + (Node.isDeepCopy({params[0][1]}, result) ? "✅ PASS" : "❌ FAIL"));' if params and params[0][0] == "Node" else '')
        )
    elif "[]" in return_type:
        if "[][]" in return_type:
            exec_str = f"        var result = solution.{method_name}({call_args_str});"
            print_output = '        System.out.println("Output: " + Arrays.deepToString(result));'
        else:
            exec_str = f"        var result = solution.{method_name}({call_args_str});"
            print_output = '        System.out.println("Output: " + Arrays.toString(result));'
    else:
        exec_str = f"        var result = solution.{method_name}({call_args_str});"
        print_output = '        System.out.println("Output: " + result);'

    return f"""package {full_package};

{imports_block}

public class {main_class} {{
    public static void main(String[] args) {{
        {sol_class} solution = new {sol_class}();

        // Pseudo parameters for testing (manually update these test values)
{param_decls_str}

        System.out.println("==================================================");
        System.out.println("Running Problem {num}{header_suffix}: {method_name}");
        System.out.println("==================================================");
{print_inputs_str}

{exec_str}
{print_output}
    }}
}}
"""

def main():
    import argparse
    parser = argparse.ArgumentParser(
        description="Auto-import LeetCode problems directly from xclip clipboard into packages with test runner and README documentation."
    )
    parser.add_argument("problem", nargs="?", help="Problem number or variant (e.g. 168, 168-2, 8, 8-2)")
    parser.add_argument("-y", "--overwrite", action="store_true", help="Overwrite existing files without prompting")

    args = parser.parse_args()

    problem_arg = args.problem
    if not problem_arg:
        try:
            problem_arg = input("Enter problem number (e.g. 168 or 168-2): ").strip()
            if not problem_arg:
                print("Error: Problem number is required.")
                sys.exit(1)
        except (KeyboardInterrupt, EOFError):
            print("\nAborted.")
            sys.exit(1)

    try:
        prob_info = parse_problem_arg(problem_arg)
    except ValueError as e:
        print(f"\n❌ {e}")
        sys.exit(1)

    # 1. Directly read clipboard using xclip
    clip_content = read_clipboard_xclip()

    # 2. Check if xclip returned anything
    if not clip_content or not clip_content.strip():
        print("\n" + "=" * 60)
        print("❌ Import Failed: Clipboard is empty!")
        print("=" * 60)
        print("Could not read clipboard using 'xclip -selection clipboard -o'.")
        print("Please copy the Solution class from LeetCode first, then run:")
        print(f"  make problem {problem_arg}")
        print("=" * 60)
        sys.exit(1)

    # 3. Strictly verify that 'class Solution' is in clipboard
    if not re.search(r'\bclass\s+Solution\b', clip_content):
        preview = clip_content.strip()[:180].replace("\n", " ")
        if len(clip_content.strip()) > 180:
            preview += "..."

        print("\n" + "=" * 60)
        print("❌ Import Failed: 'class Solution' not found in clipboard!")
        print("=" * 60)
        print("Please make sure you copied the LeetCode Solution class.")
        print(f"\nCurrent clipboard preview: \"{preview}\"")
        print("=" * 60)
        sys.exit(1)

    # Clean code and substitute class name if variant (e.g. Solution_2)
    sol_class_name = prob_info["sol_class_name"]
    main_class_name = prob_info["main_class_name"]
    pkg_name = prob_info["pkg_name"]
    num = prob_info["num"]

    cleaned_code = clean_solution_code(clip_content, sol_class_name)
    method_info = parse_solution_method(cleaned_code, sol_class_name)

    # Ensure method returns default value (null, 0, false, etc.) if empty
    cleaned_code = ensure_default_return(cleaned_code, method_info)

    # Fetch online problem description & testcases from LeetCode GraphQL
    print(f"🌐 Fetching Problem {num} metadata from LeetCode...")
    leetcode_data = fetch_leetcode_problem(num)
    if leetcode_data:
        print(f"   ✓ Found: {leetcode_data.get('title', f'Problem {num}')} ({leetcode_data.get('difficulty', '')})")
    else:
        print("   ℹ (Offline or question metadata not available; using standard template)")

    project_root, src_java = find_project_root()
    base_pkg = find_base_package(src_java)
    full_pkg = f"{base_pkg}.{pkg_name}"

    package_dir = src_java / base_pkg.replace('.', '/') / pkg_name
    package_dir.mkdir(parents=True, exist_ok=True)

    solution_file = package_dir / f"{sol_class_name}.java"
    main_file = package_dir / f"{main_class_name}.java"
    readme_file = package_dir / "README.md"

    if (solution_file.exists() or main_file.exists()) and not args.overwrite:
        print(f"\n⚠️  Warning: {solution_file.name} or {main_file.name} already exists in {pkg_name}.")
        try:
            choice = input(f"Overwrite {solution_file.name} and {main_file.name}? [y/N]: ").strip().lower()
            if choice not in ('y', 'yes'):
                print("Import cancelled.")
                sys.exit(0)
        except (KeyboardInterrupt, EOFError):
            print("\nAborted.")
            sys.exit(1)

    # Write Solution file
    sol_content = generate_solution_file_content(full_pkg, cleaned_code, base_pkg)
    solution_file.write_text(sol_content, encoding='utf-8')

    # Write Main file
    main_content = generate_main_file_content(full_pkg, base_pkg, prob_info, method_info, cleaned_code, leetcode_data)
    main_file.write_text(main_content, encoding='utf-8')

    # Write README.md next to problem if not already existing or overwrite
    if not readme_file.exists() or args.overwrite:
        readme_content = generate_readme_content(prob_info, leetcode_data)
        readme_file.write_text(readme_content, encoding='utf-8')

    variant_desc = f" (Solution #{prob_info['variant']})" if prob_info['variant'] else ""
    print("\n" + "=" * 60)
    print(f"✅ Successfully imported Problem {num}{variant_desc}!")
    print("=" * 60)
    print(f" Package:  {full_pkg}")
    print(f" Location: {package_dir}")
    print(f" - Solution: {solution_file.relative_to(project_root)}")
    print(f" - Main:     {main_file.relative_to(project_root)}")
    print(f" - README:   {readme_file.relative_to(project_root)}")
    if method_info:
        print(f" Detected: {method_info['return_type']} {method_info['method_name']}(...)")
    print("=" * 60)

if __name__ == "__main__":
    main()
