# Problem-Solving Workflow

## 1. Understand the contract

Write down the input domain, required output, mutation rules, and edge cases. Turn the
examples into small tests before writing the full implementation.

## 2. Choose a pattern

| Signal in the prompt | First pattern to consider |
| --- | --- |
| Sorted input or answer space | Binary search / two pointers |
| Pair, frequency, duplicate | Hash map / hash set |
| Contiguous range | Sliding window / prefix sum |
| “Best” answer built from smaller answers | Dynamic programming |
| Reachability or dependencies | DFS, BFS, topological sort |
| Intervals that overlap | Sort + merge / sweep line |

## 3. State the invariant

An invariant is what remains true during each loop or recursion. For example, in binary
search, the answer is always contained in the current search interval. Naming it makes
off-by-one errors much easier to catch.

## 4. Prove and measure

Briefly explain why the invariant produces the answer, then record time and space
complexity. Prefer a clear correct solution before optimizing it.

## 5. Verify boundaries

At minimum, test empty or smallest input, one-element input, duplicates, extremes, and
the example cases. Add a regression test whenever a bug is found.

## Note template

```md
# Topic / pattern

## When to use it

## Core invariant

## Java template

## Complexity

## Pitfalls

## Related problems
```
