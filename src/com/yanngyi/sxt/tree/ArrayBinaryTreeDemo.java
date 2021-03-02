package com.yanngyi.sxt.tree;

/**
 * @author yangyi
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrayBinaryTree binaryTree = new ArrayBinaryTree(arr);
//        binaryTree.preSequence(0);
//        binaryTree.midSequence(0);

        binaryTree.postSequence(0);
    }

}

class ArrayBinaryTree {
    /**
     * 存储数据节点的数组
     */
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 编写一个方法，完成顺序存储二叉树的前序遍历
     * @param index 数组的下标
     */
    public void preSequence(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("当前数组为空！");
        }
        System.out.println(arr[index]);
        if (2 * index + 1 < arr.length) {
            preSequence(2 * index + 1);
        }
        if (2 * index + 2 < arr.length) {
            preSequence(2 * index + 2);
        }
    }

    /**
     * 编写一个方法，完成顺序存储二叉树的中序遍历
     * @param index 数组的下标
     */
    public void midSequence(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("当前数组为空！");
        }
        if (2 * index + 1 < arr.length) {
            midSequence(2 * index + 1);
        }
        System.out.println(arr[index]);
        if (2 * index + 2 < arr.length) {
            midSequence(2 * index + 2);
        }
    }

    /**
     * 编写一个方法，完成顺序存储二叉树的后序遍历
     * @param index 数组的下标
     */
    public void postSequence(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("当前数组为空！");
        }
        if (2 * index + 1 < arr.length) {
            postSequence(2 * index + 1);
        }
        if (2 * index + 2 < arr.length) {
            postSequence(2 * index + 2);
        }
        System.out.println(arr[index]);
    }


}
