package com.chg.hot100;

public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int pre = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //通过这行三元运算符，你精确地抓住了核心：
            // 判断的依据变成了“历史累加和 pre 是否成为了负债”。如果是负债（< 0），就果断丢弃，从当前的 n 重新起步；
            // 如果还是正资产（>= 0），就继续累加。
            pre = pre < 0 ? nums[i] : pre + nums[i];
            max = Math.max(pre, max);
        }
        return max;
    }
}
