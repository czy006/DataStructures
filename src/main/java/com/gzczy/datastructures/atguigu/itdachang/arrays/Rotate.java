package com.gzczy.datastructures.atguigu.itdachang.arrays;

import java.util.Arrays;

/**
 * @Description #48 旋转图像
 * @Author chenzhengyu
 * https://leetcode-cn.com/problems/rotate-image/
 * @Date 2021-01-14 13:14
 */
public class Rotate {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        MathMethod(matrix);
        //rotate(matrix);
        //转置后结果
        printlnArray(matrix);
    }

    /**
     * 方法1：使用数学方法进行解决，首先进行转列操作
     * 时间复杂度：O(N^2)。这个简单的方法已经能达到最优的时间复杂度O(N^2) ，因为既然是旋转，
     * 那么每个点都应该遍历到，N^2的复杂度不可避免。
     * 空间复杂度：O(1)。旋转操作是原地完成的，只耗费常数空间
     *
     * @param matrix
     */
    public static void MathMethod(int[][] matrix) {
        int n = matrix.length;
        //1、矩阵转置操作 只需要遍历矩阵的对角线一半元素即可
        // 1(0,0)->2(0,1)->3(0,2)->5(1,1)->6(1,2)->9(2,2)
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // 赋值临时变量 [行][列]
                int temp = matrix[i][j];
                // 当前的 [行][列] 进行转置，例如Demo数组中的 [0][1] = 2 转置为[1][0] = 4
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //输出进行反转的数组查看
        System.out.println("=======转置矩阵======");
        printlnArray(matrix);
        //2、翻转行 以每一行中间作为转换的中界线 进行前后下标互换即可
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                // 赋值临时变量
                int temp = matrix[i][j];
                // n - 1是
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }

    /**
     * 方法二：分治（分为四部分旋转）
     * 时间复杂度：O(N^2) 是两重循环的复杂度。
     * 空间复杂度：O(1) 由于我们在一次循环中的操作是“就地”完成的，并且我们只用了长度为 4 的临时列表做辅助
     * @param matrix
     */
    public static void rotate2(int[][] matrix) {
        //1、只需要遍历1/4矩阵，左上角开始 需要只考虑一次中间值 行考虑了 就列不用考虑
        // 固定中间值不变，其他都要旋转
        int n = matrix.length;
        // n % 2 基数+1 偶数不加 只处理1/4 然后其他通过位置对应关系进行推断；然后做行的时候考虑中间，列则无需考虑
        for (int i = 0; i < n / 2 + n % 2; i++) {
            // 左上1/4
            for (int j = 0; j < n / 2; j++) {
                //对于matrix[i][j] ,需要找到不同的四个矩阵中对应的另外三个位置和元素
                //定义一个临时数组 保存4个元素
                int[] tmp = new int[4];
                // 行列的索引值进行替换
                int row = i;
                int col = j;
                // 4维数组作为案例
                //观察图片发现行列转换的规律：row + newCol = n - 1; col = newCol
                for (int k = 0; k < 4; k++) {
                    //保存当前的行和列
                    tmp[k] = matrix[row][col];
                    // 定位下一个数，行列转换
                    int x = row;
                    row = col;
                    col = n - 1 - x;
                }
                //再次遍历那四个数的位置，将旋转后的数填入
                for (int k = 0; k < 4; k++) {
                    // 用上一个值替换当前的值
                    // 我们这里不能直接填入 k -1 因为 k -1  后 当下标为 0 的时候无法继续
                    // 所以 （k -1 +4）% 4 不大于就是自己，大于就取余
                    matrix[row][col] = tmp[(k + 3) % 4];
                    int x = row;
                    row = col;
                    col = n - 1 - x;
                }
            }
        }
    }

    /**
     * 方法三：改进
     * 时间复杂度：O(N^2)，是两重循环的复杂度。
     * 空间复杂度：O(1)。我们在一次循环中的操作是“就地”完成的。
      */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        // 旋转 1/4 个矩阵 n+1/2是为了防止出现奇数矩阵的情况
        // 假如是5*5矩阵 中间元素不动，那就是需要遍历24个元素 需要（25-1）/4 = 6 作为 1/4 的矩阵进行推导运算
        for (int i = 0; i < (n + 1) / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                //不断的将上一个位置的元素往前面挪动
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
                //Debug
                //printlnArray(matrix);
            }
        }
    }

    public static void printlnArray(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println("=====================");
    }
}
