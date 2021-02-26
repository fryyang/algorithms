package com.yanngyi.sxt.search;

import java.util.Arrays;

/**
 * 斐波那契查找算法
 * @author yangyi
 */
public class FibonacciSearch {
    public static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};
        System.out.println(fibSearch(arr, 89));
    }

    /**
     * 因为后面我们使用公式mid = low + F(k-1)-1需要使用斐波那契数列，因此我们需要先获取一个斐波那契数列
     */
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i<maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 非递归的方式编写算法
     * @param a
     * @param key
     * @return
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        //表示斐波那契分割数值的下标
        int k = 0;
        int mid = 0;
        //获取到斐波那契数列
        int[] f = fib();
        //获取到斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        //因为f[k]值 可能大于a的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向a[]
        //不足部分会先使用0填充
        int[] temp = Arrays.copyOf(a, f[k]);
        //实际上需要使用a数组 最后的数 填充temp
        //temp = {1,8,10,89,1000,1234,0,0,0} => {1,8,10,89,1000,1234,1234,1234}
        for (int i = high+1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        //使用while循环来处理，找到我们的key
        while (low <= high) {
            mid = low + f[k-1] -1;
            //应该继续向数组的左边部分查找
            if (key < temp[mid]) {
                high = mid - 1;

                //为什么是k--？
                //1、全部元素 = 前面的元素 + 后面的元素
                //2、f[k] = f[k-1] + f[k-2]
                //因为前面有f[k-1]个元素，所以可以继续拆分f[k-1] = f[k-2] + f[k-3]
                //即 在f[k-1]中继续查找
                //即下次循环 mid = f[k-1-1]-1;
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                //1、全部元素 = 前面的元素 + 后面的元素
                //2、f[k] = f[k-1] + f[k-2]
                //因为后面有f[k-2]个元素，所以可以继续拆分f[k-2] = f[k-3] + f[k-4]
                //即 在f[k-2]中继续查找 k-=2
                //即下次循环 mid = f[k-1-2]-1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }


}
