package com.chg.hot100;

/**
 * 121. 买卖股票的最佳时机
 * <p>
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * <p>
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */
public class MaxProfit {

    // 记录历史最低价
    // 每一天都判断当前天卖出是否是最高价，如果是，则更新结果，如果不是，则继续循环
    // public int maxProfit(int[] prices) {
    //     int min = prices[0];
    //     int res = 0;
    //     for (int price : prices) {
    //         if (price < min) {
    //             min = price;
    //         } else if (price - min > res) {
    //             res = price - min;
    //         }
    //     }
    //     return res;
    // }

    /**
     * 122. 买卖股票的最佳时机 II
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int res = 0;
        if (prices == null || prices.length <= 1) {
            return res;
        }
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i - 1] > 0) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }
}
