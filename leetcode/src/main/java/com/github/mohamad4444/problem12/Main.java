package com.github.mohamad4444.problem12;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Pseudo parameters for testing (manually update these test values)
        int num = 1994;

        System.out.println("==================================================");
        System.out.println("Running Problem 12: intToRoman");
        System.out.println("==================================================");
        System.out.println("Input: num = " + num);

        var result = solution.intToRoman(num);
        System.out.println("Output: " + result);
    }
}
