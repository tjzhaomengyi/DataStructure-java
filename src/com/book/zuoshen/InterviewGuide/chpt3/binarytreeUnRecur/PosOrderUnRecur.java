package com.book.zuoshen.InterviewGuide.chpt3.binarytreeUnRecur;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/19 20:51
 * @Description:
 */
public class PosOrderUnRecur {
    public class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int data){
            this.value = data;
        }
    }
    public static void posOrderUnRecur(Node head){
        System.out.print("pos-order");
        if(head != null){
            Stack<Node> s1 = new Stack<Node>();
            Stack<Node> s2 = new Stack<Node>();
            s1.push(head);
            while(!s1.isEmpty()){
                head = s1.pop();
                s2.push(head);
                if(head.left != null){
                    s1.push(head.left);
                }
                if(head.right != null){
                    s1.push(head.right);
                }
            }
            while(!s2.isEmpty()){
                System.out.print(s2.pop().value + " ");
            }
        }
    }
}
