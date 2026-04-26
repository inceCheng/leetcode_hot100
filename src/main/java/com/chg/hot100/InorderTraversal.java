package com.chg.hot100;


import java.util.*;

/**
 * 94. 二叉树的中序遍历
 * <a href="https://leetcode.cn/problems/binary-tree-inorder-traversal/?envType=study-plan-v2&envId=top-100-liked">链接：二叉树的中序遍历</a>
 */
public class InorderTraversal {
    // 递归的本质也就是栈，所以可以手动维护一个栈
    // 中序遍历要先访问最左边的节点，所以：
    // 1. 不断把当前节点和他的左孩子入站
    // 2. 当走到 null,弹出栈顶元素
    // 3. 访问该节点
    // 4. 转向他的右孩子节点
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inOrder(root, res);
        return res;
    }

    private void inOrder(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        inOrder(root.left, ans);
        ans.add(root.val);
        inOrder(root.right, ans);
    }
}

/*
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}*/
