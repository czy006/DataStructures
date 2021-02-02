package com.gzczy.datastructures.atguigu.itdachang.stack;

import java.util.Stack;

/**
 * @Description #20 有效的括号
 * 定字符串中只包含 '('，')'，'{'，'}'，'['，']'
 * @Author chenzhengyu
 * @Date 2021-02-02 18:26
 */
public class ValidParentheses {

    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("([)]"));
        System.out.println(isValid("{[]}"));
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        // 遍历字符串中所有字符，依次判断
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            // 判断当前字符是左括号还是右括号 如果是左括号，直接将对应的右括号入栈
            if (temp == '(') {
                stack.push(')');
            } else if (temp == '{') {
                stack.push('}');
            } else if (temp == '[') {
                stack.push(']');
            } else {
                // 如果是右括号，弹栈判断是否匹配
                if (stack.isEmpty() || stack.pop() != temp) return false;
            }
        }
        return stack.isEmpty();
    }
}
