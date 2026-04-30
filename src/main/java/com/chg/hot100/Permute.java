package com.chg.hot100;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列
 */
public class Permute {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];
        backtracking(nums, new ArrayList<>(), used);
        return res;
    }

    private void backtracking(int[] nums, List<Integer> path, boolean[] used) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            backtracking(nums, path, used);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}