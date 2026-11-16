# Java Practice Problems

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

A Java workspace for data structures, algorithms, and interview-style exercises from
LeetCode, GeeksForGeeks, CodeWars, AfterAcademy, and other sites. It retains the original
standalone attempts while providing a Maven-backed home for newly imported LeetCode work.

## Repository map

| Location | Purpose |
| --- | --- |
| `AfterAcademy/`, `CodeWars/`, `GeekForGeeks/`, `Other Sites/` | Standalone exercises from other platforms. |
| `leetcode-importer/` | Clipboard importer and its implementation notes. |
| `leetcode/` | The canonical home for **all** LeetCode problems: one `problemNN/` package per problem, with a solution, runner, and README. |
| `Docs/` | Reusable DSA notes, solving workflow, and the solution inventory. |

## LeetCode importer

Copy a LeetCode Java `class Solution` to your clipboard, then run from this repository’s
top directory:

```bash
make leetcode 168
make leetcode 168-2       # alternative implementation
make leetcode PROBLEM=168 OVERWRITE=1
```

The importer reads the clipboard through `xclip`, `wl-paste`, or Tkinter; fetches public
LeetCode metadata when available; and creates the package, solution, runner, and
problem README under `leetcode/src/main/java/com/github/mohamad4444/`.
See [the importer guide](leetcode-importer/README.md) for details.

## Build and status

Use the [learning checklist](Docs/learning-progress.md) to track each problem’s
number of solutions and whether you have studied it. Edit the table directly.

JDK 25 is configured for the Maven module. Compile its production sources with:

```bash
make check
```

Compilation succeeds, but some implementations are still unfinished placeholders.
The inventory, including started-but-not-finished problems, is in
[Docs/solution-status.md](Docs/solution-status.md).

## Learning notes

Use [Docs](Docs/README.md) for pattern explanations, Java templates, complexity notes,
and solution write-ups that apply across multiple problems. The initial
[problem-solving workflow](Docs/problem-solving.md) provides the preferred note format.

## AI disclosure

The original standalone practice solutions were written by hand in 2024 and 2025. Their
documentation was reformatted with AI assistance on August 23, 2026. The importer,
generated runners, and repository documentation may include AI-assisted work.

## Resources

- [Best websites a programmer should visit](https://github.com/sdmg15/Best-websites-a-programmer-should-visit)
