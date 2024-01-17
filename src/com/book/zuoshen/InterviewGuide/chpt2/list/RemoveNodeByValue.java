package com.book.zuoshen.InterviewGuide.chpt2.list;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/17 12:18
 * @Description:
 */
public class RemoveNodeByValue {
    public class Node{
        public int value;
        public Node next;
        public Node (int data){
            this.value = data;
        }
    }

    public Node removeValue(Node head, int num){
        if(head == null){
            return head;
        }
        Node cur = head;
        Node pre = null;
        Node next = null;
        while(cur != null){
            next = cur.next;
            if(cur.value == num){
                if(pre != null){
                    pre.next = next;
                } else {
                    head = cur.next;
                }
            }
            cur = next;
        }
        return head;
    }
}
