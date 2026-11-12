#!/usr/bin/env bash
# LeetCode Problem Importer Wrapper (from leetcode/ directory)

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
python3 "${SCRIPT_DIR}/../leetcode-importer/import_problem.py" "$@"
