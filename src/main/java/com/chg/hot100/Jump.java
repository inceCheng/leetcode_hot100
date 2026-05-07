package com.chg.hot100;

/**
 * 45. 跳跃游戏 II
 */
public class Jump {
    // 维护 3 个变量
    // end: 当前这一步跳跃的边界
    // farthest: 在当前边界内，下一步最远能到哪
    // step: 当前已经跳跃了几次
    public int jump(int[] nums) {
        int step = 0;
        int end = 0;
        int farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // 当前落在 i ,下一步最远能到哪
            farthest = Math.max(farthest, i + nums[i]);
            // 如果到了这一步的边界，则步数+1且边界扩张
            if (i == end) {
                step++;
                end = farthest;
            }
        }
        return step;
    }
}
