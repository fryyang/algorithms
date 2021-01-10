package com.yanngyi.sxt.sort;

import java.util.Arrays;

/**
 * 选择排序比冒泡排序快的原因：
 * 首先选择排序比冒泡排序所用的时间更少，
 * 因为冒泡排序一旦相邻两个元素之间逆序，则需要马上交换，
 * 而选择排序在遍历整个数组的过程中，只是替换最小元素指针，最后遍历结束后作一次最小元素与当前替换下标位置元素的交换。
 * 选择排序
 * @author yangyi
 */
public class SelectSorting {


    public static void selectSorting(int arr[]) {

        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }

/*        int minIndex = 0;
        for (int j = 0; j < arr.length; j++) {
            if (arr[minIndex] > arr[j]) {
                minIndex = j;
            }
        }
        int temp = arr[0];
        arr[0] = arr[minIndex];
        arr[minIndex] = temp;

        System.out.println("第一次排序");
        System.out.println(Arrays.toString(arr));

        minIndex = 1;
        for (int j = 1; j < arr.length; j++) {
            if (arr[minIndex] > arr[j]) {
                minIndex = j;
            }
        }
        temp = arr[1];
        arr[1] = arr[minIndex];
        arr[minIndex] = temp;
        System.out.println("第二次排序");
        System.out.println(Arrays.toString(arr));*/
    }

    public static void main(String[] args) {
        int arr[] = {4,3,2,1};
        selectSorting(arr);
        System.out.println(Arrays.toString(arr));
    }
}
