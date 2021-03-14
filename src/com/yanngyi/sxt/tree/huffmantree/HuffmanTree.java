package com.yanngyi.sxt.tree.huffmantree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author yangyi
 * 哈夫曼树
 */
public class HuffmanTree {

    public static void main(String[] args) {
        int[] arr = {13, 7,8,3,29,6,1};
        Node node = createHuffmanTree(arr);
        preOrder(node);
    }

    /**
     * 创建哈夫曼树
     * @param arr
     */
    public static Node createHuffmanTree(int[] arr) {
        //第一步 为了操作方便
        //1、遍历 arr 数组
        //2、将arr的每个元素构建成一个Node
        //3、将Node 放入到ArrayList中
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        int i = 0;
        while (nodes.size() > 1) {
            //排序 从小到大
            Collections.sort(nodes);

            System.out.println("nodes：" +nodes);

            //取出根节点权值最小的两棵二叉树
            //(1) 取出权值最小的结点（二叉树）
            Node leftNode = nodes.get(0);
            //(2) 取出权值第二小的结点（二叉树）
            Node rightNode = nodes.get(1);

            //(3)构建一棵新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //(4)从arrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //(5)将parent加入到nodes
            nodes.add(parent);

            System.out.println("第"+(i++)+"次加入：" +nodes);
        }
        return nodes.get(0);
    }

    public static void preOrder(Node node) {
        node.preOrder();
    }
}

/**
 * 创建结点类
 * 为了让Node 对象持续排序Collections集合排序
 *
 */
class Node implements Comparable<Node>{
    /**结点权值*/
    int value;
    /**指向左子结点*/
    Node left;
    /**指向右子结点*/
    Node right;

    public Node (int value) {
        this.value = value;
    }

    public void preOrder(){
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大排序
        return this.value - o.value;
    }
}
