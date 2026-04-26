package com.chg.hot100;

public class Rotate {
    /*
    三次反转法，举个例子 nums = [1,2,3,4,5,6,7], k = 3
    反转整个数组[7,6,5,4,3,2,1]
    反转前 k 各元素[5,6,7,5,4,3,2,1]
    反转剩余的 n-k 个元素[5,6,7,1,2,3,4,5]
    这种解法不需要额外的空间
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        if (k == 0) return;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    // public void rotate(int[] nums, int k) {
    //     int n = nums.length;
    //     k = k % n;
    //     if (n <= 1 || k == 0) return;
    //     int[] temp = new int[k];
    //     for (int i = 0; i < k; i++) {
    //         temp[i] = nums[n - k + i];
    //     }
    //     for (int i = n - 1; i >= k; i--) {
    //         nums[i] = nums[i - k];
    //     }
    //     for (int i = 0; i < k; i++) {
    //         nums[i] = temp[i];
    //     }
    // }
}
