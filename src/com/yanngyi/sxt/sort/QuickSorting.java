package com.yanngyi.sxt.sort;

import java.util.Arrays;

/**
 * 快速排序
 * @author yangyi
 */
public class QuickSorting {
    public static void main(String[] args) {
        int arr[] = {-9,78,0,23,5,70};
        quickSort(arr,0,arr.length - 1);

        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right){
        //左下标
        int l = left;
        //右下标
        int r = right;
        int temp = 0;
        //中间值
        int pivot = arr[(left + right) / 2];

        while (l < r) {
            //在中间值的左边一直找，找到大于等于pivot的值，才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            //在中间值的右边一直找，找到小于等于pivot的值，才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            if (l >= r) {
                break;
            }
            //将左边大于pivot与右边小于pivot的值进行交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //如果交换完后，发现这个arr[l] == pivot值 r前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后，发现这个arr[r] == pivot值 l后移
            if (arr[r] == pivot) {
                l += 1;
            }

        }
        //如果l == r, 必须l++，r--，否则会出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }

        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }

    }


    public static void quickSorting(int[] arr, int left, int right){
        // 1，5，4，2，3
        // 1，3，4，2，5
        // 1，3，2，4，5
        int l = left;
        int r = right;
        int pivot = arr[(left + right)/2];
        int temp = 0;

        
    }
}
