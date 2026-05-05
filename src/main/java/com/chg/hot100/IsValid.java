package com.chg.hot100;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 20. 有效的括号
 */
public class IsValid {
    // 遇到左括号，则将对应的右括号压入栈内，遇到右括号，则与栈顶元素匹配
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else {
                if (stack.isEmpty() || c != stack.pop()) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
