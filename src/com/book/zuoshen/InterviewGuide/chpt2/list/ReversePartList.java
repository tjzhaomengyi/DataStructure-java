package com.book.zuoshen.InterviewGuide.chpt2.list;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/15 21:30
 * @Description:这个是不用Dummy多余节点的解法
 */
public class ReversePartList {
    public class Node{
        private int val;
        private Node next;
        public Node(int data){
            val = data;
        }
    }

    public Node reversePart(Node head, int from, int to){
        int len = 0;
        Node node1 = head;
        Node fPre = null; //反转前面那个节点
        Node tPos = null; //反转后面那个节点
        while(node1 != null){
            len++;
            fPre = len == from - 1 ? node1 : fPre;
            tPos = len == to + 1 ? node1 : tPos;
            node1 = node1.next;
        }
        if(from > to || from < 1 || to > len){
            return head;
        }
        //现在找到连接节点了，开始出来反转内部节点
        node1 = fPre == null ? head : fPre.next; //如果从头节点反转，node1就是head
        Node node2 = node1.next;
        node1.next = tPos; //反转后最后一个节点连接整体链表的后续第一个节点
        Node next = null;
        while(node2 != tPos){
            next = node2.next;
            node2.next = node1;
            node1 = node2;
            node2 = next;
        }
        //把头连上
        if(fPre != null){
            fPre.next = node1;
            return head;
        }
        return node1;
    }
}
