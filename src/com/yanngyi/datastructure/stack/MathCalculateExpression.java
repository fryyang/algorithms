package com.yanngyi.datastructure.stack;

import com.yanngyi.constants.Constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * 使用栈实现数学计算表达式
 *
 * @author yangyi
 * @date 2020-12-12 11:29
 */
public class MathCalculateExpression {

    private Stack<Integer> numberStack = new Stack<>();
    private Stack<Character> operateStack = new Stack<>();
    static Map<Character, Integer> map = new HashMap<>();

    static {
        map.put('+', 1);
        map.put('-', 2);
        map.put('n', 3);
        map.put('*', 4);
        map.put('/', 5);
    }

    public static void main(String[] args) {
        MathCalculateExpression m = new MathCalculateExpression();
        String expression = "7-1+2*3*4-8";
        int result = m.calculateExpression(expression);
        System.out.println(result);
//        Stack<Integer> stack = new Stack<>();
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//        System.out.println(stack.firstElement());
//        System.out.println(stack.firstElement());
    }

    public int calculateExpression(String expression) {
        String[] strings = expression.split("");
        for (String s : strings) {
            if (isInteger(s)) {
                numberStack.push(Integer.parseInt(s));
            } else if (operateStack.empty()) {
                operateStack.push(s.toCharArray()[0]);
            } else if (judgeMathCharacterPriority(s.toCharArray()[0], operateStack.peek())) {
                int calculate = calculate(numberStack.pop(), numberStack.pop(), operateStack.pop());
                numberStack.push(calculate);
                operateStack.push(s.toCharArray()[0]);
            } else {
                operateStack.push(s.toCharArray()[0]);
            }
        }
        while (judgeMathCharacterPriority('n', operateStack.peek())) {
            int calculate = calculate(numberStack.pop(), numberStack.pop(), operateStack.pop());
            numberStack.push(calculate);
        }
        int numberSize = numberStack.size();
        int[] numberArray = new int[numberSize];
        if (!numberStack.empty()) {
            for (int i = numberSize-1; i >= 0; i--) {
                numberArray[i] = numberStack.pop();
            }
        }
        int operateSize = operateStack.size();
        Character[] operateArray = new Character[operateSize];
        if (!operateStack.empty()) {
            for (int i = operateSize-1; i >= 0; i--) {
                operateArray[i] = operateStack.pop();
            }
        }

        while (!operateStack.empty()) {
            Integer pop1 = numberStack.pop();
            Integer pop2 = numberStack.pop();
            int calculate = calculate(pop2, pop1, operateStack.pop());
            numberStack.push(calculate);
        }
        int operateIndex = 0;
        int numberIndex = 0;
        while (operateIndex != operateSize) {
            int num1 = numberArray[numberIndex];
            numberIndex++;
            int num2 = numberArray[numberIndex];
            numberIndex++;
            int result = calculate(num2, num1, operateArray[operateIndex]);
            operateIndex++;
            --numberIndex;
            numberArray[numberIndex] = result;
        }
        if (numberArray.length > 0) {
            numberStack.push(numberArray[numberArray.length-1]);
        }
        return numberStack.pop();
    }

    private boolean judgeMathCharacterPriority(Character c1, Character c2) {
        return map.get(c1) <= map.get(c2);
    }

    public int calculate(int num1, int num2, Character c) {
        int n = 0;
        switch (c.toString()) {
            case Constants.MULTIPLE:
                n = num2 * num1;
                break;
            case Constants.DIVIDE:
                n = num2 / num1;
                break;
            case Constants.SUBTRACT:
                n = num2 - num1;
                break;
            default:
                n = num2 + num1;
        }
        return n;
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
}
