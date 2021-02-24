package com.yanngyi.sxt.sort;

import java.util.Arrays;

/**
 * 基数排序
 * @author yangyi
 */
public class RadixSorting {
    public static void main(String[] args) {
        int[] arr = {53,3,542,748,14,214};
        radixSort(arr);
    }

    public static void radixSort(int[] arr) {

        //1、得到数组中最大的数的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();



        //第一轮排序（针对每个元素的个位进行排序处理）

        //定义一个二维数组，表示10个桶，每个桶都是一个一维数组
        //说明
        //1、二维数组包含10个一维数组
        //2、为了防止在放入数的时候，数据溢出，则每个一维数组（桶），大小定义为arr.length
        //3、明确，基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入的数据个数
        int[] bucketElementCounts = new int[10];

        for (int k = 0, n = 1; k < maxLength; k++, n*=10) {
            //第k轮（针对每个元素的n位进行排序处理）
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素个位的值
                int digitIfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitIfElement][bucketElementCounts[digitIfElement]] = arr[j];
                bucketElementCounts[digitIfElement]++;
            }
            //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
            int index = 0;
            //遍历每一个桶，并将桶中数据，放入到原数组
            for (int i = 0; i < bucketElementCounts.length; i++) {
                //判断当前桶中是否有数据
                if (bucketElementCounts[i] != 0) {
                    //循环该桶 即第k个桶
                    for (int l = 0; l < bucketElementCounts[i]; l++) {
                        //取出元素 放入到arr
                        arr[index] = bucket[i][l];
                        index++;
                    }
                }
                //将桶的计数清零
                bucketElementCounts[i] = 0;
            }

            System.out.println("第一轮基数排序结果:"+ Arrays.toString(arr));
        }

        /*//第一轮（针对每个元素的个位进行排序处理）
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素个位的值
            int digitIfElement = arr[j] % 10;
            //放入到对应的桶中
            bucket[digitIfElement][bucketElementCounts[digitIfElement]] = arr[j];
            bucketElementCounts[digitIfElement]++;
        }
        //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
        int index = 0;
        //遍历每一个桶，并将桶中数据，放入到原数组
        for (int i = 0; i < bucketElementCounts.length; i++) {
            //判断当前桶中是否有数据
            if (bucketElementCounts[i] != 0) {
                //循环该桶 即第k个桶
                for (int l = 0; l < bucketElementCounts[i]; l++) {
                    //取出元素 放入到arr
                    arr[index] = bucket[i][l];
                    index++;
                }
            }
            //将桶的计数清零
            bucketElementCounts[i] = 0;
        }

        System.out.println("第一轮基数排序结果:"+ Arrays.toString(arr));


        //第二轮（针对每个元素的十位进行排序处理）
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素十位的值
            int digitIfElement = arr[j] / 10 % 10;
            //放入到对应的桶中
            bucket[digitIfElement][bucketElementCounts[digitIfElement]] = arr[j];
            bucketElementCounts[digitIfElement]++;
        }
        //按照这个痛的顺序（一维数组的下标依次取出数据，放入原来数组）
        index = 0;
        //遍历每一个桶，并将桶中数据，放入到原数组
        for (int i = 0; i < bucketElementCounts.length; i++) {
            //判断当前桶中是否有数据
            if (bucketElementCounts[i] != 0) {
                //循环该桶 即第k个桶
                for (int l = 0; l < bucketElementCounts[i]; l++) {
                    //取出元素 放入到arr
                    arr[index] = bucket[i][l];
                    index++;
                }
            }
            //将桶的计数清零
            bucketElementCounts[i] = 0;
        }

        System.out.println("第二轮基数排序结果:"+ Arrays.toString(arr));

        //第三轮（针对每个元素的个位进行排序处理）
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素个位的值
            int digitIfElement = arr[j] / 100 % 10;
            //放入到对应的桶中
            bucket[digitIfElement][bucketElementCounts[digitIfElement]] = arr[j];
            bucketElementCounts[digitIfElement]++;
        }
        //按照这个痛的顺序（一维数组的下标依次取出数据，放入原来数组）
        index = 0;
        //遍历每一个桶，并将桶中数据，放入到原数组
        for (int i = 0; i < bucketElementCounts.length; i++) {
            //判断当前桶中是否有数据
            if (bucketElementCounts[i] != 0) {
                //循环该桶 即第k个桶
                for (int l = 0; l < bucketElementCounts[i]; l++) {
                    //取出元素 放入到arr
                    arr[index] = bucket[i][l];
                    index++;
                }
            }
            //将桶的计数清零
            bucketElementCounts[i] = 0;
        }

        System.out.println("第三轮基数排序结果:"+ Arrays.toString(arr));
*/
    }
}
