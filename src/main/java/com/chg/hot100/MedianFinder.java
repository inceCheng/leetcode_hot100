package com.chg.hot100;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 295. 数据流的中位数
 * <p>
 * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
 * <p>
 * 例如 arr = [2,3,4] 的中位数是 3 。
 * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
 * 实现 MedianFinder 类:
 * <p>
 * MedianFinder() 初始化 MedianFinder 对象。
 * <p>
 * void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
 * <p>
 * double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。
 */
public class MedianFinder {
    // 大根堆，存放左边小的元素
    private PriorityQueue<Integer> small = null;
    // 小跟堆，存放右边大的元素
    private PriorityQueue<Integer> large = null;

    public MedianFinder() {
        small = new PriorityQueue<>(Collections.reverseOrder());
        large = new PriorityQueue<>();
    }

    // 先入左边的大根堆，然后大根堆堆顶统一入右边小跟堆
    // 如果右边堆与左边堆的大小相差大于 1，则将右边小跟堆的堆顶放入左边大根堆，平衡两个堆的大小
    public void addNum(int num) {
        small.offer(num);
        large.offer(small.poll());
        if (large.size() > small.size() + 1) {
            small.offer(large.poll());
        }
    }

    public double findMedian() {
        if (large.size() > small.size()) {
            return large.peek();
        }
        return ((double) small.peek() + large.peek()) / 2.0;
    }

    // 手动判断应该进入哪个堆
    // public void addNum(int num) {
    //
    //     if (small.isEmpty() || num <= small.peek()) {
    //         small.offer(num);
    //     } else {
    //         large.offer(num);
    //     }
    //     if (small.size() > large.size() + 1) {
    //         large.offer(small.poll());
    //     } else if (large.size() > small.size()) {
    //         small.offer(large.poll());
    //     }
    // }
    //
    // public double findMedian() {
    //     if (small.size() > large.size()) {
    //         return small.peek();
    //     }
    //     return ((double) small.peek() + large.peek()) / 2.0;
    // }
}
