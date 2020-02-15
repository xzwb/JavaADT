package com.xzwb.test;

import com.xzwb.tree.AVLTree;

public class AVLTreeTest {
    public static void main(String[] args) {
        AVLTree<Integer, Integer> avlTree = new AVLTree<>();
        avlTree.insert(10, 10);
        avlTree.insert(7, 7);
        avlTree.insert(20, 20);
        avlTree.insert(6, 6);
        avlTree.insert(8, 8);
        avlTree.insert(20, 20);
        avlTree.insert(30, 30);
        avlTree.insert(25, 25);
        System.out.println(avlTree);
    }
}
