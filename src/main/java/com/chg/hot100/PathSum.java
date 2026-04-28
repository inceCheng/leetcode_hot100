package com.chg.hot100;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 437. 路径总和 III
 */
public class PathSum {
    // 前缀和 + DFS 回溯
    private int res = 0;
    private Map<Long, Integer> map = new HashMap<>();

    public int pathSum(TreeNode root, int targetSum) {
        map.put(0L, 1);
        dfs(root, 0L, targetSum);
        return res;
    }

    private void dfs(TreeNode root, long currSum, int targetSum) {
        if (root == null) {
            return;
        }
        currSum += root.val;
        // 将 currSum 加入到 map中，也就是加入当前节点的前缀和
        res += map.getOrDefault(currSum - targetSum, 0);
        map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        dfs(root.left, currSum, targetSum);
        dfs(root.right, currSum, targetSum);
        // 回溯，递归完左右子树后，必须将map中的currSum撤销
        map.put(currSum, map.getOrDefault(currSum, 0) - 1);
    }
}
