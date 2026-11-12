package com.github.mohamad4444.problem89;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Pseudo parameters for testing (manually update these test values)
        int n = 5;

        System.out.println("==================================================");
        System.out.println("Running Problem 89: grayCode");
        System.out.println("==================================================");
        System.out.println("Input: n = " + n);

        var result = solution.grayCode(n);
        System.out.println("Output: " + result);
    }
}
