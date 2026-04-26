package com.chg.hot100;

import java.util.HashMap;
import java.util.Map;

/**
 * 138. 随机链表的复制
 * <a href="https://leetcode.cn/problems/copy-list-with-random-pointer/?envType=study-plan-v2&envId=top-100-liked">链接：随机链表的复制</a>
 */
public class CopyRandomList {

    // 遍历两遍链表
    // 第一遍复制节点，并且使用 hashMap 存储新的节点，做旧节点与新节点之间的一一映射
    // 第二遍复制节点之间的指针，
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node curr = head;
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }
        curr = head;
        while (curr != null) {
            Node node = map.get(curr);
            // 深拷贝，所以新节点的 next 也是指向新的节点
            node.next = map.get(curr.next);
            node.random = map.get(curr.random);
            curr = curr.next;
        }
        return map.get(head);
    }


    // 使用原地穿插复制，这样时间复杂度还是 O(n)，但是空间复杂度是O(1)
    // 先给每个节点之间插入复制节点
    // 然后再拷贝他们的random指针
    // public Node copyRandomList(Node head) {
    //     if (head == null) {
    //         return null;
    //     }
    //     Node curr = head;
    //     // 插入新节点
    //     while (curr != null) {
    //         Node temp = new Node(curr.val);
    //         temp.next = curr.next;
    //         curr.next = temp;
    //         curr = temp.next;
    //     }
    //
    //     curr = head;
    //     while (curr != null) {
    //         Node copyNode = curr.next;
    //         if (curr.random != null) {
    //             copyNode.random = curr.random.next;
    //         }
    //         curr = copyNode.next;
    //     }
    //     curr = head;
    //     Node copyHead = head.next;
    //     while (curr != null) {
    //         Node copyNode = curr.next;
    //         curr.next = copyNode.next;
    //         if (copyNode.next != null) {
    //             copyNode.next = copyNode.next.next;
    //         }
    //         curr = curr.next;
    //     }
    //     return copyHead;
    // }
}


















