package com.book.zuoshen.InterviewGuide.chpt2.list;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/15 12:54
 * @Description:
 */
public class DelLastKthDoubleWayList {
    public class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data){
            this.value = data;
        }
    }

    public DoubleNode removeLastKthNode(DoubleNode head, int lastKth){
        if(head == null || lastKth < 1){
            return head;
        }
        DoubleNode cur = head;
        while(cur != null){
            lastKth--;
            cur = cur.next;
        }
        if(lastKth < 0){
            cur = head;
            while(++lastKth != 0){
                cur = cur.next;
            }
            DoubleNode newNext = cur.next.next;
            cur.next = newNext;
            if(newNext != null){
                newNext.last = cur;
            }
        }
        return head;
    }
}
