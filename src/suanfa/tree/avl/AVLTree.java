package suanfa.tree.avl;

/**
 * 树的高度的两种说法:
 *     叶子节点的高度为1
 *     叶子节点的高度为0
 * 平衡因子: 左子树的高度 - 右子树的高度
 * 他是一颗空树,或者左右两颗子树高度差的绝对值不超过一，要求左右两颗子树都是平衡二叉树
 *
 * 对于插入一个新的节点造成的失衡来说:
 *     有可能导致若干个祖先失衡,但是除了祖先之外的其他节点不可能失衡,对于非新插入节点的祖先，
 *     无论是他们的高度,还是他们孩子的高度都不会因为新插入的节点的高度发生变化,所以平衡因子不会发生变化;
 *     失衡发生在新插入节点的爷爷辈往上
 * 对于删除一个节点造成的失衡:
 *      在删除节点的瞬间至多只有一个祖先会失衡;因为失衡时失衡的祖先的高度必然保持不变,
 *      因为失衡时,删除的节点必然属于失衡的祖先相对更短的分支
 *
 *
 * 当插入新结点导致不平衡时, 我们需要找到距离新节点最近的不平衡结点为轴来转动AVL树来达到平衡
 *
 * avl树的插入：
 * 单旋转的第一种情况---右旋转:
 *     1. 找到最低失衡点 a;
 *     2. 找到a的左孩子 b;
 *     3. a,b右旋4分之1,使得b作为父节点,a节点变为b的右节点,b如果有右子树将成为a的左子树;
 * 左旋转:
 *     1. 找到最低失衡点 a;
 *     2. 找到a的右孩子 b;
 *     3. a,b左旋4分之1,使得b作为父节点,a节点变为b的左节点,b如果有左子树将成为a的右子树;
 */

public class AVLTree<T extends Comparable<T>> {

