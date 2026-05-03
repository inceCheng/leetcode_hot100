package com.chg.hot100;

public class SearchInsert {
    public int searchInsert(int[] nums, int target) {
        return dfs(nums, target, 0, nums.length - 1);
    }

    private int dfs(int[] nums, int target, int left, int right) {
        if (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                return dfs(nums, target, mid + 1, right);
            } else {
                return dfs(nums, target, left, mid - 1);
            }
        }
        return left;
    }
}
