package com.hots100.list;

/**
 * @Author: zhaomengyi
 * @Date: 2022-11-30 10:19 上午
 * @Description:
 */
public class Code0142_DetectCycle {
  class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
     }
 }
 //儿童趣味益智：一快一慢，快的先到头，然后回来一起走就是最终的相遇点
  public ListNode detectCycle(ListNode head) {
    if(head == null || head.next == null || head.next.next == null){
      return null;
    }
    //扣边界：这里要避开前两个直接成环的可能，让快的直接越过去
    ListNode slow = head.next;
    ListNode fast = head.next.next;
    while(slow != fast){
      //扣边界：别让fast超了边界，因为可能没有环，没有环的话就走完了，但是如果有环他俩最终会同时到达终点
      if(fast.next == null || fast.next.next == null) return null;
      fast = fast.next.next;
      slow = slow.next;
    }
    //儿童趣味益智：slow 和 fast 相遇，让fast回到 head让后俩人同时走就可以遇到。
    fast = head;
    while(slow != fast){
//      i++;
      fast = fast.next;
      slow = slow.next;
    }
    return fast;

  }
}
