package com.gzczy.datastructures.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Description
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入 [1,2,3,null,5,null,4]
 * 输出 [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *    \
 *     6           <---
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author chenzhengyu
 * @Date 2021-03-28 09:31
 */
public class _199 {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);

        t1.right = t2;
        t1.left = t3;
        t2.left = t5;
        t2.right = null;
        t3.left = t4;
        t4.right =null;
        t5.right = t6;
        t5.left = null;

        System.out.println(rightSideViewBFS(t1));
        System.out.println(rightSideViewDFS(t1));
    }

    /**
     * bfs 利用广度优先搜索进行层次遍历，记录下每层的最后一个元素
     * @param root
     * @return
     */
    public static List<Integer> rightSideViewBFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left!=null){
                    queue.offer(node.left);
                }
                if (node.right!=null){
                    queue.offer(node.right);
                }
                if (i == size - 1) {  //将当前层的最后一个节点放入结果列表
                    result.add(node.val);
                }
            }
        }
        return result;
    }

    /*
        ----- ----- ----- ----- ----- DFS 实现方式 ----- ----- ----- ----- ----- ----- ----- -----
        ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- -----
     */

    static List<Integer> dfs_result = new ArrayList<>();

    public static List<Integer> rightSideViewDFS(TreeNode root) {
        dfs(root,0);
        return dfs_result;
    }

    /**
     * dfs 递归实现
     * @param root
     * @param depth
     */
    private static void dfs(TreeNode root,int depth){
        if (root == null) {
            return;
        }
        // 先访问 当前节点，再递归地访问 右子树 和 左子树。
        if (depth == dfs_result.size()){
            // 如果当前节点所在深度还没有出现在res里，
            // 说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中
            dfs_result.add(root.val);
        }
        depth++;
        dfs(root.right,depth);
        dfs(root.left,depth);
    }

}
