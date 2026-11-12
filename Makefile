# Java Practice Problems
#
# Import a copied LeetCode `class Solution` from the repository root:
#   make leetcode 168
#   make leetcode 168-2
#   make leetcode PROBLEM=168 OVERWRITE=1

.DEFAULT_GOAL := help

.PHONY: help leetcode problem check status

IMPORTER := python3 leetcode-importer/import_problem.py

# Keep positional commands convenient: `make leetcode 168` and `make problem 168`.
ifeq ($(filter leetcode problem,$(firstword $(MAKECMDGOALS))),$(firstword $(MAKECMDGOALS)))
  PROBLEM_ARGS := $(wordlist 2,$(words $(MAKECMDGOALS)),$(MAKECMDGOALS))
  $(eval $(PROBLEM_ARGS):;@:)
endif

IMPORT_TARGET := $(if $(PROBLEM),$(PROBLEM),$(firstword $(PROBLEM_ARGS)))
IMPORT_FLAGS := $(if $(OVERWRITE),--overwrite,)

help:
	@printf '%s\n' \
	  'Usage:' \
	  '  make leetcode 168                 Import the clipboard solution for problem 168.' \
	  '  make leetcode 168-2               Import a second implementation.' \
	  '  make leetcode PROBLEM=168 OVERWRITE=1' \
	  '  make check                         Compile importer-managed solutions.' \
	  '  make status                        Open the solution status guide.'

leetcode problem:
	@if [ -z "$(IMPORT_TARGET)" ]; then \
		printf '%s\n' 'Provide a problem number, e.g. make leetcode 168' >&2; exit 2; \
	fi
	@$(IMPORTER) $(IMPORT_TARGET) $(IMPORT_FLAGS)

# Backward-compatible shorthand: `make 168`.
%:
	@$(IMPORTER) $@ $(IMPORT_FLAGS)

check:
	@mkdir -p .build/classes
	@find leetcode/src/main/java -name '*.java' -print0 | \
		xargs -0 javac -d .build/classes

status:
	@sed -n '1,220p' Docs/solution-status.md
