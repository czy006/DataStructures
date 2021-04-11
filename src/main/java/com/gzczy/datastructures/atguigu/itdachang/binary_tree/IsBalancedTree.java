package com.gzczy.datastructures.atguigu.itdachang.binary_tree;

/**
 * @Description #110 是否为平衡的二叉树
 * @Author chenzhengyu
 * @Date 2021-04-10 16:48
 */
public class IsBalancedTree {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;


        System.out.println(IsBalancedTree.isBalanced(node1));
        System.out.println(IsBalancedTree.isBalanced2(node1));

    }

    /**
     * 方法一：先序遍历
     * @param root
     * @return
     */
    public static boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        //判断左右子树是否平衡 且 高度是否小于等于1
        return Math.abs(height(root.left) - height(root.right)) <= 1 &&
                isBalanced(root.left) &&
                isBalanced(root.right);
    }

    public static int height(TreeNode root){
        if (root == null) return 0;
        return Math.max(height(root.left), height(root.right));
    }

    /**
     * 方法二：后序遍历
     * @param root
     * @return
     */
    public static boolean isBalanced2(TreeNode root) {
        return BalancedHeight(root) > -1;
    }

    public static int BalancedHeight(TreeNode root){
        if (root == null) return 0;
        //递归计算左右子树高度
        int leftHeight = BalancedHeight(root.left);
        int rightHeight = BalancedHeight(root.right);
        //子树发生不平衡的情况
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1)
            return -1;
        //如果平衡返回当前高度
        return Math.max(leftHeight,rightHeight);
    }
}
