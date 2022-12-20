package com.hots100.list;

/**
 * @Author: zhaomengyi
 * @Date: 2022-11-29 6:00 下午
 * @Description:
 */
public class Code0019_DelNthFromEnd {
  /**
   * Definition for singly-linked list.*/
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
      if(head == null || (head.next == null && n == 1)) return null;
      ListNode slow = head;
      ListNode fast = head;
      ListNode pre = null;
      int i = 1;
      while(i < n){
        fast = fast.next;
        i++;
      }
      while(fast != null){
        fast = fast.next;
        pre = slow;
        slow = slow.next;
      }
      //执行到头执行断连
      if(slow.next == null){
        pre.next = null;
      } else {
        pre.next = slow.next;
        slow.next = null;
      }
      return head;
    }
}
