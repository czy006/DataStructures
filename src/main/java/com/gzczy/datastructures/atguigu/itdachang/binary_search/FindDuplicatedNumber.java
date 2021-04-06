package com.gzczy.datastructures.atguigu.itdachang.binary_search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Description #287 寻找重复数
 * @Author chenzhengyu
 * @Date 2021-01-20 11:02
 */
public class FindDuplicatedNumber {

    public static void main(String[] args) {
        int num1[] = new int[]{1, 3, 4, 2, 2};
        int num2[] = new int[]{1, 1};
        System.out.println(findDuplicatedNumber(num1));
    }

    /**
     * 方法一：保存元素法
     *
     * @param arr
     * @return
     */
    public static int findDuplicatedNumber1(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>(arr.length);
        for (int i = 0; i < arr.length - 1; i++) {
            if (map.containsKey(arr[i])) {
                return arr[i];
            }
            map.put(arr[i], i);
        }
        throw new RuntimeException("can't find data");
    }

    /**
     * 方法二：保存元素法改进
     *
     * @param arr
     * @return
     */
    public static int findDuplicatedNumber2(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int j : arr) {
            if (set.contains(j)) {
                return j;
            }
            set.add(j);
        }
        throw new RuntimeException("can't find data");
    }

    /**
     * 方法三：二分查找：查找 1-N的自然数列，寻找target
     * 数组里面最小数是1 ，最大数是n+1-1，这里是利用了一个小技巧，直接使用指针去做处理，然后返回的时候也是返回的指针
     * 这里的左右指针是鉴于元素进行特殊处理，而不是数组下标 这是需要注意的
     *
     * @param arr
     * @return
     */
    public static int findDuplicatedNumber3(int[] arr) {
        // 定义左右指针 左指针从1开始是因为题目要求 我们是从自然数1 开始查找 数字是 1 到 n
        int left = 1;
        // 这里是鉴于元素进行特殊处理，而不是数组下标 这是需要注意的 所以假设数据数组是 1 2 3 4 5 6 left = 1；right = n - 1
        int right = arr.length - 1;

        while (left <= right) {
            // 计算中间值
            int mid = (left + right) / 2;
            // 对当前的mid计算count值
            int count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] <= mid) count++;
            }
            // 判断count和mid本身的大小关系
            if (count <= mid)
                left = mid + 1;    // count小于等于mid自身，说明mid比target小，左指针右移
            else
                right = mid; //右指针左移 以往是mid -1 因为有可能需要包含mid

            // 左右指针重合时，找到target
            if (left == right)
                return left;
        }
        throw new RuntimeException("can't find data");
    }

    /**
     * 方法四：前后对比法 通过排序后进行前后顺序对比 一致则证明了 就是一样的数
     *
     * @param arr
     * @return
     */
    public static int findDuplicatedNumber4(int[] arr) {
        Arrays.sort(arr);
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] == arr[j + 1]) {
                return arr[j];
            }
        }
        throw new RuntimeException("can't find data");
    }

    /**
     * 方法五：快慢指针法（循环检测）
     * slow走一步 fast 走两步
     *
     * @param arr
     * @return
     */
    public static int findDuplicatedNumber(int[] arr) {
        // 定义快慢指针
        int fast = 0, low = 0;
        // 第一阶段：寻找链表中的环，如果fast!=low就一直寻找下去
        do {
            low = arr[low];
            //进行条件判读，如果取出值比 数组长度大 证明没有环，不用继续查找了
            if (arr[fast] >= arr.length){
                throw new RuntimeException("can't find data");
            }
            fast = arr[arr[fast]];
        } while (fast != low);
        System.out.println("fast==>" + fast + ",low==>" + low);
        // 第二阶段：寻找环在链上的入口节点就是那个重复的数字
        int ptr1 = 0, ptr2 = low;
        while (ptr1 != ptr2) {
            ptr1 = arr[ptr1];
            ptr2 = arr[ptr2];
        }
        return ptr1;
    }
}
