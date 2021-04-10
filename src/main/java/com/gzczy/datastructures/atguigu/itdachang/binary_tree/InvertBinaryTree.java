package com.gzczy.datastructures.atguigu.itdachang.binary_tree;

import java.util.LinkedList;

/**
 * @Description 反转二叉树 #226
 * 输入：
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3  6   9
 * 输出：
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6  3   1
 *
 * @Author chenzhengyu
 * @Date 2021-04-10 13:18
 */
public class InvertBinaryTree {

    public static void main(String[] args) {

        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(9);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        TreePrinter.printTreeLevelOrder(node1);

        System.out.println();

        InvertBinaryTree invertBinaryTree = new InvertBinaryTree();

        TreePrinter.printTreeLevelOrder(invertBinaryTree.invertTree(node1));
        System.out.println();
        //再反转过来
        TreePrinter.printTreeLevelOrder(invertBinaryTree.invertTree2(node1));
    }

    /**
     * 先序遍历
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    /**
     * 后序遍历
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        if(root == null) return null;
        TreeNode left = invertTree2(root.left);
        TreeNode right = invertTree2(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 循环实现
     * @param root
     * @return
     */
    public TreeNode invertTree3(TreeNode root){
        if(root == null) return null;
        //将二叉树中的节点逐层放入队列中，再迭代处理队列中的元素
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            //每次都从队列中拿一个节点，并交换这个节点的左右子树
            TreeNode temp = queue.poll();
            TreeNode left = temp.left;
            temp.left = temp.right;
            temp.right = left;
            //如果当前节点的左子树不为空，则放入队列等待后续处理
            if (root.left != null){
                queue.add(root.left);
            }
            //如果当前节点的右子树不为空，则放入队列等待后续处理
            if (root.left != null){
                queue.add(root.right);
            }
        }
        return root;
    }

}
