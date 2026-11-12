package com.github.mohamad4444.problem05;

public class Main_2 {
    public static void main(String[] args) {
        Solution_2 solution = new Solution_2();

        System.out.println("==================================================");
        System.out.println("Running Problem 05 (Solution 2): longestPalindrome");
        System.out.println("==================================================");

        // Test 1: Single character (Base case)
        System.out.println("Test 1 (Expected: a) -> Actual: " + solution.longestPalindrome("a"));

        // Test 2: Two identical characters (Even length minimal)
        System.out.println("Test 2 (Expected: bb) -> Actual: " + solution.longestPalindrome("bb"));

        // Test 3: Two distinct characters (Any single character is valid)
        System.out.println("Test 3 (Expected: a or c) -> Actual: " + solution.longestPalindrome("ac"));

        // Test 4: Classic odd-length palindrome
        System.out.println("Test 4 (Expected: bab or aba) -> Actual: " + solution.longestPalindrome("babad"));

        // Test 5: Classic even-length palindrome
        System.out.println("Test 5 (Expected: bb) -> Actual: " + solution.longestPalindrome("cbbd"));

        // Test 6: Entire string is a palindrome
        System.out.println("Test 6 (Expected: racecar) -> Actual: " + solution.longestPalindrome("racecar"));

        // Test 7: All repeating identical characters
        System.out.println("Test 7 (Expected: aaaaa) -> Actual: " + solution.longestPalindrome("aaaaa"));

        // Test 8: No multi-character palindrome (Strictly unique characters)
        System.out.println("Test 8 (Expected: a) -> Actual: " + solution.longestPalindrome("abcdef"));

        // Test 9: Palindrome located at the very end
        System.out.println("Test 9 (Expected: aba) -> Actual: " + solution.longestPalindrome("abacdfgdcaba"));

        // Test 10: User prompt sample
        System.out.println("Test 10 (Expected: abbba) -> Actual: " + solution.longestPalindrome("abbbaafeertda"));
    }
}
