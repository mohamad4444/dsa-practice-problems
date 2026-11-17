package com.github.mohamad4444.problem16;

import java.util.Arrays;

public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int left, right;
        int distance=Integer.MAX_VALUE;
        int bestsum=Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if(i>0 && nums[i]==nums[i-1]){
                continue;
            }
            left = i + 1;
            right = nums.length-1;

            while (left < right) {
                int sum=nums[i]+nums[right]+nums[left];
                int diff=Math.abs(target-sum);
                if(diff<distance){
                    distance=diff;
                    bestsum=sum;
                }
                if (sum< target) {                    distance=Math.abs(sum-target);
                    left++;
                } else if (sum>target ) {
                    right--;
                }else{
                    return bestsum;
                }
            }

        }
        return bestsum;
    }
}
