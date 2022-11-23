package com.point2offer;

import com.mikemyzhao.list_1.ListNode;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-06 15:49
 * @Description:反转链表
 */
public class ReverseList_24 {
  public ListNode reverseList(ListNode head) {
    if(head==null||head.next==null) return head;
    ListNode last = reverseList(head.next);
    head.next.next=head;
    head.next=null;
    return last;
  }

}
