package com.gzczy.datastructures.atguigu.itdachang.linked_list;

import java.util.Stack;

/**
 * @Description #19 删除链表的倒数第N个节点
 * @Author chenzhengyu
 * @Date 2021-01-31 20:26
 */
public class RemoveNthNodeFromEnd {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);


        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = null;

        ListNode.printList(listNode1);

        ListNode.printList(removeNthFromEndDoubleNode(listNode1, 2));
    }

    /**
     * 方法一：计算链表长度
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // 1. 遍历链表，得到长度l
        int length = getLength(head);
        // 2. 从前到后继续遍历，找到正数第l-n+1个元素 定义一个哨兵节点，next指向头节点
        ListNode sentinel = new ListNode(-1, head);
        ListNode curr = sentinel;
        for (int i = 0; i < length - n; i++) {
            curr = curr.next;
        }
        // 跳过第l-n+1个节点
        curr.next = curr.next.next;
        return sentinel.next;
    }

    public static int getLength(ListNode head){
        int length = 0;
        while (head != null){
            length ++;
            head = head.next;
        }
        return length;
    }


    /**
     * 方法二：利用栈
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEndStack(ListNode head, int n) {
        // 定义一个哨兵节点，next指向头节点
        ListNode sentinel = new ListNode(-1, head);
        ListNode temp = sentinel;
        Stack<ListNode> result = new Stack<>();
        while (temp != null) {
            result.push(temp);
            temp = temp.next;
        }
        //弹出n个节点
        for (int i = 0; i < n; i++)
            result.pop();
        //把当前节点的下个节点的指针指向 当前节点的下个节点的下个节点指针
        result.peek().next = result.peek().next.next;
        return sentinel.next;
    }

    /**
     * 方法三：双指针
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEndDoubleNode(ListNode head, int n) {
        // 定义一个哨兵节点，next指向头节点
        ListNode sentinel = new ListNode(-1, head);
        ListNode first = sentinel,second = sentinel;

        // 1. first先走n+1步
        for (int i = 0; i < n + 1; i++)
            first = first.next;

        // 2. first、second同时前进，当first变为null，second就是倒数第n+1个节点
        while (first != null ){
            first = first.next;
            second = second.next;
        }

        // 3. 删除倒数第n个节点
        second.next = second.next.next;
        return sentinel.next;
    }
}
