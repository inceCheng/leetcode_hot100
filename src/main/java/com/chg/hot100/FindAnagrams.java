package com.chg.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        // 剪枝
        if (s.length() < p.length()) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        // 定义两个数组，分别记录 s 和 p 中，对应字符的数量
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        // 初始化两个数组
        for (int i = 0; i < p.length(); i++) {
            // 记录前 p.length()位，s字符串的情况
            sCount[s.charAt(i) - 'a']++;
            // 记录 p 字符串的情况
            pCount[p.charAt(i) - 'a']++;
        }
        // 对比两个字符串是否相等
        if (Arrays.equals(sCount, pCount)) {
            res.add(0);
        }
        // 遍历 s 字符串，每一次判断相同滑动窗口内，字符串是否相等
        for (int i = 0; i < s.length() - p.length(); i++) {
            sCount[s.charAt(i + p.length()) - 'a']++;
            sCount[s.charAt(i) - 'a']--;
            if (Arrays.equals(sCount, pCount)) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
