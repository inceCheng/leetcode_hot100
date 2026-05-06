package com.chg.hot100;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 84. 柱状图中最大的矩形
 */
public class LargestRectangleArea {
    // 使用单调栈，从左往右遍历，遇到比当前柱子高的就入栈，否则就结算当前柱子的面积
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        int[] newHeights = new int[heights.length + 2];
        System.arraycopy(heights, 0, newHeights, 1, heights.length);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < newHeights.length; i++) {
            while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]) {
                int currHeight = newHeights[stack.pop()];
                int left = stack.peek();
                int width = i - left - 1;
                res = Math.max(res, width * currHeight);
            }
            stack.push(i);
        }
        return res;
    }

}
