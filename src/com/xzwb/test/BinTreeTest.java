package com.xzwb.test;

import com.xzwb.tree.BinTree;

public class BinTreeTest {
    public static void main(String[] args) {
        BinTree<String> binTree = new BinTree<>("1");
        binTree.insert("2", "1", -1);
        binTree.insert("3", "1", 1);
        binTree.insert("4", "2", 1);
        System.out.println(binTree);
    }
}
