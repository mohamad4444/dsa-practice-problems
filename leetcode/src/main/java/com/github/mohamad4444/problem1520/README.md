# 1520. Maximum Number of Non-Overlapping Substrings

- **Difficulty:** Hard
- **LeetCode URL:** [LeetCode #1520](https://leetcode.com/problems/maximum-number-of-non-overlapping-substrings/)
- **Tags:** String, Greedy, Sorting

---

## 📝 Problem Description

Given a string `s` of lowercase letters, you need to find the maximum number of non-overlapping substrings of `s` that meet the following conditions:

1. The substrings do not overlap, that is you cannot have two substrings where their start and end index overlap.
2. If a substring contains a character `c`, then all occurrences of `c` in `s` must be within this substring.
3. If there are multiple solutions with the maximum number of substrings, return the one with the minimum total length.

Return the substrings as a list of strings in any order.

### Example 1:
```
Input: s = "adefaddaccc"
Output: ["e","f","ccc"]
Explanation: 
The possible valid substrings are "adefaddaccc", "adefadda", "ef", "e", "f", and "ccc".
Choosing ["e", "f", "ccc"] yields 3 substrings (maximum possible) with total length 5.
```

### Example 2:
```
Input: s = "abbaccd"
Output: ["d","bb","cc"]
Explanation:
Notice that while the entire string "abbaccd" contains all occurrences, ["d","bb","cc"] gives 3 substrings with smaller total length.
```

### Constraints:
- `1 <= s.length <= 10^5`
- `s` contains only lowercase English letters.

---

## 💡 Approach & Intuition

### Key Insights:
1. **Starting Point Property:** Every valid substring must start at the *first* occurrence of some character. Since there are only 26 lowercase English letters, there are at most 26 candidate intervals.
2. **Interval Expansion:** For each character `c` present in `s`, start with `[left, right] = [first[c], last[c]]`. Expand `right` to include the last occurrences of all characters encountered in `[left, right]`. If any character encountered has `first < left`, no valid substring can start at `left`, so discard the candidate.
3. **Laminar Family (No Partial Overlap):** Valid candidate intervals either do not overlap at all or one is completely nested inside the other.
4. **Greedy Scheduling:** Sort valid intervals by their end index ascending (tie-breaker: start index descending for shorter length). Greedily select non-overlapping intervals.

### Complexity:
- **Time Complexity:** $\mathcal{O}(26 \cdot N) = \mathcal{O}(N)$ where $N = \text{s.length()}$.
- **Space Complexity:** $\mathcal{O}(1)$ auxiliary space (at most 26 intervals).

---

## 🧪 Run & Test
Run `Main.java` to test against multiple test cases.
