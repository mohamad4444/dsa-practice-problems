package com.github.mohamad4444.problem10;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println("==================================================");
        System.out.println("Running Problem 10: isMatch");
        System.out.println("==================================================");

        String s = "aa";
        String p = "a*";
        System.out.println("Input: s = \"" + s + "\", p = \"" + p + "\"");
        System.out.println("Output: " + solution.isMatch(s, p));
    }
}
