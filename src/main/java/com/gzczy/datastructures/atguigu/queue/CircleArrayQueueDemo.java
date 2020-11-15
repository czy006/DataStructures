package com.gzczy.datastructures.atguigu.queue;

import java.util.Scanner;

/**
 * @Description 环形队列
 * @Author chenzhengyu
 * @Date 2020-11-15 10:26
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(3);
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

class CircleArrayQueue {

    private int[] array;
    private int maxSize;
    //头部
    private int front;
    //尾部
    private int rear;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
    }

    /**
     * 判断队列是否已满
     * （尾部下标 + 1）% 最大存储量 = 头部
     *
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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
            System.out.println("队列已满!");
            return;
        }
        //因为rear本身就指向后一个元素，所以直接赋值
        array[rear] = data;
        //将 rear 后移, 这里必须考虑取模，如果按照以前 ++ 数组越界
        rear = (rear + 1) % maxSize;
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
        //分析：需要将数据指向第一个元素，先把front取出做成一个临时变量，将front后移考虑取模，将临时变量保存返回
        int result = array[front];
        front = (front + 1) % maxSize;
        return result;
    }

    /**
     * 显示队列里所有数据
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空！");
            return;
        }
        //思路：从front开始遍历，遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, array[i % maxSize]);
        }
    }

    /**
     * 返回环形队列中有效的数据个数
     *
     * @return
     */
    public int size() {
        // rear = 2
        // front = 1
        // maxSize = 3
        return (rear + maxSize - front) % maxSize;
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
        return array[front];
    }
}
