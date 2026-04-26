package com.chg.hot100;

/**
 * 104. 二叉树的最大深度
 */
public class MaxDepth {
    // 最大深度，深度遍历即可，前中后序遍历都可
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return inOrder(root, 1);
    }

    private int inOrder(TreeNode root, int depth) {
        if (root == null) {
            return 0;
        }
        int left = inOrder(root.left, depth) + 1;
        int right = inOrder(root.right, depth) + 1;
        return left > right ? left : right;
    }
}
