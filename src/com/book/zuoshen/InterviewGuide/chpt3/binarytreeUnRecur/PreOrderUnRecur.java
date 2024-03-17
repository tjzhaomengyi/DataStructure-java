package com.book.zuoshen.InterviewGuide.chpt3.binarytreeUnRecur;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/19 19:21
 * @Description:
 */
public class PreOrderUnRecur {
    public class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int data){
            this.value = data;
        }
    }
    //使用栈来实现前序遍历：只要弹出节点，就放入右侧和左侧节点，然后再弹出
    public static void preOrderUnRecur(Node head){
        System.out.println("Pre-Order");
        Stack<Node> stack = new Stack<Node>();
        if(head != null){
            stack.push(head);
            while(!stack.isEmpty()){
                Node node = stack.pop();
                System.out.print(node.value + ",");
                if(head.right != null){
                    stack.push(head.right);
                }
                if(head.left != null){
                    stack.push(head.left);
                }
            }
        }
    }
}
