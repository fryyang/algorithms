package com.yanngyi.sxt.search;

/**
 * @author yangyi
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        int i = binarySearch(arr, 0, 4, 6);
        if (i == -1) {
            System.out.println("未找到");
        } else {
            System.out.println("找到了当前下标为："+ i);
        }
    }

    public static int binarySearch(int[] arr, int left, int right, int findValue){
        if (left > right) {
            return -1;
        }
        int mid = (left + right)/2;
        if (arr[mid] == findValue) {
            return mid;
        }
        if (arr[mid] > findValue) {
            return binarySearch(arr, left, mid-1, findValue);
        } else if (arr[mid] < findValue) {
            return binarySearch(arr, mid+1, right, findValue);
        } else {
            return mid;
        }
    }
}
