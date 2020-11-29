package com.gzczy.datastructures.xiaoma.fibonacci;

import lombok.extern.slf4j.Slf4j;


/**
 * @Description 斐波那契数
 * @Author chenzhengyu
 * @Date 2020-11-29 16:29
 */
@Slf4j
public class FibonacciDemo {

    public static void main(String[] args) {
        System.out.println(fib1(40));
        System.out.println(fib2(40));
        System.out.println(fib3(40));
        System.out.println(fib4(40));
    }

    /**
     * 斐波那契数 0 1 1 2 3 5 8 13 ...
     *
     * @param n
     * @return
     */

    public static int fib1(int n) {
        if (n <= 1) {
            return n;
        }
        int first = 0;
        int second = 1;

        for (int i = 0; i < n - 1; i++) {
            //两个相加的结果要给下次的second
            int sum = first + second;
            first = second;
            second = sum;
            //second += first;
            //first = second - first;
        }
        return second;
    }

    /**
     * 斐波那契数 0 1 1 2 3 5 8 13 ...
     *
     * @param n
     * @return
     */

    public static int fib2(int n) {
        if (n <= 1) {
            return n;
        }
        int first = 0;
        int second = 1;

        for (int i = 0; i < n - 1; i++) {
            //两个相加的结果要给下次的second
            second += first;
            first = second - first;
        }
        return second;
    }

    /**
     * 递归实现 时间复杂度 n
     *
     * @param n
     * @return
     */

    public static int fib3(int n) {
        if (n <= 1) return n;
        return fib3(n - 1) + fib3(n - 2);
    }

    /**
     * 斐波那契的线性代数解决方法-特征方程
     * 利用公式F(n) = [fn/sqrt(5)]快速计算第n个斐波那契数，找出出现误差时的最小n值
     * 只需要将直接求值法（比内共识）用代码来描述就可以完成
     * @param n
     * @return
     */
    public static int fib4(int n) {
        double c = Math.sqrt(5);
        return (int) ((Math.pow((1 + c) / 2, n) - Math.pow((1 - c) / 2, n)) / c);
    }
}
