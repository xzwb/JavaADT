package com;

import java.util.HashMap;
import java.util.HashSet;

public class Test9 {
    HashMap<TreeNode, TreeNode> map = new HashMap<>();

    public void dfs(TreeNode root) {
        if (root.left != null) {
            map.put(root.left, root);
            dfs(root.left);
        }
        if (root.right != null) {
            map.put(root.right, root);
            dfs(root.right);
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
