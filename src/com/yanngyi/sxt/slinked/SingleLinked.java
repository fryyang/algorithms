package com.yanngyi.sxt.slinked;


import java.util.Stack;

/**
 * 单链表的面试题
 * 1、求单链表中节点的个数
 * 2、查找单链表中的倒数第k个节点 （新浪面试题）
 * 3、单链表的反转（腾讯面试题）
 * 4、从尾到头打印单链表（百度面试题 方式 1、反向遍历 2、Stack栈）
 * 5、合并两个有序的单链表
 * @author yangyi
 */
public class SingleLinked {
    HeroNode firstNode = new HeroNode(0,"首节点", "首节点");
    HeroNode detail;
    HeroNode first;

    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "第二节点", "第二节点");
        HeroNode heroNode2 = new HeroNode(2, "第三节点", "第三节点");

        HeroNode heroNode3 = new HeroNode(3, "第四节点", "第四节点");
        SingleLinked singleLinked = new SingleLinked();
        singleLinked.addByOrder(heroNode3);
        singleLinked.addByOrder(heroNode2);
        singleLinked.addByOrder(heroNode1);

        singleLinked.list();
//        HeroNode heroNode4 = new HeroNode(1, "新一节点", "新一节点");
//        singleLinked.update(heroNode4);
//        singleLinked.list();

//        int length = singleLinked.length();
//        System.out.println(length);
//        HeroNode indexNode = singleLinked.getIndexNode(1);
//        System.out.println(indexNode.toString());

        singleLinked.reverse();
        singleLinked.list();

        int a = 0;
        int b = 2;
        int c = 1;
        a = b;
        b = c;
        System.out.println(b = a);

//        Stack<HeroNode> heroNodeStack = singleLinked.stackReverse();
//        System.out.println(heroNodeStack.pop().toString());

    }

    public void add(HeroNode node) {
        HeroNode temp = firstNode;
        while (true) {
            if (temp.next == null) {
                temp.next = node;
                break;
            }
            temp = temp.next;
        }
    }

    /**
     * 找到链表的当前节点并修改
     * @param update
     */
    public void update(HeroNode update){
        HeroNode node = firstNode;
        while (true) {
            //链表为空
            if (node.next == null) {
                break;
            }
            //已找到
            if (node.next.no == update.no) {
                node.next.name = update.name;
                node.next.nickName = update.nickName;
                System.out.println("修改成功");
                break;
            }
            //找到的节点已大于当前节点
            else if (node.next.no > update.no) {
                System.out.println("没找到");
                break;
            }
            node = node.next;
        }
    }

    /**
     * 按照序号添加
     * @param node 当前加入节点
     */
    public void addByOrder(HeroNode node) {
        HeroNode temp = firstNode;
        boolean flag = false;
        while (true) {
            //temp已经遍历到了链表最后
            if (temp.next == null) {
                temp.next = node;
                break;
            }
            //位置找到
            if (temp.next.no > node.no) {
                node.next = temp.next;
                temp.next = node;
                break;
            } else if (temp.next.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("号码已存在");
        }
    }

    public void list() {
        HeroNode node = firstNode;
        while (true) {
            if (node.next == null) {
                System.out.println(node.toString());
                break;
            }
            System.out.println(node.toString());
            node = node.next;
        }
    }

    public void reverse() {
        HeroNode reverseNode = new HeroNode(0,"反转首节点","反转首节点");
        //cur的含义为旧单链表的下一个node
        HeroNode cur = firstNode.next;
        //保存旧单链表的关系
        HeroNode next = null;
        while (cur != null) {
            //当cur为第一节点时，next为第二节点
            //当cur为第二节点时，next为第三节点
            //a = b;
            next = cur.next;
            //1、当reverseNode第一次插入节点时，reverseNode.next为null，将null赋值给加入第一节点的next
            //2、当reverseNode第二次插入节点时，reverseNode.next为第一节点，将第一节点赋值给加入第二节点的next
            //b = c;
            cur.next = reverseNode.next;
            //将第一节点存入反转首节点的next
            //将第二节点存入反转首节点的next
            reverseNode.next = cur;
            //将cur.next第二节点还原
            //将cur.next第三节点还原
            //d = a;
            cur = next;
        }
        firstNode.next = reverseNode.next;
    }

    public void del(int no){
        HeroNode node = firstNode;
        while (true) {
            //链表为空
            if (node.next == null) {
                break;
            }
            //已找到
            if (node.next.no == no) {
                node.next = node.next.next;
                System.out.println("已找到");
                break;
            }
            node = node.next;
        }
    }

    /**
     * 获取链表的长度
     * @return
     */
    public int length(){
        HeroNode node = firstNode;
        int i = 0;
        while (true) {
            //链表为空
            if (node.next == null) {
                i++;
                break;
            }
            if (node.no != 0) {
                i++;
            }
            node = node.next;
        }
        return i;
    }

    /**
     * 获取链表的长度
     * @return
     */
    public Stack<HeroNode> stackReverse(){
        Stack<HeroNode> heroNodeStack = new Stack<>();
        HeroNode node = firstNode;
        while (node.next != null) {
            //链表为空
            node = node.next;
            heroNodeStack.push(node);
        }
        return heroNodeStack;
    }

    /**
     * 获取单向链表的倒数第index个节点
     * 1、先获取链表的总长度
     * 2、再将链表的 j = 总长度-index+1
     * 3、在遍历链表，到第j个停止 并返回
     * @param index 节点下标
     * @return
     */
    public HeroNode getIndexNode(int index) {
        HeroNode node = firstNode;
        int i = 0;
        while (true) {
            //链表为空
            if (node.next == null) {
                i++;
                break;
            }
            if (node.no != 0) {
                i++;
            }
            node = node.next;
        }
        int sequence =  i - index + 1;
        int j = 0;
        node = firstNode;
        while (j != sequence) {
            j++;
            node = node.next;
        }
        return node;
    }


    static class HeroNode {
        int no;
        String name;
        String nickName;
        HeroNode next;
        public HeroNode (int no, String name, String nickName) {
            this.name = name;
            this.no = no;
            this.nickName = nickName;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickName='" + nickName + '\'' +
                    '}';
        }
    }


}
