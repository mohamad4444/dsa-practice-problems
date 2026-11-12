# 10. Regular Expression Matching

- **Difficulty:** Hard
- **LeetCode URL:** [LeetCode #10](https://leetcode.com/problems/regular-expression-matching/)
- **Tags:** String, Dynamic Programming, Recursion

---

## 📝 Problem Description

Given an input string `s` and a pattern `p`, implement regular expression matching with support for `'.'` and `'*'` where:
- `'.'` Matches any single character.
- `'*'` Matches zero or more of the preceding element.

The matching should cover the **entire** input string (not partial).

### Example 1:
```
Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
```

### Example 2:
```
Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
```

### Example 3:
```
Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
```

### Constraints:
- `1 <= s.length <= 20`
- `1 <= p.length <= 20`
- `s` contains only lowercase English letters.
- `p` contains only lowercase English letters, `'.'`, and `'*'`.
- It is guaranteed for each appearance of the character `'*'`, there will be a previous valid character to match.

---

## 💡 Approach & Intuition

- Dynamic Programming / Memoized Recursion:
  - Base case: if pattern `p` is empty, return `s.isEmpty()`.
  - Check if first character matches: `firstMatch = (!s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'))`.
  - If `p.length() >= 2 && p.charAt(1) == '*'`:
    - Either ignore the pattern `x*` (match 0 times: `isMatch(s, p.substring(2))`), OR
    - If `firstMatch`, consume one char from `s` (`isMatch(s.substring(1), p)`).
  - Else:
    - Return `firstMatch && isMatch(s.substring(1), p.substring(1))`.

---

## 🧪 Run & Test
Run `Main.java` to test.
