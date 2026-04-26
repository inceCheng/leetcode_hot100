package com.chg.hot100;

public class MoveZeroes {
    // 将所有 0 移动到数组的末尾,且要保证非零元素的相对位置
    // 快慢指针
    public void moveZeroes(int[] nums) {
        if (nums.length <= 1) return;
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            // 不为零，则这个数需要进行交换，如果为零，则快指针直接过，慢指针不动
            if (nums[j] != 0) {
                nums[i++] = nums[j];
            }
        }
        while (i < nums.length) {
            nums[i++] = 0;
        }
    }
}
