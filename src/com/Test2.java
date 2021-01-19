package com;

import java.util.*;

public class Test2 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        Deque<TreeNode> deque = new LinkedList();
        deque.push(root);
        int flag = 0;
        while (deque.size() != 0) {
            List<Integer> list = new ArrayList<>();
            int length = deque.size();
            TreeNode[] treeNodes = new TreeNode[length];
            for (int i = 0; i < length; i++) {
                treeNodes[i] = deque.pop();
            }
            for (int i = 0; i < length; i++) {
                list.add(treeNodes[i].val);
                if (flag % 2 == 0) { // 偶数行

                    if (treeNodes[i].left != null) {
                        deque.push(treeNodes[i].left);
                    }
                    if (treeNodes[i].right != null) {
                        deque.push(treeNodes[i].right);
                    }
                } else { // 奇数行

                    if (treeNodes[i].right != null) {
                        deque.push(treeNodes[i].right);
                    }
                    if (treeNodes[i].left != null) {
                        deque.push(treeNodes[i].left);
                    }
                }
            }
            flag++;
            lists.add(list);
        }

        return lists;
    }
}
