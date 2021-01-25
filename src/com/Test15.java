package com;

import java.util.Deque;
import java.util.LinkedList;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}


public class Test15 {
    public  ListNode reverseList(ListNode head) {
        Deque<ListNode> deque = new LinkedList<>();
        if (head == null) {
            return null;
        }
        while (head != null) {
            deque.push(head);
            head = head.next;
        }
        head = deque.pop();
        ListNode node = head;
        while (!deque.isEmpty()) {
            node.next = deque.pop();
            node = node.next;
        }
        node.next = null;
        return head;
    }

    public  ListNode reverseList1(ListNode head) {
        ListNode node1 = head;
        ListNode node2 = head;
        if (head == null) {
            return null;
        }
        node1 = head.next;
        head.next = null;
        head = node1;
        while (head != null) {
            node1 = head.next;
            head.next = node2;
            node2 = head;
            head = node1;
        }
        return node2;
    }
}
