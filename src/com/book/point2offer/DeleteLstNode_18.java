package com.book.point2offer;

import com.mikemyzhao.list_1.ListNode;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-06 13:10
 * @Description:
 */
public class DeleteLstNode_18 {
  public ListNode deleteNode(ListNode head, int val) {
    if(head.val==val) return head.next;
    ListNode pre = head;
    ListNode cur = head.next;
    while(cur!=null && cur.val!=val){
      pre=cur;
      cur=cur.next;
    }
    if(cur.val==val){
      pre.next = cur.next;
      cur.next=null;
    }
    return head;
  }
}
