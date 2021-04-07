package com.gzczy.datastructures.atguigu.itdachang.binary_tree;

/**
 * @Description N的阶层（递归和循环实现）和 对比
 * @Author chenzhengyu
 * @Date 2021-04-07 16:29
 */
public class Recursion {


    public static void main(String[] args) {
        System.out.println(factorial(6));
        System.out.println(factorialTail(6, 1));
        System.out.println(factorialAdd(6));
    }

    /**
     * 普通递归（底层基于栈）耗费额外的存储空间，因为每次都需要保存递归调用的上下文信息
     *
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n == 0) return 1;
        return factorial(n - 1) * n;
    }

    /**
     * 尾部递归 相当于可以这样理解
     *
     * @param n
     * @param acc 初始值为1 外部传入 排除外部情况
     * @return f(6,6*1) -> f(5,6*5*1) -> f(4,6*5*4*1) -> f(3,6*5*4*3*1) -> f(2,6*5*4*3*2*1)
     */
    public static int factorialTail(int n, int acc) {
        if (n == 0) return acc;
        return factorialTail(n - 1, acc * n);
    }

    /**
     * 循环处理方法 思路是 1 -> 2 -> 3 -> 4 -> 5 ->6 逐个相乘叠加得到结果
     * @param n
     * @return
     */
    public static int factorialAdd(int n) {
        if (n == 0) return 1;
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
