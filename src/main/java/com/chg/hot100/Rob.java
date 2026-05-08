package com.chg.hot100;

/**
 * 198. 打家劫舍
 */
public class Rob {
    // 写出状态转移方程，dp[i]表示考虑到前面 i 间房的最高金额
    // dp[i] = max(dp[i-2] + nums[i], dp[i-1])
    // 可以考虑优化空间状态转移方程，只需要保存两个变量
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int prev2 = nums[0];
        int prev1 = Math.max(prev2, nums[1]);
        int res = Math.max(prev2, prev1);
        for (int i = 2; i < n; i++) {
            res = Math.max(prev2 + nums[i], prev1);
            prev2 = prev1;
            prev1 = res;
        }
        return res;
    }
}
