package com.github.mohamad4444.problem08;

public class Solution {
    public int myAtoi(String s) {
        int res = 0;
        int start = 0;
        boolean neg = false;
        int digits = 0;
        while (start < s.length() && s.charAt(start) == ' ') {
            start++;
        }
        if (start >= s.length()) {
            return 0;
        }
        char current = s.charAt(start);
        if (current == '-') {
            neg = true;
            start++;
        } else if (current == '+') {
            start++;
        }
        for (int i = start; i < s.length(); i++) {
            current = s.charAt(i);
            if (current >= 48 && current <= 57) {
                res = neg ? res - (current - 48) : res + (current - 48);
                if (i + 1 < s.length() && s.charAt(i + 1) >= 48 && s.charAt(i + 1) <= 57) {
                    int next = s.charAt(i + 1) - 48;
                    if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && next > 7)) {
                        return Integer.MAX_VALUE;
                    } else if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && next > 8)) {
                        return Integer.MIN_VALUE;
                    }
                    res = res * 10;
                }
            } else {
                break;
            }
        }
        return res;
    }
}