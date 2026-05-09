package com.chg.hot100;

/**
 * 300. 最长递增子序列
 */
public class LengthOfLIS {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(dp[i], res);
        }
        return res;
    }


    private void test(int n) {
        int[] dp = new int[n];
        for (int a : dp) {
            a = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
    }
}
