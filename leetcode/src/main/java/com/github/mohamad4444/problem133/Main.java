package com.github.mohamad4444.problem133;

import java.util.*;
import com.github.mohamad4444.common.Node;

public class Main {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Running Problem 133: cloneGraph");
        System.out.println("==================================================");

        // Test Cases matching LeetCode Examples
        int[][][] testCases = {
            // Test 1: Standard 4-node connected graph [[2,4],[1,3],[2,4],[1,3]]
            {{2, 4}, {1, 3}, {2, 4}, {1, 3}},

            // Test 2: Single isolated node [[]]
            {{}},

            // Test 3: Empty graph []
            {},

            // Test 4: Simple 2-node graph [[2],[1]]
            {{2}, {1}}
        };

        for (int i = 0; i < testCases.length; i++) {
            int[][] adjList = testCases[i];
            Node original = Node.fromAdjList(adjList);

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("  Input:    adjList = " + Node.toAdjListString(original));

            // Create a fresh solution instance for each test
            Solution solution = new Solution();
            Node cloned = solution.cloneGraph(original);

            System.out.println("  Output:   adjList = " + Node.toAdjListString(cloned));

            boolean deepCopy = Node.isDeepCopy(original, cloned);
            System.out.println("  Deep Copy Validated: " + (deepCopy ? "✅ PASS" : "❌ FAIL"));
            System.out.println("--------------------------------------------------");
        }
    }
}
