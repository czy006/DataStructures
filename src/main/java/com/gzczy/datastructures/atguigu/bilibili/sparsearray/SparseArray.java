package com.gzczy.datastructures.atguigu.bilibili.sparsearray;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description 稀疏数组-完成五子棋棋盘存盘与读取
 *
 * 思路分析：五子棋是11*11盘，黑子用1表示，白子用2表示
 * 二维数组--》稀疏数组--》磁盘
 * 磁盘--》稀疏数组--》二维数组
 * @Author chenzhengyu
 * @Date 2020-11-14 16:11
 */
public class SparseArray {

    //棋盘长
    private static int pieceLong = 11;
    //棋盘宽
    private static int pieceWidth = 11;
    //刷盘路径
    private static final String path = "/Users/chenzhengyu/Downloads/a.txt";

    public static void main(String[] args) throws Exception{
        int[][] array = new int[pieceLong][pieceWidth];
        array[1][2] = 1;
        array[2][3] = 2;
        //array[3][4] = 2;
        System.out.println("=======输出生成棋盘的二维数组=======");
        printArray(array);
        int sum = 0;
        //打印原来数组
        for (int[] ints : array) {
            for (int anInt : ints) {
                if (anInt != 0) {
                    //对原来数组进行采集 变为稀疏数组 首先要先sum有几个
                    sum++;
                }
            }
        }

        System.out.println("检测到数据个数：" + sum);
        //创建对应的稀疏数组
        int[][] sparse_array = new int[sum + 1][3];
        sparse_array[0][0] = pieceLong;
        sparse_array[0][1] = pieceWidth;
        sparse_array[0][2] = sum;
        //计数器 用于计数塞入到哪一行
        int count = 0;
        //遍历塞入数据
        for (int i = 0; i < pieceLong; i++) {
            for (int j = 0; j < pieceWidth; j++) {
                if (array[i][j] != 0) {
                    count++;
                    sparse_array[count][0] = i;
                    sparse_array[count][1] = j;
                    sparse_array[count][2] = array[i][j];
                }
            }
        }
        System.out.println("=======输出整理后得到的稀疏数组=======");
        printArray(sparse_array);

        System.out.println("=======正在刷入磁盘=======");
        //稀疏数组写入磁盘
        flushFile(path,sparse_array);
        TimeUnit.SECONDS.sleep(3);
        //磁盘读取文件恢复稀疏数组
        int[][] fileArray = ReadFile(path);
        printArray(fileArray);
        /**
         * 将稀疏数组恢复为二维数组步骤
         * 1.根据稀疏数组第一行数据 创建好对应的二维数组
         * 2.遍历稀疏数组，往二维数组填充数据
         */
        int array_int[][] = new int[sparse_array[0][0]][sparse_array[0][1]];
        for (int i = 1; i <= sparse_array[0][2]; i++) {
            array_int[sparse_array[i][0]][sparse_array[i][1]] = sparse_array[i][2];
        }

        System.out.println("=======输出还原后的二维数组=======");
        printArray(array_int);
    }

    /**
     * 读取文件磁盘
     * @param path
     * @return
     * @throws Exception
     */
    public static int[][] ReadFile(String path) throws Exception {
        int[][] sparse_array = new int[][]{};
        File file = new File(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),
                "UTF-8"));
        String lineTxt = null;
        int line = 0;
        while ((lineTxt=br.readLine())!=null){
            String[] tempArray = lineTxt.split(",");
            if (line == 0){
                int initNum = Integer.parseInt(tempArray[2]) + 1;
                sparse_array = new int[initNum][initNum];
            }
            sparse_array[line][0]= Integer.parseInt(tempArray[0]);
            sparse_array[line][1]= Integer.parseInt(tempArray[1]);
            sparse_array[line][2]= Integer.parseInt(tempArray[2]);
            line++;
        }
        System.out.println("=======读取文件成功=======");
        return sparse_array;
    }

    /**
     * 写入文件至磁盘
     * @param path
     * @param sparse_array
     * @throws Exception
     */
    public static void flushFile(String path, int[][] sparse_array) throws Exception {
        File file = new File(path);
        FileOutputStream outputStream = new FileOutputStream(file);
        for (int i = 0; i < sparse_array.length; i++) {
            String write = sparse_array[i][0] + "," + sparse_array[i][1] + ","
                    + sparse_array[i][2]+"\n";
            outputStream.write(write.getBytes());
        }
        outputStream.flush();
        outputStream.close();
        System.out.println("=======写入文件成功=======");
    }

    /**
     * 打印二维数组
     * @param array
     */
    public static void printArray(int[][] array) {
        for (int[] temp : array) {
            for (int data : temp) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
