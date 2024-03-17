package com.book.zuoshen.InterviewGuide.chpt3.MorrisRecur;

import com.MCAAlgorithm.base.class30.Code01_MorrisTraversal;


/**
 * @Author: zhaomengyi
 * @Date: 2024/1/22 15:42
 * @Description:最难的就是morris的后续遍历，需要将该链倒转一下
 */
public class MorrisPostOrder {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int data){
            this.value = data;
        }
    }



    //重新审视后序遍历的规则，沿着斜线边，左子树沿着的斜边，右子树沿着的斜边
    public static void postMorris(Node head){
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while(cur != null){
            mostRight = cur.left;
            if(mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    //打印左侧的
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);
    }

    public static void printEdge(Node head){
        Node tail = reverseEdge(head);
        Node cur = tail;
        while(cur != null){
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    //将整个右链反转
    public static Node reverseEdge(Node from){
        Node pre = null;
        Node next = null;
        while(from != null){
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
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
        postMorris(head);
//        postMorris(head);
    }

}
