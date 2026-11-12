package com.github.mohamad4444.problem70;

import java.util.HashMap;

public class Solution {
    HashMap<Integer, Integer> hashmap = new HashMap<>();

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        if (hashmap.containsKey(n)) {
            return hashmap.get(n);
        }
        hashmap.put(n - 1, climbStairs(n - 1));
        hashmap.put(n - 2, climbStairs(n - 2));
        return hashmap.get(n - 1) + hashmap.get(n - 2);
    }
}