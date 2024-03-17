package com.book.point2offerspecial.four_list.two_pointer;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-03 21:10
 * @Description:
 */
public class PTOS021_RemoveNthFromEnd {
  public class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
  public ListNode removeNthFromEnd(ListNode head, int n){
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode front = head, back = dummy;
    for(int i = 0; i < n; i++){//如果是倒数第二个，那么就是front 和 back挨着，要找到back的前一个，因为用了dummy所以不可能为空
      front = front.next;
    }
    while(front != null){//技巧：让front走到null，后面有dummy兜底
      front = front.next;
      back = back.next;
    }

    return back;
  }


}
