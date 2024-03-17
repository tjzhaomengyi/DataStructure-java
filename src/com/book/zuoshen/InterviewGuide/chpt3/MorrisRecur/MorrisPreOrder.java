package com.book.zuoshen.InterviewGuide.chpt3.MorrisRecur;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/22 10:41
 * @Description:
 */
public class MorrisPreOrder {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 一个满二叉树,4,2,1,3,6,5,7[先序]的morris序列为4、2、1、2、3、4、6、5、6、7
     * 根据morris序列加工出先序序列：4、2、1、3、6、5、7
     * @param head
     */
    public static void morrisPre(Node head){
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while(cur != null){
            mostRight = cur.left;
            if(mostRight != null){
                while(mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null){
                    mostRight.right = cur;
                    System.out.println(cur.value + " ");//只要不是最右节点都会从这里返回到（中间节点或者左节点），所这里在第一次打印即可
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                //没有左子树的节点，因为mostRight最开始的时候是等于cur.left
                System.out.print(cur.value + "++ ");
            }
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
        morrisPre(head);
    }

}

