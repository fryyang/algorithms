package com.yanngyi.sxt.sort;

import java.util.Arrays;

/**
 * 插入排序
 * @author yangyi
 */
public class InsertSorting {
    public static void main(String[] args) {
        int[] arr = {17,3,25,14,20,9};
        //第一次排序结果 3,17,25,14,20,9
        //第二次排序结果 3,17,25,14,20,9
        //第三次排序结果 3,14,17,25,20,9
        //第四次排序结果 3,14,17,20,25,9
        //第五次排序结果 3,9,14,17,20,25
        insertSorting(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSorting(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            int insertValue = arr[i];
            //取出当前需要插入数的下标
            int index = i;
            //index > 0，说明还没有与待插入数字之前的数字比对结束
            //arr[index-1] > insertValue
            //在index指针前移的过程中，比较待插入的数字是否小于index-1对应的数字（待插入数字前index-1位数字）
            while (index > 0 && arr[index-1] > insertValue) {
                //如果小于则将数组下标为index-1对应的数字后移一位
                arr[index] = arr[index-1];
                //并将数组下标index前移，以便于继续比较
                index--;
            }
            //比较结束后，将待插入的数字，存入数组下标为index位置
            arr[index] = insertValue;
        }
    }
}
