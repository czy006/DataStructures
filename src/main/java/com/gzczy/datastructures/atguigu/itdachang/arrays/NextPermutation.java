package com.gzczy.datastructures.atguigu.itdachang.arrays;

import java.util.Arrays;

/**
 * @Description #31 下一个排列
 * @Author chenzhengyu
 * @Date 2021-01-14 11:32
 */
public class NextPermutation {

    public static void main(String[] args) {
        int [] test = new int[]{1,2,3};
        nextPermutation(test);
        System.out.println(Arrays.toString(test));
    }

    public static void nextPermutation(int[] nums) {

        int n = nums.length;
        //0、从后面向前找升序子序列，找到第一次下降的数值为k
        // 两个两个比较，所以第一次没可能从最后一个开始比较，是从倒数第二个跟倒数第一个比较
        int k = n - 2;

        // 1、为什么要等于也要继续while循环？答案：因为当数值等于的时候相当于点是平的（相同），相当于
        // 直接调换这2个数是无法得到比它大的数，所以是需要继续往后找的

        while (k >= 0 && nums[k] >= nums[k + 1])
            k--;

        //2、找到k 就是需要调整的最高位

        //2.1、 如果k = -1 说明所有数都是降序排列，这时候我们需要反转作为升序排列
        if (k == -1) {
            Arrays.sort(nums);
            return;
        }

        //2.2、 一般情况下 k>=0 之后的子序列逐个数进行遍历 与 最高位的数值进行比较判断 只要大就继续往后找
        // 找到替换最高位的那个数。如果想节省步骤的话 K+1 无需再查找，可以从k+2 开始查找
        int i = k + 2;
        while (i < n && nums[i] > nums[k])
            i++;

        //2.3、交换 i - 1 和 k的位置上面的数值
        int temp = nums[k];
        nums[k] = nums[i - 1];
        nums[i - 1] = temp;

        //2.4、交换完成后，从当前k高位后面所有数进行排序（升序排列）
        int start = k + 1;
        int end = n - 1;
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }

}
