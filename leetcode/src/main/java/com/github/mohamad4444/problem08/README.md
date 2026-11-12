# 8. String to Integer (atoi)

- **Difficulty:** Medium
- **LeetCode URL:** [LeetCode #8](https://leetcode.com/problems/string-to-integer-atoi/)
- **Tags:** String

---

## 📝 Problem Description

Implement the `myAtoi(string s)` function, which converts a string to a 32-bit signed integer.

The algorithm for `myAtoi(string s)` is as follows:
1. **Whitespace:** Ignore any leading whitespace (`" "`).
2. **Signedness:** Determine the sign by checking if the next character is `'-'` or `'+'`. Assume positive if neither is present.
3. **Conversion:** Read the integer by skipping leading zeros until a non-digit character is encountered or the end of the string is reached. If no digits were read, then the result is `0`.
4. **Rounding:** If the integer is out of the 32-bit signed integer range `[-2^31, 2^31 - 1]`, then clamp the integer so that it remains in the range.

### Example 1:
```
Input: s = "42"
Output: 42
```

### Example 2:
```
Input: s = "   -42"
Output: -42
```

### Example 3:
```
Input: s = "1337c0d3"
Output: 1337
```

### Example 4:
```
Input: s = "0-1"
Output: 0
```

### Example 5:
```
Input: s = "words and 987"
Output: 0
```

### Constraints:
- `0 <= s.length <= 200`
- `s` consists of English letters (lower-case and upper-case), digits (`0-9`), `' '`, `'+'`, `'-'`, and `'.'`.

---

## 💡 Approach & Intuition

### Approach 1: Lookahead Clamping (`Solution.java`)
- Skip leading spaces.
- Process sign character.
- Check lookahead overflow before multiplying current result by 10.

### Approach 2: Direct Overflow Threshold (`Solution_2.java`)
- Check `res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && digit > 7)` to prevent integer overflow before arithmetic.
- **Complexity:**
  - Time Complexity: $\mathcal{O}(N)$ where $N = \text{s.length()}$.
  - Space Complexity: $\mathcal{O}(1)$.

---

## 🧪 Run & Test
Run `Main.java` or `Main_2.java` to test all 10 edge cases.
