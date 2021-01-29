package com.gzczy.datastructures.atguigu.itdachang.linked_list;

/**
 * @Description #206 反转链表
 * @Author chenzhengyu
 * @Date 2021-01-29 08:47
 */
public class ReverseLinkedList {

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
        ListNode listNode = reverseList(listNode1);
        ListNode.printList(listNode);
    }

    /**
     * 方法1：迭代
     * 迭代是从前到后依次去遍历
     * @param head
     * @return
     */
    public static ListNode reverseListForeach(ListNode head) {
        //首先定义两个指针

        //当前访问的节点
        ListNode curr = head;
        //当前访问节点的前一个节点
        ListNode prev = null;

        while (curr != null) {
            //当前节点的下个节点提取出来临时保存
            ListNode temp = curr.next;
            // 当前节点的下个节点 = 上个节点
            curr.next = prev;
            //下个节点 = 当前访问的节点
            prev = curr;
            // 当前访问的节点 = 下个节点（临时变量）
            curr = temp;
        }
        //prev指向的就是末尾的节点，也就是翻转之后的头节点
        return prev;
    }

    /**
     * 方法二：递归
     * 对于这道题目 我只考虑当前这个节点应该怎么做
     * 有点像数学归纳法，假如说我们能在最简单的情形下返回一个结果 那么 我们能在更复杂场景下也可以返回结果
     * 递归的顺序是从后到前，从前往后递归到最深的地方，然后执行过程是从后往前一个个执行 返回
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        // 递归调用，翻转剩余所有节点
        ListNode temp = head.next;
        ListNode reverseNode = reverseList(temp);
        //结束时候 把当前节点接在翻转之后的链表末尾
        temp.next = head;
        // 当前节点就是链表末尾，直接指向null
        head.next = null;
        //Debug
        //ListNode.printList(temp);
        return reverseNode;
    }
}
