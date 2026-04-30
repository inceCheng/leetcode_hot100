package com.chg.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的数字组合
 */
public class LetterCombinations {
    String[] letterMap = {
            "", // 0
            "", // 1
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl",// 5
            "mno",// 6
            "pqrs", // 7
            "tuv", // 8
            "wxyz" // 9
    };

    List<String> res = new ArrayList<>();
    StringBuilder path = new StringBuilder();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return res;
        }
        backtrack(digits, 0);
        return res;
    }

    // index 表示当前正在处理 digits 字符串中的第几个数字
    private void backtrack(String digits, int index) {
        // 每一轮都加一个，加到长度为digits长度为止，就把结果添加到结果集中
        if (index == digits.length()) {
            res.add(path.toString());
            return;
        }
        int digit = digits.charAt(index) - '0';
        String letters = letterMap[digit];

        for (int i = 0; i < letters.length(); i++) {
            path.append(letters.charAt(i));
            backtrack(digits, index + 1);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
