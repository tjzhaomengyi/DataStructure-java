package com.hots100.list;


import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2022-11-30 12:25 下午
 * @Description:
 */
public class Code0234_IsPalindrome {
  public static class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int data) {
      this.val = data;
    }
  }
  public boolean isPalindrome(ListNode head) {
    if(head == null) return false;
    if(head.next == null) return true;
    Stack<ListNode> s = new Stack<ListNode>();
    ListNode cur = head;
    while(cur != null){
      s.push(cur);
      cur = cur.next;
    }
    cur = head;
    while(!s.empty()){
      if(s.pop().val != cur.val){
        return false;
      }
      cur = cur.next;
    }
    return true;
  }
}
