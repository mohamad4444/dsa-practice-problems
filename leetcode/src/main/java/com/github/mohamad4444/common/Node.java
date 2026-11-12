package com.github.mohamad4444.common;

import java.util.*;

/**
 * Definition for a graph Node used in LeetCode graph problems (e.g. 133. Clone Graph).
 */
public class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors != null ? _neighbors : new ArrayList<>();
    }

    public Node(int _val, List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors != null ? _neighbors : new ArrayList<>();
    }

    /**
     * Builds an undirected graph from an adjacency list representation.
     * In LeetCode:
     * - Index 0 contains neighbors for node 1.
     * - Index i contains neighbors for node i + 1.
     *
     * Example:
     * adjList = [[2,4],[1,3],[2,4],[1,3]]
     */
    public static Node fromAdjList(int[][] adjList) {
        if (adjList == null || adjList.length == 0) {
            return null;
        }

        int n = adjList.length;
        // Handle single isolated node [[]]
        if (n == 1 && (adjList[0] == null || adjList[0].length == 0)) {
            return new Node(1);
        }

        Node[] nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < n; i++) {
            if (adjList[i] != null) {
                for (int neighborVal : adjList[i]) {
                    if (neighborVal >= 1 && neighborVal <= n) {
                        nodes[i + 1].neighbors.add(nodes[neighborVal]);
                    }
                }
            }
        }

        return nodes[1];
    }

    /**
     * Serializes a graph starting from the given node into an adjacency list matrix.
     */
    public static int[][] toAdjList(Node node) {
        if (node == null) {
            return new int[0][0];
        }

        // BFS to collect all reachable nodes sorted by value
        Map<Integer, Node> map = new TreeMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        map.put(node.val, node);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (curr.neighbors != null) {
                for (Node neighbor : curr.neighbors) {
                    if (neighbor != null && !map.containsKey(neighbor.val)) {
                        map.put(neighbor.val, neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }

        int[][] res = new int[map.size()][];
        int idx = 0;
        for (Node n : map.values()) {
            if (n.neighbors == null || n.neighbors.isEmpty()) {
                res[idx++] = new int[0];
            } else {
                int[] nbrs = new int[n.neighbors.size()];
                for (int j = 0; j < n.neighbors.size(); j++) {
                    nbrs[j] = n.neighbors.get(j).val;
                }
                res[idx++] = nbrs;
            }
        }

        return res;
    }

    /**
     * Converts a graph to standard LeetCode string representation like "[[2,4],[1,3],[2,4],[1,3]]".
     */
    public static String toAdjListString(Node node) {
        if (node == null) {
            return "[]";
        }
        int[][] adj = toAdjList(node);
        if (adj.length == 0) {
            return "[]";
        }
        if (adj.length == 1 && adj[0].length == 0) {
            return "[[]]";
        }
        return Arrays.deepToString(adj);
    }

    /**
     * Validates whether cloned is a true deep copy of original:
     * - Identical graph structure and values
     * - None of the cloned nodes share references with original nodes
     */
    public static boolean isDeepCopy(Node original, Node cloned) {
        if (original == null && cloned == null) return true;
        if (original == null || cloned == null) return false;

        Map<Integer, Node> origMap = collectNodes(original);
        Map<Integer, Node> cloneMap = collectNodes(cloned);

        if (origMap.size() != cloneMap.size()) return false;

        for (int val : origMap.keySet()) {
            if (!cloneMap.containsKey(val)) return false;

            Node origNode = origMap.get(val);
            Node cloneNode = cloneMap.get(val);

            // Must NOT be the same object reference
            if (origNode == cloneNode) return false;

            // Values must match
            if (origNode.val != cloneNode.val) return false;

            // Neighbor counts must match
            int origNbrCount = origNode.neighbors != null ? origNode.neighbors.size() : 0;
            int cloneNbrCount = cloneNode.neighbors != null ? cloneNode.neighbors.size() : 0;
            if (origNbrCount != cloneNbrCount) return false;

            // Neighbor values must match
            for (int i = 0; i < origNbrCount; i++) {
                if (origNode.neighbors.get(i).val != cloneNode.neighbors.get(i).val) {
                    return false;
                }
            }
        }

        return true;
    }

    private static Map<Integer, Node> collectNodes(Node start) {
        Map<Integer, Node> map = new HashMap<>();
        if (start == null) return map;

        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        map.put(start.val, start);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (curr.neighbors != null) {
                for (Node nbr : curr.neighbors) {
                    if (nbr != null && !map.containsKey(nbr.val)) {
                        map.put(nbr.val, nbr);
                        queue.add(nbr);
                    }
                }
            }
        }
        return map;
    }

    @Override
    public String toString() {
        return "Node(" + val + ")";
    }
}
