package com.yanngyi.sxt.hashtab;

/**
 * 使用数组+链表实现hashtable
 * @author yangyi
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单

    }
}
    /**
     * 创建HashTab 管理多条链表
     */
    class HashTab {
        private EmpLinkedList[] empLinkedListArray;
        private int size;

        public HashTab(int size) {
            this.empLinkedListArray = new EmpLinkedList[size];
            for (int i = 0; i < size; i++) {
                empLinkedListArray[i] = new EmpLinkedList();
            }
            this.size = size;
        }

        /**
         * 添加雇员
         * @param emp
         */
        public void add(Emp emp) {
            //根据雇员的id，得到该员工应当添加到哪条链表
            int empLinkedListNo = hashFun(emp.id);
            empLinkedListArray[empLinkedListNo].add(emp);
        }

        public  int hashFun(int id) {
            return id % size;
        }

        /**
         * 遍历所有的链表，遍历hashtable
         */
        public void list(){
            for (int i=0; i<size; i++) {
                empLinkedListArray[i].list(i);
            }
        }

        public void finEmpById(int id) {
            int empLinkedListNo = hashFun(id);
            Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
            if (emp != null) {
                System.out.printf("在第%d条链表中找到 雇员id = %d\n", (empLinkedListNo + 1), id);
            } else {
                System.out.println("在哈希表中没有找到该雇员～");
            }
        }
    }

    /**
     * 表示一个雇员
     */
    class Emp {
        int id;
        String name;
        /**
         * next 默认为空
         */
        Emp next;

        public Emp(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }


/**
 * 创建一个EmpLinkedList，表示链表
 */
class EmpLinkedList {
    /**
     * 头指针，执行第一个Emp，因此我们这个链表的head是直接指向第一个Emp
     */
    private Emp head;

    /**
     * 添加雇员到链表
     * 1、假定，当添加雇员时，id是自增长，即id的分配总是从小到大
     * 因此我们将该雇员直接加入到本链表的最后即可
     * @param emp
     */
    public void add(Emp emp) {
        //如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是添加第一个雇员，则使用一个辅助的指针，帮助定位到最后
        Emp curEmp = head;
        while (true) {
            //说明到链表最后
            if (curEmp.next == null) {
                break;
            }
            //后移
            curEmp = curEmp.next;
        }
        //退出时，直接将emp 加入链表
        curEmp.next = emp;
    }

    /**
     * 遍历链表的雇员信息
     */
    public void list(int no) {
        if (head == null) {
            System.out.println("第"+(no+1)+"链表为空！");
        }
        System.out.println("第"+(no+1)+"链表信息为");
        Emp curEmp = head;
        while (true) {
            System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
            //说明到链表最后
            if (curEmp.next == null) {
                break;
            }
            //后移
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    /**
     * 根据ID查找雇员
     * 如果找到，就返回Emp，如果没有找到就返回null
     * @return
     */
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空！");
        }
        //辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                //找到
                break;
            }
            if (curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}