package com;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个数组判断是不是二叉搜索树的后序遍历
 */
public class Test4 {
    public boolean verifyPostorder(int[] postorder) {
        Deque<Integer> deque = new LinkedList<>();
        int root = Integer.MAX_VALUE;
        int length = postorder.length;
        for (int i = length - 1; i >= 0; i--) {
            if (postorder[i] > root) {
                return false;
            }
            while (!deque.isEmpty() && deque.peek() > postorder[i]) {
                root = deque.pop();
            }
            deque.push(postorder[i]);
        }
        return true;
    }

}
