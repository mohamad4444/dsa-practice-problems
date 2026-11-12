package com.github.mohamad4444.problem70;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println("==================================================");
        System.out.println("Running Problem 70: climbStairs");
        System.out.println("==================================================");

        // Test case 1: Base case 1
        System.out.println("Input: n = 1  | Expected: 1          | Output: " + solution.climbStairs(1));

        // Test case 2: Base case 2
        System.out.println("Input: n = 2  | Expected: 2          | Output: " + solution.climbStairs(2));

        // Test case 3: Small number
        System.out.println("Input: n = 3  | Expected: 3          | Output: " + solution.climbStairs(3));

        // Test case 4: Typical mid-range number
        System.out.println("Input: n = 5  | Expected: 8          | Output: " + solution.climbStairs(5));

        // Test case 5: Slightly larger number
        System.out.println("Input: n = 10 | Expected: 89         | Output: " + solution.climbStairs(10));

        // Test case 6: Upper bound for standard constraints
        System.out.println("Input: n = 45 | Expected: 1836311903 | Output: " + solution.climbStairs(45));
    }
}
