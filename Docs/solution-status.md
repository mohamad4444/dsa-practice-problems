# Solution Status

Last reviewed: 2026-11-12. “Implemented” means the source contains a complete-looking
algorithm; it does **not** mean every edge case has been regression-tested. The repository
does not yet have a unified test suite for the legacy standalone files.

All abbreviated paths below start at
`leetcode/src/main/java/com/github/mohamad4444/`.

## Needs completion

| Location | Problem | Evidence |
| --- | --- | --- |
| `.../problem14/Solution_2.java` | #14 Longest Common Prefix, variant 2 | Builds, but returns an unmodified empty result. |
| `.../problem67/Solution.java` | #67 Add Binary | Contains an empty loop and a placeholder return value. |
| `.../problem10/Solution.java` | #10 Regular Expression Matching | Skeleton always returns `false`. |
| `.../problem89/Solution.java` | #89 Gray Code | Contains the incomplete expression `positions[i%]`; it does not compile. |
| `.../problem1520/Solution.java` | #1520 Maximum Number of Non-Overlapping Substrings | Method is a `return null` placeholder. |

## Implemented — importer-managed LeetCode packages

| Problems | Notes |
| --- | --- |
| #1, #3–#5, #7–#9, #11, #13–#14, #20–#21, #26, #28, #35, #58, #66, #69–#72, #84, #133, #168, #2192 | Every completed attempt now lives in an importer-style `problemNN/` package. |
| #3, #5, #7–#8, #14, #168, #2192 | Have alternative implementations. |

`make check` compiles this collection and intentionally reports #89 until it is completed.

## Other practice platforms

The standalone exercises under `AfterAcademy/`, `CodeWars/`, `GeekForGeeks/`, and
`Other Sites/` remain outside the LeetCode module because they are not LeetCode problems.

## Needs review before relying on the result

These files are syntactically complete but source inspection found a correctness concern.

| Location | Concern |
| --- | --- |
| `.../problem03/Solution_2.java` | The longest-substring calculation never updates the maximum for an all-distinct input. |
| `.../problem2192/Solution_2.java` | Uses a fixed `new boolean[8]`, so it cannot support arbitrary `n`. |
| `.../problem72/Solution.java` | The dynamic-programming table has no row/column for empty strings. |

## Suggested next work

1. Finish the five incomplete entries above, starting with #89 so `make check` is green.
2. Correct and test the three review entries.
3. Add focused example and boundary tests for each completed exercise.
4. Move validated legacy solutions into the packaged module only when desired; keep the
   originals as historical attempts.
