package com.chg.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * 763. 划分字母区间
 * <p>
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。
 * <p>
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 * <p>
 * 返回一个表示每个字符串片段的长度的列表。
 */
public class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<>();
        if (s == null) {
            return res;
        }

        int[] pos = new int[26];
        // 记录每个字符最晚出现的位置
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            pos[c - 'a'] = i;
        }
        int end = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            end = Math.max(end, pos[c - 'a']);
            if (end == i) {
                res.add(i - left + 1);
                left = i + 1;
            }
        }
        return res;
    }
}
