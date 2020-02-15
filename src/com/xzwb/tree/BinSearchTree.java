package com.xzwb.tree;

import com.xzwb.stack.Stack;

public class BinSearchTree<K extends Comparable, V> {
    private BinSearchTree.BinSearchTreeNode<K, V> root;
    private BinSearchTree.BinSearchTreeNode<K, V> temp;
    public BinSearchTree() {
        root = null;
    }

    public <K extends Comparable> V getValue(K key) {
        temp = root;
        while (temp != null) {
            if (key.compareTo(temp.key) == 0) {
                return temp.value;
            } else if (key.compareTo(temp.key) > 0) {
                if (temp.rightChild == null) {
                    break;
                } else {
                    temp = temp.rightChild;
                }
            } else if (key.compareTo(temp.key) < 0) {
                if (temp.leftChild == null) {
                    break;
                } else {
                    temp = temp.leftChild;
                }
            }
        }
        return null;
    }

    /**
     * 返回当前key对应节点中序遍历的后一个元素
     * @param key
     * @return
     */
    private BinSearchTree.BinSearchTreeNode<K, V> search(K key) {
        Stack<BinSearchTree.BinSearchTreeNode<K, V>> stack = new Stack<>();
        temp = root;
        boolean index = false;
        while (temp != null || stack.getSize() != 0) {
            if (temp != null) {
                stack.push(temp);
                temp = temp.leftChild;
            } else {
                temp = stack.pull();
                if (index) {
                    return temp;
                }
                if (key.equals(temp.key)) {
                    index = true;
                }
                temp = temp.rightChild;
            }
        }
        return null;
    }

    public void remove(K key) {
        BinSearchTree.BinSearchTreeNode<K, V> p;
        if (getValue(key) == null) {
            return;
        }
        p = temp.parent;
        System.out.println(p.key + " " + temp.key);
        if (temp.leftChild == null) {
            temp = temp.rightChild;
        } else if (temp.rightChild == null) {
            temp = temp.leftChild;
        } else {
            BinSearchTree.BinSearchTreeNode<K, V> removeNode = temp;
            BinSearchTree.BinSearchTreeNode<K, V> node = search(key);
            V vTemp = removeNode.value;
            removeNode.value = node.value;
            node.value = vTemp;
            K kTemp = removeNode.key;
            removeNode.key = node.key;
            node.key = kTemp;
            p = node.parent;
            node = node.rightChild;
            if (p.rightChild.key.compareTo(key) == 0) {
                p.rightChild = node;
            } else {
                p.leftChild = node;
            }
            if (node != null) {
                node.parent = p;
            }
            return;
        }
        if (p.leftChild.key.compareTo(key) == 0) {
            p.leftChild = temp;
        } else {
            p.rightChild = temp;
        }
        if (temp != null) {
            temp.parent = p;
        }
    }

    public void add(K key, V value) {
        if (root == null) {
            root = new BinSearchTree.BinSearchTreeNode(key, value, null);
        } else {
            getValue(key);
            if (temp.key.compareTo(key) == 0) {
                temp.value = value;
            } else if (key.compareTo(temp.key) > 0) {
                temp.insertRightTree(key, value);
            } else if (key.compareTo(temp.key) < 0) {
                temp.insertLeftTree(key, value);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("tree = {");
        Stack<BinSearchTree.BinSearchTreeNode<K, V>> stack = new Stack<>();
        temp = root;
        if (root == null) {
            stringBuilder.append("}");
        } else {
            while (temp != null || stack.getSize() != 0) {
                if (temp != null) {
                    stack.push(temp);
                    temp = temp.leftChild;
                } else {
                    temp = stack.pull();
                    stringBuilder.append(temp.value + ", ");
                    temp = temp.rightChild;
                }
            }
        }
        return stringBuilder.toString();
    }

    private static class BinSearchTreeNode<K extends Comparable, V> {
        K key;
        V value;
        BinSearchTree.BinSearchTreeNode<K, V> parent;
        BinSearchTree.BinSearchTreeNode<K, V> leftChild;
        BinSearchTree.BinSearchTreeNode<K, V> rightChild;

        BinSearchTreeNode(K key, V value, BinSearchTree.BinSearchTreeNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            leftChild = null;
            rightChild = null;
        }

        BinSearchTreeNode<K, V> insertLeftTree(K key, V value) {
            return this.leftChild = new BinSearchTreeNode<>(key, value, this);
        }

        BinSearchTreeNode<K, V> insertRightTree(K key, V value) {
            return this.rightChild = new BinSearchTreeNode<>(key, value, this);
        }
    }
}
