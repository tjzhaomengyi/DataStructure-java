package com.book.zuoshen.InterviewGuide.chpt2.list;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/16 11:56
 * @Description:
 */
public class IsPalindromeList {
    public class Node{
        private int value;
        private Node next;
        public Node(int value){
            this.value = value;
        }
    }
    //方法1，入栈后出栈看看是否一样
    public boolean isPalindrome1(Node head){
        Stack<Node> stack = new Stack<Node>();
        Node cur = head;
        while(cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while(head != null){
            if(head.value != stack.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    //找到中间节点，然后将右侧部分反转，这个方法太磨叽了
    public boolean isPalindrome(Node head){
        if(head == null || head.next == null){
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        while(n2.next != null && n2.next.next != null){
            //找到中间节点
            n1 = n1.next;//n1正好是中间的节点
            n2 = n2.next.next;
        }
        n2 = n1.next; //n2右半部分第一个节点
        n1.next = null; //mid.next -> null
        Node n3 = null;
        //将右半部分反转
        while(n2 != null){
            n3 = n2.next;
            n2.next = n1;//下一个反转节点
            n1 = n2;
            n2 = n3;
        }
        n3 = n1;//用n3保存右边最后一个节点
        n2 = head; //左半部分第一个节点
        boolean res = true;
        //开始检查回文
        while(n1 != null && n2 != null){
            if(n1.value != n2.value){
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        //恢复列表
        n1 = n3.next;
        n3.next = null;
        while(n1 != null){
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }
}
