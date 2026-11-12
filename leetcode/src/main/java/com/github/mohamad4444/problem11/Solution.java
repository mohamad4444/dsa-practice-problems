package com.github.mohamad4444.problem11;

public class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int maxheight = Math.min(height[i], height[j]);
                int width = j - i;
                if (maxheight * width > max) {
                    max = maxheight * width;
                }
            }
        }
        return max;
    }
}