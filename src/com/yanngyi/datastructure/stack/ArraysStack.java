package com.yanngyi.datastructure.stack;

/**
 * @date 2020-12-11 21:43
 * @author yangyi
 */
public class ArraysStack {

    private int [] stack;
    private int top = -1;
    //private int depth = 0;

    public ArraysStack (int length) {
        stack = new int[length];
    }

    public void push(int n){
        top++;
        stack[top] = n;
    }

    public int pop(){
        if (top > -1) {
            int n = stack[top];
            top--;
            return n;
        } else {
            return 0;
        }
    }

    public boolean empty(){
        return top < 0;
    }

    public void list(){

        for (int i=top; i>-1; i--) {
            System.out.println("栈中第"+i+"个元素为："+stack[i]);

        }
    }

    public static void main(String[] args) {
        ArraysStack stack = new ArraysStack(10);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        stack.push(10);
        //stack.list();
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }

    }
}
