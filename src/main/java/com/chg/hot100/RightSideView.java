package com.chg.hot100;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 199. 二叉树的右视图
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 */
public class RightSideView {
    // 右侧能看到的值，其实就是每一层的最右边节点，所以层序遍历，找到最右边节点即可
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.pollFirst();
                if (i == size - 1) {
                    res.add(curr.val);
                }
                if (curr.left != null) queue.offerLast(curr.left);
                if (curr.right != null) queue.offerLast(curr.right);
            }
        }
        return res;
    }
/**
 * 二叉树的右视图
 * 从二叉树的右侧看，按从上到下的顺序返回看到的节点值
 *
 * @param root 二叉树的根节点
 * @return 右视图节点值的列表
/**
 * 递归实现右视图
 *
 * @param root 当前节点
 * @param depth 当前深度
 * @param res 结果列表，存储右视图节点值
 */

    // public List<Integer> rightSideView(TreeNode root) {
    //     if (root == null) {
    //         return new ArrayList<>();
    //     }
    //     List<Integer> res = new ArrayList<>();
    //     dfs(root, 0, res);
    //     return res;
    // }
    //
    // private void dfs(TreeNode root, int depth, List<Integer> res) {
    //     if (root == null) {
    //         return;
    //     }
    //     // 第一次到达某一层时，记录当前节点
    //     // 先遍历右子树，所以第一次到达新的一层，遇到的那个节点，就是最右侧的节点
    //     // 当更新 res后，res.size也变了，所以下一次返回这一层的时候，depth == res.size 为 false，确保了只有最右侧的节点值会被记录
    //     if (depth == res.size()) {
    //         res.add(root.val);
    //     }
    //     dfs(root.right, depth + 1, res);
    //     dfs(root.left, depth + 1, res);
    // }
}
