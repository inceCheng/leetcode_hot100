package com.chg.hot100;

/**
 * 543. 二叉树的直径
 */
public class DiameterOfBinaryTree {

    // 二叉树的直径
    // 通过 dfs 遍历每个节点，并且计算该节点的左右子树高度，并更新全局最大高度
    private int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        postOrder(root);
        return max;
    }

    private int postOrder(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = postOrder(root.left);
        int right = postOrder(root.right);
        if (left + right > max) {
            max = left + right;
        }
        return Math.max(left, right) + 1;
    }
}

