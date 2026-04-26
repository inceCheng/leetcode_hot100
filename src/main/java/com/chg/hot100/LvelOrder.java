package com.chg.hot100;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 102. 二叉树的层序遍历
 */
public class LvelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> order = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        order.add(root);
        while (!order.isEmpty()) {
            int size = order.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = order.poll();
                if (curr.left != null) {
                    order.add(curr.left);
                }
                if (curr.right != null) {
                    order.add(curr.right);
                }
                level.add(curr.val);
            }
            res.add(level);
        }
        return res;
    }
}
