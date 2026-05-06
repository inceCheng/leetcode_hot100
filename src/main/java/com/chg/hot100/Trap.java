package com.chg.hot100;

/**
 * 42. 接雨水
 */
public class Trap {
    // 双指针，left,right，向中间移动
    // 若 leftMax<rightMax，说明当前位置 left的短板一定在左侧，可以计算 left 位置可以接到的雨水数量，然后 left 右移
    // 若 leftMax>=rightMax，说明当前位置 right的短板一定在右侧，可以计算 right 位置可以接到的雨水数量，然后 right 左移
    public int trap(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int leftMax = Integer.MIN_VALUE;
        int rightMax = Integer.MIN_VALUE;
        int res = 0;
        while (left <= right) {
            leftMax = Math.max(height[left], leftMax);
            rightMax = Math.max(height[right], rightMax);
            if (leftMax < rightMax) {
                res += Math.max((leftMax - height[left]), 0);
                left++;
            } else {
                res += Math.max((rightMax - height[right]), 0);
                right--;
            }
        }
        return res;
    }
}
