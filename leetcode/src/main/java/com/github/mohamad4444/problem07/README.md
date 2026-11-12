# 7. Reverse Integer

- **Difficulty:** Medium
- **LeetCode URL:** [LeetCode #7](https://leetcode.com/problems/reverse-integer/)
- **Tags:** Math

---

## 📝 Problem Description

Given a signed 32-bit integer `x`, return `x` with its digits reversed. If reversing `x` causes the value to go outside the signed 32-bit integer range `[-2^31, 2^31 - 1]`, then return `0`.

**Assume the environment does not allow you to store 64-bit integers (signed or unsigned).**

### Example 1:
```
Input: x = 123
Output: 321
```

### Example 2:
```
Input: x = -123
Output: -321
```

### Example 3:
```
Input: x = 120
Output: 21
```

### Constraints:
- `-2^31 <= x <= 2^31 - 1`

---

## 💡 Approach & Intuition

### Approach 1: Digit Array Boundary Check (`Solution.java`)
- Check individual digits against `Integer.MAX_VALUE` and `Integer.MIN_VALUE` digit arrays.

### Approach 2: Mathematical Pre-Overflow Check (`Solution_2.java`)
- Before executing `result = result * 10 + lastDigit`, check if `result > Integer.MAX_VALUE / 10` or `result < Integer.MIN_VALUE / 10`.
- **Complexity:**
  - Time Complexity: $\mathcal{O}(\log_{10} |x|) \le \mathcal{O}(1)$ (at most 10 digits).
  - Space Complexity: $\mathcal{O}(1)$.

---

## 🧪 Run & Test
Execute `Main.java` or `Main_2.java` to test.
