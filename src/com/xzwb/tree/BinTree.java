package com.xzwb.tree;

public class BinTree<T> {
    private BinTree.BinNode<T> root;
    int size;

    BinTree() {
        this.root = null;
        size = 0;
    }

    /**
     * 判断是否为空
     * @return true为空
     */
    public boolean empty() {
        return this.root == null;
    }

    private static class BinNode<T> {
        BinTree.BinNode<T> parent;
        BinTree.BinNode<T> leftChild;
        BinTree.BinNode<T> rightChild;
        T ele;
        int height;

        BinNode(T ele, BinTree.BinNode<T> parent) {
            this.parent = parent;
            this.leftChild = null;
            this.rightChild = null;
            this.ele = ele;
        }

        BinTree.BinNode<T> insertLeftNode(T ele) {
            return leftChild = new BinTree.BinNode(ele, this);
        }

        BinTree.BinNode<T> insertRightNode(T ele) {
            return rightChild = new BinTree.BinNode(ele, this);
        }

        int size() {
            int s = 1;
            if (leftChild != null) {
                s += leftChild.size();
            }
            if (rightChild != null) {
                s += rightChild.size();
            }
            return s;
        }
    }
}
