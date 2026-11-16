package com.github.mohamad4444.problem10;

import java.util.HashMap;
import java.util.Map;

class Solution {
    private Map<String, Boolean> memo = new HashMap<>();

    public boolean isMatch(String s, String p) {
        String key = s + "|" + p;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (s.isEmpty() && p.isEmpty()) {
            memo.put(key, true);
            return true;
        }

        if (p.isEmpty()) {
            memo.put(key, false);
            return false;
        }

        if (p.length() >= 2 && p.charAt(1) == '*') {
            char t = p.charAt(0);

            if (s.isEmpty()) {
                memo.put(key, isMatch(s, p.substring(2)));
            } else if (s.charAt(0) == t || t == '.') {
                memo.put(key, isMatch(s, p.substring(2))
                        || isMatch(s.substring(1), p));
            } else {
                memo.put(key, isMatch(s, p.substring(2)));
            }
        } else {
            if (!s.isEmpty() &&
                    (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
                memo.put(key, isMatch(s.substring(1), p.substring(1)));
            } else {
                memo.put(key, false);
            }
        }

        return memo.get(key);
    }
}