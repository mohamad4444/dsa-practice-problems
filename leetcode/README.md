# LeetCode Solutions

This Maven module is the canonical home for every LeetCode problem in the repository.
Each problem lives in a package named `problemNN`:

```text
src/main/java/com/github/mohamad4444/problemNN/
├── Solution.java       # primary implementation
├── Main.java           # local runner
├── Solution_2.java     # optional alternative implementation
├── Main_2.java         # optional runner for that alternative
└── README.md           # problem-specific notes
```

Use the repository-root importer to add a problem:

```bash
make leetcode 168
make leetcode 168-2
```

Earlier standalone LeetCode files have been migrated into this structure. Original
implementations remain intact inside their `Solution` classes or variants; only their
package and file organization changed.
