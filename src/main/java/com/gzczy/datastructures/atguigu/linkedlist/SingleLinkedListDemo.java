package com.gzczy.datastructures.atguigu.linkedlist;

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
        System.out.println("======================================================================");
        HeroNode hero3 = new HeroNode(1, "wjr", "test-wangjunru");
        singleLinkedList.updateNode(hero3);
        System.out.println("======================================================================");
        singleLinkedList.printlnList();
        singleLinkedList.deleteNode(1);
        System.out.println("======================================================================");
        singleLinkedList.printlnList();
        System.out.println("======================================================================");
        HeroNode hero4 = new HeroNode(4, "test4", "test4");
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
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
            if (temp.nextNode == null){
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

    //todo 如果返回该节点 否则返回null
    public void findLastIndexNode() {

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

    //返回头节点信息
    public HeroNode getHeadNode() {
        return headNode;
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
