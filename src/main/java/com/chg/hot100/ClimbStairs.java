package com.chg.hot100;

/**
 * 70. 爬楼梯
 */
public class ClimbStairs {

    // 动态规划，使用数组实现
    // public int climbStairs(int n) {
    //     if (n == 0 || n == 1) {
    //         return 1;
    //     }
    //     int[] dp = new int[n + 1];
    //     dp[0] = 1;
    //     dp[1] = 1;
    //     for (int i = 2; i <= n; i++) {
    //         dp[i] = dp[i - 1] + dp[i - 2];
    //     }
    //     return dp[n];
    // }

    // 动态规划，空间优化版
    public int climbStairs(int n) {
        int l = 1;
        int r = 1;
        int res = 1;
        for (int i = 2; i <= n; i++) {
            res = l + r;
            l = r;
            r = res;
        }
        return res;
    }
}
