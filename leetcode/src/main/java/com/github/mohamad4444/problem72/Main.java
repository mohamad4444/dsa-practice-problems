package com.github.mohamad4444.problem72;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Pseudo parameters for testing (manually update these test values)
        String word1 = "horse";
        String word2 = "ros";

        System.out.println("==================================================");
        System.out.println("Running Problem 72: minDistance");
        System.out.println("==================================================");
        System.out.println("Input: word1 = " + word1);
        System.out.println("Input: word2 = " + word2);

        var result = solution.minDistance(word1, word2);
        System.out.println("Output: " + result);
    }
}
