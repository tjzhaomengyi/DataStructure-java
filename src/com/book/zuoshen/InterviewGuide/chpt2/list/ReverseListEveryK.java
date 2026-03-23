package com.book.zuoshen.InterviewGuide.chpt2.list;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/17 10:09
 * @Description:
 */
public class ReverseListEveryK {
    //方法1：使用栈
    public class Node{
        public int value;
        public Node next;
        public Node(int value){
            this.value = value;
        }
    }
    public Node reverseNodeEveryK1(Node head, int k){
        if (k < 2){
            return head;
        }
        Stack<Node> stack = new Stack<Node>();
        Node newHead = head;
        Node cur = head;
        Node pre = null;
        Node next = null;
        while(cur != null){
            next = cur.next;
            stack.push(cur);
            if(stack.size() == k){
                pre = resign1(stack, pre, next);
                newHead = newHead == head ? cur : newHead;//如果是刚开始，那么需要修改为cur；否则newHead不动
            }
            cur = next;
        }
        return newHead;
    }

    public Node resign1(Stack<Node> stack, Node left, Node right){
        Node cur = stack.pop();
        if(left != null){
            left.next = cur;
        }
        Node next = null;
        while(!stack.isEmpty()){
            next = stack.pop();
            cur.next = next;
            cur = next;
        }
        cur.next = right;
        return cur;
    }

    //不使用栈结构，使用一个变量记录
    public Node reverseNodes2(Node head, int k){
        if(k < 2){
            return head;
        }
        Node cur = head;
        Node start = null;//记录反转位置
        Node pre = null; //pre一直指向每k个节点的前一个节点
        Node next = null;
        int count = 0;
        while(cur != null){
            ++count;
            next = cur.next;
            if(count == k){
                start = pre == null ? head : pre.next;
                head = pre == null ? cur : head;
                resign2(pre, start, cur, next);
                pre = start; //此时start已经被反转了，是该组的最后一个，pre指向这组的最后一个
                count = 0;
            }
            cur = next;
        }
        return head;
    }

    public void resign2(Node left, Node start, Node end, Node right){
        Node pre = start;
        Node cur = start.next;
        Node next = null;
        while(cur != right){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if(left != null){
            left.next = end;
        }
        start.next = right;
    }
}
