package com;

public class Test18 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode node = head;
        for (int i = 0; i < k; i++) {
            node = node.next;
        }
        while (node != null) {
            head = head.next;
            node = node.next;
        }
        return head;
    }
}
