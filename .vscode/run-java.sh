#!/usr/bin/env bash
set -e

# Collect all arguments and strip any surrounding quotes or whitespace
file_path="$*"
while :; do
    case "$file_path" in
        \"* | \'* | \ * ) file_path="${file_path#?}" ;;
        *\" | *\' | *\  ) file_path="${file_path%?}" ;;
        *) break ;;
    esac
done

if [ -z "$file_path" ] || [ ! -f "$file_path" ]; then
    echo "Error: File not found: '$file_path'" >&2
    exit 1
fi

file_dir="$(cd "$(dirname "$file_path")" && pwd)"
file_name="$(basename "$file_path")"
base_name="${file_name%.*}"

target_file="$file_path"
target_class_name="$base_name"

# If current file does not contain a main method, check for companion Main file in the same directory
if ! grep -qE '\bpublic\s+static\s+void\s+main\b' "$file_path"; then
    suffix="${base_name#Solution}"
    if [ "$suffix" != "$base_name" ] && [ -f "$file_dir/Main$suffix.java" ] && grep -qE '\bpublic\s+static\s+void\s+main\b' "$file_dir/Main$suffix.java"; then
        target_file="$file_dir/Main$suffix.java"
        target_class_name="Main$suffix"
    elif [ -f "$file_dir/Main.java" ] && grep -qE '\bpublic\s+static\s+void\s+main\b' "$file_dir/Main.java"; then
        target_file="$file_dir/Main.java"
        target_class_name="Main"
    fi
fi

if ! grep -qE '\bpublic\s+static\s+void\s+main\b' "$target_file"; then
    echo "Error: No 'public static void main' method found in '$file_name' or companion Main file in '$file_dir'." >&2
    exit 1
fi

target_dir="$(cd "$(dirname "$target_file")" && pwd)"

# Extract package declaration if present
pkg=$(grep -v '^\s*//' "$target_file" | grep -E '^\s*package\s+[^;]+;' | head -n 1 | sed -E 's/^\s*package\s+([^;]+);.*/\1/' | tr -d ' \r\t')

if [ -n "$pkg" ]; then
    pkg_rel_path="$(echo "$pkg" | tr '.' '/')"
    if [[ "$target_dir" == *"$pkg_rel_path" ]]; then
        source_root="${target_dir%"/$pkg_rel_path"}"
    else
        source_root="$target_dir"
    fi
    full_class_name="$pkg.$target_class_name"
else
    source_root="$target_dir"
    full_class_name="$target_class_name"
fi

# Locate workspace root
workspace_dir=""
cur="$target_dir"
while [ "$cur" != "/" ] && [ -n "$cur" ]; do
    if [ -d "$cur/.git" ] || [ -f "$cur/Makefile" ]; then
        workspace_dir="$cur"
        break
    fi
    cur="$(dirname "$cur")"
done

if [ -n "$workspace_dir" ]; then
    build_dir="$workspace_dir/.build/classes"
else
    build_dir="/tmp/java_runner_build"
fi
mkdir -p "$build_dir"

compile_files=("$target_file")
if [ "$file_path" != "$target_file" ]; then
    compile_files+=("$file_path")
fi

if [[ "$target_class_name" =~ ^Main(_.*)?$ ]]; then
    sol_suffix="${target_class_name#Main}"
    sol_file="$target_dir/Solution$sol_suffix.java"
    if [ -f "$sol_file" ] && [ "$sol_file" != "$file_path" ]; then
        compile_files+=("$sol_file")
    fi
fi

# Compile target and related files
javac -d "$build_dir" -sourcepath "$source_root" -cp "$build_dir:$source_root" "${compile_files[@]}"

# Run the class
exec java -cp "$build_dir" "$full_class_name"
