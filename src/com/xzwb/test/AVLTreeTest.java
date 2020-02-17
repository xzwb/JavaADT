package com.xzwb.test;

import com.xzwb.tree.AVLTree;

public class AVLTreeTest {
    public static void main(String[] args) {
        AVLTree<Integer, Integer> avlTree = new AVLTree<>();
        avlTree.insert(200, 200);
        avlTree.insert(100, 100);
        avlTree.insert(400, 400);
        avlTree.insert(50, 50);
        avlTree.insert(150, 150);
        avlTree.insert(300, 300);
        avlTree.insert(450, 450);
        avlTree.insert(500, 500);
        avlTree.remove(300);
//        avlTree.insert(420, 420);
//        avlTree.insert(600, 600);
//        avlTree.insert(400, 400);
//        avlTree.insert(600, 600);avlTree.insert(800, 800);
//        avlTree.remove(200);
        System.out.println(avlTree);
    }
}
