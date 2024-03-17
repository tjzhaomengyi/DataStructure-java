package com.book.zuoshen.InterviewGuide.chpt2.list;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/16 14:50
 * @Description:
 */
public class CopyListWithRandomPointer {
    public class Node{
        private int value;
        private Node next;
        private Node random;

        public Node(int value){
            this.value = value;
        }
    }

    //使用直接复制的方法，时间复杂度O(N),空间复杂度是O(N)
    public Node copyListWithRand1(Node head){
        HashMap<Node, Node> map = new HashMap<Node, Node>();
        Node cur = head;
        while(cur != null){
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while(cur != null){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    //将生成链表和原始链表混合在一起生成，然后将生成链表拆解出来的方法
    public Node copyListWithRand2(Node head){
        if(head == null){
            return null;
        }
        Node cur = head;
        Node next = null;
        //复制并连接每一个节点
        while(cur != null){
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;//新生成的节点指向新节点
            cur = next;
        }
        cur = head;
        Node curCopy = null;
        //设置复制节点的rand指针
        while(cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.random = cur.random != null ? cur.random.next : null;
            cur = next;
        }
        Node res = head.next;
        cur = head;
        //拆分
        while(cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }
}
