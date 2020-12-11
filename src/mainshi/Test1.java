package mainshi;


import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;

        while (head != null) {
            ListNode tail = pre;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] reserve = myReverse(head, tail);
            head = reserve[0];
            tail = reserve[1];
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }

        return hair.next;
    }

    public static ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        return new ListNode[]{tail, head};
    }

    public static ListNode reverseList(ListNode head) {
        Deque<ListNode> deque = new LinkedList<>();
        if (head == null) {
            return null;
        }
        while (head != null) {
            deque.push(head);
            head = head.next;
        }
        head = deque.pop();
        ListNode p1 = head;
        while (deque.size() > 0) {
            ListNode p = deque.pop();
            p1.next = p;
            p1 = p;
        }
        p1.next = null;
        return head;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        ListNode node1 = new ListNode(2);
        node.next = node1;
        ListNode node2 = new ListNode(3);
        node1.next = node2;
        ListNode node3 = new ListNode(4);
        node2.next = node3;
        ListNode node4 = new ListNode(5);
        node3.next = node4;
        ListNode p = reverseList(node);
        while (p != null) {
            System.out.println(p.val);
            p = p.next;
        }
    }
}