package com.book.point2offerspecial.four_list.two_pointer;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-04 12:40
 * @Description:
 */
public class PTOS023_DetectionNode {
  public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if(headA == null || headB == null) return null;
    if(headA == headB) return  headA;
    ListNode a = headA;
    ListNode b = headB;
    int alen = 0;
    int blen = 0;
    while(a != null){
      a = a.next;
      alen++;
    }
    while(b != null){
      b = b.next;
      blen++;
    }
    ListNode longHead = alen >= blen ? headA : headB;
    ListNode shortHead = longHead == headA ? headB : headA;
    a = longHead;
    b = shortHead;
    for(int i = 0; i < Math.abs(alen - blen); i++){
      a = a.next;
    }
    while(a != b){
      if(a == null || b == null) return null;
      a = a.next;
      b = b.next;
    }
    return a;
  }
}
