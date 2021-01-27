package com.gzczy.datastructures.atguigu.itdachang.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @Description #136 只出现一次的数字
 * @Author chenzhengyu
 * @Date 2021-01-27 21:18
 */
public class SingleNumber {

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        System.out.println(singleNumber(nums));
    }

    /**
     * 方法一：暴力法
     */
    public static int singleNumber1(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length - 1; i++) {
            if (list.contains(nums[i])) {
                list.remove(nums[i]);
            }
            list.add(nums[i]);
        }
        return list.get(0);
    }

    /**
     * 方法二 ：hashmap存入
     *
     * @param nums
     * @return
     */
    public static int singleNumber2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer num : nums) {
            if (map.get(num) != null) {
                map.remove(num);
            } else {
                map.put(num, 1);
            }
        }
        return map.keySet().iterator().next();
    }

    /**
     * 方法三：数学方法解决，利用set
     * 用set去重，a = 2 * (a+b+c) - (a+b+c+b+c)
     * @param nums
     * @return
     */
    public static int singleNumber3(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int arraySum = 0;
        int setSum = 0;
        for (Integer num : nums) {
            set.add(num);
            arraySum += num;
        }
        for (Integer num : set) {
            setSum += num;
        }
        return 2 * (setSum) - arraySum;
    }

    /**
     * 方法四：异或运算（数学方法）
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int result = 0;
        for (Integer num : nums) {
            result ^= num;
        }
        return result;
    }
}
