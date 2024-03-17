package com.book.zuoshen.InterviewGuide.chpt2.list;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/15 21:14
 * @Description:
 */
public class ReverseDoubleList {
    public class Node{
        public int value;
        public Node next;
        public Node pre;
        public Node(int data){
            this.value = data;
        }
    }
    public Node reverseList(Node head){
        Node pre = null;
        Node next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }
        return pre;
    }
}

