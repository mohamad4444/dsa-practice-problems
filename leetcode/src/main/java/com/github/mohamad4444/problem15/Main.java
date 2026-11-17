package com.github.mohamad4444.problem15;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println("==================================================");
        System.out.println("Running Problem 15: threeSum");
        System.out.println("==================================================");

        // Triplet order and the order of values within each triplet do not matter.
        runExample(solution, "Multiple triplets in an unsorted array",
                new int[]{-1, 0, 1, 2, -1, -4}, "[[-1, -1, 2], [-1, 0, 1]]");
        runExample(solution, "Three values with no solution",
                new int[]{0, 1, 1}, "[]");
        runExample(solution, "Exactly three zeros",
                new int[]{0, 0, 0}, "[[0, 0, 0]]");
        runExample(solution, "Extra zeros must not create duplicate triplets",
                new int[]{0, 0, 0, 0, 0}, "[[0, 0, 0]]");
        runExample(solution, "Repeated values must produce only one triplet",
                new int[]{-2, 0, 0, 2, 2}, "[[-2, 0, 2]]");
        runExample(solution, "All positive values",
                new int[]{1, 2, 3, 4, 5}, "[]");
        runExample(solution, "All negative values",
                new int[]{-5, -4, -3, -2, -1}, "[]");
        runExample(solution, "Mixed signs with no zero-sum triplet",
                new int[]{-5, -2, 1, 3}, "[]");
        runExample(solution, "A valid triplet without a zero",
                new int[]{3, -1, -2}, "[[-2, -1, 3]]");
        runExample(solution, "Several distinct triplets with shared values",
                new int[]{-2, -1, 0, 1, 2, 3},
                "[[-2, -1, 3], [-2, 0, 2], [-1, 0, 1]]");
        runExample(solution, "Values at the allowed limits",
                new int[]{-100000, 100000, 0, -100000, 100000},
                "[[-100000, 0, 100000]]");
    }

    private static void runExample(Solution solution, String description, int[] nums, String expected) {
        System.out.println();
        System.out.println(description);
        System.out.println("Input: nums = " + Arrays.toString(nums));
        System.out.println("Expected: " + expected);

        try {
            System.out.println("Output: " + solution.threeSum(nums));
        } catch (RuntimeException exception) {
            // Keep the remaining examples runnable while the solution is in progress.
            System.out.println("Error: " + exception);
        }
    }
}
