package com.book.zuoshen.InterviewGuide.chpt2.list;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/15 20:59
 * @Description:
 */
public class DeladevidebPOSNode {
    public class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value = data;
        }
    }

    public Node removeByRatio(Node head, int a, int b) {
        if (a < 1 || a > b) {
            return head;
        }
        int n = 0;
        Node cur = head;
        while(cur != null){
            n++;
            cur = cur.next;
        }
        n = (int)Math.ceil(((double)(a * n)) / (double) b);
        if (n == 1){
            head = head.next;
        }
        if(n > 1){
            cur = head;
            while (--n != 1){
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;
    }
}
