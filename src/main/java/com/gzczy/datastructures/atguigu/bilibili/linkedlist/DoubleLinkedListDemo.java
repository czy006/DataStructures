package com.gzczy.datastructures.atguigu.bilibili.linkedlist;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description 双向链表实现
 * @Author chenzhengyu
 * @Date 2020-11-29 12:51
 */
@Slf4j
public class DoubleLinkedListDemo {

    public static void main(String[] args) {

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        HeroNodes add1 = new HeroNodes(1, "czy", "红警专家");
        HeroNodes add2 = new HeroNodes(3, "yejoe", "游戏带师");
        HeroNodes add3 = new HeroNodes(4, "wjr", "落井下石");
        HeroNodes add4 = new HeroNodes(5, "zzl", "老阴阳人了");

        doubleLinkedList.add(add1);
        doubleLinkedList.add(add2);
        doubleLinkedList.add(add3);
        doubleLinkedList.add(add4);
        //打印列表
        doubleLinkedList.printList();
        log.info("打印链表大小:" + doubleLinkedList.size());
        log.info("更新链表");
        HeroNodes updateNode = new HeroNodes(4, "wjr", "好人一个");
        doubleLinkedList.update(updateNode);
        doubleLinkedList.printList();
        log.info("删除节点");
        doubleLinkedList.delete(3);
        doubleLinkedList.printList();
        log.info("按照顺序插入节点");
        HeroNodes addBySort = new HeroNodes(2, "ttf", "宝宝");
        doubleLinkedList.addBySort(addBySort);
        doubleLinkedList.printList();
    }
}

class DoubleLinkedList {

    private HeroNodes headNode = new HeroNodes(0, null, null);

    /**
     * 添加节点
     *
     * @param addNode
     */
    public void add(HeroNodes addNode) {
        HeroNodes temp = this.headNode;
        while (true) {
            if (temp.nextNode == null) {
                temp.nextNode = addNode;
                addNode.preNode = temp;
                return;
            }
            temp = temp.nextNode;
        }
    }

    /**
     * 更新节点
     *
     * @param updateNode
     */
    public void update(HeroNodes updateNode) {
        HeroNodes temp = this.headNode;
        while (true) {
            if (updateNode.id == temp.id) {
                temp.name = updateNode.name;
                temp.nickName = updateNode.nickName;
                break;
            }
            temp = temp.nextNode;
        }
    }

    /**
     * 根据id删除节点
     *
     * @param id
     */
    public void delete(int id) {
        HeroNodes temp = this.headNode.nextNode;
        while (true) {
            if (id == temp.id) {
                temp.preNode.nextNode = temp.nextNode;
                if (temp.nextNode != null) {
                    temp.nextNode.preNode = temp.preNode;
                }
                break;
            }
            temp = temp.nextNode;
        }
    }

    /**
     * 添加节点
     *
     * @param addNode
     */
    public void addBySort(HeroNodes addNode) {
        HeroNodes temp = this.headNode;
        //如果是一个空链表直接插入
        if (temp.nextNode == null){
            temp.nextNode = addNode;
            addNode.preNode = temp;
            return;
        }
        while (true) {
            if (temp.nextNode.id == addNode.id) {
                System.out.println("id相同，无法添加");
                break;
            }
            //如果当前节点的下个id 大于 这个节点id 就插入到这个节点后面,例如1，4，5 插入个 2 正确顺序是1，2，4，5
            if (temp.nextNode.id > addNode.id) {
                //添加节点的下个节点 = 当前节点的下个节点
                addNode.nextNode = temp.nextNode;
                //添加节点的上个节点 = 当前节点
                addNode.preNode = temp;
                //当前节点的下个节点的上个节点 = 新增节点
                temp.nextNode.preNode = addNode;
                //当前节点的下个节点 = 新增节点
                temp.nextNode = addNode;
                break;
            }
            temp = temp.nextNode;
        }
    }

    /**
     * 打印链表
     */
    public void printList() {
        if (headNode.nextNode == null) {
            System.out.println("DoubleLinkList is empty ... ");
            return;
        }
        HeroNodes temp = this.headNode;
        while (temp.nextNode != null) {
            //判断是否到链表最后
            HeroNodes heroNodes = temp.nextNode;
            System.out.println(heroNodes);
            temp = temp.nextNode;
        }
    }

    /**
     * 判断链表有效大小
     */
    public int size() {
        int size = 0;
        if (headNode.nextNode == null) {
            System.out.println("DoubleLinkList is empty ... ");
            return size;
        }
        HeroNodes temp = this.headNode;
        while (temp.nextNode != null) {
            //判断是否到链表最后
            temp = temp.nextNode;
            size++;
        }
        return size;
    }
}

class HeroNodes {

    public int id;
    public String name;
    public String nickName;

    public HeroNodes nextNode;
    public HeroNodes preNode;

    public HeroNodes(int id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HeroNodes{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", nickName='").append(nickName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}