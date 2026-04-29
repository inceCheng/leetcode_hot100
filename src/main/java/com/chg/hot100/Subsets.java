package com.chg.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 */
public class Subsets {
    // 存放所有最终结果的集合
    List<List<Integer>> res = new ArrayList<>();
    // path 记录当前的子集路径
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0);
        return res;
    }

    private void backtrack(int[] nums, int start) {
        // 每一次进入回溯，都要收集当前节点的结果，每次进入函数，都代表找到一个子集，包括空集
        // 必须new一个新的ArrayList,进行深拷贝
        res.add(new ArrayList<>(path));
        // 从start开始遍历，保证不回头，避免产生重复子集
        for (int i = start; i < nums.length; i++) {
            // 前进，做选择
            path.add(nums[i]);
            // 进入下一层决策树，注意下一层是从i+1开始的，不能重复选当前数字
            backtrack(nums, i + 1);
            // 撤销 回溯
            // 将最后加进去的数字弹出来，退回到上一个状态，去尝试别的分支
            path.remove(path.size() - 1);
        }
    }
}
