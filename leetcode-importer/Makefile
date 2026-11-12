# LeetCode Problem Importer Makefile

.PHONY: problem

# Support: make problem 168 or make problem 168-2
ifeq (problem,$(firstword $(MAKECMDGOALS)))
  PROBLEM_ARGS := $(wordlist 2,$(words $(MAKECMDGOALS)),$(MAKECMDGOALS))
  $(eval $(PROBLEM_ARGS):;@:)
endif

problem:
	@python3 import_problem.py $(PROBLEM_ARGS)

# Also support running directly: make 168 or make 168-2
%:
	@python3 import_problem.py $@
