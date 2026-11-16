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

Track solution counts and mark problems studied in the
[learning checklist](../Docs/learning-progress.md). Edit its table directly.

Earlier standalone LeetCode files have been migrated into this structure. Original
implementations remain intact inside their `Solution` classes or variants; only their
package and file organization changed.

## Debugging in VS Code

Open the problem's `Main.java` (or `Main_2.java` for an alternative), select
**Debug current Main (pause at entry)** in Run and Debug, and press **F5**.
The debugger pauses at the start of `main`; use **F10** to step over or **F11**
to step into the solution. Set breakpoints in `Solution.java` before continuing
with **F5**. The **Debug** link above `main` also uses these breakpoints.

Debug output appears in the **Debug Console**. This avoids a terminal launch
timeout seen in this workspace. **Code Runner / Run Code** runs the program to
completion without a debugger, so it does not stop at breakpoints.
