package com.book.zuoshen.InterviewGuide.chpt3.MorrisRecur;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/22 10:21
 * @Description:Morris遍历详解版本
 */
public class MorrisSequenceRecur {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 得到Morris序列，思路就是：先找到当前节点的最右节点mostright，然后让mostrright.right=cur,然后遍历cur.left，
     * 如果遍历到mostright.right = cur,说明是第二次遍历，让mostright.right = null,如果cur.left==null，就让cur=cur.right
     * @param head
     * 一个满二叉树,4,2,1,3,6,5,7[先序]的morris序列为4、2、1、2、3、4、6、5、6、7
     *
     */
    public static void morris(Node head){
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while(cur != null){
            System.out.print(cur.value + " ");
            mostRight = cur.left;
            if(mostRight != null){//如果当前节点有左子树,开始找左子树的最右节点
                //找到cur左子树的最右节点,因为mostRight.right会在第一次为null时候动态指向到cur，第二次再遇到将mostright.right=null
                while(mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                //从上面while出来后，mostRight就是cur左子树上最右的节点
                if(mostRight.right == null){
                    //第一次让mostRight.right指向cur
                    mostRight.right = cur;
                    //将cur移动到cur.left
                    cur = cur.left;
                    continue;//回到最外层的cur遍历逻辑
                } else {
                    //第二次遍历到mostRight，将mostRight.right
                    mostRight.right = null;
                }
            }
            //不管什么情况都往右子树走！这里面有两种情况
            //遍历右侧树的逻辑：cur如果没有左子树，cur向右侧移动
            //cur左子树上最右节点的右指针指向cur，cur向右移动返回到cur节点
            cur = cur.right;

        }

    }

    public static void main(String[] args) {
        Node head = new Node(4);
        Node node2 = new Node(2);
        Node node1 = new Node(1);
        Node node3 = new Node(3);
        Node node6 = new Node(6);
        Node node5 = new Node(5);
        Node node7 = new Node(7);
        head.left = node2;
        head.right = node6;
        node2.left = node1;
        node2.right = node3;
        node6.left = node5;
        node6.right = node7;
        morris(head);
    }
}
