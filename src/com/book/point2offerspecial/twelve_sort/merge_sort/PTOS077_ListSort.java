package com.book.point2offerspecial.twelve_sort.merge_sort;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-14 16:15
 * @Description:
 */
public class PTOS077_ListSort {
  public static class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
  //因为链表容易分开，并且归并排序会找到mid中间位置，所以他俩刚好合适
  public ListNode sortList(ListNode head){
    //三个条件卡住返回边界
    if(head == null ){return null;}
    if(head.next == null) return head;
    if(head.next.next == null && head.next != null){
      ListNode ansHead = head.val > head.next.val ? head.next : head;
      ListNode bak = ansHead == head ? head.next : head;
      ansHead.next = bak;
      bak.next = null;
      return ansHead;
    }
    ListNode head1 = head;
    ListNode head2 = splitList(head);
    head1 = sortList(head1);
    head2 = sortList(head2);
    return merge(head1, head2);
  }

  //用断链的方式代替数组的mid计算
  private ListNode splitList(ListNode head){
    //找到上中位结点的方法
    ListNode slow = head.next;
    ListNode fast = head.next.next;
    while(fast.next != null && fast.next.next != null){
      slow = slow.next;
      fast = fast.next.next;
    }
    //断链
    ListNode bakHead = slow.next;
    slow.next = null;
    return bakHead;
  }

  private ListNode merge(ListNode head1, ListNode head2){
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    while(head1 != null && head2 != null){
      if(head1.val < head2.val) {
        cur.next = head1;
        head1 = head1.next;
      } else {
        cur.next = head2;
        head2 = head2.next;
      }
      cur = cur.next;
    }
    cur.next = head1 != null ? head1 : head2;
    return dummy.next;
  }

  public static void main(String[] args) {
    ListNode a = new ListNode(4);
    a.next = new ListNode(2);
    a.next.next = new ListNode(1);
    a.next.next.next = new ListNode(3);
    ListNode ans = new PTOS077_ListSort().sortList(a);
    System.out.println(ans);
  }
}
