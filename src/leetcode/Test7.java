package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给出中序遍历和先序遍历创建二叉树
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Test7 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        TreeNode root = build(preorder, 0, preorder.length-1, 0, inorder.length-1, map);
        return  root;
    }

    TreeNode build(int[] preorder, int perorderStart, int perOrderEnd, int inorderStart, int inOrderEnd, Map<Integer, Integer> map) {
        if (perorderStart > perOrderEnd) {
            return null;
        }
        // 创建根节点
        TreeNode treeNode = new TreeNode(preorder[perorderStart]);
        // 获取根节点在中序遍历中的位置
        int rootIndexInInorder = map.get(preorder[perorderStart]);

        if (perorderStart == perOrderEnd) {
            return treeNode;
        } else {
            // 左子树节点数
            int leftNode = rootIndexInInorder - inorderStart;
            // 右子树个数
            int rightNode = inOrderEnd - rootIndexInInorder;
            treeNode.left = build(preorder, perorderStart+1, perorderStart+leftNode, inorderStart, rootIndexInInorder-1, map);
            treeNode.right = build(preorder, perOrderEnd-rightNode+1, perOrderEnd, rootIndexInInorder+1, inOrderEnd, map);
        }
        return treeNode;
    }
}
