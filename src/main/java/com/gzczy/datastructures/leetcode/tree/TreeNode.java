package com.gzczy.datastructures.leetcode.tree;

/**
 * @Description TreeNode Leetcode标准模板
 * @Author chenzhengyu
 * @Date 2021-03-28 10:27
 */
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
}