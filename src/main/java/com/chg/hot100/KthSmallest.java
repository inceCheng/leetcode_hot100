package com.chg.hot100;

/**
 * 230. 二叉搜索树中第 K 小的元素
 */
public class KthSmallest {
    // 二叉搜索树本身就是有序的 左<跟<右
    // 所以，中序遍历这颗二叉搜索树即可，遍历到的第 k 个节点，就是第k小的数
    int seq = 0;
    int res = 0;

    public int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return res;
    }

    private void inOrder(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        inOrder(root.left, k);
        seq++;
        if (seq == k) {
            res = root.val;
        }
        inOrder(root.right, k);
    }
}
