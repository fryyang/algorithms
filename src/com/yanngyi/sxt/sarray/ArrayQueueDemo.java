package com.yanngyi.sxt.sarray;

import java.util.Arrays;

/**
 * @author yangyi
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(10);
        queue.add(10);
        queue.add(8);
        queue.add(5);
        queue.add(9);
        queue.add(6);
        queue.add(3);
        queue.add(1);
        queue.add(2);
        System.out.println(queue.toString());
        while (queue.hasNext()) {
            System.out.println(queue.get());
        }


    }
    static class ArrayQueue {
        private int [] array;
        private int front = -1;
        private int rear = -1;
        private int maxSize = 0;

        public ArrayQueue (int size){
            array = new int[size];
            maxSize = size;
        }

        public boolean add(int x) {
            rear++;
            if (rear > maxSize) {
                return false;
            }
            array[rear] = x;
            return true;
        }

        public int get() {
            front++;
            if (front > rear) {
                return -1;
            }
            return array[front];
        }

        public boolean hasNext(){
            return front <= rear;
        }

        public boolean isEmpty(){
            return front == rear;
        }

        public boolean isFull(){
            return rear == maxSize-1;
        }

        @Override
        public String toString() {
            return "ArrayQueue{" +
                    "array=" + Arrays.toString(array) +
                    '}';
        }
    }
}
