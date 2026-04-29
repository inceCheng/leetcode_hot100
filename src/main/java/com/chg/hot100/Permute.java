package com.chg.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列
 */
public class Permute {
    // 存放所有最终结果的集合
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        boolean[] uesd = new boolean[nums.length];
        backtrack(nums, new ArrayList<>(), uesd);
        return res;
    }

    private void backtrack(int[] nums, List<Integer> path, boolean[] used) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;

            backtrack(nums, path, used);

            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
