package com.github.mohamad4444.problem16;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println("==================================================");
        System.out.println("Running Problem 16: threeSumClosest");
        System.out.println("==================================================");


        runExample(solution, "Mixed values with a distant target", new int[]{-20, -19, 41, 1}, 6, 2);
        runExample(solution, "LeetCode example 1", new int[]{-1, 2, 1, -4}, 1, 2);
        runExample(solution, "LeetCode example 2", new int[]{0, 0, 0}, 1, 0);
        runExample(solution, "An exact target sum", new int[]{-1, 0, 1, 2, -1, -4}, 0, 0);
        runExample(solution, "Target below every possible sum", new int[]{0, 1, 1, 1}, -100, 2);
        runExample(solution, "Target above every possible sum", new int[]{1, 2, 4, 8, 16}, 100, 28);
        runExample(solution, "Negative values with an exact match", new int[]{-7, -5, -3, -1}, -11, -11);
    }

    private static void runExample(Solution solution, String description, int[] nums, int target, int expected) {
        System.out.println();
        System.out.println(description);
        System.out.println("Input: nums = " + Arrays.toString(nums) + ", target = " + target);
        System.out.println("Expected: " + expected);

        // The solution sorts its input, so use a copy to keep the printed example unchanged.
        int actual = solution.threeSumClosest(Arrays.copyOf(nums, nums.length), target);
        System.out.println("Actual: " + actual);
    }
}
