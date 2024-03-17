package com.book.point2offer;

import com.mikemyzhao.list_1.ListNode;

import java.util.Arrays;
import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-04 15:48
 * @Description:
 */
public class ReverseList_06 {
  public int[] reversePrint(ListNode head) {
    if(head == null || head.next==null){
      return null;
    }
    Stack<Integer> tmp = new Stack();
    while (head!=null){
      tmp.push(head.val);
      head=head.next;
    }
    int size = tmp.size();
    int[] res = new int[size];

    for(int i=0;i<size;i++) {
      res[i]=tmp.pop();
    }
    return res;
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    int i=2;
    ListNode cur = head;
    while(cur!=null){
      ListNode nxt = new ListNode(i);
      cur.next=nxt;
      cur=nxt;
      i++;
      if(i==4) break;
    }
    int[] res = new ReverseList_06().reversePrint(head);
    System.out.println(Arrays.toString(res));
  }
}
