package com.book.zuoshen.InterviewGuide.chpt3.MorrisRecur;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/22 14:36
 * @Description:使用morris遍历完整中序遍历
 */
public class MorrisInOrder {
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
     * 根据morris序列加工出中序序列：1、2、3、4、5、6、7
     * @param head
     */
    public static void inorderMorris(Node head){
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while(cur != null){
            mostRight = cur.left;//从左子树开始找最右节点
            if(mostRight != null) {
                while (mostRight.right != cur && mostRight.right != null) {
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            System.out.print(cur.value + " ");//对于中序遍历，没有左子树的节点直接打印，两次访问的节点在第二次访问时候打印
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
        inorderMorris(head);
    }
}
