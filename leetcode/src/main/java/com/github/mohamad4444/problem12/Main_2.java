package com.github.mohamad4444.problem12;

import java.util.*;

public class Main_2 {
    public static void main(String[] args) {
        Solution_2 solution = new Solution_2();

        // Pseudo parameters for testing (manually update these test values)
        int num = 5;

        System.out.println("==================================================");
        System.out.println("Running Problem 12 (Solution 2): intToRoman");
        System.out.println("==================================================");
        System.out.println("Input: num = " + num);

        var result = solution.intToRoman(num);
        System.out.println("Output: " + result);
    }
}
