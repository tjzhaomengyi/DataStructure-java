package com.book.point2offer;

import com.mikemyzhao.list_1.ListNode;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-09 14:41
 * @Description:
 */
public class GetInteresectionNoe_52 {
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if(headA==null || headB==null) return null;
    ListNode A = headA, B = headB;
    while(A!=B){
      A=A==null?headB:A.next;
      B=B==null?headA:B.next;
    }
    return A;
  }
}
