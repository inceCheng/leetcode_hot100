package com.chg.hot100;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 */
public class LengthOfLongestSubstring {
    // 使用一个 Set 实现对滑动窗口的去重，如果头部和尾部重合了，则去掉尾部，头部向前移动
    // 滑动窗口维护 i，j
    // 维护一个窗口最大值
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int i = 0, j = 0, max = 0;
        while (j < chars.length) {
            if (set.contains(chars[j])) {
                set.remove(chars[i]);
                i++;
            } else {
                set.add(chars[j]);
                j++;
                max = Math.max(max, j - i);
            }
        }
        return max;
    }
}
