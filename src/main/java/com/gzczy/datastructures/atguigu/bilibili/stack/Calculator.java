package com.gzczy.datastructures.atguigu.bilibili.stack;

/**
 * @Description Stack实现计算器
 * @Author chenzhengyu
 * @Date 2021-01-04 20:14
 */
public class Calculator {

    public static void main(String[] args) {
        //1、输入需要计算的表达式
        String exp = "7*2*2-5+1-50+3-4";

        //2、分别创建数字和符号的栈 用于保存
        CalculatorArrayStack numStack = new CalculatorArrayStack(10);
        CalculatorArrayStack calculatorStack = new CalculatorArrayStack(10);

        //3、定义一些需要使用到的变量
        int result = 0;
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int operator = 0;
        char ch = ' '; //将每次扫描得到 char 保存到 ch
        String keepNum = ""; //用于拼接 多位数

        //4、while循环遍历表达式
        while (true) {
            ch = exp.substring(index, index + 1).charAt(0);
            //如果是运算符
            if (calculatorStack.isOperation(ch)) {
                //如果里面已经有了运算符号
                if (!calculatorStack.isEmpty()) {
                    //就进行比较,如果当前的操作符的优先级 小于或者等于 栈中的操作符, 就需要从数栈中出栈两个数
                    if (calculatorStack.priority(ch) <= calculatorStack.priority(calculatorStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        //在从符号栈中出栈一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
                        operator = calculatorStack.pop();
                        result = numStack.cal(num1, num2, operator);
                        //把运算的结果如数栈
                        numStack.push(result);
                        //然后将当前的操作符入符号栈
                        calculatorStack.push(ch);
                    } else {
                        //如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈
                        calculatorStack.push(ch);
                    }
                } else {
                    //如果运算的stack 为空，则直接放入
                    calculatorStack.push(ch);
                }
            } else {
                //如果是数，则直接入数栈
                //numStack.push(ch - 48); // 48的原因是ASCI码
                //处理多位数
                keepNum += ch;
                /**
                 * 处理多位数-分析思路
                 * 1. 当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                 * 2. 在处理数，需要向 expression 的表达式的 index 后再看一位,如果是数就进行扫描，如果是符号 才入栈
                 * 3. 因此我们需要定义一个变量 字符串，用于拼接
                 */
                if (index == exp.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈 注意是看后一位，不是 index++
                    if (calculatorStack.isOperation(exp.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符，则入栈 keepNum = "1" 或者 "123"
                        numStack.push(Integer.parseInt(keepNum));
                        //keepNum 重置
                        keepNum = "";
                    }
                }

            }
            //让 index + 1, 并判断是否扫描到 expression 最后.
            index++;
            if (index >= exp.length()) {
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从 数栈和符号栈中 pop 出相应的数和符号，并运行.
        while (true) {
            if (calculatorStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            operator = calculatorStack.pop();
            result = numStack.cal(num1, num2, operator);
            numStack.push(result);//入栈
        }
        //将数栈的最后数，pop 出，就是结果
        int res2 = numStack.pop();
        System.out.printf("表达式 %s = %d", exp, res2);
    }
}

class CalculatorArrayStack {

    /**
     * 栈大小
     */
    private int size;

    /**
     * 初始化栈顶为-1
     */
    private int top = -1;

    /**
     * 栈数组
     */
    private int[] stack;

    public CalculatorArrayStack(int size) {
        this.size = size;
        stack = new int[size];
    }

    public boolean isFull() {
        return top == size - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     *
     * @param data
     */
    public void push(int data) {
        if (isFull()) {
            return;
        }
        top++;
        stack[top] = data;
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 返回头部元素 不出栈
     *
     * @return
     */
    public int peek() {
        return stack[top];
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        if (isEmpty()) {
            return buf.toString();
        }
        for (int i = top; i >= 0; i--) {
            buf.append(stack[i]).append(",");
        }
        return buf.toString();
    }

    /**
     * 返回运算符的优先级，优先级是程序员来确定, 优先级使用数字表示
     * 数字越大，则优先级就越高.
     */
    public int priority(int val) {
        if (val == '*' || val == '/') {
            return 1;
        } else if (val == '+' || val == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 判断是否为运算符号
     *
     * @param val
     * @return
     */
    public boolean isOperation(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 计算方法
     */
    public int cal(int num1, int num2, int calculate) {
        int result = 0;
        switch (calculate) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                //这里需要注意的是顺序
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
        }
        return result;
    }
}
