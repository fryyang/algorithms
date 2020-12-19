package com.yanngyi.sxt.recursing;

/**
 * 八皇后问题的实现
 * 问题表述为：
 * 在8×8格的国际象棋上摆放8个皇后，使其不能互相攻击，即任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。
 * @author yangyi
 */
public class EightQueen {

    /**定义有几个皇后*/
    static int max = 8;
    /**定义数组array，保存皇后放置位置的结果，比如arr = {0,4,7,5,2,6,1,3}*/
    static int[] array = new int[max];
    static int count = 0;
    public static void main(String[] args) {
        check(0);
        System.out.println(count);
    }

    /**
     * 放置第n个皇后
     * @param n
     */
    private static void check(int n){
        //当n==8时，就是在放置第九个皇后
        if (n == max) {
            print();
            count++;
            return;
        }
        //依次将皇后放入，并且判断是否冲突
        for (int i = 0; i < max; i++) {
            //将第n个皇后放置到n行的i列
            array[n] = i;
            //判断将皇后放入i列后是否跟之前皇后冲突
            if (judge(n)) {
                check(n+1);
            }
            //如果冲突 就继续执行出栈，退出本次循环，进入下一次循环，将皇后放入n行的下一列 i++; array[n] = i;
        }
    }

    /**
     * 当我们放置第n个皇后时判断它与之前的皇后是否冲突
     * @param n 当前需要放置的第n个皇后
     * @return
     */
    private static boolean judge(int n){
        for (int i = 0; i < n; i++) {
            //array[i] == array[n] 判断当前第i行的array[i]列与当前第n行的array[n]列，是否在同一列
            //Math.abs(n-i) == Math.abs(array[n] - array[i])
            //判断第i个皇后与第n个皇后是否在同一条斜线上
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印结果
     */
    private static void print(){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
