package com.yanngyi.datastructure.stack;

import java.util.*;

/**
 * 使用栈实现逆波兰表达式
 * @author yangyi
 */
public class PolandNotation {

    static Map<String, Integer> map = new HashMap<>();
    static {
        map.put("(", 0);
        map.put(")", 0);
        map.put("+", 1);
        map.put("-", 1);
        map.put("x", 2);
        map.put("/", 2);
    }

    public static void main(String[] args) {
        String expression = "1+((2+3)x4)-5";
        List<String> list = new ArrayList<>(Arrays.asList(expression.split("")));
        List<String> list1 = parseSuffixExpressionList(list);
        System.out.println(list1);
    }


    public static List<String> parseSuffixExpressionList(List<String> s){
        //符号栈
        Stack<String> s1 = new Stack<>();
        //最终的后缀表达式【【【【；
        List<String> s2 = new ArrayList<>();

        for (String item : s) {
            //遇到操作数时，将其压入s2；
            if (item.matches("\\d+")) {
                s2.add(item);
                //如果是左括号“(”，则直接压入s1
            } else if (item.matches("\\(")) {
                s1.push(item);
            } else if (item.matches("\\)")) {
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃。
                while (!"(".equals(s1.peek())) {
                    s2.add(s1.pop());
                }
                //消除左括号(
                s1.pop();
            } else {
                //当item优先级小于等于栈顶运算符，将s1栈顶的运算符弹出并压入到s2中，再次转到步骤1中与s1中新的栈顶运算符相比较。
                while (s1.size() != 0 && map.get(s1.peek()) >= map.get(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并压入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }


}
