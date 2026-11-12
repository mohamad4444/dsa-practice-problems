package com.github.mohamad4444.problem05;

public class Solution {

    public boolean checkIfPalindrom(int start, int end, String s) {
        if (end - start < 1) {
            return false;
        }
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public record Range(int start, int end, int maxLength) {
    }

    public String longestPalindrome(String s) {
        Range range = new Range(0, 0, 0);
        for (int i = 0; i < s.length(); i++) {
            int start = i;
            int end = s.length() - 1;
            while (end > start && end - start > range.maxLength) {
                if (checkIfPalindrom(start, end, s)) {
                    if (end - start > range.maxLength) {
                        range = new Range(start, end, end - start);
                    }
                }
                end--;
            }
        }
        return s.substring(range.start, range.end + 1);
    }
}
