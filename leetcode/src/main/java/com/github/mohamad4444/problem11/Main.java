package com.github.mohamad4444.problem11;

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println("==================================================");
        System.out.println("Running Problem 11: maxArea");
        System.out.println("==================================================");

        // Test Case 1: The standard LeetCode example
        int[] test1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Test 1 (Expected: 49) -> Actual: " + sol.maxArea(test1));

        // Test Case 2: Minimum possible array length
        int[] test2 = {1, 1};
        System.out.println("Test 2 (Expected: 1)  -> Actual: " + sol.maxArea(test2));

        // Test Case 3: Tall lines on the outside, short on the inside
        int[] test3 = {4, 3, 2, 1, 4};
        System.out.println("Test 3 (Expected: 16) -> Actual: " + sol.maxArea(test3));

        // Test Case 4: Zero height
        int[] test4 = {0, 0};
        System.out.println("Test 4 (Expected: 0)  -> Actual: " + sol.maxArea(test4));
    }
}
