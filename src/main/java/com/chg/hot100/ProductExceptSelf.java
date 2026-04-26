package com.chg.hot100;

public class ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        // 从左往后，记录左侧的乘积
        int[] ans = new int[nums.length];
        ans[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            ans[i] = nums[i - 1] * ans[i - 1];
        }
        // 从右往左，乘上右侧的乘积
        int t = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            ans[i] = t * ans[i];
            t = t * nums[i];
        }
        return ans;
    }
}
