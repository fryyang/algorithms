package com.yanngyi.sxt.slinked;

/**
 * @author yangyi
 */
public class DoubleLinked {
    private Node head;
    private Node detail;
    private int size;

    public static void main(String[] args) {
        DoubleLinked doubleLinked = new DoubleLinked();
        Node firstNode = new Node(1, "第一节点");
        Node secondNode = new Node(2, "第二节点");
        Node thirdNode = new Node(3, "第三节点");
        Node fourNode = new Node(4, "第四节点");
        Node fiveNode = new Node(5, "第五节点");
        doubleLinked.add(firstNode);
        doubleLinked.add(secondNode);
        doubleLinked.add(thirdNode);
        doubleLinked.add(fourNode);
        doubleLinked.add(fiveNode);
        doubleLinked.list();
        doubleLinked.update(new Node(2, "第2节点"));
        doubleLinked.list();
        doubleLinked.del(new Node(5, "第2节点"));
        doubleLinked.list();
    }

    public void del(Node node){
        int delNo = node.no;
        if (size > 0) {
            if (detail != null && delNo > size / 2) {
                //从后往前遍历并修改
                if (detail.no == delNo) {
                    //先将尾节点的前一个节点取出
                    Node newDetail = detail.pre;
                    //将尾节点的前一个节点的next置为null
                    detail.pre.next = null;
                    //将尾节点的前一个节点置为null
                    detail.pre = null;
                    detail = newDetail;
                    size--;
                    return;
                }
                Node cur = detail.pre;
                while (cur != null) {
                    if (cur.no == delNo) {
                        Node newNextNode = cur.next;
                        cur.pre.next = newNextNode;
                        cur.pre = newNextNode.pre;
                        cur.next = null;
                        cur.pre = null;
                        size--;
                        return;
                    }
                    cur  = cur.pre;
                }
            } else {
                //从前往后遍历并修改
                if (head.no == delNo) {
                    //先将头节点的下一个节点取出
                    Node newHead = head.next;
                    //将头节点的下一个节点的pre置为null
                    head.next.pre = null;
                    //将头节点的下一个节点置为null
                    head.next = null;
                    head = newHead;
                    size--;
                    return;
                }
                Node cur = head.next;
                while (cur != null) {
                    if (cur.no == delNo) {
                        Node newNextNode = cur.next;
                        cur.pre.next = newNextNode;
                        cur.pre = newNextNode.pre;
                        cur.next = null;
                        cur.pre = null;
                        size--;
                        return;
                    }
                    cur  = cur.next;
                }
            }
        }
    }

    public void update(Node node){
        int updateNo = node.no;
        if (size > 0) {
            if (detail != null && updateNo > size / 2) {
                //从后往前遍历并修改
                if (detail.no == updateNo) {
                    detail.name = node.name;
                    return;
                }
                Node cur = detail.pre;
                while (cur != null) {
                    if (cur.no == updateNo) {
                        cur.name = node.name;
                        return;
                    }
                    cur  = cur.pre;
                }
            } else {
                //从前往后遍历并修改
                if (head.no == updateNo) {
                    head.name = node.name;
                    return;
                }
                Node cur = head.next;
                while (cur != null) {
                    if (cur.no == updateNo) {
                        cur.name = node.name;
                        return;
                    }
                    cur  = cur.next;
                }
            }
        }


    }


    public void add(Node node){
        if (head == null) {
            head = node;
            size++;
            return;
        }
        if (detail == null) {
            head.next = node;
            node.pre = head;
        } else {
            //将尾节点赋给当前节点的pre
            node.pre = detail;
            //将当前节点赋给尾节点的next
            detail.next = node;
            //将当前节点赋给尾节点
        }
        size++;
        detail = node;

    }

    public void list(){
        if (size > 0) {
            System.out.println(head.toString());
            Node cur = head.next;
            while (cur != null) {
                System.out.println(cur.toString());
                cur  = cur.next;
            }
        }
    }


    static class Node {
        Node pre;
        Node next;
        int no;
        String name;

        public Node(int no, String name) {
            this.no = no;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
