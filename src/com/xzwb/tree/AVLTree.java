package com.xzwb.tree;

import com.xzwb.stack.Stack;

public class AVLTree<K extends Comparable, V> {
    AVLTree.AVLTreeNode<K, V> root;

    public AVLTree() {
        this.root = null;
    }

    /**
     * 平衡条件
     * @param node
     * @return
     */
    private boolean avlBalanced(AVLTree.AVLTreeNode node) {
        if (node.rightChild != null && node.leftChild != null) {
            return (((node.leftChild.height - node.rightChild.height) > -2) || ((node.leftChild.height - node.rightChild.height) < 2));
        } else if (node.leftChild == null && node.rightChild == null) {
            return true;
        } else if (node.leftChild != null && node.rightChild == null) {
            return node.leftChild.height < 2;
        } else if (node.rightChild != null && node.leftChild == null) {
            return node.rightChild.height < 2;
        }
        return false;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    private int getHeight(AVLTree.AVLTreeNode node) {
        return (node == null) ? -1 : node.height;
    }

    private int updateHeight(AVLTree.AVLTreeNode node) {
        return node.height = 1 + max(getHeight(node.leftChild), getHeight(node.rightChild));
    }

    /**
     * 查找节点如果找到就返回当前节点,如果没找到就返回应该插入的位置;
     * @param key
     * @return
     */
    private AVLTree.AVLTreeNode<K, V> search(K key) {
        AVLTree.AVLTreeNode<K, V> temp;
        temp = root;
        while (temp != null) {
            if (key.compareTo(temp.key) == 0) {
                return temp;
            } else if (key.compareTo(temp.key) > 0) {
              if (temp.rightChild == null) {
                  return temp;
              }
              temp = temp.rightChild;
            } else if (key.compareTo(temp.key) < 0) {
                if (temp.leftChild == null) {
                    return temp;
                }
                temp = temp.leftChild;
            }
        }
        return null;
    }

    public void insert(K key, V value) {
        if (root == null) {
            root = new AVLTreeNode<>(key, value, null);
            return;
        }
        AVLTree.AVLTreeNode<K, V> temp = search(key);
        if (temp.key.compareTo(key) == 0) {
            temp.value = value;
            return;
        } else if (key.compareTo(temp.key) > 0) {
            temp.insertRightTree(key, value);
        } else {
            temp.insertLeftTree(key, value);
        }
        while (temp != null) {
            if (!avlBalanced(temp)) {
                AVLTree.AVLTreeNode<K, V> parentTemp = temp.parent;
                AVLTree.AVLTreeNode<K, V> childTemp;
                if (temp.parent == null) {
                    if (temp.rightChild == null) {
                        root = temp.leftChild;
                    } else {
                        root = temp.rightChild;
                    }
                    root.parent = null;
                    temp.parent = root;
                    temp.height--;
                } else {
                    parentTemp = temp.parent;
                    if (temp.leftChild == null) {
                        childTemp = temp.rightChild;
                    } else {
                        childTemp = temp.leftChild;
                    }
                    if (childTemp.leftChild == null) {
                        childTemp.leftChild = temp;
                    } else {
                        childTemp.rightChild = temp;
                    }
                    temp.parent = childTemp;
                    temp.height--;
                    if (parentTemp.leftChild.key.compareTo(temp.key) == 0) {
                        parentTemp.leftChild = childTemp;
                    }  else {
                        parentTemp.rightChild = childTemp;
                    }
                    childTemp.parent = parentTemp;
                    break;
                }
            } else {
                updateHeight(temp);
            }
            temp = temp.parent;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("tree = {");
        Stack<AVLTree.AVLTreeNode<K, V>> stack = new Stack<>();
        AVLTree.AVLTreeNode<K, V> temp = root;
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

    private static class AVLTreeNode<K extends Comparable, V> {
        K key;
        V value;
        int height;
        AVLTree.AVLTreeNode<K, V> parent;
        AVLTree.AVLTreeNode<K, V> leftChild;
        AVLTree.AVLTreeNode<K, V> rightChild;

        AVLTreeNode(K key, V value, AVLTree.AVLTreeNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.height = 0;
            this.leftChild = null;
            this.rightChild = null;
            this.parent = parent;
        }

        AVLTreeNode<K, V> insertLeftTree(K key, V value) {
            return this.leftChild = new AVLTreeNode<>(key, value, this);
        }

        AVLTreeNode<K, V> insertRightTree(K key, V value) {
            return this.rightChild = new AVLTreeNode<>(key, value, this);
        }
    }
}
