package com.yanngyi.sxt.tree.threadedbinarytree;

/**
 * @author yangyi
 */
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "root");
        HeroNode three = new HeroNode(3, "3");
        HeroNode eight = new HeroNode(8, "8");
        HeroNode ten = new HeroNode(10, "10");
        HeroNode six = new HeroNode(6, "6");
        HeroNode fourteen = new HeroNode(14, "14");

        root.setLeft(three);

        three.setLeft(eight);
        three.setRight(ten);

        root.setRight(six);

        six.setLeft(fourteen);

        ThreadedBinaryTree binaryTree = new ThreadedBinaryTree();
        binaryTree.setRoot(root);
        binaryTree.threadedNodes();

        HeroNode left = ten.getLeft();
        HeroNode right = ten.getRight();
        System.out.println("10号节点的前驱节点是：" + left);
        System.out.println("10号节点的后继节点是：" + right);

    }
}

class ThreadedBinaryTree {
    private HeroNode root;

    private HeroNode pre;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void threadedNodes() {
        threadedNodes(this.root);
    }

    /**
     * 编写对二叉树进行中序线索化的方法
     * @param node 当前需要线索化的root节点
     */
    public void threadedNodes(HeroNode node) {
        if (node == null) {
            return;
        }
        threadedNodes(node.getLeft());

        //处理当前节点的前驱节点
        //以首节点8来理解
        //8节点的left = null 8节点的leftType = 1
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }

        //处理后继节点
        //以第二节点3来理解
        //3节点的left = pre（8） 3节点的leftType = 1
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //每处理玩当前节点，就将当前节点赋值给pre
        pre = node;
        //线索化右子树
        threadedNodes(node.getRight());
    }

    /**
     * 前序遍历
     */
    public void preSequence() {
        if (this.root != null) {
            this.root.preSequence();
        } else {
            System.out.println("二叉树为空，不能遍历");
        }
    }

    /**
     * 前序遍历查找
     */
    public HeroNode preSequenceFind(int no) {
        HeroNode heroNode = null;
        if (this.root != null) {
            heroNode = this.root.preSequenceFind(no);
        } else {
            System.out.println("二叉树为空，不能查找");
        }
        return heroNode;
    }

    /**
     * 中序遍历
     */
    public void midSequence() {
        if (this.root != null) {
            this.root.midSequence();
        } else {
            System.out.println("二叉树为空，不能遍历");
        }
    }

    /**
     * 中序遍历查找
     */
    public HeroNode midSequenceFind(int no) {
        HeroNode heroNode = null;
        if (this.root != null) {
            heroNode = this.root.midSequenceFind(no);
        } else {
            System.out.println("二叉树为空，不能查找");
        }
        return heroNode;
    }

    /**
     * 后序遍历
     */
    public void postSequence() {
        if (this.root != null) {
            this.root.postSequence();
        } else {
            System.out.println("二叉树为空，不能遍历");
        }
    }

    /**
     * 后序遍历查找
     */
    public HeroNode postSequenceFind(int no) {
        HeroNode heroNode = null;
        if (this.root != null) {
            heroNode = this.root.postSequenceFind(no);
        } else {
            System.out.println("二叉树为空，不能查找");
        }
        return heroNode;
    }

    /**
     * 线索化二叉树 中序遍历
     */
    public void threadedMidSequence() {
        //递归向左子树前序遍历
        HeroNode node = root;
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }


    }

    /**
     * 删除节点
     * @param no
     */
    public void delNode(int no) {
        if (this.root != null) {
            if (this.root.getNo() == no) {
                this.root = null;
            }else {
                this.root.delNodeReset(no);
            }
        }
    }
}
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //1、如果leftType == 0  表示指向的是左子树，如果 1 则表示指向前驱节点
    //2、如果rightType == 0 表示指向的是右子树，如果 2 则表示指向后继节点
    private int leftType;
    private int rightType = 0;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preSequence() {
        System.out.println(this);
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preSequence();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preSequence();
        }
    }



    /**
     * 中序遍历
     */
    public void midSequence() {
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.midSequence();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.midSequence();
        }
    }

    /**
     * 后序遍历
     */
    public void postSequence() {
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.postSequence();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.postSequence();
        }
        //输出父节点
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     * @return
     */
    public HeroNode preSequenceFind(int no){
        System.out.println("前序遍历=======");
        //判断当前节点是不是
        if (this.no == no) {
            return this;
        }
        HeroNode findNode = null;
        //1、判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
        //2、如果左节点递归前序查找，找到节点，则返回
        if (this.left != null) {
            findNode = this.left.preSequenceFind(no);
            if (findNode != null) {
                return findNode;
            }
        }
        //1、左节点的递归前序查找，找到节点，则返回，否继续判断
        //2、当前的节点的右子节点是否为空，如果不空，则继续向右节点递归前序查找
        if (this.right != null) {
            findNode = this.right.preSequenceFind(no);
            return findNode;
        }
        return null;
    }

    /**
     * 中序遍历查找
     * @return
     */
    public HeroNode midSequenceFind(int no){
        //判断当前节点的左子节点是否为空，如果不为空，则递归中序查找
        HeroNode findNode = null;
        if (this.left != null) {
            findNode = this.left.midSequenceFind(no);
            if (findNode != null) {
                return findNode;
            }
        }
        //如果找到，则返回，如果没有找到，就与当前节点比较，如果是则返回当前节点
        System.out.println("中序遍历=======");
        if (this.no == no) {
            return this;
        }
        //否则继续进行右节点的中序查找
        if (this.right != null) {
            findNode = this.right.midSequenceFind(no);
            return findNode;
        }
        return null;
    }

    /**
     * 后序遍历查找
     * @return
     */
    public HeroNode postSequenceFind(int no){
        HeroNode findNode = null;
        //判断当前节点的左子节点是否为空，如果不为空，则递归后序查找
        if (this.left != null) {
            findNode = this.left.postSequenceFind(no);
            //说明在左子树已经找到
            if (findNode != null) {
                return findNode;
            }
        }
        //如果左子树没有找到，则向右子树递归进行后序遍历查找
        if (this.right != null) {
            findNode = this.right.postSequenceFind(no);
            return findNode;
        }
        System.out.println("后序遍历=======");
        //如果左右子树都没有找到，则比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        return null;
    }

    /**
     * 删除节点
     * @param no
     */
    public void delNode(int no) {
        //判断是否删除左节点
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        //判断是否删除右节点
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //递归向左子树查找，并删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        //递归向右子树查找，并删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    /**
     * 删除节点
     * @param no
     */
    public void delNodeReset(int no) {
        //判断是否删除左节点
        if (this.left != null && this.left.no == no) {
            HeroNode newNode = null;
            if (this.left.left != null) {
                newNode = this.left.left;
                newNode.left = this.left.right;
            } else if (this.left.right != null) {
                newNode = this.left.right;
            }
            this.left = newNode;
            return;
        }
        //判断是否删除右节点
        if (this.right != null && this.right.no == no) {
            HeroNode newNode = null;
            if (this.right.left != null) {
                newNode = this.right.left;
                newNode.left = this.right.right;
            } else if (this.right.right != null) {
                newNode = this.right.right;
            }
            this.right = newNode;
            return;
        }
        //递归向左子树查找，并删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        //递归向右子树查找，并删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }
}