package com.chg.hot100;

public class MaxArea {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int max = Math.min(height[l], height[r]) * (r - l);
        while (l < r) {
            if (height[l] < height[r]) {
                int temp = height[l];
                // 移动过程中，跳过小于等于之前大小的
                while (temp >= height[l] && l < r) {
                    l++;
                }
            } else {
                int temp = height[r];
                // 移动过程中，跳过小于等于之前大小的
                while (temp >= height[r] && l < r) {
                    r--;
                }
            }
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
        }
        return max;
    }
}
