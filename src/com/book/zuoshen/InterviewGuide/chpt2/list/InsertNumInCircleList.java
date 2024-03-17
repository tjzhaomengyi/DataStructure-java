package com.book.zuoshen.InterviewGuide.chpt2.list;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/17 15:30
 * @Description:
 */
public class InsertNumInCircleList {
    public class Node{
        private int value;
        private Node next;
        public Node(int value){
            this.value = value;
        }

        public Node insertNum(Node head, int num){
            Node node = new Node(num);
            if(head == null){
                node.next = node;
                return node;
            }
            Node pre = head;
            Node cur = head.next;
            while(cur != head){
                if(pre.value <= num && cur.value >= num){
                    break;
                }
                pre = cur;
                cur = cur.next;
            }
            pre.next = node;
            node.next = cur;
            return head.value < num ? head : node;
        }
    }
}
