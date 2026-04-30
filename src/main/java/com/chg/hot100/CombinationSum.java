package com.chg.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. 组合总和
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(res, new ArrayList<>(), candidates, target, 0);
        return res;

    }

    private void backtrack(List<List<Integer>> res, List<Integer> tempList, int[] candidates, int remain, int start) {
        if (remain == 0) {
            res.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > remain) {
                break; // 剪枝
            }
            tempList.add(candidates[i]);
            backtrack(res, tempList, candidates, remain - candidates[i], i);
            // 移除最后一个加入的数字
            tempList.remove(tempList.size() - 1);
        }
    }
}
