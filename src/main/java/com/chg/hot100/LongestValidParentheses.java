package com.chg.hot100;

/**
 * 32. 最长有效括号
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n <= 1) {
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = 0;
        int res = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '(') {
                continue;
            }

            if (s.charAt(i - 1) == '(') {
                if (i - 2 >= 0) {
                    dp[i] = dp[i - 2] + 2;
                } else {
                    dp[i] = 2;
                }
            } else {
                if (i - 1 - dp[i - 1] >= 0 && s.charAt(i - 1 - dp[i - 1]) == '(') {
                    if (i - 2 - dp[i - 1] >= 0) {
                        dp[i] = dp[i - 1] + 2 + dp[i - 2 - dp[i - 1]];
                    } else {
                        dp[i] = dp[i - 1] + 2;
                    }
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
