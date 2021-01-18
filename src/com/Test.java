package com;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
  }
}

public class Test {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        Deque<TreeNode> deque = new LinkedList<>();
        while (root != null || deque.size() != 0) {
            while (root != null) {
                deque.push(root);
                root = root.left;
            }
            root = deque.pop();
//            list.add(root.val);
            root = root.right;
        }
        return list;
    }
}
