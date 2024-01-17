package com.book.zuoshen.InterviewGuide.chpt2.list;

import java.util.HashSet;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/17 12:13
 * @Description:
 */
public class RemoveRep {
    //利用哈希表
    public class Node{
        public int value;
        public Node next;
        public Node(int value){
            this.value = value;
        }
    }

    //使用Hash表删除
    public void removeRep(Node head){
        HashSet<Integer> set = new HashSet<Integer>();
        Node pre = head;
        Node cur = head.next;
        set.add(head.value);
        while(cur != null){
            if(set.contains(cur.value)){
                pre.next = cur.next;
            } else {
                set.add(cur.value);
                pre = cur;
            }
            cur = cur.next;
        }
    }
}
