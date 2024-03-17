package com.book.zuoshen.InterviewGuide.chpt3.showtree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/24 16:38
 * @Descriptiona:按照层级遍历方式序列化和反序列化,注意如何序列化就如何反序列化
 */
public class SequenceBinaryTreeByLevel {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int data){
            this.value = data;
        }
    }
    public static String serialByLevel(Node head){
        if(head == null){
            return "#!";
        }
        String res = head.value + "!";
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(head);
        while(!queue.isEmpty()){
            head = queue.poll();
            if(head.left != null){
                res += head.left.value + "!";
                queue.offer(head.left);
            } else {
                res += "#!";
            }
            if(head.right != null){
                res += head.right.value + "!";
                queue.offer(head.right);
            } else {
                res += "#!";
            }
        }
        return res;
    }

    public static Node reconByLevelString(String levelStr){
        String[] values = levelStr.split("!");
        int index = 0;
        Node head = generateNodeByString(values[index++]);
        Queue<Node> queue = new LinkedList<Node>();
        if(head != null){
            queue.offer(head);
        }
        Node node = null;
        while(!queue.isEmpty()){
            node = queue.poll();
            node.left = generateNodeByString(values[index++]);
            node.right = generateNodeByString(values[index++]);
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
        return head;
    }

    public static Node generateNodeByString(String val){
        if(val.equals("#")){
            return null;
        }
        return new Node(Integer.valueOf(val));
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
        String str = serialByLevel(head);
        System.out.println(str);
        Node reserialHead = reconByLevelString(str);
        printTree(reserialHead);
    }
}
