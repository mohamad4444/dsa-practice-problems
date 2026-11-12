package com.github.mohamad4444.common;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node used in LeetCode problems.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {}

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * Helper to create a binary tree from level-order array/varargs (null indicates missing node).
     */
    public static TreeNode of(Integer... values) {
        if (values == null || values.length == 0 || values[0] == null) return null;

        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode curr = queue.poll();
            if (i < values.length && values[i] != null) {
                curr.left = new TreeNode(values[i]);
                queue.add(curr.left);
            }
            i++;
            if (i < values.length && values[i] != null) {
                curr.right = new TreeNode(values[i]);
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }

    @Override
    public String toString() {
        return "TreeNode(" + val + ")";
    }
}
