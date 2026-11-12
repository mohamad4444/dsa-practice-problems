package com.github.mohamad4444.problem133;

import java.util.*;
import com.github.mohamad4444.common.Node;

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
public class Solution {

    public Node cloneGraph(Node node) {
        Set<Node> visited = new HashSet<>();
        Stack<Node> toVisit = new Stack<>();
        if (node == null) {
            return null;
        }

        if (node.neighbors == null) {
            return new Node(node.val);
        }
        toVisit.add(node);
        HashMap<Node, Node> map = new HashMap<>();
        while (!toVisit.isEmpty()) {
            Node curr = toVisit.pop();
            Node clone;
            visited.add(curr);
            if (map.get(curr) == null) {
                clone = new Node(curr.val);
                map.put(curr, clone);
            } else {
                clone = map.get(curr);
            }
            for (Node n : curr.neighbors) {
                if (!visited.contains(n)) {
                    toVisit.add(n);
                }
                Node nclone;
                if (map.get(n) == null) {
                    nclone = new Node(n.val);
                    map.put(n, nclone);
                } else {
                    nclone = map.get(n);
                }
                clone.neighbors.add(nclone);

            }

        }

        return map.get(node);
    }
}
