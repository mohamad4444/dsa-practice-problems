package com.github.mohamad4444.problem07;

public class Main_2 {
    public static void main(String[] args) {
        Solution_2 solution = new Solution_2();

        System.out.println("==================================================");
        System.out.println("Running Problem 06 (Solution 2): reverse");
        System.out.println("==================================================");

        int[] testCases = {123, -123, 120, 0, 2003847419, 1534236469};
        for (int x : testCases) {
            System.out.println("Input:  x = " + x);
            System.out.println("Output: " + solution.reverse(x));
            System.out.println("--------------------------------------------------");
        }
    }
}
