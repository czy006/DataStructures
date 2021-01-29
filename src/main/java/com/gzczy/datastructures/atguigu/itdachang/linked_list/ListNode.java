package com.gzczy.datastructures.atguigu.itdachang.linked_list;

/**
 * @Description
 * @Author chenzhengyu
 * @Date 2021-01-29 08:49
 */
public class ListNode {
    int val;    // 当前节点保存的数据值
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static void printList(ListNode head){
        ListNode curNode = head;
        while (curNode != null){
            System.out.print(curNode.val + " -> ");
            curNode = curNode.next;
        }
        System.out.print("null");
        System.out.println();
    }
}
