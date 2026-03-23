package com.niuke.BM101.list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/***
 * 合并多个链表，使用小根堆，每次只需要把每个链表的头节点塞入即可，然后每次弹出，再把这个节点的下一个节点压入小根堆即可。循环弹出
 */
public class MergeKList {

    public class ListNode {
      int val;
      ListNode next = null;
      public ListNode(int val) {
        this.val = val;
      }
    }
    public ListNode mergeKLists (ArrayList<ListNode> lists) {
        // write code here
        PriorityQueue<ListNode> min_heap = new PriorityQueue<>((a, b)->a.val - b.val);
        ListNode dummy = new ListNode(-1);
        //这里只加每个节点的头部就行，然后出去一次删除一个再把这列节点头部再加进小根堆中
        for(int i = 0; i < lists.size(); i++){
            ListNode head = lists.get(i);
            if(head == null) continue;
            min_heap.add(lists.get(i));
        }
        ListNode new_pre = dummy;
        while(!min_heap.isEmpty()){
            ListNode node = min_heap.poll();
            new_pre.next = node;
            new_pre = new_pre.next;

            if(node.next != null){
                min_heap.add(node.next);
            }
        }
        return dummy.next;
    }
}
