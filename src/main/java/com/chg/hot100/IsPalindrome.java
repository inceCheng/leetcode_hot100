package com.chg.hot100;

public class IsPalindrome {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        // 快慢指针
        // 对后半部分链表进行反转
        // 反转后对两部分链表比较即可
        ListNode low = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            low = low.next;
            fast = fast.next.next;
        }

        ListNode half = reverse(low);
        low = head;
        fast = half;
        while (fast != null) {
            if (low.val != fast.val) {
                return false;
            }
            low = low.next;
            fast = fast.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nex = curr.next;
            curr.next = pre;
            pre = curr;
            curr = nex;
        }
        return pre;
    }
}
