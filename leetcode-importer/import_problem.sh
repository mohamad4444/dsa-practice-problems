#!/usr/bin/env bash
# LeetCode Problem Importer Wrapper

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
python3 "${SCRIPT_DIR}/import_problem.py" "$@"
