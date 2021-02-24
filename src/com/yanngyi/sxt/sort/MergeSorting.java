package com.yanngyi.sxt.sort;

import java.util.Arrays;

/**
 * 归并排序
 * @author yangyi
 */
public class MergeSorting {
    public static void main(String[] args) {
        int[] arr = {8,4,5,7,1,3,6,2};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length-1, temp);

        System.out.println("归并排序后="+ Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid+1, right, temp);
            //合并
            //第一次合并时 left=0 mid=0 right=1
            //第二次合并时 left=2 mid=2 right=3
            //第三次合并时 left=0 mid=1 right=3
            //第四次合并时 left=4 mid=4 right=5
            //第五次合并时 left=6 mid=6 right=7
            //第六次合并时 left=4 mid=5 right=7
            //第七次合并时 left=0 mid=3 right=7
            System.out.println("第n次合并时 left="+ left +" mid="+ mid+" right="+right);
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 归并排序
     * @param arr 排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边索引
     * @param temp 做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //左边有序序列的初始索引
        int i = left;
        //右边有序序列的初始索引
        int j = mid + 1;
        //指向temp数组的当前索引
        int t = 0;

        //(一)
        //先把左右两边（有序）的数据 按照规则填充到temp数组
        //直到左右两边的有序序列，有一遍处理完毕为止
        while (i <= mid && j <= right) {
            //如果左边的有序序列的当前元素，小于等于右边序列的当前元素
            //将左边序列的当前元素拷贝到temp数组
            //然后t++，i++
            if(arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                //如果左边的有序序列的当前元素，小于等于右边序列的当前元素
                //将左边序列的当前元素拷贝到temp数组
                //然后t++，j++
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        //把有剩余数据的一遍的数据依次全部填充到temp

        //(二)
        //左边的有序序列还有剩余的元素，全部填充到temp
        while (i <= mid) {
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        //右边的有序序列还有剩余的元素，全部填充到temp
        while (j <= right) {
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        //(三)
        //将temp数组的元素拷贝到arr
        //注意，并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        //第一次合并tempLeft = 0，right = 1
        //第二次合并tempLeft = 2，right = 3
        //第三次合并tempLeft = 0，right = 3
        //......
        //最后一次合并tempLeft = 0，right = 7
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            tempLeft += 1;
            t += 1;
        }
    }
}
