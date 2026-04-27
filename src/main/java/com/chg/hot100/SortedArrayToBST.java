package com.chg.hot100;

/**
 * 108. 将有序数组转换为二叉搜索树
 */
public class SortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        return toBst(nums, 0, nums.length - 1);
    }

    // 数组有序，取中间节点，左边的就是左子树，右边的就是右子树，递归即可
    private TreeNode toBst(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = toBst(nums, left, mid - 1);
        root.right = toBst(nums, mid + 1, right);
        return root;
    }
}