    public Node<T> root;
    // 查找
    public Node<T> search(T value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    // 查找父节点
    public Node<T> searchParent(T value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     *
     * @param node
     * @return 以Node为根节点的最小二叉排序树的值,删除node为根节点的最小节点
     */
    public T delRightTree(Node<T> node) {
        Node<T> target = node;
        // 循环的查找左节点
        while (target.left != null) {
            target = target.left;
        }
        delNode(target.value);
        return target.value;
    }

    // 删除节点
    public void delNode(T value) {
        if (root == null) {
            return;
        } else {
            // 1.需要先找到要删除的节点
            Node<T> targetNode = search(value);
            // 如果没有找到
            if (targetNode == null) {
                return;
            }
            // 如果我们发现当前这颗二叉排序树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            // 找到targetNode的父节点
            Node<T> parent = searchParent(value);
            // 如果删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                // 判断targetNode是父节点的左子节点还是右子节点
                if (targetNode.equals(parent.left)) {
                    parent.left = null;
                } else if (targetNode.equals(parent.right)) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) { // 删除有两颗子树的节点
                T minValue = delRightTree(targetNode.right);
                targetNode.value = minValue;
            } else { // 删除只有一颗子树的节点
                // 如果要删除的节点有左子节点
                if (targetNode.left != null) {
                    if (parent != null) {
                        if (targetNode.equals(parent.left)) {
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else { //要删除的节点有右子节点
                    if (parent != null) {
                        if (targetNode.equals(parent.left)) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
            if (targetNode == root && !targetNode.balance()) { // 删除的是根节点而且是根节点有两颗子树
                if (targetNode.left.balanceNumber() < 0) { // 左右
                    leftRotate(targetNode.left, targetNode);
                    rightRotate(targetNode, parent);
                } else {
                    rightRotate(targetNode, parent);
                }
            }
            // 从被删除节点开始判断是否失衡
            targetNode = parent;
            while (parent != null) {
                // 获取当前节点的父亲节点
                parent = getNodeParent(targetNode);
                if (!targetNode.balance()) {
                    if (targetNode.balanceNumber() < 0) { // 说明插入发生在最低失衡点的右子树上
                        if (targetNode.right.balanceNumber() < 0) { // 说明是右右
                            leftRotate(targetNode, parent);
                        } else { // 右左
                            rightRotate(targetNode.right, targetNode);
                            leftRotate(targetNode, parent);
                        }
                    } else { // 说明失衡发生在最低失衡点的z左子树上
                        if (targetNode.left.balanceNumber() < 0) { // 左右
                            leftRotate(targetNode.left, targetNode);
                            rightRotate(targetNode, parent);
                        } else {
                            rightRotate(targetNode, parent);
                        }
                    }
                }
            }
        }

    }



    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空不能遍历");
        }
    }

    /**
     * 找到最低失衡点
     */
    public Node<T> minNotBalance(T value) {
        // 临时
        Node<T> temp = root;
        // 指向最小的失衡点
        Node<T> temp1 = root;
        while(true) {
            temp1 = temp;
            if (value.compareTo(temp.value) < 0) { // 传入的value小于root的
                temp = temp.left;
            } else {
                temp = temp.right;
            }
            if (temp.balance()) {
                return temp1;
            }
        }
    }

    /**
     * 获取节点的父亲节点
     */
    public Node<T> getNodeParent(Node<T> node) {
        Node<T> temp = root;
        if (node.equals(root)) {
            return null;
        }
        while (true) {
            if ((temp.left != null && temp.left.equals(node)) ||
                    (temp.right != null && temp.right.equals(node))) {
                return temp;
            } else if (node.value.compareTo(temp.value) < 0) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }

    }

    /**
     * 左旋转
     * @param node 最低失衡点
     * @param parent 当前节点的父节点
     */
    public void leftRotate(Node<T> node, Node<T> parent) {
        Node<T> b = node.right;
        if (parent == null) {
            root = b;
        } else {
            if (node.value.compareTo(parent.value) < 0) {
                parent.left = b;
            } else {
                parent.right = b;
            }
        }
        node.right = b.left;
        b.left = node;

    }

    /**
     * 右旋转
     * @param node
     * @param parent
     */
    public void rightRotate(Node<T> node, Node<T> parent) {
        Node<T> b = node.left;
        if (parent == null) {
            root = b;
        } else {
            if (node.value.compareTo(parent.value) < 0) {
                parent.left = b;
            } else {
                parent.right = b;
            }
        }
        node.left = b.right;
        b.right = node;
    }

    /**
     * 添加
     * @param value
     */
    public void add(T value) {
        Node<T> node = new Node<>(value);
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
        if (!root.balance()) { // 如果root失衡
            Node<T> minNotBalance = minNotBalance(value); // 找到最低失衡点
            Node<T> parent = getNodeParent(minNotBalance); // 找到最低失衡点的父节点

            if (minNotBalance.balanceNumber() < 0) { // 说明插入发生在最低失衡点的右子树上
                if (minNotBalance.right.balanceNumber() < 0) { // 说明是右右
                    leftRotate(minNotBalance, parent);
                } else { // 右左
                    rightRotate(minNotBalance.right, minNotBalance);
                    leftRotate(minNotBalance, parent);
                }
            } else { // 说明失衡发生在最低失衡点的z左子树上
                if (minNotBalance.left.balanceNumber() < 0) { // 左右
                    leftRotate(minNotBalance.left, minNotBalance);
                    rightRotate(minNotBalance, parent);
                } else {
                    rightRotate(minNotBalance, parent);
                }
            }
        }
    }


    class Node<T extends Comparable<T>> {
        T value;
        Node<T> left;
        Node<T> right;


        /**
         * 构造方法
         * @param value
         */
        public Node(T value) {
            this.value = value;
        }

        /**
         * 获取平衡因子
         * @return 平衡因子
         */
        public int balanceNumber() {
            if (this.left == null) {
                if (this.right == null) {
                    return 0;
                } else {
                    return -1 - this.right.height();
                }
            } else {
                if (this.right == null) {
                    return this.left.height() + 1;
                } else {
                    return this.left.height() - this.right.height();
                }
            }
        }

        /**
         * 是否平衡
         * @return
         */
        public boolean balance() {
            int balanceNumber = this.balanceNumber();
            if (balanceNumber < 2 && balanceNumber > -2) { // 平衡因子小于2且大于-2
                return true;
            } else {
                return false;
            }
        }

        /**
         * 获取树的高度
         */
        int height() {
            return max(this.left == null ? -1 : this.left.height(), this.right == null ? -1 : this.right.height()) + 1;
        }

        /**
         * 比较大小
         * @param left
         * @param right
         * @return
         */
        public int max(int left, int right) {
            return left > right ? left : right;
        }

        /**
         *   中序遍历
         */
        public void infixOrder() {
            if (this.left != null) {
                this.left.infixOrder();
            }
            System.out.println(this);
            if (this.right != null) {
                this.right.infixOrder();
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

        // 递归添加
        public void add(Node<T> node) {
            if (node == null) {
                return;
            }
            // 判断传入节点的值
            if (node.value.compareTo(this.value) < 0) { // 传入的节点小于当前节点
                // 如果当前左子节点为空
                if (this.left == null) {
                    this.left = node;
                } else {
                    // 递归的向左子树添加
                    this.left.add(node);
                }
            } else {
                if (this.right == null) {
                    this.right = node;
                } else {
                    this.right.add(node);
                }
            }
        }

        /**
         * 查找要删除的节点
         * @param value 希望删除的节点的值
         * @return 如果找到返回该节点
         */
        public Node<T> search(T value) {
            if (value.equals(this.value)) {
                return this;
            } else if (value.compareTo(this.value) < 0) { // 小于当前节点
                if (this.left == null) {
                    return null;
                }
                return this.left.search(value);
            } else {
                if (this.right == null) {
                    return null;
                }
                return this.right.search(value);
            }
        }

        /**
         * 查找要删除的节点的父节点
         * @param value
         * @return
         */
        public Node<T> searchParent(T value) {
            // 如果当前节点就是要删除的节点的父节点
            if ((this.left != null && this.left.value.equals(value) ||
                    (this.right != null && this.right.value.equals(value)))) {
                return this;
            } else {
                // 如果查找的值小于当前节点的值,并且当前节点的左子节点不为空
                if (value.compareTo(this.value) < 0 && this.left != null) {
                    return this.left.searchParent(value);
                } else if (value.compareTo(this.value) >= 0 && this.right != null) {
                    return this.right.searchParent(value);
                } else {
                    return null;
                }
            }
        }

    }
}

class Test {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
//        tree.add(2);
//        tree.add(4);
//        tree.add(6);
//        tree.add(8);
//        tree.add(7);
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.add(2);
        tree.add(7);
        tree.delNode(10);
        tree.infixOrder();
    }
}
