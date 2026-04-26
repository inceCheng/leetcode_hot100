package com.chg.hot100;

/**
 * 101. 对称二叉树
 */
public class IsSymmetric {
    // 判断一颗二叉树是否对称，关键是看镜像节点是否一样
    // 1. 左右节点都为空 true
    // 2. 左右其中一个不为空 false
    // 3. 左右节点值不相等 false
    // 4. 递归时，需要注意对比的是镜像节点，即：左边的左节点与右边的右节点对比；左边的右节点与右边的左节点对比

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return postOrder(root.left, root.right);
    }

    // 后序遍历并判断左右子树是否对称
    private boolean postOrder(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return postOrder(left.left, right.right) && postOrder(left.right, right.left);
    }

}
