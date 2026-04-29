package com.chg.hot100;

/**
 * 124. 二叉树中的最大路径和
 */
public class MaxPathSum {
    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        // 空节点 路径和为 0
        if (root == null) {
            return 0;
        }
        // 遍历左子树，获取左子树的最大路径和
        int left = dfs(root.left);
        // 如果左子树的路径和小于 0，那么就取路径和为 0
        int leftGain = Math.max(left, 0);
        // 遍历右子树，获取右子树最大路径和
        int right = dfs(root.right);
        // 如果右子树的路径和小于 0 ，那么就取路径和为 0
        int rightGain = Math.max(right, 0);
        // 当前节点为最顶部节点的路径和
        int pathSum = root.val + leftGain + rightGain;
        // 与全局最大路径和做比较
        max = Math.max(max, pathSum);
        // 返回当前节点+左右路径和较大的一边
        return root.val + Math.max(leftGain, rightGain);
    }
}