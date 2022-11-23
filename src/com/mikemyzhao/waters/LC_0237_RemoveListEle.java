package com.mikemyzhao.waters;

import com.MCAAlgorithm.bigshua.class33.Problem_0237_DeleteNodeInLinkedList;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 12:36
 * @Description:
 */
public class LC_0237_RemoveListEle {
  public static class ListNode {
    int val;
    ListNode next;
  }

  public void deleteNode(ListNode node){
    node.val = node.next.val;
    node.next = node.next.next;
  }
}
