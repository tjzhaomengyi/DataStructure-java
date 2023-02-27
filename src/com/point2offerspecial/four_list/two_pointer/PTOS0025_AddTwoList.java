package com.point2offerspecial.four_list.two_pointer;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-04 14:11
 * @Description:
 */
public class PTOS0025_AddTwoList {
  public static class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if(l1 == null) return l2;
    if(l2 == null) return l1;
    ListNode l1_r = reverseAndCnt(l1);
    ListNode l2_r = reverseAndCnt(l2);
    int l1_len = l1_r.val;
    int l2_len = l2_r.val;
    ListNode longLst = l1_len >= l2_len ? l1_r.next : l2_r.next;
    ListNode shortLst = longLst == l1_r.next ? l2_r.next : l1_r.next;
    int curry = 0;
    ListNode curLong = longLst;
    ListNode curShort = shortLst;
    while(curLong != null){
      int shortVal = curShort == null ? 0 : curShort.val;
      int longVal = curLong.val;
      curLong.val = (longVal + shortVal + curry) % 10;
      curry = (longVal + shortVal + curry) / 10;
      curLong = curLong.next;
      if(curShort != null){
        curShort = curShort.next;
      }
    }
    if(curry == 1){
      ListNode newNode = new ListNode(1);
      newNode.next = reverseAndCnt(longLst).next;
      return newNode;
    } else {
      return reverseAndCnt(longLst).next;
    }
  }

  //反转链表，并将头结点设置为该链表的长度
  public ListNode reverseAndCnt(ListNode head){
    int cnt = 0;
    ListNode pre = null;
    ListNode cur = head;
    while(cur != null){
      ListNode next = cur.next;
      cur.next = pre;
      pre = cur;
      cur = next;
      cnt++;
    }
    ListNode lenNode = new ListNode(cnt);
    lenNode.next = pre;
    return lenNode;
  }

  public static void main(String[] args) {
    System.out.println(10 % 10);
    ListNode l1 = new ListNode(7);
    l1.next = new ListNode(2);
    l1.next.next = new ListNode(4);
    l1.next.next.next = new ListNode(3);

    ListNode l2 = new ListNode(5);
    l2.next = new ListNode(6);
    l2.next.next = new ListNode(4);

    ListNode ans = new PTOS0025_AddTwoList().addTwoNumbers(l1, l2);
    ListNode tmp = ans;
    while(tmp != null) {
      System.out.print(tmp.val);
      tmp = tmp.next;
    }
  }
}

