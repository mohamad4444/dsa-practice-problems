package com.github.mohamad4444.problem44;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Pseudo parameters for testing (manually update these test values)
        String s = "helloo";
        String p = "hell?o";

        System.out.println("==================================================");
        System.out.println("Running Problem 44: isMatch");
        System.out.println("==================================================");
        System.out.println("Input: s = " + s);
        System.out.println("Input: p = " + p);

        var result = solution.isMatch(s, p);
        System.out.println("Output: " + result);
        System.out.println(solution.reducePattern("aa****c"));
    }
}
