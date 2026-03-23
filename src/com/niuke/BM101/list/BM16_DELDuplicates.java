package com.niuke.BM101.list;

/**
 * 删除链表中的重复节点，只要重复就删除
 * 111122 -- > null
 */
public class BM16_DELDuplicates {

     public class ListNode {
       int val;
       ListNode next = null;
       public ListNode(int val) {
         this.val = val;
       }
     }

     public ListNode deleteDuplicates(ListNode head){
         if(head == null) return head;
         ListNode dummy = new ListNode(-1);
         dummy.next = head;
         ListNode pre = dummy;
         ListNode cur = head;
         while(cur != null){
             if(cur.next != null && cur.val == cur.next.val){
                 while(cur.next != null && cur.val == cur.next.val){
                     cur = cur.next;
                 }
                 pre.next = cur.next;
                 cur = cur.next;
             } else {
                 pre = cur;
                 cur = cur.next;
             }
         }
         return dummy.next;
     }
}
