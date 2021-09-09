package com.mikemyzhao.TwoPntAndBSearch;

import com.mikemyzhao.list.ListNode;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-03 11:31
 * @Description:快慢指针模板
 */
public class SlowFastPointer {
  /**判断链表是否有环**/
  boolean hasCycle(ListNode head){
    ListNode fast,slow;
    fast = slow = head;
    while(fast != null && fast.next!=null){
      fast = fast.next.next;
      slow = slow.next;
      if(fast == slow) return true;
    }
    return false;
  }

  /**判断环的起始位置,撞击后，让一个回去，再同时走**/
  ListNode detectCycel(ListNode head){
    ListNode fast,slow;
    fast = slow = head;
    while(fast!=null && fast.next!=null){
      fast = fast.next.next;
      slow = slow.next;
      if(fast == slow) break;
    }
    slow = head;
    while(slow!=fast){
      fast = fast.next;
      slow = slow.next;
    }
    return slow;
  }

}
