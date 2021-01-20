package com;

public class Test6 {

    public static TreeNode node = null;

    public static void treeNode(TreeNode A, TreeNode B) {
        if (A == null) {
            return;
        }
        if (A.val == B.val) {
            node = A;
        }
        treeNode(A.left, B);
        treeNode(A.right, B);
    }

    public static boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        treeNode(A, B);
        if (node == null) {
            return false;
        }
        return bianli(node, B);
    }

    public static boolean bianli(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        if (A.val == B.val) {
            return true && bianli(A.left, B.left) && bianli(A.right, B.right);
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(3);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        System.out.println(isSubStructure(treeNode1, treeNode5));
    }
}
