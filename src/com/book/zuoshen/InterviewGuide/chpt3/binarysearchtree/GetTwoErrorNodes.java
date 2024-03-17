package com.book.zuoshen.InterviewGuide.chpt3.binarysearchtree;

import sun.reflect.generics.tree.Tree;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/29 11:11
 * @Description:
 */
public class GetTwoErrorNodes {
    public class Node {
        int val;
        Node left;
        Node right;

        Node() {
        }

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public Node[] getTwoErrorNodes(Node head){
            Node[] errs = new Node[2];
            if (head == null){
                return errs;
            }
            Stack<Node> stack = new Stack<Node>();
            Node pre = null;
            while (!stack.isEmpty() || head != null){
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    if(pre != null && pre.val > head.val){
                        errs[0] = errs[0] == null ? pre : errs[0];
                        errs[1] = head;
                    }
                    pre = head;
                    head = head.right;
                }
            }
            return errs;
        }

        }
}
