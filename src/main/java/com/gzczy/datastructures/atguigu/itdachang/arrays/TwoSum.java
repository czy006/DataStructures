package com.gzczy.datastructures.atguigu.itdachang.arrays;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Description 两数之和
 * @url https://leetcode-cn.com/problems/two-sum/
 *
 * @Author chenzhengyu
 * @Date 2021-01-06 11:46
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 7, 11, 13};
        int target = 15;
        System.out.println(Arrays.toString(twoSum1(arr, target)));
        System.out.println(Arrays.toString(twoSum2(arr, target)));
        System.out.println(Arrays.toString(twoSum3(arr, target)));
    }

    /**
     * 暴力法
     * 时间复杂度：O(N的平方)
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new RuntimeException("没有适合的数据！");
    }

    /**
     * 时间复杂度：O(N)，我们把包含有N个元素的列表遍历两次。由于哈希表将查找时间缩短到O(1)，所以时间复杂度为O(N)
     * 空间复杂度：O(N)，所需的额外空间取决于哈希表中存储的元素数量，该表中存储了N个元素。
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target){
        //存放数组下标 Map<数字，数组下标>
        HashMap<Integer,Integer> result = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++){
            result.put(nums[i], i);
        }
        // 遍历数组，挨个查找对应的“那个数”在不在map中
        for (int i = 0; i < nums.length; i++){
            int temp = target - nums[i];
            //数组中同一个元素不能使用两遍，因为这里是提前把所有元素放好了，所以每次取出需要查看一下他们是否重复
            if (result.containsKey(temp) && result.get(temp) != i){
                //返回首先是当前数组下标，其次是取出的下标
                return new int[] {i,result.get(temp)};
            }
        }
        throw new RuntimeException("没有适合的数据！");
    }

    /**
     * 时间复杂度：O(N)，我们只遍历了包含有N个元素的列表一次。在表中进行的每次查找只花费O(1)的时间。
     * 其实这个过程中，我们也借鉴了动态规划的思想、把子问题解保存起来，后面用到就直接查询。
     * 空间复杂度：O(N)，所需的额外空间取决于哈希表中存储的元素数量，该表最多需要存储N个元素。
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum3(int[] nums, int target){
        //存放数组下标 Map<数字，数组下标>
        HashMap<Integer,Integer> result = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++){
            //获得第二个数的那个需要的解
            int temp = target - nums[i];
            //为什么这里无需使用比较条件：例如[3,3]这组特殊数据，第一次比较map没有，放入，第二次如果还重复出现
            //证明下标已经 + 1 了 所以可以直接返回，无需进行去重了
            if (result.containsKey(temp)){
                //这里对比twoSum2方法为什么需要交换位置：取出的value肯定是要在前面的，当前的位置肯定在后面
                return new int[] {result.get(temp),i};
            }
            //放入map中 下次用于比较
            result.put(nums[i], i);
        }
        throw new RuntimeException("没有适合的数据！");
    }
}
