package com.book.zuoshen.InterviewGuide.chpt3.showtree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/24 15:44
 * @Description:使用前序遍历的方式序列化反序列化，如何序列化就如何反序列化，借助队列进行递归
 */
public class SequenceBinaryTreePreOrder {
    //使用右左中的顺序打印
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int data){
            this.value = data;
        }
    }
    //preorder,因为res是在内存中的一段数据所以每次递归的时候不用带着，就字符串在递归的时候最特殊

    public static String serialByPre(Node head){
        if(head == null){
            return "#!";
        }
        String res = head.value + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    //通过前序序列化后的字符串还原出二叉树
    public static Node reconByPreString(String preStr){
        String[] values = preStr.split("!");
        Queue<String> queue = new LinkedList<String>();
        for(int i = 0; i != values.length; i++){
            queue.offer(values[i]);
        }
        return reconPreOrder(queue);
    }
    public static Node reconPreOrder(Queue<String> queue){
        String value = queue.poll();
        if(value.equals("#")){
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
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
        String str = serialByPre(head);
        System.out.println(str);
        Node reserialHead = reconByPreString(str);
        printTree(reserialHead);
    }
}
