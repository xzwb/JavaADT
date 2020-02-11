package com.xzwb.list;

import java.util.LinkedList;

public class List<T> {
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

    private  static class Node<T> {
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
