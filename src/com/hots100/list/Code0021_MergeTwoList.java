package com.hots100.list;

/**
 * @Author: zhaomengyi
 * @Date: 2022-11-29 9:14 下午
 * @Description:
 */
public class Code0021_MergeTwoList {
  public class ListNode {
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

  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    if(list1 == null) return list2;
    if(list2 == null) return list1;
    ListNode head = list1.val < list2.val ? list1 : list2;
    ListNode pre = head;
    ListNode cur1 = head == list1 ? list1.next : list1;
    ListNode cur2 = head == list2 ? list2.next : list2;
    while(cur1 != null & cur2 != null){
      if(cur1.val < cur2.val){
        pre.next = cur1;
        cur1 = cur1.next;
      } else {
        pre.next = cur2;
        cur2 = cur2.next;
      }
      pre = pre.next;
    }

    pre.next = cur1 == null ? cur2 : cur1;

    return head;
  }
}
