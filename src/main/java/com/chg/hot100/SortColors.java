package com.chg.hot100;

/**
 * 75. 颜色分类
 */
public class SortColors {
    // 使用三指针，分别是 left, i , right
    // left 表示下一个 0 存放的位置，从数组最左侧开始走
    // i 表示当前正在扫描的位置 从左往右遍历，
    // right 表示下一个 2 存放的位置，从数组最右侧开始往左走
    public void sortColors(int[] nums) {
        int i = 0;
        int left = 0;
        int right = nums.length - 1;
        while (i <= right) {
            if (nums[i] == 0) {
                swap(nums, i, left);
                i++;
                left++;
            } else if (nums[i] == 2) {
                swap(nums, i, right);
                right--;
            } else {
                i++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
