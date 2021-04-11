package com.gzczy.datastructures.atguigu.itdachang.binary_tree;

import java.util.ArrayList;

/**
 * @Description 验证二叉树（#98）
 * @Author chenzhengyu
 * @Date 2021-04-11 11:10
 */
public class ValidateBST {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(8);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        System.out.println(isValidBST(node1));
    }

    /**
     * 方法一：先序遍历
     *
     * @param root
     * @return
     */
    public static boolean isValidBST1(TreeNode root) {
        if (root == null) return true;
        return isValidBST1(root, null, null);
    }

    public static boolean isValidBST1(TreeNode root, Integer low, Integer upper) {
        if (root == null) return true;
        if (low != null && root.val <= low)
            return false;
        if (upper != null && root.val >= upper) {
            return false;
        }
        return isValidBST1(root.left, low, root.val) &&
                isValidBST1(root.right, root.val, upper);
    }


    /**
     * 中序遍历
     */
    static ArrayList<Integer> inorderBST = new ArrayList<>();

    public static boolean isValidBST(TreeNode root) {
        // 1.中序遍历，得到升序数组
        inOrder(root);
        // 2. 遍历数组，判断是否升序
        for (int i = 0; i < inorderBST.size(); i++) {
            if (i > 0 && inorderBST.get(i) <= inorderBST.get(i - 1)) return false;
        }

        return true;
    }

    // 实现一个中序遍历的方
    private static void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        inorderBST.add(root.val);
        inOrder(root.right);
    }


}
