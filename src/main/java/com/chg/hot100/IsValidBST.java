package com.chg.hot100;

/**
 * 98. 验证二叉搜索树
 */
public class IsValidBST {
    // 用户存储左子树的根节点值
    private Long prev = null;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return inOrder(root);
    }

    private boolean inOrder(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 先遍历左子树
        if (!inOrder(root.left)) {
            return false;
        }
        // 如果当前节点的值，小于等于左子树根节点的值，则返回 false
        if (prev != null && root.val <= prev) {
            return false;
        }
        // 存储左子树根节点的值
        prev = (long) root.val;
        // 遍历右子树
        return inOrder(root.right);
    }
}
