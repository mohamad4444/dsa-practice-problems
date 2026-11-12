package com.github.mohamad4444.problem10;

public class Solution {
    public boolean isMatch(String s, String p) {
        if (p.length() < 3) {
            return false;
        }
        int matched = 0;
        for (int i = 0; i < p.length(); i++) {
            //pattern 1 (normal character)
            if (p.charAt(i) != '.' && p.charAt(i) != '*' && i + 1 < p.length() && p.charAt(i + 1) != '*') {
                if (i < s.length() && s.charAt(i) == p.charAt(i)) {
                    
                }
            }    
        }
        return false;
    }
}
