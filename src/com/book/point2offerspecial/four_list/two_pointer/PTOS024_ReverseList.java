package com.book.point2offerspecial.four_list.two_pointer;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-04 12:57
 * @Description:
 */
public class PTOS024_ReverseList {
  public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
  public ListNode reverseList(ListNode head) {
    if(head == null || head.next == null) return head;
    ListNode pre = null;
    ListNode cur = head;
    while(cur != null){
      ListNode next = cur.next;
      cur.next = pre;
      pre = cur;
      cur = next;
    }
    return pre;
  }
}
