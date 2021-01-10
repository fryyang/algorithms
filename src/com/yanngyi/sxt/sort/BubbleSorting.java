package com.yanngyi.sxt.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * @author yangyi
 */
public class BubbleSorting {
    public static void main(String[] args) {
        int arr[] = {3,9,10,20,30};
        bubbleSort(arr);

        /*//第一趟排序将最大的数排在最后
        for (int j = 0; j < arr.length - 1; j++) {
            if (arr[j]>arr[j+1]) {
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
        System.out.println("第一趟排序结果");
        System.out.println(Arrays.toString(arr));

        //第二趟排序，就是将第二大的数排在倒数第二位
        for (int j = 0; j < arr.length - 2; j++) {
            if (arr[j]>arr[j+1]) {
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
        System.out.println("第二趟排序结果");
        System.out.println(Arrays.toString(arr));

        //第三趟排序，就是将第三大的数排在倒数第三位
        for (int j = 0; j < arr.length - 3; j++) {
            if (arr[j]>arr[j+1]) {
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
        System.out.println("第三趟排序结果");
        System.out.println(Arrays.toString(arr));

        //第四趟排序，就是将第四大的数排在倒数第四位
        for (int j = 0; j < arr.length - 4; j++) {
            if (arr[j]>arr[j+1]) {
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
        System.out.println("第四趟排序结果");
        System.out.println(Arrays.toString(arr));*/



    }

    private static void bubbleSort(int arr[]){
        int temp = 0;
        int count = 0;
        boolean flag = true;
        for (int i = 0; i<arr.length-1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j]>arr[j+1]) {
                    flag = false;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
                count++;
            }
            System.out.println(count);
            System.out.println("第"+(i+1)+"趟排序结果");
            System.out.println(Arrays.toString(arr));
            if (flag) {
                break;
            } else {
                flag = true;
            }
        }
    }
}
