package com.gzczy.datastructures.atguigu.itdachang.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * @Description #316 去除重复字母
 * @Author chenzhengyu
 * @Date 2021-01-25 13:04
 */
public class RemoveDuplicateLetters {

    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("cbacdcbc"));
    }

    /**
     * 方法一：暴力法，贪心策略递归
     * 时间复杂度：O(N^3)，因为用到了三重循环，最坏情况下时间复杂度达到了N^3。（超出运行时间限制）
     * 空间复杂度：O(N)，每次给字符串切片都会创建一个新的字符串（字符串不可变），切片的数量受常数限制，最终复杂度为 O(N) * C = O(N)
     */
    public static String removeDuplicateLetters1(String s) {
        // 递归的基准情形
        if (s.length() == 0) return "";

        // 希望找到当前最左侧的字母，位置记为position
        int position = 0;
        // 遍历字符串
        for (int i = 0; i < s.length(); i++) {
            // 只有当前字母比已经找到的position位置的字母要小，才有资格继续判断
            if (s.charAt(i) < s.charAt(position)) {
                // 定义一个布尔变量，表示当前i位置的字母是否可以替换position位置的字母
                boolean isReplaceable = true;
                // 遍历i之前的所有字母，判断是否在i后面重复出现
                for (int j = position; j < i; j++) {
                    // 定义一个布尔变量，表示j位置的字母是否重复出现
                    boolean isDuplicated = false;
                    // 遍历i后面的所有字母，看j位置的字母是否重复出现
                    for (int k = i + 1; k < s.length(); k++) {
                        if (s.charAt(k) == s.charAt(j)) {
                            isDuplicated = true;
                            break;
                        }
                    }
                    // 如果任一字母不重复出现，就不能替换当前position，后面的字母不用判断
                    if (!isDuplicated) {
                        isReplaceable = false;
                        break;
                    }
                }
                if (isReplaceable) position = i;
            }
        }

        // 遍历结束，position位置的字母就是结果中最左侧的元素
        return s.charAt(position) + removeDuplicateLetters1(s.substring(position + 1).replaceAll(s.charAt(position) + "", ""));
    }

    /**
     * 方法二:贪心策略改进
     * 时间复杂度：O(N)。 每次递归调用占用 O(N) 时间。递归调用的次数受常数限制(只有26个字母)，最终复杂度为 O(N) * C = O(N)。
     * 空间复杂度：O(N)，每次给字符串切片都会创建一个新的字符串（字符串不可变），切片的数量受常数限制，最终复杂度为O(N) * C = O(N)。
     *
     * @param s
     * @return
     */
    public static String removeDuplicateLetters2(String s) {
        // 递归的基础情形
        if (s.length() == 0) return "";
        // 希望找到当前最左侧的字母，位置标记为position
        int position = 0;
        //定义一个count数组，用来保存26个字母在s中出现的频率次数
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++; // count[0]保存a的个数；count[1]保存b的个数
        }
        // 遍历字符串，找到当前最左端字母
        for (int i = 0; i < s.length(); i++) {
            // 把当前字符和position位置比较，如果更小就替换
            if (s.charAt(i) < s.charAt(position)) {
                position = i;
            }
            //每遇到一个字符，count就要减少1
            // 如果遇到count减为0，就直接退出，以当前最小的字母作为最左端字符
            if (--count[s.charAt(i) - 'a'] == 0) {
                break;
            }
        }
        // 递归调用
        return s.charAt(position) + removeDuplicateLetters2(s.substring(position + 1).replaceAll("" + s.charAt(position), ""));
    }

    /**
     * 方法三：贪心策略（用栈实现）
     * 时间复杂度：O(N)。虽然看起来是双重循环，但内循环的次数受栈中剩余字符总数的限制，因为栈中的元素不重复，不会超出字母表大小，因此最终复杂度仍为 O(N)
     * 空间复杂度：O(1)。看上去空间复杂度像是 O(N)，但实际上并不是。首先，seen 中字符不重复，其大小会受字母表大小的限制，所以是O(1)。
     * 其次，只有 stack 中不存在的元素才会被压入，因此 stack 中的元素也唯一。所以最终空间复杂度为 O(1)
     * <p>
     * 注意：Stack peek 不改变栈的值(不删除栈顶的值)只是单纯取出看看，pop会把栈顶的值删除
     *
     * @param s
     * @return
     */
    public static String removeDuplicateLetters(String s) {
        //定义一个字符栈，保存去重之后的结果
        Stack<Character> stack = new Stack<>();
        // 为了快速判断一个字符是否在栈中出现过，用一个Set来保存元素是否出现
        HashSet<Character> seen = new HashSet<>();
        //为了判断一个字符是否在某个位置之后重复出现，用一个HashMap来保存字母出现在字符串的最后位置
        HashMap<Character, Integer> lastOccur = new HashMap<>();
        // 遍历字符串，将最后一次出现的位置保存进map
        for (int i = 0; i < s.length(); i++) {
            lastOccur.put(s.charAt(i), i);
        }
        // 遍历字符串，判断每个字符是否需要入栈
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 只有在c没有出现过的情况下，才判断是否入栈
            if (!seen.contains(c)) {
                // c入栈之前，要判断之前栈顶元素，是否在后面会重复出现；如果重复出现就可以删除
                // 判断之前的栈是否为空 且 当前的元素 < 栈中取出的元素 且 获得当前最后字符串出现的位置 是否大于当前的位置
                while (!stack.isEmpty() && c < stack.peek() && lastOccur.get(stack.peek()) > i) {
                    seen.remove(stack.pop());
                }
                stack.push(c);
                seen.add(c);
            }
        }
        // 将栈中的元素保存成字符串输出
        StringBuilder stringBuilder = new StringBuilder();
        for (Character c : stack)
            stringBuilder.append(c.charValue());

        return stringBuilder.toString();
    }
}
