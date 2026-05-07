package com.chg.hot100;

import java.util.*;

/**
 * 347. 前 K 个高频元素
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 */
public class TopKFrequent {
    // 先统计每个元素出现的次数，使用 hashMap存储，key 是元素的值，value是频次
    // 然后维护一个大小为 k 的小根堆，遇到比堆顶大的元素，则替换堆顶元素，遍历完后，堆里的 k 个元素就是频次前 k 高的元素

    /**
     * 使用小跟堆
     * @param nums
     * @param k
     * @return
     */
    // public int[] topKFrequent(int[] nums, int k) {
    //     int[] res = new int[k];
    //     Map<Integer, Integer> map = new HashMap<>();
    //     for (int i = 0; i < nums.length; i++) {
    //         map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
    //     }
    //     PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> Integer.compare(map.get(a), map.get(b)));
    //     for (int num : map.keySet()) {
    //         if (heap.size() < k) {
    //             heap.offer(num);
    //         } else {
    //             if (map.get(num) > map.get(heap.peek())) {
    //                 heap.poll();
    //                 heap.offer(num);
    //             }
    //         }
    //     }
    //     for (int i = 0; i < k; i++) {
    //         res[i] = heap.poll();
    //     }
    //     return res;
    // }

    /**
     * 桶排序
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        // 统计频次
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] buckets = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            if (buckets[value] == null) {
                buckets[value] = new ArrayList<>();
            }
            buckets[value].add(key);
        }
        for (int i = buckets.length - 1, index = 0; i >= 0 && index < k; i--) {
            if (buckets[i] != null) {
                for (int num : buckets[i]) {
                    res[index++] = num;
                }
            }
        }
        return res;
    }
}
