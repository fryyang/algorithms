package com.yanngyi.sxt.slinked;

/**
 * 单向环形链表用来解决约瑟夫问题
 * 1、出链表时需要一层循环 + 循环内的判断
 * （1）n = k % size 为当前链表第n个需要出列的节点
 * （2）第一层循环是判断链表中当前节点是否还存在
 * （3）判断当前链表是否只有一个节点，如果是则直接输出并跳出循环。否则（4）
 * （4）初始化i=1；
 * （5）先取出第一个节点（pre）与第一个节点的下个节点（cur）；i++
 * （6）循环判断i是否等于k 如果等于进入（7），如果不等于继续下一个节点（cur） i++；
 * （7）将pre.next = cur.next；将当前节点的下个节点指向当前节点前一个节点的下个节点
 *     head = cur.next；并将当前节点的下个节点置为头节点
 * （8）重复（2）
 *
 * @author yangyi
 */
public class SingleRingLinked {

    Node head;
    Node detail;
    int size;

    public static void main(String[] args) {
        SingleRingLinked ringLinked = new SingleRingLinked();
        ringLinked.add(new Node(1));
        ringLinked.add(new Node(2));
        ringLinked.add(new Node(3));
        ringLinked.add(new Node(4));
        ringLinked.add(new Node(5));
        ringLinked.add(new Node(6));
//        ringLinked.list();
//        ringLinked.pop(3);
//        ringLinked.pop(2);
        ringLinked.pop(4);
    }

    void pop(int k){
        //k % size 为当前链表第n个需要出列的节点
        int n = k % size;
        while (size > 0) {
            if (size == 1) {
                System.out.println(head.toString());
                size--;
            } else {
                int i = 1;
                //记录当前节点的前一个节点
                Node pre = head;
                Node cur = head.next;
                i++;
                while (i != n) {
                    i++;
                    pre = cur;
                    cur = cur.next;
                }
                size--;
                System.out.println(cur.toString());
                pre.next = cur.next;
                head = cur.next;
                // k=3
                //123456 -3
                //45612  -6
                //1245   -4
                //512    -2
                //51     -5
                //1      -1
                // k=2
                //123456 -2
                //34561  -4
                //5613   -6
                //135    -3
                //51     -1
                //5      -5
                // k=4
                //123456 -4
                //56123  -2
                //3561   -1
                //356    -3
                //56     -6
                //       -5
            }
        }

    }

    void list() {
        if (head != null) {
            size--;
            System.out.println(head.toString());
            Node next = head.next;
            while (size > 0) {
                System.out.println(next.toString());
                next = next.next;
                size--;
            }
        }
    }

    void add(Node node) {
        if (head == null) {
            head = node;
            detail = node;
            size++;
            return;
        }
        if (size == 1) {
            head.next = node;
            node.next = head;
            detail = node;
            size++;
            return;
        }
        detail.next = node;
        node.next = head;
        detail = node;
        size++;
    }


    static class Node {
        Node next;
        int sequence;

        public Node(int sequence) {
            this.sequence = sequence;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "sequence=" + sequence +
                    '}';
        }
    }
}
