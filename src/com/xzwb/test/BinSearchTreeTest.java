package com.xzwb.test;

import com.xzwb.tree.BinSearchTree;

public class BinSearchTreeTest {
    public static void main(String[] args) {
        BinSearchTree<Integer, String> tree = new BinSearchTree<>();
        tree.add(1, "789");
        tree.add(3, "864");
        tree.add(4, "555");
        tree.add(2, "741");
        System.out.println(tree);
        System.out.println(tree.getValue(2));
    }
}
