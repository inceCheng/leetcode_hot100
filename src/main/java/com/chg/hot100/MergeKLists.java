package com.chg.hot100;

import java.util.PriorityQueue;

/**
 * 23. 合并 K 个升序链表
 */
public class MergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        // 先对列表判空
        if (lists == null || lists.length == 0) {
            return null;
        }
        // 创建小跟堆
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
        // 每个链表的头结点入堆
        for (ListNode head : lists) {
            if (head != null) {
                heap.offer(head);
            }
        }
        // 创建虚拟头结点，方便操作合并后的结果链表
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while (!heap.isEmpty()) {
            ListNode minNode = heap.poll();
            curr.next = minNode;
            curr = curr.next;
            // 该节点后续还有节点，则将后续节点入堆
            if (minNode.next != null) {
                heap.offer(minNode.next);
            }
        }
        return dummy.next;
    }
}
