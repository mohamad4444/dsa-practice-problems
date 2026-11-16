package com.github.mohamad4444.problem44;


public class Solution {

    public boolean isMatchHelper(String s, String p, int l, int m, Boolean[][] mem) {
        // already checked
        if (mem[l][m] != null) {
            return mem[l][m];
        }

        // if both empty return true
        if (l == s.length() && m == p.length()) {
            mem[l][m] = true;
            return true;
        }

        // case p="" and s not empty
        if (m == p.length()) {
            mem[l][m] = false;
            return false;
        }

        // case s="" and p not empty
        if (l == s.length()) {
            if (p.charAt(m) == '*') {
                mem[l][m] = isMatchHelper(s, p, l, m + 1, mem);
            } else {
                mem[l][m] = false;
            }
        }
        else if (p.charAt(m) == '*') {
            mem[l][m] =
                isMatchHelper(s, p, l + 1, m, mem)
                || isMatchHelper(s, p, l, m + 1, mem);
        }
        else if (p.charAt(m) == '?' || s.charAt(l) == p.charAt(m)) {
            mem[l][m] = isMatchHelper(s, p, l + 1, m + 1, mem);
        }
        else {
            mem[l][m] = false;
        }

        return mem[l][m];
    }

    public String reducePattern(String p) {
        String newp = "";

        for (int i = 0; i < p.length(); i++) {
            newp = newp + p.charAt(i);

            // skip extra stars
            if (p.charAt(i) == '*') {
                while (i < p.length() && p.charAt(i) == '*') {
                    i++;
                }
                i--;
            }
        }

        return newp;
    }

    public boolean isMatch(String s, String p) {
        // ? any single character
        // * any sequence of characters

        String newp = reducePattern(p);

        Boolean[][] memory =
            new Boolean[s.length() + 1][newp.length() + 1];

        return isMatchHelper(s, newp, 0, 0, memory);
    }
}
