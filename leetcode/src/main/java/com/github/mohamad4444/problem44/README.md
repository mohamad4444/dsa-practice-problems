# 44. Wildcard Matching

- **Difficulty:** Hard
- **LeetCode URL:** [LeetCode #44](https://leetcode.com/problems/wildcard-matching/)
- **Tags:** String, Dynamic Programming, Greedy, Recursion

---

## 📝 Problem Description

Given an input string (`s`) and a pattern (`p`), implement wildcard pattern matching with support for `'?'` and `'*'` where:

	- `'?'` Matches any single character.
	- `'*'` Matches any sequence of characters (including the empty sequence).

The matching should cover the **entire** input string (not partial).

 

**Example 1:**

```

**Input:** s = "aa", p = "a"
**Output:** false
**Explanation:** "a" does not match the entire string "aa".

```

**Example 2:**

```

**Input:** s = "aa", p = "*"
**Output:** true
**Explanation:** '*' matches any sequence.

```

**Example 3:**

```

**Input:** s = "cb", p = "?a"
**Output:** false
**Explanation:** '?' matches 'c', but the second letter is 'a', which does not match 'b'.

```

 

**Constraints:**

	- `0 <= s.length, p.length <= 2000`
	- `s` contains only lowercase English letters.
	- `p` contains only lowercase English letters, `'?'` or `'*'`.

---

## 💡 Approach & Intuition

### Approach 1
- **Implementation:** [Solution.java](Solution.java) — existing memoized recursion.
- **Algorithm:** Try consuming a character or skipping `*`, caching each pair of string
  and pattern positions. Consecutive stars are collapsed first.
- **Complexity:**
  - Matching takes $\mathcal{O}(s.length \times p.length)$ time and memo space.
    The current pattern reduction also uses repeated string concatenation.

### Approach 2 — online alternative to study

- **Implementation:** [Solution_2.java](Solution_2.java), supplied in chat from an
  online solution. Original URL not provided.
- **Intuition:** Remember the most recent `*` and where its match began. When a later
  character fails, let that star consume one more character and retry the pattern
  after it. Once the string is consumed, only trailing stars may remain.
- **Complexity:** $\mathcal{O}(s.length \times p.length)$ worst-case time and
  $\mathcal{O}(1)$ extra space. Retrying a long suffix can revisit pattern characters;
  for example, many `a`s against `*aaaaab`. It saves the memo table but is not always linear.
- **Check:** Run [Main_2.java](Main_2.java) for 12 example and boundary checks.

This algorithm uses #44's wildcard semantics (`?` and `*`); it does not implement
#10's regular-expression semantics (`.` and repetition of the preceding element).
Record understanding and practice separately in the
[learning tracker](../../../../../../../../Docs/learning-tracker.md).

---

## 🧪 Run & Test
Execute `Main.java` (or click **Run | Debug** above `main()` in VS Code) to verify solutions against test cases.
