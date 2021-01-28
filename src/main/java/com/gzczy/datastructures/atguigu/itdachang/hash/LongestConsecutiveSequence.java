package com.gzczy.datastructures.atguigu.itdachang.hash;

import java.util.HashSet;

/**
 * @Description #128 最长连续序列
 * @Author chenzhengyu
 * @Date 2021-01-27 21:17
 */
public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};

        System.out.println(LongestConsecutiveSequence.longestConsecutiveSequence(nums1));
        System.out.println(LongestConsecutiveSequence.longestConsecutiveSequence(nums2));
    }

    /**
     * 方法一：暴力法
     */
    public static int longestConsecutiveSequence1(int[] nums) {
        // 定义一个变量，保存当前最长连续序列的长度
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            int currNumber = nums[i];
            int currLength = 1;
            //循环遍历，查找下一个数字是否加一 找到则相加
            while (contains(nums, currNumber + 1)) {
                currLength++;
                currNumber++;
            }
            maxLength = Math.max(currLength, maxLength);
        }

        return maxLength;
    }

    /**
     * 方法二：利用hash表进行改进
     *
     * @param nums
     * @return
     */
    public static int longestConsecutiveSequence2(int[] nums) {
        int maxLength = 0;
        HashSet<Integer> set = new HashSet<>();
        for (Integer num : nums) {
            set.add(num);
        }

        for (int i = 0; i < nums.length; i++) {
            int currNumber = nums[i];
            int currLength = 1;
            // 通过hashset直接取出对比查看是否有下一个数据
            while (set.contains(currNumber + 1)) {
                currLength++;
                currNumber++;
            }
            maxLength = Math.max(maxLength, currLength);
        }

        return maxLength;
    }

    public static boolean contains(int[] nums, int currNumber) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == currNumber) {
                return true;
            }
        }
        return false;
    }

    /**
     * 方法三：改进
     *
     * @param nums
     * @return
     */
    public static int longestConsecutiveSequence(int[] nums) {
        int maxLength = 0;
        HashSet<Integer> set = new HashSet<>();
        for (Integer num : nums) {
            set.add(num);
        }

        for (int i = 0; i < nums.length; i++) {
            int currNumber = nums[i];
            int currLength = 1;
            // 判断：只有当前元素的前驱不存在的情况下，才去进行寻找连续序列的操作
            if (!set.contains(currNumber - 1)) {
                // 寻找后续数字，组成连续序列
                while (set.contains(currNumber + 1)) {
                    currLength++;
                    currNumber++;
                }
            }
            maxLength = Math.max(maxLength, currLength);
        }

        return maxLength;
    }
}
