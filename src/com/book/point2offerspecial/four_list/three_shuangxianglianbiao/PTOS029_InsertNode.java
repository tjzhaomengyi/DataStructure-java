package com.book.point2offerspecial.four_list.three_shuangxianglianbiao;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-06 17:32
 * @Description:
 */
public class PTOS029_InsertNode {
  class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, Node _next) {
      val = _val;
      next = _next;
    }
  };
  public Node insert(Node head, int insertVal) {
    Node node = new Node(insertVal);
    if(head == null){
      head = node;
      head.next = node;
      return node;
    } else if(head != null && head.next == head){
      head.next = node;
      node.next = head;
    } else {
      insertNode(head, node);
    }
    return head;
  }

  public void insertNode(Node head , Node node){
    Node cur = head;
    Node next = cur.next;
    Node biggest = head;
    while(!(cur.val <= node.val && next.val >= node.val) && next != head){
      cur = next;
      next = next.next;
      if(cur.val >= biggest.val){
        biggest = cur;
      }
    }
    //如果满足了插入条件
    if(cur.val <= node.val && next.val >= node.val){//正好在环中
      cur.next = node;
      node.next = next;
    } else { //插在环位
      node.next = biggest.next;
      biggest.next = node;
    }
  }
}
