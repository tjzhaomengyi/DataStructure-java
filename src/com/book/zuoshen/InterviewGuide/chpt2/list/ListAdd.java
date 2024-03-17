package com.book.zuoshen.InterviewGuide.chpt2.list;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/16 15:14
 * @Description:
 */
public class ListAdd {
    public class Node {
        public int value;
        public Node next;
        public Node(int value){
            this.value = value;
        }
    }

    //使用栈结构
    public Node addList1(Node head1, Node head2){
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();
        while (head1 != null){
            s1.push(head1.value);
            head1 = head1.next;
        }
        while(head2 != null){
            s2.push(head2.value);
            head2 = head2.next;
        }

        int ca = 0;//进位标志
        int n1 = 0;
        int n2 = 0;
        int n = 0;
        Node node = null;
        Node pre = null;
        while(!s1.isEmpty() || !s2.isEmpty()){
            n1 = s1.isEmpty() ? 0 : s1.pop();
            n2 = s2.isEmpty() ? 0 : s2.pop();
            n = n1 + n2 + ca;
            pre = node;
            node  = new Node(n % 10);
            node.next = pre;
            ca = n / 10;
        }
        if(ca == 1){
            pre = node;
            node = new Node(1);
            node.next = pre;
        }
        return node;
    }

    //利用链表的逆序求解可以节省栈空间
    public Node addList2(Node head1, Node head2){
        head1 = reverseList(head1);
        head2 = reverseList(head2);
        int ca = 0;
        int n1 = 0;
        int n2 = 0;
        int n = 0;
        Node c1 = head1;//注意这里其实不恢复两个原始链表这里不用设置c1和c2直接用head1和head2往后迭代就行
        Node c2 = head2;
        Node node = null;
        Node pre = null;
        while(c1 != null || c2 != null){
            n1 = c1 != null ? c1.value : 0;
            n2 = c2 != null ? c2.value : 0;
            n = n1 + n2 + ca;
            pre = node;
            node = new Node(n % 10);
            node.next = pre;
            ca = n / 10;
            c1 = c1 != null ? c1.next : null;
            c2 = c2 != null ? c2.next : null;
        }

        if(ca == 1){
            pre = node;
            node = new Node(1);
            node.next = pre;
        }

        return node;

    }

    public Node reverseList(Node head){
        Node pre = null;
        Node next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
