package com.chg.hot100;

/**
 * 55. 跳跃游戏
 */
public class CanJump {
    public boolean canJump(int[] nums) {
        // 维护一个“最远可到到的位置”,表示当前格子下，可以跳到的最远距离。任何时候，只要最远距离大于等于数组最后一个下标，说明可达，直接返回 true
        // 如果当前的格子超过了“最远可到位置”，说明不可能到达现在这个格子，后续也不可能再到达，直接返回 false
        int pos = 0;
        int target = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (i > pos) {
                return false;
            }
            pos = Math.max(i + nums[i], pos);
            if (pos >= target) {
                return true;
            }
        }
        return false;
    }
}
