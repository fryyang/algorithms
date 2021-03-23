package com.yanngyi.sxt.tree.binarysorttree;

/**
 * 二叉排序树
 * @author yangyi
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9,2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int a : arr) {
            binarySortTree.add(new Node(a));
        }
        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(1);
        binarySortTree.delNode(10);

        binarySortTree.midOrder();

    }
}
class BinarySortTree {
    private Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void midOrder() {
        if (root != null) {
            root.midOrder();
        }
    }

    /**
     * 查找节点
     * @param value
     * @return
     */
    public Node find(int value) {
        if (root != null) {
           return root.find(value);
        }
        return null;
    }

    /**
     * 查找当前节点的父节点
     * @param value
     * @return
     */
    public Node findParent(int value) {
        if (root != null) {
           return root.findParent(value);
        }
        return null;
    }

    /**
     * 1、返回以node为根节点的二叉排序树的根节点的最小节点的值
     * 2、删除以node节点为根节点的二叉排序树
     * @param node 传入的节点（当作二叉排序树的根节点）
     * @return 以node为根节点的二叉排序树的根节点的最小节点的值
     */
    public int delRightNodeMin(Node node) {
        Node target = node;
        //循环的查找做节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //循环退出后target就指向了最小节点
        delNode(target.value);
        return target.value;
    }


    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //1、需要先找到要删除的节点
            Node targetNode = find(value);
            //如果没有找到要删除的节点
            if (targetNode == null) {
                return;
            }
            //如果我们发现这棵二叉排序树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //去找到targetNode的父节点
            Node parent = findParent(value);
            //如果要删除的节点是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是父节点的左子节点 还是右子节点
                if (parent.left != null && parent.left.value == value) {
                    //是左子节点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    //是右子节点
                    parent.right = null;
                }
            } else
            //如果要删除的节点是只有一个叶子结点的父节点
            if (targetNode.left == null || targetNode.right == null) {
                if (parent != null) {
                    //判断targetNode是父节点的左子节点 还是右子节点
                    if (parent.left != null && parent.left.value == value) {
                        //是左子节点
                        parent.left = targetNode.left != null ? targetNode.left: targetNode.right;
                    } else if (parent.right != null && parent.right.value == value) {
                        //是右子节点
                        parent.right = targetNode.right != null ? targetNode.right: targetNode.left;
                    }
                } else {
                    root = targetNode.left != null ? targetNode.left: targetNode.right;
                }

                //删除有两棵子树的节点
            } else {
                targetNode.value = delRightNodeMin(targetNode.right);
                //targetNode.value = delLeftNodeMax(targetNode.left);
            }
        }
    }

    public int delLeftNodeMax(Node node) {
        Node target = node;
        while (target.right != null) {
            target = target.right;
        }
        delNode(target.value);
        return target.value;
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 添加结点的方法
     * 递归的形式添加结点，注意需要满足二叉排序树的要求
     * @param node
     */
    public void add(Node node){
        if (node == null) {
            return;
        }

        //判断传入的节点的值，和当前子树的根节点的值关系
        if (node.value < this.value) {
            //如果当前节点的左节点为null，直接将当前节点挂在根节点的左节点
            if (this.left == null) {
                this.left = node;
            } else {
                //递归向左子树添加
                this.left.add(node);
            }
        } else {
            //如果当前节点的右节点为null，直接将当前节点挂在根节点的右节点
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    public void midOrder(){
        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    /**
     * 查找要删除的节点
     * @param value 希望删除的节点的值
     * @return 如果找到返回该节点，否则返回null
     */
    public Node find(int value) {
        if (this.value == value) {
            return this;
            //如果查找的值小于当前节点，向左子树递归查找
        } else if (value < this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.find(value);
            //如果查找的值小于当前节点，向右子树递归查找
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.find(value);
        }
    }

    /**
     * 查找当前节点的父节点
     * @param value 要找到的节点的值
     * @return 返回要删除节点的父节点
     */
    public Node findParent(int value){
        //判断当前节点是不是删除节点的父节点
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                //向左子树递归查找
                return this.left.findParent(value);
            } else if (value >= this.value && this.right != null) {
                //向右子树递归查找
                return this.right.findParent(value);
            } else {
                //没有找到父节点
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}