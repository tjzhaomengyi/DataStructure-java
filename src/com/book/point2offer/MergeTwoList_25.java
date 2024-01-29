package com.book.point2offer;

import com.mikemyzhao.list_1.ListNode;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-06 13:26
 * @Description:将两个有序链表合并成一个有序链表
 */
public class MergeTwoList_25 {
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dum = new ListNode(0),cur=dum;//先构造一个空的链表
    while(l1!=null && l2!=null){
      if(l1.val<l2.val){
        cur.next=l1;
        l1=l1.next;
      }else{
        cur.next=l2;
        l2=l2.next;
      }
      cur=cur.next;
    }
    cur.next = l1!=null?l1:l2;
    return dum.next;
  }

}
