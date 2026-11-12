package com.github.mohamad4444.problem08;

public class Solution_2 {
    public int myAtoi(String s) {
        int i = 0;
        int n = s.length();
        int sign = 1;
        int res = 0;

        // 1. Skip leading spaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        if (i >= n) return 0;

        // 2. Handle sign
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // 3. Process digits
        while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            int digit = s.charAt(i) - '0';

            // Check for overflow BEFORE multiplying by 10
            // Integer.MAX_VALUE is 2147483647 (ends in 7)
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && digit > 7)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            res = res * 10 + digit;
            i++;
        }

        return res * sign;
    }
}