package com.github.mohamad4444.problem168;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Pseudo parameters for testing (manually update these test values)
        int columnNumber = 701;

        System.out.println("==================================================");
        System.out.println("Running Problem 168: convertToTitle");
        System.out.println("==================================================");
        System.out.println("Input: columnNumber = " + columnNumber);

        var result = solution.convertToTitle(columnNumber);
        System.out.println("Output: " + result);
    }
}
