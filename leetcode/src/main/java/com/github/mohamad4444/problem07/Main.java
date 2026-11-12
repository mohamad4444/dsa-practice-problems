package com.github.mohamad4444.problem07;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println("==================================================");
        System.out.println("Running Problem 06: reverse");
        System.out.println("==================================================");

        int[] testCases = {123, -123, 120, 0, 563847412, 1534236469};
        for (int x : testCases) {
            System.out.println("Input:  x = " + x);
            System.out.println("Output: " + solution.reverse(x));
            System.out.println("--------------------------------------------------");
        }
    }
}
