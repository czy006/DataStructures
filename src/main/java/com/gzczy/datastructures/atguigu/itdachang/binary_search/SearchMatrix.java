package com.gzczy.datastructures.atguigu.itdachang.binary_search;

/**
 * @Description #74 搜索二维矩阵
 * @Author chenzhengyu
 * @Date 2021-01-13 19:33
 */
public class SearchMatrix {

    public static void main(String[] args) {
        int matrix[][] = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        System.out.println(searchMatrix(matrix, 3));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        //0、边界条件筛选
        int m = matrix.length;
        if (m == 0) return false;

        int n = matrix[0].length;
        int left = 0;
        // 行 * 列 - 1 = 数据个数
        int right = m * n - 1;

        while (left <= right) {
            //计算中间下标值
            int midIndex = (left + right) / 2;
            // 行
            int x = midIndex / n;
            // 列
            int y = midIndex % n;
            // 行列坐标为(row, col)的元素，展开之后索引下标为idx = row * n + col；
            // 反过来，对于一维下标为idx的元素，对应二维数组中的坐标就应该是：row = idx / n;  col = idx % n;
            int midElement = matrix[x][y];
            // 如果当前元素 < 目标元素 ，证明需要 左面往中间（大的地方）靠一点
            if (midElement < target) {
                left = midIndex + 1;
                // 如果当前元素 > 目标元素 ，证明需要 右面往中间（小的地方）靠一点
            } else if (midElement > target) {
                right = midIndex - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
