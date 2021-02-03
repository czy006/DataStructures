package com.gzczy.datastructures.atguigu.itdachang.stack;

import java.util.Stack;

/**
 * @Description #232 使用栈实现队列 改良版
 * @Author chenzhengyu
 * @Date 2021-02-02 22:55
 */
public class MyQueueOpt {

    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();

    public MyQueueOpt() {
    }

    public void push(Integer target) {
        s1.push(target);
    }

    public int pop() {
        // 1. 判断stack2是否为空，如果为空，就要将stack1中所有元素弹出，然后压入
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        // 2. 弹出stack2栈顶元素
        return s2.pop();
    }

    public int peek() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s1.peek();
    }

    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    public static void main(String[] args) {
        MyQueueOpt myQueue = new MyQueueOpt();
        myQueue.push(1);
        myQueue.push(2);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.isEmpty());
    }
}
