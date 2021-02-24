package com.yanngyi.sxt.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
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
        int[] arr2 = {1,2,3,4,5,6,7,7,7,7,7,7};
        List<Integer> list = binarySearch2(arr2, 0, arr2.length - 1, 7);
        System.out.println(list);
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

    /*
     * 课后练习题 思考一个数组中存在多个同样的值
     * 思路分析
     * 1、在找到mid索引值，不要马上返回
     * 2、向mid索引值的左边扫描，将所有等于findVal的元素的下标，加入到集合ArrayList
     * 3、向mid索引值的右边扫描，将所有等于findVal的元素的下标，加入到集合ArrayList
     * 4、将ArrayList返回*/

     /**
     * @param arr
     * @param left
     * @param right
     * @param findValue
     * @return
     */
    public static List binarySearch2(int[] arr, int left, int right, int findValue){
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right)/2;
        if (arr[mid] > findValue) {
            return binarySearch2(arr, left, mid-1, findValue);
        } else if (arr[mid] < findValue) {
            return binarySearch2(arr, mid+1, right, findValue);
        } else {
            List<Integer> resIndexList = new ArrayList<>();
            resIndexList.add(mid);
            //左移查找
            int temp = mid - 1;
            while (temp >= 0 && arr[temp] == findValue) {
                resIndexList.add(temp);
                temp -= 1;
            }
            //右移查找
            temp = mid + 1;
            while (temp <= arr.length - 1 && arr[temp] == findValue) {
                resIndexList.add(temp);
                temp += 1;
            }
            return resIndexList;
        }

    }
}
