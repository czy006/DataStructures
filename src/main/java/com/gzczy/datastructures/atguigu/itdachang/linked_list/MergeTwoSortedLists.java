package com.gzczy.datastructures.atguigu.itdachang.linked_list;

/**
 * @Description #21 合并2个有序列表
 * @Author chenzhengyu
 * @Date 2021-01-31 17:36
 */
public class MergeTwoSortedLists {

    public static void main(String[] args) {
        ListNode listNode11 = new ListNode(1);
        ListNode listNode12 = new ListNode(2);
        ListNode listNode13 = new ListNode(4);
        ListNode listNode21 = new ListNode(1);
        ListNode listNode22 = new ListNode(3);
        ListNode listNode23 = new ListNode(4);


        listNode11.next = listNode12;
        listNode12.next = listNode13;
        listNode13.next = null;
        listNode21.next = listNode22;
        listNode22.next = listNode23;
        listNode23.next = null;

        ListNode.printList(listNode11);
        ListNode.printList(listNode21);

        ListNode.printList(mergeTwoLists(listNode11, listNode21));
    }

    /**
     * 方法1：遍历循环
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoListsIter(ListNode l1, ListNode l2) {
        // 定义一个哨兵节点，它的next指向最终结果的头节点
        ListNode sentinel = new ListNode(-1);

        // 保存当前结果链表里的最后一个节点，作为判断比较的“前一个节点”
        ListNode prev = sentinel;

        // 迭代遍历两个链表，直到至少有一个为null
        while (l1 != null && l2 != null) {
            // 比较当前两个链表的头节点，较小的那个就接在结果链表末尾
            if (l1.val <= l2.val) {
                // 将l1头节点连接到prev后面
                prev.next = l1;
                // 当前指针为l1
                prev = l1;
                // l1指针向后移动
                l1 = l1.next;
            } else {
                // 将l2头节点连接到prev后面
                prev.next = l2;
                // 指针向前移动，以下一个节点作为链表头节点
                prev = l2;
                l2 = l2.next;
            }
        }

        // 循环结束，最多还有一个链表没有遍历完成，因为已经排序，可以直接把剩余节点接到结果链表上
        prev.next = (l1 == null) ? l2 : l1;
        //返回哨兵节点的下一个节点才是真实节点
        return sentinel.next;
    }

    /**
     *
     * 递归实现
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2){
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        //进行节点值比较
        if (l1.val <= l2.val){
            // l1头节点较小，直接提取出来，剩下的l1和l2继续合并，接在l1后面
            l1.next =mergeTwoLists(l1.next,l2);
            return l1;
        }else {
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }
}
