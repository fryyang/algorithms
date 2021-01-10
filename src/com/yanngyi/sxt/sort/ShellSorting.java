package com.yanngyi.sxt.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * @author yangyi
 */
public class ShellSorting {

    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        int k = 2;
        k -= 1;
        System.out.println(k);
        shellSort(arr);
//        int halfLength = arr.length / 2;
//        for (int j = 5-halfLength; j >= 0; j-=halfLength) {
//            System.out.println(j-=halfLength);
//        }
    }

    /**
     * 希尔排序交换法实现
     * @param arr
     */
    public static void shellSort(int[] arr){
        int temp = 0;
        int halfLength = arr.length / 2;
        while (halfLength > 0) {
            for (int i = halfLength; i < arr.length; i++) {
                //遍历各组中所有的元素（共halfLength组，每组有arr.length/halfLength个元素），当前组第一个元素与当前组的下一个元素的距离为halfLength
                for (int j = i-halfLength; j >= 0; j-=halfLength) {
                    //如果当前组第一个元素大于当前组第二个元素，那就交换
                    if (arr[j] > arr[j + halfLength]) {
                        temp = arr[j];
                        arr[j] = arr[j + halfLength];
                        arr[j + halfLength] = temp;
                    } else {
                        break;
                    }
                }
            }
            halfLength = halfLength / 2;
        }
        System.out.println("希尔排序第一轮"+Arrays.toString(arr));
        //希尔排序的第一轮排序
        //将数组分成length / 2 组

//        for (int i = halfLength; i < arr.length; i++) {
//            //遍历各组中所有的元素（共5组，每组有2个元素），当前组第一个元素与当前组的下一个元素的距离为5
//            for (int j = i-halfLength; j >= 0; j-=halfLength) {
//                //如果当前组第一个元素大于当前组第二个元素，那就交换
//                if (arr[j] > arr[i]) {
//                    temp = arr[j];
//                    arr[j] = arr[i];
//                    arr[i] = temp;
//                }
//            }
//        }
//        System.out.println("希尔排序第一轮"+Arrays.toString(arr));
//
//        //希尔排序的第二轮排序
//        //将数组分成length / 2 / 2 组
//        halfLength = halfLength / 2;
//        for (int i = 2; i < arr.length; i++) {
//            //遍历各组中所有的元素（共2组，每组有5个元素），当前组第一个元素与当前组的下一个元素的距离为2
//            for (int j = i-2; j >= 0; j-=2) {
//                //如果当前组第一个元素大于当前组第二个元素，那就交换
//                if (arr[j] > arr[j+2]) {
//                    temp = arr[j];
//                    arr[j] = arr[j+2];
//                    arr[j+2] = temp;
//                } else {
//                    break;
//                }
//                System.out.println("希尔排序第二轮"+Arrays.toString(arr) + "i:"+i+"j:"+j);
//            }
//        }
//        //1，5，0，6，3，4，7，2，9，8
//        System.out.println("希尔排序第二轮后"+Arrays.toString(arr));
//        //希尔排序的第三轮排序
//        //将数组分成length / 2 / 2 / 2组
//        for (int i = 1; i < arr.length; i++) {
//            //遍历各组中所有的元素（共1组，每组有10个元素），当前组第一个元素与当前组的下一个元素的距离为1
//            for (int j = i-1; j >= 0; j-=1) {
//                //如果当前组第一个元素大于当前组第二个元素，那就交换
//                if (arr[j] > arr[j+1]) {
//                    temp = arr[j];
//                    arr[j] = arr[j+1];
//                    arr[j+1] = temp;
//                } else {
//                    break;
//                }
//                System.out.println("希尔排序第三轮"+Arrays.toString(arr) + "i:"+i+"j:"+j);
//            }
//        }
//        //1，5，0，6，3，4，7，2，9，8
//        System.out.println("希尔排序第三轮后"+Arrays.toString(arr));
    }
}
