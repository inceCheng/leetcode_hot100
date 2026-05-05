package com.chg.hot100;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 394. 字符串解码
 * <p>
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * <p>
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * <p>
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * <p>
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 * 测试用例保证输出的长度不会超过 105。
 */
public class DecodeString {
    public String decodeString(String s) {
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<StringBuilder> strStack = new ArrayDeque<>();
        int currNum = 0;
        StringBuilder currStr = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                currNum = currNum * 10 + (c - '0');
            } else if (c == '[') {
                // 保存当前状态
                numStack.push(currNum);
                strStack.push(currStr);
                currStr= new StringBuilder();
                currNum = 0;
            } else if (c == ']') {
                // 弹出栈，取出上一层的状态进行合并
                StringBuilder preStr = strStack.pop();
                int repeatTimes = numStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    // 把当前的字符串追加到上一层字符串尾部
                    preStr.append(currStr);
                }
                currStr = preStr;
            } else {
                // 是普通字符，追加到当前字符串中
                currStr.append(c);
            }
        }
        return currStr.toString();
    }
}
