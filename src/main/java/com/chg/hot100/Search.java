package com.chg.hot100;

/**
 * 33. 搜索旋转排序数组
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 向左旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 下标 3 上向左旋转后可能变为 [4,5,6,7,0,1,2] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * <p>
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 */
public class Search {
    // 二分查找，因为数组是旋转过的，所以每一次取中点的时候，左右两边一定是有一边是有序的，确定哪边有序，然后在有序的那一边可以通过下标获得最大最小值，
    // 最大最小值和 target判断，是否在有序区间内，如果在，则在有序区间内寻找，否则在另一半区间寻找
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2; // 这里的除法是向下取证的，所以有可能会出现 l == r 的情况，所以下面的判断，靠考虑等号
            if (nums[mid] == target) {
                return mid;
            }
            // 这里的等号是必须的，因为可能左边只有一个或两个元素
            if (nums[l] <= nums[mid]) {
                // 左边有序
                if (target >= nums[l] && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}
