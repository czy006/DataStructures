package com.gzczy.datastructures.atguigu.bilibili.stack;


import java.util.Scanner;

/**
 * @Description
 * @Author chenzhengyu
 * @Date 2021-01-04 18:56
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        //测试一下ArrayStack 是否正确
        //先创建一个ArrayStack对象->表示栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.toString();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~~");
    }
}

class ArrayStack {

    /**
     * 栈大小
     */
    private int size;

    /**
     * 初始化栈顶为-1
     */
    private int top = -1;

    /**
     * 栈数组
     */
    private int[] stack;

    public ArrayStack(int size) {
        this.size = size;
        stack = new int[size];
    }

    public boolean isFull() {
        return top == size - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     * @param data
     */
    public void push(int data) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = data;
    }

    /**
     * 出栈
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            System.out.println("无数据");
            return -1;
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 返回头部元素 不出栈
     * @return
     */
    public int peek(){
        return stack[top];
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        if (isEmpty()) {
            System.out.println("栈空，没有数据~~");
            return buf.toString();
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
            buf.append(stack[i]).append(",");
        }
        return buf.toString();
    }
}
