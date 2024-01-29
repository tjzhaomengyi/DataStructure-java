package com.book.zuoshen.InterviewGuide.chpt3.showtree;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/24 15:18
 * @Description:
 */
public class PrintBinaryTree {
    //使用右左中的顺序打印
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int data){
            this.value = data;
        }
    }

    public static void printTree(Node head){
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len){
        if(head == null){
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height +1, "^", len);
    }

    public static String getSpace(int num){
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for(int i = 0; i < num; i++){
            buf.append(space);
        }
        return buf.toString();
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
        printTree(head);
    }

}
