package com;

import java.util.Deque;
import java.util.LinkedList;

public class Test16 {
    Deque<Integer> list = new LinkedList<>();

    public void bianli(TreeNode node) {
        if (node == null) {
            return;
        }
        bianli(node.left);
        list.push(node.val);
        bianli(node.right);
    }

    public int kthLargest(TreeNode root, int k) {
        bianli(root);
        int result = 0;
        while (k != 0) {
            result = list.pop();
            k--;
        }
        return result;
    }
}
