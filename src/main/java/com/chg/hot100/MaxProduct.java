package com.chg.hot100;

/**
 * 155. 乘积最大子数组
 * <p>
 * 测试用例的答案是一个 32-位 整数。说明数组元素中包含负数，需要考虑负数乘以负数，结果是正数的情况
 */
public class MaxProduct {
    public int maxProduct(int[] nums) {
        int res = nums[0];
        int preMax = nums[0];
        int preMin = nums[0];
        // 要考虑到最小值，因为最小值可能是负数，如果遇到num[i]也是负数，那么可能符号改变变成最大值
        for (int i = 1; i < nums.length; i++) {
            int currMax = preMax;
            // 这里就会对 preMax更新，下面求最小值不能再使用这个变量了，否则求出来是错误的答案
            preMax = Math.max(nums[i], Math.max(preMax * nums[i], preMin * nums[i]));
            // 这里计算最小值，需要用到之前的最大值，而不是上面更新后的最大值，这是一个易错点
            preMin = Math.min(nums[i], Math.min(currMax * nums[i], preMin * nums[i]));
            res = Math.max(preMax, res);
        }
        return res;
    }
}