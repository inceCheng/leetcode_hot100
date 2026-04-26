package com.chg.hot100;

public class SwapPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        while (pre.next != null && pre.next.next != null) {
            ListNode a = pre.next;
            ListNode b = pre.next.next;
            ListNode nex = b.next;
            pre.next = b;
            b.next = a;
            a.next = nex;
            pre = a;
        }
        return dummy.next;
    }
}
