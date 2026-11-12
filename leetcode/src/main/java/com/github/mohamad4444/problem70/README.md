# 70. Climbing Stairs

- **Difficulty:** Easy
- **LeetCode URL:** [LeetCode #70](https://leetcode.com/problems/climbing-stairs/)
- **Tags:** Math, Dynamic Programming, Memoization

---

## 📝 Problem Description

You are climbing a staircase. It takes `n` steps to reach the top.

Each time you can either climb `1` or `2` steps. In how many distinct ways can you climb to the top?

### Example 1:
```
Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
```

### Example 2:
```
Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
```

### Constraints:
- `1 <= n <= 45`

---

## 💡 Approach & Intuition

- **Recurrence Relation:** `ways(n) = ways(n - 1) + ways(n - 2)`.
- Base cases: `ways(1) = 1`, `ways(2) = 2`.
- Using memoization (HashMap) or bottom-up DP reduces time complexity from $\mathcal{O}(2^N)$ to $\mathcal{O}(N)$.
- **Complexity:**
  - Time Complexity: $\mathcal{O}(N)$
  - Space Complexity: $\mathcal{O}(N)$ (or $\mathcal{O}(1)$ with iterative variables)

---

## 🧪 Run & Test
Run `Main.java` to test.
