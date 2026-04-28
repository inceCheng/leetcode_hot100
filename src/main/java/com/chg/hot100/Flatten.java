package com.chg.hot100;

/**
 * 114. 二叉树展开为链表
 */
public class Flatten {
    // 方法一
    // 反向先序 dfs, 先序：根 左 右,反向就是：右 左 根
    // 可以用一个 prev指针记录已经处理好的链表头
/*    private TreeNode prev = null;
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }*/

    // 方法二
    // 前序 dfs，保存左右子树
    // 不满足 O(1)空间复杂度
/*    private TreeNode prev = null;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;
        if (prev != null) {
            prev.left = null;
            prev.right = root;
        }
        prev = root;
        flatten(left);
        flatten(right);
    }*/

    // 方法三：原地迭代，空间更优
    // 如果有左子树
    // 找到左子树的最右节点
    // 把原右子树接到这个最右节点后面
    // 把左子树整体移动到右边
    // 左指针置空
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            // 左孩子不为空，
            if (curr.left != null) {
                TreeNode predecessor = curr.left;
                // 找到左子树的最后一个节点，
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            // 处理下一个节点
            curr = curr.right;
        }
    }

}
