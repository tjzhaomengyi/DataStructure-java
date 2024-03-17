package com.book.zuoshen.InterviewGuide.chpt2.list;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/17 13:33
 * @Description:
 */
public class SelectSortList {
    public class Node{
        private int value;
        private Node next;
        public Node(int value){
            this.value = value;
        }
    }

    public static Node selectionSort(Node head){
        Node tail = null;//排序部分尾部
        Node cur = head; //未排序部分头部
        Node smallPre = null; //最小节点的前一个节点
        Node small = null; //最小节点
        while(cur != null){
            small = cur;
            smallPre = getSmallPreNode(cur);
            if(smallPre != null){
                small = smallPre.next;
                smallPre.next = small.next; //smallPre是删除的small的前一个节点
            }
            cur = cur == small ? cur.next : cur;
            if(tail == null){
                head = small;
            } else {
                tail.next = small;
            }
            tail = small;
        }
        return head;
    }

    //找到当前找到小节点的前一个节点
    private static Node getSmallPreNode(Node head){
        Node smallPre = null;
        Node small = head;
        Node pre = head;
        Node cur = head.next;
        while(cur != null){
            if(cur.value < small.value){
                smallPre = pre;
                small = cur;
            }
            pre = cur;
            cur = cur.next;
        }
        return smallPre;
    }
}
