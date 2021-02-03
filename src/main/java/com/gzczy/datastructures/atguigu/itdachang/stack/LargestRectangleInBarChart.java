package com.gzczy.datastructures.atguigu.itdachang.stack;

import java.util.Stack;

/**
 * @Description #84 柱状图中最大的矩形
 * @Author chenzhengyu
 * @Date 2021-02-03 10:24
 */
public class LargestRectangleInBarChart {

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea5(heights));
    }

    /**
     * 方法1：暴力法
     *
     * @param heights
     * @return
     */
    public static int largestRectangleArea1(int[] heights) {
        //定义变量保存的最大面积
        int largestArea = 0;
        for (int left = 0; left < heights.length; left++) {
            int currHeight = heights[left];
            for (int right = left; right < heights.length; right++) {
                //确定当前矩形高度
                currHeight = Math.min(heights[right], currHeight);
                //计算当前矩形面积
                int currArea = (right - left + 1) * currHeight;
                //更新最大面积
                largestArea = Math.max(currArea, largestArea);
            }
        }
        return largestArea;
    }

    /**
     * 方法二：双指针解法，遍历所有可能的高度
     * 思想：先假设某个元素（柱子）的高度作为矩形最终高度，确定最终能扩展到多宽的范围
     * 确定了某个元素之后，朝着两个方向进行扩展寻找计算高度，然后和之前的最大面积进行对比
     *
     * @param heights
     * @return
     */
    public static int largestRectangleArea2(int[] heights) {
        //定义变量保存的最大面积
        int largestArea = 0;
        // 遍历数组，以每个柱子高度作为最终矩形的高度
        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];
            int left = i, right = i;
            // 寻找左边界，左指针左移
            while (left >= 0) {
                //查看左边的数的高度是否小于当前的数的高度
                if (heights[left] < height) break;
                left--;
            }
            // 寻找右边界，右指针右移
            while (right < heights.length) {
                if (heights[right] < height) break;
                right++;
            }
            // 计算当前宽度 -1因为是已经超出当前范围了，例如我们使用6作为最终高度，然后左右指针往左右移动后，相减其实是等于2 需要再减少1才为1它自己本身
            int width = right - left - 1;
            // 计算面积并且对比出面积大小
            int currArea = height * width;
            largestArea = Math.max(largestArea, currArea);
        }
        return largestArea;
    }

    /**
     * 方法三：双指针法改良版
     * 如果我们在判断左边界的时候，左边界的柱子比当前柱子要高的话 我们直接拿左边更高柱子的左边界作为当前有可能的左边界，然后再继续判断迭代，这样的做法就是一步一步的跳过中间更加高的柱子
     *
     * @param heights
     * @return
     */
    public static int largestRectangleArea3(int[] heights) {
        //定义变量保存的最大面积
        int largestArea = 0;

        //定义两个数组，保存每个柱子对应的左右边界
        int n = heights.length;
        int[] lefts = new int[n];
        int[] rights = new int[n];
        // 遍历数组，计算左边界
        for (int i = 0; i < n; i++) {
            int height = heights[i];
            //定义左指针
            int left = i - 1;
            //左指针左移动，寻找左边界
            while (left >= 0) { // 如果不懂可以在此断点逐个查看
                if (heights[left] < height) break;
                //核心： 如果左边柱子更高，就直接跳到它的左边界柱子再判断
                left = lefts[left];
            }
            lefts[i] = left;
        }

        //遍历数组，计算右边界
        for (int i = n - 1; i >= 0; i--) {
            int height = heights[i];
            //定义右指针
            int right = i + 1;
            //右指针右移动，寻找右边界
            while (right < n) {
                if (heights[right] < height) break;
                right = rights[right];
            }
            rights[i] = right;
        }

        //遍历所有的柱子，计算面积
        for (int i = 0; i < n; i++) {
            // 宽度（） * 高度
            int currArea = (rights[i] - lefts[i] - 1) * heights[i];
            largestArea = Math.max(currArea, largestArea);
        }

        return largestArea;
    }

    /**
     * 方法四：单调栈
     *
     * @param heights
     * @return
     */
    public static int largestRectangleArea4(int[] heights) {
        int largestArea = 0;

        // 定义两个数组，保存每个柱子对应的左右边界
        int n = heights.length;
        int[] lefts = new int[n];
        int[] rights = new int[n];

        // 定义一个栈
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            // 首先我们必须栈不是为空（ -1 为哨兵） 然后判断当前的高和栈顶的高是否大 如果是直接弹出元素 如果相等也需要弹掉 一定找到比它小的才行，
            // 不停的弹出边界 剩余的就是
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            // 所有大于等于当前高度的元素全部弹出，找到了左边界
            lefts[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();

        // 遍历所有柱子，作为当前高度，寻找右边界

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            rights[i] = stack.isEmpty() ? n :stack.peek();
            stack.push(i);
        }

        //遍历所有的柱子，计算面积
        for (int i = 0; i < n; i++) {
            // 宽度（） * 高度
            int currArea = (rights[i] - lefts[i] - 1) * heights[i];
            largestArea = Math.max(currArea, largestArea);
        }

        return largestArea;
    }

    /**
     * 方法5：单调栈优化
     * @param heights
     * @return
     */
    public static int largestRectangleArea5(int[] heights) {
        // 定义变量保存最大面积
        int largestArea = 0;

        // 定义两个数组，保存每个柱子对应的左右边界
        int n = heights.length;
        int[] lefts = new int[n];
        int[] rights = new int[n];

        // 初始化rights为右哨兵n
        for (int i = 0; i < n; i ++) rights[i] = n;

        // 定义一个栈
        Stack<Integer> stack = new Stack<>();

        // 遍历所有柱子，作为当前高度，先找左边界
        for (int i = 0; i < n ; i ++){
            while ( !stack.isEmpty() && heights[stack.peek()] >= heights[i] ){
                // 栈顶元素如果小于当前元素，那么它的右边界就是当前元素
                rights[stack.peek()] = i;
                stack.pop();
            }

            // 所有大于等于当前高度的元素全部弹出，找到了左边界
            lefts[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(i);
        }

        // 遍历所有柱子，计算面积
        for (int i = 0; i < n; i++){
            int currArea = (rights[i] - lefts[i] - 1) * heights[i];
            largestArea = Math.max(currArea, largestArea);
        }
        return largestArea;
    }
}
