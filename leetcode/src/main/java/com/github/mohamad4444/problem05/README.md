# 5. Longest Palindromic Substring

- **Difficulty:** Medium
- **LeetCode URL:** [LeetCode #5](https://leetcode.com/problems/longest-palindromic-substring/)
- **Tags:** Two Pointers, String, Dynamic Programming

---

## 📝 Problem Description

Given a string `s`, return *the longest palindromic substring* in `s`.

### Example 1:
```
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
```

### Example 2:
```
Input: s = "cbbd"
Output: "bb"
```

### Constraints:
- `1 <= s.length <= 1000`
- `s` consist of only digits and English letters.

---

## 💡 Approach & Intuition

### Approach 1: Check Palindrome Range Pruning (`Solution.java`)
- **Intuition:** Scan starting indices and check substrings against current maximum known length to prune unnecessary palindrome checks.
- **Complexity:**
  - Time Complexity: $\mathcal{O}(N^3)$ worst-case.
  - Space Complexity: $\mathcal{O}(1)$ auxiliary space.

### Approach 2: Expand Around Center (`Solution_2.java`)
- **Intuition:** A palindrome mirrors around its center. There are $2N - 1$ centers:
  - $N$ single character centers (odd length: `"aba"` centered at `'b'`).
  - $N - 1$ between-character centers (even length: `"abba"` centered between `'b'` and `'b'`).
  - Expanding outward from each center takes $\mathcal{O}(N)$.
- **Complexity:**
  - Time Complexity: $\mathcal{O}(N^2)$
  - Space Complexity: $\mathcal{O}(1)$

---

## 🧪 Run & Test
Execute `Main.java` or `Main_2.java` to test both solutions across all edge cases.
