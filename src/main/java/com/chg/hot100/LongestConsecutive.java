package com.chg.hot100;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>(nums.length);
        for (int num : nums) {
            set.add(num);
        }
        int max = 0;
        for (int n : set) {
            // 如果不是起点，则会直接跳过，进入下一个数，所以 set 中的数，在这里只会遍历一遍
            if (!set.contains(n - 1)) {
                // 是起点，则直接向后判断
                int cur = n;
                int len = 1;
                while (set.contains(cur + 1)) {
                    cur++;
                    len++;
                }
                if (len > max) {
                    max = len;
                }
            }
        }
        return max;
    }
}
