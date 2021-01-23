package com;

public class Test12 {
    public TreeNode mirrorTree(TreeNode root) {
        swap(root);
        return root;
    }

    public void swap(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        TreeNode node = treeNode.left;
        treeNode.left = treeNode.right;
        treeNode.right = node;
        swap(treeNode.left);
        swap(treeNode.right);
    }
}
