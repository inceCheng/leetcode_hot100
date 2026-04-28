package com.chg.hot100;

/**
 * 236. 二叉树的最近公共祖先
 */
public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        // 递归找左子树，要么返回空，要么返回 root
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 递归找右子树 要么返回空，要么返回 root
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 左右都不为空，说明这个节点就是最近祖先
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

}
