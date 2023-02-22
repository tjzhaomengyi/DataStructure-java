package com.point2offerspecial.four_list.two_pointer;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-06 10:17
 * @Description:
 */
public class PTOS026_ReorderList {
  public static class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  public void reorderList(ListNode head) {
    ListNode slow = head.next;
    ListNode fast = head.next.next;
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    //这里注意是找链表的上中位或者中位
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    //1->2->3->4->5->6 变 1->6->2->5->3->4
    //1->2->3->4->5 变 1->5->2->4->3 都是从中间位置往后开始反转
    //从这里开始反转
    ListNode bakhalf = slow.next;
    slow.next = null;
    ListNode backHalfRev = reverseList(bakhalf);

    mergeList(head, backHalfRev);//合并两个链表
  }

  //技巧：原地合并两个链表,记住就完了
  public void mergeList(ListNode node1, ListNode node2) {
    //太难了~直接记住例子吧
    ListNode l1_tmp;
    ListNode l2_tmp;
    while(node1 != null && node2 != null){
      l1_tmp = node1.next;
      l2_tmp = node2.next;

      //接上，往后走
      node1.next = node2;
      node1 = l1_tmp;

      node2.next = l1_tmp;
      node2 = l2_tmp;

    }

  }


  public ListNode reverseList(ListNode head) {
    ListNode pre = null;
    ListNode cur = head;
    while (cur != null) {
      ListNode next = cur.next;
      cur.next = pre;
      pre = cur;
      cur = next;
    }
    return pre;
  }

  public static void main(String[] args) {
    ListNode l1 = new ListNode(1);
    l1.next = new ListNode(2);
    l1.next.next = new ListNode(3);
    l1.next.next.next = new ListNode(4);
    new PTOS026_ReorderList().reorderList(l1);
    System.out.println(l1);
  }
}
