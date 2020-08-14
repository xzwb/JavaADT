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
 * 双旋转的第一种情况----右左(a b c)
 *     1. b右旋
 *     2. a左旋
 */
public class AVLTree<T extends Comparable<T>> {

    private Node<T> root;

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
       return null;

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


    }
}

class Test {
    public static void main(String[] args) {

    }
}
