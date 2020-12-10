package com.yanngyi.leetcode.l321;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 难度 困难
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 *
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 *
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 *
 * 示例 1:
 *
 * 输入:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * 输出:
 * [9, 8, 6, 5, 3]
 * 示例 2:
 *
 * 输入:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * 输出:
 * [6, 7, 6, 0, 4]
 *
 * 示例 3:
 *
 * 输入:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * 输出:
 * [9, 8, 9]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/create-maximum-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author yangyi
 */
public class JointMaxNumber {
    /**
     * 设array1长度为m，array2长度为n，array1中取出数字长度为x，array2中取出的数字长度为y，x+y = k，
     * 在array1中取出多个x长度的子序列后，对比取出最大值，在array2中取出多个y长度的子序列后，对比取出最大值，
     * x与y的长度是动态变化的
     * 最后将两个最大的子序列合并
     * @param nums1 数组1
     * @param nums2 数组2
     * @param k 要取出的数字长度
     * @return
     */
    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        // List<Integer> list = new ArrayList() 保存每次循环结束后的最大数，最后对list中的数排序，取出最大数，转为数组
        // 2、按照k的长度从0->k循环，
        // 当nums1取1时，调用getArrayMax(num1, 1)
        // 先判断num2.length>=k-1, 如果符合则num2取k-1，调用getArrayMax(num2, k-1);如果不符合，则继续下次循环
        // 将num1与num2传入方法getJointMaxNumber(array1, array2)中,获取最大数，后加入list中
        List<Integer> list = new ArrayList<>();
        if (k < nums1.length + nums2.length) {
            list = getNumber(nums1, nums2, k);
            list.addAll(getNumber(nums2, nums1, k));
            list.sort(Comparator.comparingInt(a -> a));
            System.out.println(Arrays.toString(list.toArray()));
        }

        return null;
    }

    public static List<Integer> getNumber(int[] nums1, int[] nums2, int k) {
        List<Integer> list = new ArrayList<>();
        int result = 0;
        for (int i = 0; i < k; i++) {
            int[] jointMaxNumber = null;
            if (nums1.length > k-i) {
                jointMaxNumber = getJointMaxNumber(getArrayMax(nums2, i),getArrayMax(nums1, k-i));
            }
            if (nums2.length > k-i) {
                jointMaxNumber = getJointMaxNumber(getArrayMax(nums1, i),getArrayMax(nums2, k-i));
            }
            list.add(Integer.parseInt(Arrays.toString(jointMaxNumber).replaceAll(",","").replace("[","").replace("]","").replaceAll(" ","")));
        }
        return list;
    }



    public static void main(String[] args) {
        int[] array1 = {1,5,3,8,4,7,6,1};
        int[] array2 = {9,5,2};
        maxNumber(array1, array2, 6);
//        int[] arrayMax1 = getArrayMax(array1, 4);
//        int[] arrayMax2 = getArrayMax(array2, 2);
//        System.out.println(Arrays.toString(arrayMax1));
//        System.out.println(Arrays.toString(arrayMax2));
//        int[] jointMaxNumber = getJointMaxNumber(arrayMax1, arrayMax2);
//        System.out.println(Arrays.toString(jointMaxNumber));

    }

    /**
     * 例如：数组中有五个数[1,9,5,7,1]从中取出四个数，需要相对顺序排列，保证最后取出的数字最大
     * 先从一个数组中取出n个数字，求出可以组成的最大的数
     * @param arrays 数组
     * @param k 取k个数
     * @return 按相对顺序排列的最大数
     */
    public static int[] getArrayMax(int [] arrays, int k){
        int length = arrays.length;
        int[] stack = new int[k];
        int top = -1;
        int remain = length - k;
        for (int num : arrays) {
            while (top >= 0 && stack[top] < num && remain > 0) {
                top--;
                remain--;
            }
            if (top < k - 1) {
                stack[++top] = num;
            } else {
                remain--;
            }
        }
        return stack;
    }

    /**
     * 给出两串排好序的数字，将两串数字组合为最大数，相对顺序不能变。
     * @param array1 数组1
     * @param array2 数组2
     * @return 最大数
     */
    public static int[] getJointMaxNumber(int [] array1, int [] array2){
        int n = array1.length + array2.length;
        int [] result = new int[n];
        int k = 0;
        int a1 = 0;
        int a2 = 0;
        while (k != n) {
            if (a1 == array1.length) {
                while (a2 < array2.length) {
                    result[k] = array2[a2];
                    a2++;
                    k++;
                }
                return result;
            } else if (a2 == array2.length) {
                while (a1 < array1.length) {
                    result[k] = array1[a1];
                    a1++;
                    k++;
                }
                return result;
            }
            if (array1[a1] > array2[a2]) {
                result[k] = array1[a1];
                a1++;
            } else {
                result[k] = array2[a2];
                a2++;
            }
            k++;
        }
        return result;
    }



}
