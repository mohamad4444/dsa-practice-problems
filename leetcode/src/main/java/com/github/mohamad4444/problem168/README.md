# 168. Excel Sheet Column Title

- **Difficulty:** Easy
- **LeetCode URL:** [LeetCode #168](https://leetcode.com/problems/excel-sheet-column-title/)
- **Tags:** Math, String

---

## 📝 Problem Description

Given an integer `columnNumber`, return its corresponding column title as it appears in an Excel sheet.

For example:
```
A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28 
...
```

### Example 1:
```
Input: columnNumber = 1
Output: "A"
```

### Example 2:
```
Input: columnNumber = 28
Output: "AB"
```

### Example 3:
```
Input: columnNumber = 701
Output: "ZY"
```

### Constraints:
- `1 <= columnNumber <= 2^31 - 1`

---

## 💡 Approach & Intuition

- This is a base-26 numbering system, but 1-indexed (`A=1, ..., Z=26`) rather than 0-indexed (`0-25`).
- In each iteration:
  - Subtract 1 from `columnNumber` (`columnNumber--`) to convert from 1-indexed to 0-indexed.
  - Determine the current character: `(char) ('A' + columnNumber % 26)`.
  - Append to result.
  - Divide: `columnNumber /= 26`.
- **Complexity:**
  - Time Complexity: $\mathcal{O}(\log_{26} N)$
  - Space Complexity: $\mathcal{O}(1)$

---

## 🧪 Run & Test
Run `Main.java` to test column conversions.
