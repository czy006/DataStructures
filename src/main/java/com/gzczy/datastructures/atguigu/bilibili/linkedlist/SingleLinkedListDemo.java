package com.gzczy.datastructures.atguigu.bilibili.linkedlist;

import java.util.Stack;

/**
 * @Description 单链列表案例
 * @Author chenzhengyu
 * @Date 2020-11-19 19:37
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(0, "czy", "chenzhengyu");
        HeroNode hero2 = new HeroNode(1, "wjr", "wangjunru");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addNode(hero1);
        singleLinkedList.addNode(hero2);
        singleLinkedList.printlnList();
        System.out.println("======================================================================");
        System.out.println("singleLinkedList-size:" + singleLinkedList.size());
        System.out.println("=============================updateNode===============================");
        HeroNode hero3 = new HeroNode(1, "wjr", "test-wangjunru");
        singleLinkedList.updateNode(hero3);
        singleLinkedList.printlnList();
        System.out.println("=============================deleteNode===============================");
        singleLinkedList.deleteNode(1);
        singleLinkedList.printlnList();
        System.out.println("=============================addByOrder===============================");
        HeroNode hero4 = new HeroNode(4, "test4", "test4");
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.printlnList();
        System.out.println("==============================reversePrint=============================");
        singleLinkedList.reversePrint();
        System.out.println("======================================================================");
        System.out.println(singleLinkedList.findLastIndexNode(1).toString());
        System.out.println("===============================reverseList===========================");
        singleLinkedList.printlnList();
        singleLinkedList.reverseList();
        System.out.println("after:");
        singleLinkedList.printlnList();
    }
}

class SingleLinkedList {

    private HeroNode headNode = new HeroNode(0, "", "");

    //添加头节点
    public void addNode(HeroNode node) {
        HeroNode temp = headNode;
        while (true) {
            if (temp.nextNode == null) {
                break;
            }
            temp = temp.nextNode;
        }
        temp.nextNode = node;
    }

    //根据id更新节点信息
    public void updateNode(HeroNode updateNode) {
        HeroNode temp = this.headNode;
        if (temp.nextNode == null && size() == 0) {
            System.out.println("can not update node!");
        }
        while (true) {
            if (temp.nextNode.id == updateNode.id) {
                temp.nextNode.name = updateNode.name;
                temp.nextNode.nickName = updateNode.nickName;
                System.out.println("update suc , update id is ：" + updateNode.id);
                break;
            }
            temp = temp.nextNode;
        }
    }

    //删除节点
    public void deleteNode(int nodeId) {
        HeroNode temp = this.headNode;
        if (temp.nextNode == null && size() <= 0) {
            System.out.println("can not delete node!");
        }
        while (true) {
            if (temp.nextNode == null) {
                break;
            }
            if (temp.nextNode.id == nodeId) {
                temp.nextNode = temp.nextNode.nextNode;
                break;
            }
            temp = temp.nextNode;
        }
    }

    //按照节点进行排序添加
    public void addByOrder(HeroNode addNode) {
        HeroNode temp = this.headNode;
        while (true) {
            if (temp.nextNode == null || size() == 0) {
                headNode.nextNode = addNode;
                break;
            }
            if (temp.nextNode == null) {
                break;
            }
            if (temp.nextNode.id == addNode.id) {
                System.out.println("id相同，无法添加");
                break;
            }
            if (temp.nextNode.id > addNode.id) {
                addNode.nextNode = temp.nextNode;
                temp.nextNode = addNode;
                break;
            }
            temp = temp.nextNode;
        }
    }

    /**
     * 如果返回该节点 否则返回null
     * 思路
     * 1. 编写一个方法，接收 head 节点，同时接收一个 index
     * 2. index 表示是倒数第 index 个节点
     * 3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
     * 4. 得到 size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
     * 5. 如果找到了，则返回该节点，否则返回 null
     *
     * @param index 查找倒数第几个节点
     */
    public HeroNode findLastIndexNode(int index) {
        HeroNode next = headNode;
        int size = size();
        if (next.nextNode == null && index <= 0 && index > size) {
            System.out.println("输入index非法或list为空，请检查！");
            return null;
        }
        HeroNode cur = headNode.nextNode;
        for (int i = 0; i < size - index; i++) {
            cur = cur.nextNode;
        }
        return cur;
    }

    /**
     * 将单链表进行反转[难点]
     * 思路：
     * 1. 先定义一个节点 reverseHead = new HeroHead
     * 2. 从头到尾遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
     * 3. 原来的链表head.next = reverseHead.next
     */
    public void reverseList() {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (headNode.nextNode == null || headNode.nextNode.nextNode == null) {
            return;
        }
        //定义一个辅助的指针(变量)，帮助我们遍历原来的链表
        HeroNode tempNode = headNode.nextNode;
        // 指向当前节点[tempNode]的下一个节点
        HeroNode next;
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表 reverseHead 的最前端，直至遍历到此链表没有节点
        while (tempNode != null) {
            //先暂时保存当前节点的下一个节点，因为后面需要使用
            next = tempNode.nextNode;
            //将tempNode的下一个节点指向新的链表的最前端
            tempNode.nextNode = reverseHead.nextNode;
            //将新的链表的下个节点指向取出的tempNode
            reverseHead.nextNode = tempNode;
            //让tempNode 后移到下一个节点
            tempNode = next;
        }
        //将 head.next 指向 reverseHead.next , 实现单链表的反转
        headNode.nextNode = reverseHead.nextNode;
    }

    //逆序打印代码 (不会改变实际原来位置 只是打印倒序)
    public void reversePrint() {
        Stack<HeroNode> temp = new Stack<HeroNode>();
        HeroNode next = headNode;
        if (next.nextNode == null) {
            System.out.println("链表为空，请检查后再打印！");
            return;
        }
        while (true) {
            if (next.nextNode == null) {
                break;
            }
            // 将temp后移，一定小心
            HeroNode nextNode = next.nextNode;
            temp.add(nextNode);
            next = nextNode;
        }
        while (temp.size() > 0) {
            System.out.println(temp.pop().toString()); //stack 的特点是先进后出
        }
    }

    //显示单链表里面的内容
    public void printlnList() {
        HeroNode next = headNode;
        if (next.nextNode == null) {
            System.out.println("node is empty!");
        } else {
            while (true) {
                //判断是否到链表最后
                if (next.nextNode == null) {
                    break;
                }
                // 将temp后移，一定小心
                HeroNode nextNode = next.nextNode;
                System.out.println(nextNode.toString());
                next = nextNode;
            }
        }
    }

    //链表大小 获取到单链表的节点个数
    public int size() {
        int size = 0;
        HeroNode temp = this.headNode;
        while (true) {
            if (temp.nextNode == null) {
                return size;
            }
            size++;
            temp = temp.nextNode;
        }
    }
}

/**
 * 英雄节点
 */
class HeroNode {

    public int id;
    public String name;
    public String nickName;
    public HeroNode nextNode;

    public HeroNode(int id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HeroNode{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", nickName='").append(nickName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
