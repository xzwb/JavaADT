package leetcode;

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
}


public class Test6 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        System.out.println(reversePrint(listNode)[0]);
    }

    public static int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        int size = 0;
        ListNode listNode = head;
        while (listNode.next != null) {
            size++;
            listNode = listNode.next;
        }
        size++;
        int[] nums = new int[size];
        while (size > 0) {
            nums[--size] = head.val;
            head = head.next;
        }
        return nums;
    }
}
