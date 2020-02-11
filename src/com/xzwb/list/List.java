package com.xzwb.list;

public class List<T extends Comparable> {
    private List.Node<T> head;
    private List.Node<T> last;
    private int size;

    public List() {
        this.size = 0;
        this.head = null;
        this.last = null;
    }

    /**
     * 头插法
     * @param ele
     */
    public void addFirst(T ele) {
        List.Node<T> h = this.head;
        List.Node<T> newNode = new List.Node(null, ele, h);
        this.head = newNode;
        if (h == null) {
            this.last = newNode;
        } else {
            h.prev = newNode;
        }
        ++size;
    }

    /**
     * 尾插法
     * @param ele
     */
    public void addLast(T ele) {
        List.Node<T> l = this.last;
        List.Node<T> newNode = new List.Node(l, ele, null);
        this.last = newNode;
        if (l == null) {
            this.head = newNode;
        } else {
            l.next = newNode;
        }
        ++size;
    }

    private void addBefore(T ele, List.Node<T> node) {
        List.Node<T> prev = node.prev;
        List.Node<T> newNode = new List.Node(prev, ele, node);
        node.prev = newNode;
        if (prev == null) {
            this.head = newNode;
        } else {
            prev.next = newNode;
        }

        ++size;
    }

    public void add(T ele, int index) {
        if (index > size) {
            addLast(ele);
        } else if (index <= 0) {
            addFirst(ele);
        } else {
            List.Node<T> node = getNode(index);
            addBefore(ele, node);
        }
    }

    private List.Node<T> getNode(int index) {
        List.Node<T> h = this.head;
        for (int i = 0; i < index-1; i++) {
            h = h.next;
        }
        return h;
    }

    public int getSize() {
        return size;
    }

    public T get(int index) {
        List.Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.ele;
    }

    public int get(T ele) {
        List.Node<T> node = head;
        for (int i = 0; i < size; i++) {
            if (node.ele.equals(ele)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("List = {");
        List.Node<T> h = head;
        for (int i = 0; i < size-1; i++) {
            stringBuilder.append(h.ele + ", ");
            h = h.next;
        }
        stringBuilder.append(h.ele + "}");
        return stringBuilder.toString();
    }

    /**
     * 删除对应元素
     * @param ele
     */
    public void remove(T ele) {
        List.Node<T> node = this.head;
        while (node != null) {
            if (node.ele.equals(ele)) {
                if (size < 2) {
                  removeHead();
                } else if (node.next == null) {
                    removeLast();
                } else if (node.prev == null) {
                    removeHead();
                } else {
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                }
            }
            node = node.next;
        }
        size--;
    }

    /**
     * 找到最大值
     * @return
     */
    public T selectMax() {
        List.Node<T> maxNode = this.head;
        List.Node<T> node = this.head;
        for (int i = 0; i < size-1; i++) {
            if (maxNode.ele.compareTo(node.ele) <= 0) {
                maxNode = node;
            }
            node = node.next;
        }
        if (maxNode.ele.compareTo(node.ele) <= 0) {
            maxNode = node;
        }
        return maxNode.ele;
    }

    /**
     * 删除头元素
     */
    private void removeHead() {
        if (size < 2) {
            this.head = this.last = null;
        } else {
            List.Node<T> node = this.head;
            node.next.prev = null;
            this.head = node.next;
        }
        size--;
    }

    /**
     * 删除末尾元素
     */
    private void removeLast() {
        if (size < 2) {
            this.head = this.last = null;
        } else {
            List.Node<T> node = this.last;
            node.prev.next = null;
            this.last = node.prev;
        }
        size--;
    }

    private  static class Node<T extends Comparable> {
        T ele;
        List.Node<T> next;
        List.Node<T> prev;

        Node(List.Node<T> prev, T ele, List.Node<T> next) {
            this.ele = ele;
            this.next = next;
            this.prev = prev;
        }
    }
}
