package com.hots100.list;

/**
 * @Author: zhaomengyi
 * @Date: 2022-11-29 10:59 下午
 * @Description:
 */
public class Code0160_GetIntersectionNode {
  public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }
  //儿童趣味益智：俩人分别走：然后求两条链长度差多少，然后长的把这段走完，再一起走就见面了
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if(headA == null || headB == null) return null;
    int n = 0;
    ListNode cur1 = headA;
    ListNode cur2 = headB;
    while(cur1 != null){
      n++;
      cur1 = cur1.next;
    }
    while(cur2 != null){
      n--;
      cur2 = cur2.next;
    }
    //把cur1指向长的
    cur1 = n > 0 ? headA : headB;
    cur2 = cur1 == headA ? headB : headA;
    n = Math.abs(n);
    while(n != 0){
      cur1 = cur1.next;
      n--;
    }
    while(cur1 != cur2){
      cur1 = cur1.next;
      cur2 = cur2.next;
    }
    return cur1;
  }
}
