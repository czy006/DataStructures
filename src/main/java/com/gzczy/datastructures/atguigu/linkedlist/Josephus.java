package com.gzczy.datastructures.atguigu.linkedlist;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description 约瑟夫问题
 * @Author chenzhengyu
 * @Date 2020-12-03 10:57
 */
public class Josephus {
    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.addBoy(120);
        list.printBoy();
        list.countBoy(10,20,120);
    }
}

/**
 * 创建环形单向链表
 */
@Slf4j
class CircleSingleLinkedList {

    private Boy first = null;

    /**
     * 初始化男孩
     * @param num 初始化个数
     */
    public void addBoy(int num) {
        if (num < 1) {
            log.warn("you type is not right !");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <= num; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    /**
     * 输出男孩id
     */
    public void printBoy() {
        if (first == null){
            log.warn("you type is not right !");
            return;
        }
        Boy temp = first;
        while (true){
            System.out.println(temp);
            if (temp.getNext() == first){
                break;
            }
            temp = temp.getNext();
        }
    }

    /**
     * 根据用户输入 计算出圈顺序
     * @param startNum 开始num
     * @param countNum 次数
     * @param nums 初始值
     */
    public void countBoy(int startNum,int countNum,int nums) {
        if (first == null || startNum < 1 || startNum > countNum){
            log.warn("you type is warn,please check !");
            return;
        }
        //创建helper指针
        Boy helper = first;
        while (true){
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        //报数前，先让first和helper移动k-1次
        for (int j =0 ;j<startNum -1 ;j++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //小孩子报数前，让first 和 helper 指针同时移动 m - 1 次然后出圈
        while (true){
            //圈中只是剩下了自己first节点
            if (helper == first){
                break;
            }
            //
            for (int j =0 ;j<countNum -1 ;j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            log.info("出圈节点:{}",first);
            first = first.getNext();
            helper.setNext(first);
        }
        log.info("最后在圈中节点:{}",first);
    }

}

class Boy {

    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}