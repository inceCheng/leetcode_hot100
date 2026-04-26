package com.chg.hot100;

/**
 * 26. 翻转二叉树
 */
public class IvertTree {
    // 翻转二叉树
    public TreeNode invertTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        postOrder(root);
        return root;
    }

    // 后序遍历 + 翻转
    private void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
}
