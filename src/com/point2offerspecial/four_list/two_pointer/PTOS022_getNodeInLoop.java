package com.point2offerspecial.four_list.two_pointer;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-03 21:38
 * @Description:
 */
public class PTOS022_getNodeInLoop {
  public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next;}
  }
  public ListNode detectCycle(ListNode head) {
    if(head == null || head.next == null) return head;
    ListNode slow = head;
    ListNode fast = head.next;
    while(slow != fast){
      if(fast.next == null && fast.next.next == null){
        return null;
      }
      fast = fast.next.next;
      slow = slow.next;
    }
    fast = head;
    while(slow != fast){
      fast = fast.next;
      slow = slow.next;
    }
    return fast;
  }
}
