package com.xzwb.tree;

import java.util.Stack;

public class BinTree<T> {
    private BinTree.BinNode<T> root;
    int size;

    BinTree() {
        this.root = null;
        size = 0;
    }

    private BinTree.BinNode<T> find(T ele) {
        return null;
    }

    private BinTree.BinNode<T> insertLeft(T ele, BinTree.BinNode<T> node) {
        size++;
        node.insertLeftNode(ele);
        updateHeightAbove(node);
        return node.leftChild;
    }


    private BinTree.BinNode<T> insertRight(T ele, BinTree.BinNode<T> node) {
        size++;
        node.insertRightNode(ele);
        updateHeightAbove(node);
        return node.rightChild;
    }

    private int getHeight(BinTree.BinNode<T> node) {
        return (node == null) ? -1 : node.height;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    private int updateHeight(BinTree.BinNode<T> node) {
        return node.height = 1 + max(getHeight(node.leftChild), getHeight(node.rightChild));
    }

    private void updateHeightAbove(BinTree.BinNode<T> node) {
        while (node != null) {
            updateHeight(node);
            node = node.parent;
        }
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
            this.height = 0;
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
