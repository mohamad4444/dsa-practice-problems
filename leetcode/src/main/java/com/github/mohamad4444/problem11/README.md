# 11. Container With Most Water

- **Difficulty:** Medium
- **LeetCode URL:** [LeetCode #11](https://leetcode.com/problems/container-with-most-water/)
- **Tags:** Array, Two Pointers, Greedy

---

## 📝 Problem Description

You are given an integer array `height` of length `n`. There are `n` vertical lines drawn such that the two endpoints of the `i`th line are `(i, 0)` and `(i, height[i])`.

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return *the maximum amount of water a container can store*.

**Notice that you may not slant the container.**

### Example 1:
```
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water the container can contain is 49.
```

### Example 2:
```
Input: height = [1,1]
Output: 1
```

### Constraints:
- `n == height.length`
- `2 <= n <= 10^5`
- `0 <= height[i] <= 10^4`

---

## 💡 Approach & Intuition

### Approach: Two Pointers (Optimal)
- Start with two pointers at both ends: `left = 0`, `right = n - 1`.
- The area is `min(height[left], height[right]) * (right - left)`.
- To find a potentially larger area, always advance the pointer with the smaller height.
- **Complexity:**
  - Time Complexity: $\mathcal{O}(N)$
  - Space Complexity: $\mathcal{O}(1)$

---

## 🧪 Run & Test
Run `Main.java` or run unit tests via JUnit in `src/test/java/com/github/mohamad4444/problem11/SolutionTest.java`.
