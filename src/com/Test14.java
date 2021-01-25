package com;

import java.util.LinkedList;
import java.util.List;

public class Test14 {
    List<List<Integer>> lists = new LinkedList<>();
    LinkedList<Integer> list = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        recur(root, sum);
        return lists;
    }

    public void recur(TreeNode root, int tar) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        tar -= root.val;
        if (tar == 0 && root.left == null && root.right == null) {
            lists.add(new LinkedList<>(list));
        }
        recur(root.left, tar);
        recur(root.right, tar);
        list.removeLast();
    }
}
