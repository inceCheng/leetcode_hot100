package com.chg.hot100;

/**
 * 72. 编辑距离
 */
public class MinDistance {
    // d[i][j]表示将word1的前i位转换为word2的前j位所需要的最少操作数
    // 边界条件
    // dp[0][j] = j：word1 为空，需要插入 j 次
    // dp[i][0] = i：word2 为空，需要删除 i 次
    // 当如果第i位和第j位相同，那么dp[i][j] = dp[i-1][j-1]
    // 否则，dp[i][j]就是三种操作中，选取最小值再加1
    //  dp[i][j] = 1 + min(
    //       dp[i-1][j-1],  // 替换：把 word1[i-1] 替换成 word2[j-1]
    //       dp[i-1][j],    // 删除：删掉 word1[i-1]
    //       dp[i][j-1]     // 插入：在 word1[i] 后插入 word2[j-1]
    //   )
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < n + 1; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int insert = dp[i][j - 1];
                    int delete = dp[i - 1][j];
                    int replace = dp[i - 1][j - 1];
                    dp[i][j] = Math.min(insert, Math.min(delete, replace)) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
