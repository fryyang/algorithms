package com.yanngyi.sxt.search;

/**
 * 插值查找
 * @author yangyi
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,7,8,9,10};
        int i = insertValueSearch(arr, 0, 9, 8);
        if (i == -1) {
            System.out.println("未找到");
        } else {
            System.out.println("找到了当前下标为："+ i);
        }
    }

    public static int insertValueSearch(int[] arr,int left, int right, int findValue){
        System.out.println("---");
        if (left > right || findValue < arr[0] || findValue > arr[arr.length - 1]) {
            return -1;
        }
        int mid = left + (right-left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        if (arr[mid] == findValue) {
            return mid;
        }
        if (arr[mid] > findValue) {
            return insertValueSearch(arr, left, mid-1, findValue);
        } else if (arr[mid] < findValue) {
            return insertValueSearch(arr, mid+1, right, findValue);
        } else {
            return mid;
        }
    }
}
