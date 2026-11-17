package com.github.mohamad4444.problem17;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Pseudo parameters for testing (manually update these test values)
        String digits = "hello";

        System.out.println("==================================================");
        System.out.println("Running Problem 17: letterCombinations");
        System.out.println("==================================================");
        System.out.println("Input: digits = " + digits);

        var result = solution.letterCombinations(digits);
        System.out.println("Output: " + result);
    }
}
