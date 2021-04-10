package com.gzczy.datastructures.atguigu.itdachang.binary_tree;

import java.util.LinkedList;

/**
 * @Description 树打印工具类(先 中 后 分层 遍历实现方式)
 * @Author chenzhengyu
 * @Date 2021-04-08 15:09
 */
public class TreePrinter {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        node1.left = node2;
        node1.right = node3;

        node3.left = node4;
        node3.right = node5;

        node4.right = node6;
        printTreePreOrder(node1);
        System.out.println();
        printTreeInOrder(node1);
        System.out.println();
        printTreePostOrder(node1);
        System.out.println();
        printTreeLevelOrder(node1);
    }

    // 1. 先序遍历 (根-左-右)
    public static void printTreePreOrder(TreeNode root) {
        if (root == null) return;
        ;
        System.out.print(root.val + "\t");
        printTreePreOrder(root.left);
        printTreePreOrder(root.right);
    }

    // 2. 中序遍历 (左-根-右)
    public static void printTreeInOrder(TreeNode root) {
        if (root == null) return;
        printTreeInOrder(root.left);
        System.out.print(root.val + "\t");
        printTreeInOrder(root.right);
    }

    // 3. 后序遍历 (左-右-根)
    public static void printTreePostOrder(TreeNode root) {
        if (root == null) return;
        printTreeInOrder(root.left);
        printTreeInOrder(root.right);
        System.out.print(root.val + "\t");
    }

    /**
     * 4. 分层遍历 (按照从上到下、从左到右的顺序，逐层遍历所有节点)
     * 利用队列先进先出特点 进行遍历
     */
    public static void printTreeLevelOrder(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            System.out.print(temp.val + "\t");
            if (temp.left != null) {
                queue.offer(temp.left);
            }
            if (temp.right != null) {
                queue.offer(temp.right);
            }
        }

    }
}
