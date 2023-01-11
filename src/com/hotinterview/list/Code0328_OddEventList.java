package com.hotinterview.list;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-30 4:06 下午
 * @Description:
 */
public class Code0328_OddEventList {
  public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public ListNode oddEvenList(ListNode head) {
      if(head == null || head.next == null || head.next.next == null) return head;
      ListNode cur = head;
      ListNode cur_odd = null;
      ListNode cur_event = null;
      ListNode head_event = null;
      ListNode head_odd = null;
      int i = 1;
      while(cur != null){
        if((i & 1) == 1){
          //奇数
          if(cur_odd == null) {
            cur_odd = cur;
            head_odd = cur_odd;
          } else {
            cur_odd.next = cur;
            cur_odd = cur_odd.next;
          }
        } else {
          if(cur_event == null) {
            cur_event = cur;
            head_event = cur_event;
          } else{
            cur_event.next = cur;
            cur_event = cur_event.next;
          }
        }
        i++;
        cur = cur.next;
      }
      if(head_event!= null) {
        cur_odd.next = head_event;
        cur_event.next = null;

      }
      return cur_odd == null ? head_event : head_odd;
    }
}
