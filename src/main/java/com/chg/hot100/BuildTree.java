package com.chg.hot100;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 */
public class BuildTree {
    // 先序 根左右
    // 中序 左根右
    // 所以先序的第一个节点，一定是当前子树的根节点，然后在中序中，找到根节点的位置，左边的就是左子树，右边的就是右子树，
    // 递归实现
    // 使用 hashMap记录中序序列中每个节点的下标，使得后续访问中序序列中节点的时间复杂度是 O(1)

    private Map<Integer, Integer> map;
    private int[] preorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.map = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode build(int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }
        int rootVal = preorder[preLeft];
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = map.get(rootVal);
        int leftSize = rootIndex - inLeft;
        root.left = build(preLeft + 1, // 左子树+1
                preLeft + leftSize, // 右侧就是左边+长度
                inLeft, // 中序的起点不变
                rootIndex - 1); // 终点变成根节点前面一个节点
        root.right = build(preLeft + leftSize + 1, // 右子树，起点+长度+1
                preRight, // 前序的右边界不变
                rootIndex + 1, // 中序的起点就是，根节点+1
                inRight); // 中序的右边界不变
        return root;
    }
}
