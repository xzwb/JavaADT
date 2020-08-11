package suanfa.tree.bst;


import java.util.Objects;

/**
 * bst的中序遍历是单调递增的
 *
 * @param <T>
 */
public class BSTTree<T extends Comparable<T>> {

    private Node<T> root;

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
                    if (targetNode.equals(parent.left)) {
                        parent.left = targetNode.left;
                    } else {
                        parent.right = targetNode.left;
                    }
                } else { //要删除的节点有右子节点
                    if (targetNode.equals(parent.left)) {
                        parent.left = targetNode.right;
                    } else {
                        parent.right = targetNode.right;
                    }
                }
            }
        }
    }

    public void add(T value) {
        Node<T> node = new Node<>();
        node.value = value;
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空不能遍历");
        }
    }


    private static final class Node<T extends Comparable<T>> {
        T value;
        Node<T> left;
        Node<T> right;

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

        // 中序遍历
        public void infixOrder() {
            if (this.left != null) {
                this.left.infixOrder();
            }
            System.out.println(this);
            if (this.right != null) {
                this.right.infixOrder();
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value);
        }

    }
}


/**
 * 7 3 10 12 5 1 9
 *
 * 1. 删除叶子节点
 * 2. 删除只有一颗子树的节点
 * 3. 删除有两颗子树的节点
 *
 * 共同的:
 * 1. 找到要删除的节点
 * 2. 找到要删除节点的父节点
 * -----------------------------
 * 1.3确定target是parent的左子节点还是右子节点
 * 左: parent.left = null;
 * 右: parent.right = null;
 * ----------------------------
 * 2.3 如果target有左子节点:
 *     2.3.1 如果target是parent的左子节点:parent.left = target.left;
 *     2.3.2 如果target是parent的右子节点:parent.right = target.left;
 * 2.4 如果target有右子节点:
 *     2.3.1 如果target是parent的左子节点:parent.left = target.right;
 *     2.3.2 如果target是parent的右子节点:parent.right = target.right;
 * ------------------------------
 * 3.3 找到target节点右子树的最小节点(minNode)
 * 3.4 target.value = minNode.value;
 * 3.5 删除minNode
 * 或者
 * 4.3 找到target左子树的最大节点(maxNode)
 * 4.4 target.value = maxNode.maxNode
 * 4.5 删除maxNode
 *
 */
class Test {
    public static void main(String[] args) {
        BSTTree<Integer> tree = new BSTTree<>();
        tree.add(7);
        tree.add(3);
        tree.add(10);
        tree.add(12);
        tree.add(5);
        tree.add(1);
        tree.add(9);
        tree.add(2);
        tree.infixOrder();
        System.out.println("----------------------------------------");
//        // 测试删除叶子节点
//        tree.delNode(2);
//        tree.infixOrder();
//        tree.delNode(1);
//        tree.infixOrder();
        tree.delNode(7);
        tree.infixOrder();

    }
}
