package com.github.mohamad4444.problem1520;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] testCases = {
            "adefaddaccc", // Expected: ["e", "f", "ccc"]
            "abbaccd",     // Expected: ["bb", "cc", "d"] (or any order)
            "hello"
        };

        System.out.println("==================================================");
        System.out.println("Running Problem 1520: maxNumOfSubstrings");
        System.out.println("==================================================");

        for (String s : testCases) {
            System.out.println("Input:  s = \"" + s + "\"");
            var result = solution.maxNumOfSubstrings(s);
            System.out.println("Output: " + result);
            System.out.println("--------------------------------------------------");
        }
    }
}
