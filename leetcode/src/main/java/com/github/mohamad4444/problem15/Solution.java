package com.github.mohamad4444.problem15;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int left, right;
        LinkedList<List<Integer>> ans = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            if(i>0 &&nums[i]==nums[i-1]){
                continue;
            }
            left = i + 1;
            right = nums.length-1;

            while (left < right) {
                if (nums[i] +nums[right] + nums[left]< 0) {
                    left++;
                } else if (nums[i] +nums[left] + nums[right]>0 ) {
                    right--;
                }else{
                    ans.add(new LinkedList<>(List.of(nums[i], nums[left], nums[right])));
                    left++;
                    while(nums[left]==nums[left-1] && left<right){
                        left++;
                    }
                }
            }

        }

        return ans;
    }
}
