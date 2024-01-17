package com.book.zuoshen.InterviewGuide.chpt2.list;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/15 17:55
 * @Description:
 */
public class DelMiddleNode {
    public class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value = data;
        }
    }

    public Node removeMidNode(Node head){
        if(head == null || head.next == null){
            return head;
        }
        if(head.next.next == null){
            return head.next;
        }
        Node pre = head;
        Node cur = head.next.next;
        while(cur.next != null && cur.next.next != null){
            pre = pre.next;
            cur = cur.next.next;
        }
        pre.next = pre.next.next;
        return head;
    }
}
