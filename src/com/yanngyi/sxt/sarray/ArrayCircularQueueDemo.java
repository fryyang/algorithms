package com.yanngyi.sxt.sarray;

import java.util.Arrays;

/**
 * @author yangyi
 */
public class ArrayCircularQueueDemo {
    public static void main(String[] args) {

        ArrayCircularQueue queue = new ArrayCircularQueue(10);
        queue.add(10);
        queue.add(8);
        queue.add(5);
        queue.add(9);
        queue.add(6);
        queue.add(3);
        queue.add(1);
        queue.add(2);
        queue.add(7);
        queue.add(6);
        System.out.println(queue.toString());

        System.out.println(queue.get());
        System.out.println(queue.get());
        System.out.println(queue.get());

        queue.add(11);
        queue.add(12);
        queue.add(13);

        System.out.println("rear" + queue.rear);
        System.out.println("front" + queue.front);
        if (queue.isFull()) {
            System.out.println("满了");
        }
        System.out.println(queue.toString());
//        while (queue.hasNext()) {
//            System.out.println(queue.get());
//        }

    }
    static class ArrayCircularQueue {
        private int [] array;
        private int front = 0;
        private int rear = 0;
        private int length = 0;

        public ArrayCircularQueue (int size){
            array = new int[size];
            length = size;
        }

        public boolean add(int x) {
            //如果rear%length -front = 1，那么说明数组已经满了
            if (isFull()) {
                return false;
            }
            array[rear] = x;
            rear = (rear + 1) % length;
            return true;
        }

        public int get() {
            if (isEmpty()) {
                return -1;
            }
            int x = array[front];
            front = (front + 1) % length;
            return x;
        }

        public boolean hasNext(){
            return front%length < rear%length;
        }

        public boolean isEmpty(){
            return front == rear;
        }

        public boolean isFull(){
            return (rear + 1) % length  == front;
        }

        @Override
        public String toString() {
            return "ArrayQueue{" +
                    "array=" + Arrays.toString(array) +
                    '}';
        }
    }
}
