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
            return (((node.leftChild.height - node.rightChild.height) > -2) && ((node.leftChild.height - node.rightChild.height) < 2));
        } else if (node.leftChild == null && node.rightChild == null) {
            return true;
        } else if (node.leftChild != null && node.rightChild == null) {
            return node.leftChild.height < 1;
        } else if (node.rightChild != null && node.leftChild == null) {
            return node.rightChild.height < 1;
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

    /**
     * 旋转
     * @param node
     * @return
     */
    private AVLTree.AVLTreeNode<K, V> rotate(AVLTree.AVLTreeNode node) {
        AVLTree.AVLTreeNode parentNode = node.parent;
        AVLTree.AVLTreeNode grandparentNode = parentNode.parent;
        if (node.key.compareTo(root.key) > 0) {
            if (node.equals(parentNode.rightChild)) {
                if (parentNode.equals(grandparentNode.rightChild)) {
                    return connect34(grandparentNode, parentNode, node, grandparentNode.leftChild, parentNode.leftChild, node.leftChild, node.rightChild);
                } else {
                    return connect34(parentNode, node, grandparentNode, parentNode.leftChild, node.leftChild, node.rightChild, grandparentNode.rightChild);

                }
            } else {
                if (parentNode.equals(grandparentNode.rightChild)) {
                    return connect34(grandparentNode, node, parentNode, grandparentNode.leftChild, node.leftChild, node.rightChild, parentNode.rightChild);
                } else {
                    return connect34(node, parentNode, grandparentNode, node.leftChild, node.rightChild, parentNode.rightChild, grandparentNode.rightChild);
                }
            }
        } else {
            if (parentNode.equals(grandparentNode.leftChild)) {
                if (node.equals(parentNode.leftChild)) {
                    return connect34(node, parentNode, grandparentNode, node.leftChild, node.rightChild, parentNode.rightChild, grandparentNode.rightChild);
                } else {
                    return connect34(parentNode, node, grandparentNode, parentNode.leftChild, node.leftChild, node.rightChild, grandparentNode.rightChild);
                }
            } else {
                if (node.equals(parentNode.rightChild)) {
                    return connect34(grandparentNode, parentNode, node, grandparentNode.leftChild, parentNode.leftChild, node.leftChild, node.rightChild);
                } else {
                    return connect34(grandparentNode, node, parentNode, grandparentNode.leftChild, node.leftChild, node.rightChild, parentNode.rightChild);
                }
            }
        }
    }
    /**
     * 3+4重构
     * @param a
     * @param b
     * @param c
     * @param t0
     * @param t1
     * @param t2
     * @param t3
     * @return
     */
    private AVLTree.AVLTreeNode<K, V> connect34(AVLTreeNode a, AVLTreeNode b, AVLTreeNode c, AVLTreeNode t0, AVLTreeNode t1, AVLTreeNode t2, AVLTreeNode t3) {
        a.leftChild = t0;
        if (t0 != null) {
            t0.parent = a;
        }
        a.rightChild = t1;
        if (t1 != null) {
            t1.parent = a;
        }
        updateHeight(a);
        c.leftChild = t2;
        if (t2 != null) {
            t2.parent = c;
        }
        c.rightChild = t3;
        if (t3 != null) {
            t3.parent = c;
        }
        updateHeight(c);
        b.leftChild = a;
        b.rightChild = c;
        a.parent = b;
        c.parent = b;
        updateHeight(b);
        return b;
    }

    public void insert(K key, V value) {
        if (root == null) {
            root = new AVLTreeNode<>(key, value, null);
            return;
        }
        int index = 0;
        AVLTreeNode temp = search(key);
        AVLTreeNode grandTemp = temp;
        AVLTreeNode newNode;
        if (key.compareTo(temp.key) == 0) {
            temp.value = value;
        } else if (key.compareTo(temp.key) > 0) {
            temp.insertRightTree(key, value);
        } else {
            temp.insertLeftTree(key, value);
        }
        while (temp != null) {
            AVLTreeNode parentTemp = temp.parent;
            if (index > 2) {
                grandTemp = grandTemp.parent;
            }
            if (avlBalanced(temp)) {
                updateHeight(temp);
            } else {
                if (root == temp) {
                    newNode = rotate(search(key));
                    if (grandTemp.key.compareTo(root.key) > 0) {
                        root = newNode;
                    } else {
                        root = newNode;
                    }
                    newNode.parent = null;
                } else {
                    newNode = rotate(grandTemp);
                    if (grandTemp.key.compareTo(parentTemp.key) > 0) {
                        parentTemp.rightChild = newNode;
                    } else {
                        parentTemp.leftChild = newNode;
                    }
                    newNode.parent = parentTemp;
                }
                break;
            }
            temp = temp.parent;
            index++;
        }
    }

    private AVLTreeNode<K, V> midNextNode(AVLTreeNode node) {
        Stack<AVLTree.AVLTreeNode<K, V>> stack = new Stack<>();
        AVLTreeNode<K, V> temp = root;
        int count = 0;
        while (temp != null || stack.getSize() != 0) {
            if (temp != null) {
                stack.push(temp);
                temp = temp.leftChild;
            } else {
                temp = stack.pull();
                if (count > 0) {
                    return temp;
                }
                if (node.key.compareTo(temp.key) == 0) {
                    count++;
                }
                temp = temp.rightChild;
            }
        }
        return null;
    }

    public void remove(K key) {
        AVLTreeNode<K, V> temp = search(key);
        AVLTreeNode<K, V> node = null;
        AVLTreeNode sonNode;
        AVLTreeNode newRootNode;
        if (temp == null) {
            return;
        }
        if (temp.key.compareTo(key) != 0) {
            return;
        }
        if (temp == root) {
            root = null;
        } else if (temp.leftChild == null && temp.rightChild == null) {
            node = temp.parent;
            if (node.leftChild.equals(temp)) {
                node.leftChild = null;
            } else {
                node.rightChild = null;
            }
        } else if (temp.leftChild != null && temp.rightChild == null) {
            node = temp.parent;
            temp = temp.leftChild;
            temp.parent = node;
            if (node.leftChild.equals(temp)) {
                node.leftChild = temp;
            } else {
                node.rightChild = temp;
            }
        } else if (temp.leftChild == null && temp.rightChild != null) {
            node = temp.parent;
            temp = temp.rightChild;
            temp.parent = node;
            if (node.leftChild.equals(temp)) {
                node.leftChild = temp;
            } else {
                node.rightChild = temp;
            }
        } else {
            AVLTreeNode<K, V> midNode = midNextNode(temp);
            K keyTemp = midNode.key;
            midNode.key = temp.key;
            temp.key = keyTemp;
            V value = midNode.value;
            midNode.value = temp.value;
            temp.value = value;
            node = midNode.parent;
            temp = midNode;
            midNode = midNode.leftChild;
            if (temp.equals(node.leftChild)) {
                node.leftChild = midNode;
            } else {
                node.rightChild = midNode;
            }
            if (midNode != null) {
                midNode.parent = node;
            }
        }
        AVLTreeNode parentNode = node.parent;
        while (parentNode != null) {
            if (avlBalanced(node)) {
                updateHeight(node);
                node = node.parent;
            } else {
                if (getHeight(node.leftChild) > getHeight(node.rightChild)) {
                    sonNode = node.leftChild;
                } else {
                    sonNode = node.rightChild;
                }
                if (sonNode.leftChild != null) {
                    newRootNode = rotate(sonNode.leftChild);
                } else {
                    newRootNode = rotate(sonNode.rightChild);
                }
                if (newRootNode.key.compareTo(parentNode.key) < 0) {
                    parentNode.leftChild = newRootNode;
                } else {
                    parentNode.rightChild = newRootNode;
                }
                newRootNode.parent = parentNode;
                node = parentNode;
            }
            parentNode = parentNode.parent;
        }
        if (!avlBalanced(node)) {
            if (getHeight(node.leftChild) > getHeight(node.rightChild)) {
                sonNode = node.leftChild;
            } else {
                sonNode = node.rightChild;
            }
            if (sonNode.leftChild != null) {
                newRootNode = rotate(sonNode.leftChild);
            } else {
                newRootNode = rotate(sonNode.rightChild);
            }
            newRootNode.parent = null;
            root = newRootNode;
        } else {
            updateHeight(node);
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
