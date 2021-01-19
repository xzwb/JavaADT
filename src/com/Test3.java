package com;

import java.util.*;

public class Test3 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return list;
        }
        queue.offer(root);
        while (queue.size() != 0) {
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                root = queue.poll();
                if (root.left != null) {
                    queue.offer(root.left);
                }
                if (root.right != null) {
                    queue.offer(root.right);
                }
            }
            list.add(root.val);
        }
        return list;
    }
}
