package com.point2offerspecial.four_list.two_pointer;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-06 13:18
 * @Description:
 */
public class PTOS027_IsPalindromeList {
  public static class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
 //利用反转链表判断，还是找上中位点
  public boolean isPalindrome(ListNode head) {
    if(head == null || head.next == null
        || (head.next != null && head.next.next == null && head.val == head.next.val) ) return true;//
    if(head.next != null && head.next.next == null && head.val != head.next.val) return false;
    //技巧：三个及三个以上的，这样写肯定能拿到上中位的结点
    ListNode slow = head.next;
    ListNode fast = head.next.next;
    while(fast.next != null && fast.next.next != null){
      slow = slow.next;
      fast = fast.next.next;
    }
    ListNode bakHalfNode = slow.next;
    slow.next = null;
    ListNode backHalfNode = reverseList(bakHalfNode);
    return judge(head, backHalfNode);
  }

  public boolean judge(ListNode node1, ListNode node2){
    int len = node2.val;
    node2 = node2.next;
    for(int i = 0; i < len; i++){
      if(node1.val != node2.val) return false;
      node1 = node1.next;
      node2 = node2.next;
    }
    return true;
  }

  public ListNode reverseList(ListNode head){
    ListNode pre = null;
    ListNode cur = head;
    int len = 0;
    while(cur != null){
      ListNode next = cur.next;
      cur.next = pre;
      pre = cur;
      cur = next;
      len++;
    }
    ListNode lenNode = new ListNode(len);
    lenNode.next = pre;
    return lenNode;
  }

  public static void main(String[] args) {
    ListNode l1 = new ListNode(1);
    l1.next = new ListNode(0);
    l1.next.next = new ListNode(1);
    boolean ans = new PTOS027_IsPalindromeList().isPalindrome(l1);
    System.out.println(ans);
  }
}
