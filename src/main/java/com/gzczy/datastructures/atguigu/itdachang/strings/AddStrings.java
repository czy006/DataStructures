package com.gzczy.datastructures.atguigu.itdachang.strings;

/**
 * @Description #415 字符串相加
 * 给定两个字符串形式的非负整数num1 和 num2，计算它们的和
 * @Author chenzhengyu
 * @Date 2021-01-21 14:11
 */
public class AddStrings {

    public static void main(String[] args) {
        String num1 = "456789";
        String num2 = "3456";
        System.out.println(addStrings(num1, num2));
    }

    public static String addStrings(String num1, String num2) {
        StringBuffer str = new StringBuffer();
        // 定义遍历2个字符串的初始字符串
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        // 定义变量用于保存进位
        int carry = 0;
        // 从个位开始依次遍历所有数，只要还有数没计算，就继续，其他位补 0
        while (i >= 0 || j >= 0 || carry != 0) {
            // 取两位数当前对应的数位
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            // 对当前数位求和
            int tempResult = n1 + n2 + carry;
            //把求和的个位保存在结果中，十位作为进位进行保存下来
            str.append(tempResult % 10);
            carry = tempResult / 10;
            j--;
            i--;
        }
        return str.reverse().toString();
    }
}
