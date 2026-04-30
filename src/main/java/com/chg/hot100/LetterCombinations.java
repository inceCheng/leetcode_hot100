package com.chg.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的数字组合
 */
public class LetterCombinations {
    List<String> res = new ArrayList<>();
    StringBuilder path = new StringBuilder();

    private final String[] map = {
            "", // 0
            "", // 1
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl", // 5
            "mno", // 6
            "pqrs", // 7
            "tuv", // 8
            "wxyz" // 9
    };

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return res;
        }
        backtrack(digits, 0);
        return res;
    }

    // index 表示当前正在处理 digits 字符串中的第几个数字,所以在外层维护
    private void backtrack(String digits, int index) {
        // 如果已经到组后一位数字了，则将当前的这种情况，添加到结果集合
        if (index == digits.length()) {
            res.add(path.toString());
            // 添加完结果后，应该结束这个递归，否则会继续向下获取 digits的下一个字符，造成越界
            return;
        }
        // 这里减 0 ，代表相对于 0 的偏移量，因为输入是数字
        int digit = digits.charAt(index) - '0';
        String letters = map[digit];
        // 当前数字的循环
        for (int i = 0; i < letters.length(); i++) {
            path.append(letters.charAt(i));
            backtrack(digits, index + 1);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
