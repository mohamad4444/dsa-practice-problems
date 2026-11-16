package com.github.mohamad4444.problem44;

public class Main_2 {
    public static void main(String[] args) {
        Solution_2 solution = new Solution_2();
        check(solution, "", "", true);
        check(solution, "", "***", true);
        check(solution, "a", "", false);
        check(solution, "", "?", false);
        check(solution, "aa", "a", false);
        check(solution, "aa", "*", true);
        check(solution, "cb", "?a", false);
        check(solution, "adceb", "*a*b", true);
        check(solution, "acdcb", "a*c?b", false);
        check(solution, "abc", "a***?c", true);
        check(solution, "abc", "???*", true);
        check(solution, "abc", "*d", false);
        System.out.println("All 12 wildcard matching checks passed.");
    }

    private static void check(Solution_2 solution, String s, String p, boolean expected) {
        boolean actual = solution.isMatch(s, p);
        if (actual != expected) {
            throw new AssertionError("s=\"" + s + "\", p=\"" + p
                    + "\": expected " + expected + ", got " + actual);
        }
    }
}
