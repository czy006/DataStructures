package com.gzczy.datastructures.atguigu.itdachang.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description #225 使用队列实现栈
 * @Author chenzhengyu
 * @Date 2021-02-02 18:53
 */
public class MyStack {

    private Queue<Integer> queue1;

    private Queue<Integer> queue2;

    public MyStack() {
        this.queue1 = new LinkedList<>();
        this.queue2 = new LinkedList<>();
    }

    /**
     * 放入元素 通过中间进行交换
     *
     * @param target
     */
    public void push(Integer target) {
        queue2.offer(target);
        // 如果queue1 不是空的话
        while (!queue1.isEmpty()) {
            // queue2放入 queue1的元素
            queue2.offer(queue1.poll());
        }
        //定义queue1 为临时变量
        Queue<Integer> temp = queue1;
        // queue2的数据挪动过去queue1
        queue1 = queue2;
        queue2 = temp;
    }

    public int pop() {
        return queue1.poll();
    }

    public int peek() {
        return queue1.peek();
    }

    public boolean isEmpty() {
        return queue1.isEmpty();
    }

    public static void main(String[] args) {
        MyStack myQueue = new MyStack();
        // 先放入1 1进入queue2 queue1的元素 交换至queue2 queue2交换至queue1
        myQueue.push(1);
        // 放入2 2进入queue2（此时这个queue为空） 然后发现queue1不是为空（刚刚进去了1） 把queue1的元素逐个取出放入queue2 这个时候queue2变成 2->1
        // 然后交换值 把queue1变成queue2当前的 然后queue2变成queue1（空）
        myQueue.push(2);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.isEmpty());
    }
}
