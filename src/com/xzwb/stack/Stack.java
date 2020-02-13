package com.xzwb.stack;


public class Stack<T> {
    private Stack.Node<T> head;
    private Stack.Node<T> last;
    private int size;

    public int getSize() {
        return size;
    }

    public Stack() {
        head = null;
        last = null;
        size = 0;
    }

    public T pull() {
        T result;
        if (size == 0) {
            return null;
        } else if (size == 1) {
            result = last.ele;
            last = head = null;
        } else {
            result = last.ele;
            last = last.prev;
            last.next = null;
        }
        size--;
        return result;
    }

    public void push(T ele) {
        if (size == 0) {
            head = new Stack.Node(ele, null);
            last = head;
        } else {
            Stack.Node<T> node = new Stack.Node(ele, last);
            last = node;
        }
        size++;
    }


    private static class Node<T> {
        T ele;
        Stack.Node<T> next;
        Stack.Node<T> prev;

        Node(T ele, Stack.Node<T> prev) {
            this.ele = ele;
            next = null;
            this.prev = prev;
        }
    }
}
