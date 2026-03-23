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
    // 思路就是走一个中序遍历，即可，并在中序遍历的递归过程中，对指针调整顺序，完成每个节点前后指向。
    // 这个题没有必要使用Queue队列做缓冲，本质上就是修改每个节点的指针指向
    Node pre = null;
    Node head = null;
    public Node Convert(Node root){
        if(root == null) return null;

        //中序遍历
        travel(root);
        return head;
    }
    private void travel(Node cur){
        if(cur == null) return;
        //中序遍历，一直走到树的最左侧节点
        travel(cur.left);

        //处理当前节点
        if(pre == null){ //一直探到最左侧节点，它是没有pre的所以他就是链表的头节点
            head = cur;
        } else { //这里左右两侧分别手动走一下，正好是卡在这两个指针修改上
            pre.right = cur;
            cur.left = pre;
        }
        pre = cur;

        //中序遍历
        travel(cur.right);
    }

    // 以下是书上写的，不是很好
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
