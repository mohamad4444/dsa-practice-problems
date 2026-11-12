package com.github.mohamad4444.problem168;

import java.util.*;

public class Main_2 {
    public static void main(String[] args) {
        Solution_2 solution = new Solution_2();

        // Pseudo parameters for testing (manually update these test values)
        int columnNumber = 28;

        System.out.println("==================================================");
        System.out.println("Running Problem 168 (Solution 2): convertToTitle");
        System.out.println("==================================================");
        System.out.println("Input: columnNumber = " + columnNumber);

        var result = solution.convertToTitle(columnNumber);
        System.out.println("Output: " + result);
    }
}
