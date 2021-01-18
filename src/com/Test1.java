package com;

import java.util.HashMap;
import java.util.HashSet;

public class Test1 {

    HashMap<TreeNode, TreeNode> map = new HashMap();

    public void dfs(TreeNode node) {
        if (node.left != null) {
            map.put(node.left, node);
            dfs(node.left);
        }
        if (node.right != null) {
            map.put(node.right, node);
            dfs(node.right);
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        dfs(root);
        HashSet<TreeNode> set = new HashSet();
        while (!p.equals(root)) {
            set.add(p);
            p = map.get(p);
        }
        set.add(root);
        while (set.add(q)) {
            q = map.get(q);
        }
        return q;
    }
}
