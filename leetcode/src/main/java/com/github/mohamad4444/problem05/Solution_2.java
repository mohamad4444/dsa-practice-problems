package com.github.mohamad4444.problem05;

public class Solution_2 {

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            // Check odd-length palindromes (centered at i)
            int len1 = expandAroundCenter(s, i, i);
            // Check even-length palindromes (centered between i and i + 1)
            int len2 = expandAroundCenter(s, i, i + 1);

            int maxLen = Math.max(len1, len2);

            // If a longer palindrome is found, update boundary indices
            if (maxLen > end - start + 1) {
                start = i - (maxLen - 1) / 2;
                end = i + maxLen / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Length of palindrome found: (right - 1) - (left + 1) + 1 = right - left - 1
        return right - left - 1;
    }
}
