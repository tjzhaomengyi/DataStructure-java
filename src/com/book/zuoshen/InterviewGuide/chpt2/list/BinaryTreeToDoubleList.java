package com.book.zuoshen.InterviewGuide.chpt2.list;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/17 12:29
 * @Description:搜索二叉树转双向链表
 */
public class BinaryTreeToDoubleList {
    public class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int data){
            this.value = data;
        }
    }
    //用队列收集
    public Node convert1(Node head){
        Queue<Node> queue = new LinkedList<Node>();
        inOrderToQuue(head, queue);
        if(queue.isEmpty()){
            return head;
        }
        head = queue.poll();
        Node pre = head;
        pre.left = null;
        Node cur = null;
        while(!queue.isEmpty()){
            cur = queue.poll();
            pre.right = cur;
            cur.left = pre;
            pre = cur;
        }
        pre.right = null;
        return head;
    }

    public void inOrderToQuue(Node head, Queue<Node> queue){
        if(head == null){
            return;
        }
        inOrderToQuue(head.left, queue);
        queue.offer(head);
        inOrderToQuue(head.right, queue);
    }
}
