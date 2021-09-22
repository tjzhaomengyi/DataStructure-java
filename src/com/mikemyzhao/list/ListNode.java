package com.mikemyzhao.list;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-02 8:49
 * @Description:算法小抄和leetcode通用ListNode节点类型
 */
public class ListNode {
  public int val;
  public ListNode next;

  ListNode(int val){
    this.val = val;
    this.next = null;
  }

  public static void main(String[] args) {
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(3);
    ListNode node3 = new ListNode(5);

    //修改节点的值
    node1.val = 9;
    //连接节点
    node2.next = node3;
    node1.next = node2;
  }
}
