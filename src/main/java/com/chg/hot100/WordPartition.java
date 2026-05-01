package com.chg.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. 分割回文串
 */
public class WordPartition {
    // 存放所有符合条件的最终结果
    List<List<String>> res = new ArrayList<>();
    // 存放当前正在探索的切割路径
    List<String> path = new ArrayList<>();

    public List<List<String>> partition(String s) {
        dfs(s, 0);
        return res;
    }

    private void dfs(String s, int startIndex) {
        // 终止条件，起始位置已经达到字符串末尾，说明找到了一组完整的分割方案
        if (startIndex >= s.length()) {
            // 放入 path 的拷贝
            res.add(new ArrayList<>(path));
            return;
        }
        // 单层搜索逻辑，尝试截取 s[startIndex...i]
        for (int i = startIndex; i < s.length(); i++) {
            // 如果截取的是回文串
            if (isPalindrome(s, startIndex, i)) {
                // 获取回文串并且加入路径
                String str = s.substring(startIndex, i + 1);
                path.add(str);
            } else {
                // 不是回文串，直接跳过
                continue;
            }
            // 递归探索剩余的字符串 S[i+1,...末尾]
            dfs(s, i + 1);
            path.remove(path.size() - 1);
        }
    }


    // 判断是否是回文串
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
