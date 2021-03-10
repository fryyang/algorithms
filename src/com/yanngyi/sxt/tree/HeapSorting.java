package com.yanngyi.sxt.tree;

import java.util.Arrays;

/**
 * 堆排序
 * @author yangyi
 */
public class HeapSorting {
    public static void main(String[] args) {
        //要求将数组进行升序排列
        int[] arr = {4,6,8,5,9};
        heapSort(arr);
    }

    public static void heapSort(int[] arr) {
        System.out.println("堆排序");

//        adjustHeap(arr,1,arr.length);
//        System.out.println("第一次"+Arrays.toString(arr));
//        adjustHeap(arr,0,arr.length);
//        System.out.println("第二次"+Arrays.toString(arr));

        //1）将一个无需数组构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length /2 -1; i>=0; i--) {
            adjustHeap(arr,i,arr.length);
        }
        int temp = 0;
        //2）将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端
        //3）重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列调整结束
        for (int j = arr.length-1;j>=0;j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }



        System.out.println("数组"+Arrays.toString(arr));
    }

    /**
     * 功能：完成将以i对应的子树调整成大顶堆
     * 举例 int[] arr = {4,6,8,5,9}; => i=1 arr = {4,9,8,5,6}
     * 将一个数组（二叉树），调整成一个大顶堆
     * @param arr 待调整的数组
     * @param i 非叶子节点 在数组中的索引
     * @param length 表示对多少个元素进行调整，length是在逐渐的减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        //先取出当前需要比较的子树的根节点
        int temp = arr[i];
        //k = i * 2 + 1 k是以i为子树的父节点的左子节点 k+1为右子节点
        for (int k = i*2+1; k<length;k=k*2+1) {
            //说明左子节点小于右子节点的值
            if (k+1 < length && arr[k] < arr[k+1]) {
                //k指向右子节点
                k++;
            }
            //如果子节点大于父节点
            if (arr[k] > temp) {
                //把较大的值赋给当前节点
                arr[i] = arr[k];
                //i指向k，继续循环比较
                i = k;
            } else {
                //此处，说明左右节点 与 父节点 比较过后 父节点依然是最大的。
                break;
            }
        }
        //当for循环结束后 我们已经将以i为父节点的树的最大值 放在了子树的顶部

        //将temp放到调整后的位置
        arr[i] = temp;
    }
}
