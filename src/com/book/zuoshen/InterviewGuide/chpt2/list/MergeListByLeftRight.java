package com.book.zuoshen.InterviewGuide.chpt2.list;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/17 16:11
 * @Description:
 */
public class MergeListByLeftRight {
    public class Node{
        private int value;
        private Node next;
        public Node(int value){
            this.value = value;
        }
    }

    public void relocate(Node head){
        if(head == null || head.next == null){
            return;
        }
        Node left = head;
        Node right = head.next;
        while(right.next != null && right.next.next != null){
            left = left.next;
            right = right.next.next;
        }
        right = left.next; //右半部分
        //将左右断开
        left.next = null;
        mergeLR(head, right);
    }

    public void mergeLR(Node left, Node right){
        Node next = null;
        while(left.next != null){ //左侧的长度肯定小于等于右侧的长度
            next = right.next;
            right.next = left.next;
            left.next = right;
            left = right.next;
            right = next;
        }
        left.next = right;//左侧要连一下右侧剩下的那个！
    }
}
