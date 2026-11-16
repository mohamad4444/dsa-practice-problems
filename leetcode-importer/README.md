# LeetCode Solution Importer

Helper to instantly import LeetCode problems directly from your clipboard (`xclip`), organize packages, create `Solution.java` (or `Solution_2.java`), generate a runnable `Main.java` test harness, and fetch problem descriptions into a local `README.md`.

---

## ⚡ Quick Commands

### 1. Primary Solution (e.g. Problem 168)
1. Copy the `Solution` class on LeetCode (`Ctrl+C`).
2. Run from the repository root:
```bash
make leetcode 168
```
*(The importer directory still supports `make problem 168`, `make 168`, or `./import 168`.)*

Creates:
- `Solution.java` (`public class Solution`)
- `Main.java` (`public class Main` with test harness)
- `README.md` (Local problem description, difficulty, tags, and approach notes)

---

### 2. Second / Variant Solution (e.g. Problem 168-2)
1. Copy the second approach on LeetCode (`Ctrl+C`).
2. Run from the repository root:
```bash
make leetcode 168-2
```
*(The importer directory still supports `make problem 168-2`, `make 168-2`, or `./import 168-2`.)*

Creates:
- `Solution_2.java` (`public class Solution_2`)
- `Main_2.java` (`public class Main_2`)

Track each problem’s solution count and studied checkbox in the
[learning checklist](../Docs/learning-progress.md). Update the table after importing.

---

## 📁 Standard Problem Package Structure
Each problem package follows a consistent, modular structure:
```
problemXXX/
├── Solution.java       # Primary solution method
├── Main.java           # Test runner with test cases
├── Solution_2.java     # Optional: Alternative/secondary approach
├── Main_2.java         # Optional: Runner for alternative approach
└── README.md           # Problem description, constraints, and approach notes
```

---

## 🌟 Key Features

1. **Auto-Fetch Problem Metadata & Descriptions:**
   - Queries LeetCode's public GraphQL API to fetch the problem title, difficulty, tags, and problem description.
   - Automatically converts the HTML description to clean Markdown in `README.md`.
2. **Safe Compilation (Default Return Fallback):**
   - If an empty LeetCode template method is copied (without a return statement), the importer automatically inserts an appropriate default return (e.g., `return null;` for objects, `return 0;` for numbers, `return false;` for booleans) so the code compiles immediately without Java compiler errors.
3. **📋 Clipboard Validation (`xclip`):**
   - Validates that `class Solution` is in your clipboard before attempting import.
4. **🏃 Running and Testing in VS Code:**
   - Open `Main.java` (or `Main_2.java`).
   - Click **"Run"** or **"Debug"** directly above `public static void main`.
