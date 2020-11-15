package com.gzczy.datastructures.atguigu.queue;

import java.util.Scanner;

/**
 * @Description 数组队列demo
 * @Author chenzhengyu
 * @Date 2020-11-15 08:59
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //取出数据
                    try {
                        int res = queue.showQueueHeadData();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }

}

class ArrayQueue {

    private int[] array;
    private int maxSize;
    //队列头
    private int front;
    //队列尾
    private int rear;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
        // 指向队列头部，分析出 front 是指向队列头的前一个位置
        front = -1;
        // 指向队列尾，指向队列尾的数据(即就是队列最后一个数据)
        rear = -1;
    }

    /**
     * 判断队列是否已满
     *
     * @return
     */
    public boolean isFull() {
        // 数组下标是从0开始 所以需要 -1
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加数据
     *
     * @param data
     */
    public void addQueue(int data) {
        if (isFull()) {
            throw new RuntimeException("队列已满!");
        }
        rear++;
        array[rear] = data;
    }

    /**
     * 获取队列数据 出队列
     *
     * @return
     */
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空!");
        }
        front++;
        return array[front];
    }

    /**
     * 显示队列里所有数据
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空！");
        } else {
            for (int i = 0; i < array.length; i++) {
                System.out.printf("arr[%d]=%d\n", i, array[i]);
            }
        }
    }

    /**
     * 显示头部数据
     *
     * @return
     */
    public int showQueueHeadData() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空!");
        }
        return array[front + 1];
    }
}