package com.gzczy.datastructures.atguigu.itdachang.stack;

import java.util.Stack;

/**
 * @Description #232 使用栈实现队列
 * @Author chenzhengyu
 * @Date 2021-02-02 22:55
 */
public class MyQueue {

    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();

    public MyQueue() {
    }

    public void push(Integer target){
        //1、首先将stack1所有元素弹出，压入stack2
        while (!s1.isEmpty()){
            s2.push(s1.pop());
        }
        //2、将新元素放入stack1
        s1.push(target);
        //3、再将stack2中所有的元素弹出，压入stack1
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }

    public int pop(){
        return s1.pop();
    }

    public int peek(){
        return s1.peek();
    }

    public boolean isEmpty(){
        return s1.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.isEmpty());
    }
}
