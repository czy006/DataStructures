package com.gzczy.datastructures.atguigu.itdachang.binary_search;

import java.util.Arrays;

/**
 * @Description 二分查找
 * @Author chenzhengyu
 * @Date 2021-01-12 15:17
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[]{16,42,62,17,49,3,11,2341,323,231,634};
        int target = 323;
        System.out.printf("目标数值 %d 的下标 => %d",target,binarySearch(arr,target));
    }

    /**
     * 二分查找法，若没找到则返回 -1 状态码
     * @param search
     * @param target
     * @return
     */
    public static int binarySearch(int[] search,int target){
        int result = -1;
        //0、先进行排序
        Arrays.sort(search);

        //1、定义双指针 同时向中间靠拢
        int start = 0;
        int end = search.length - 1;

        //1.1、排除特殊情况
        if (target < search[start] || target > search[end]) return result;

        //2、进行对比
        while (start <= end){
            //进行对半折
            int mid = (start + end) / 2;
            if (search[mid] < target){
                start = start + 1;
            } else if (search[mid] > target){
                end = end - 1;
            } else {
                return mid;
            }
        }
        return result;
    }
}
