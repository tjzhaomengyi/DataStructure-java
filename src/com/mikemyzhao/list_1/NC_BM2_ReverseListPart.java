package com.mikemyzhao.list_1;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-03 17:53
 * @Description:
 */
public class NC_BM2_ReverseListPart {

  public class ListNode {
    int val;
    ListNode next = null;
  }
  //技巧:大dummy不动法
  public ListNode reverseBetween (ListNode head, int m, int n) {
    // write code here
    ListNode dummyNode = null;
    dummyNode.next = head;

    //让left节点走到m的前一个位置
    ListNode pre = dummyNode;
    for(int i = 0; i < m - 1; i++){
      pre = pre.next;
    }

    ListNode rightNode = pre;
    for(int i = 0; i < n - m + 1; i++){
      rightNode = rightNode.next;
    }

    //截取一个子链表
    ListNode leftNode = pre.next;
    ListNode cur = rightNode.next;

    //切断链表
    pre.next = null;
    rightNode.next = null;

    //反转局部链表
    reverseList(leftNode);
    pre.next = rightNode;
    leftNode.next = cur;

    return dummyNode.next;
  }

  public ListNode reverseList(ListNode head){
    ListNode dummy = null;
    ListNode cur = head;
    while(cur != null){
      ListNode next = cur.next;
      cur.next = dummy;
      dummy = cur;
      cur = next;
    }

    return dummy;
  }


}
