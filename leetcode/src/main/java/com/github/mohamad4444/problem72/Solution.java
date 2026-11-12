package com.github.mohamad4444.problem72;

import java.util.*;

public class Solution {
    // Levenshtein Distance
    public int minDistance(String word1, String word2) {
        int d[][] = new int[word1.length()][word2.length()];
        for (int i = 0; i < word1.length(); i++) {
            d[i][0] = i;
        }
        for (int j = 0; j < word2.length(); j++) {
            d[0][j] = j;
        }
        for (int i = 1; i < word1.length(); i++) {
            for (int j = 1; j < word2.length(); j++) {
                int lambda = word1.charAt(i) == word2.charAt(j) ? 0 : 1;
                d[i][j] = Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1);
                d[i][j] = Math.min(d[i][j], d[i - 1][j - 1] + lambda);
            }
        }
        return d[word1.length() - 1][word2.length() - 1];
    }
}
