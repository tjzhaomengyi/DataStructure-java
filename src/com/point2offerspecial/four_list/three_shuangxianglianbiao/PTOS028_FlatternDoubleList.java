package com.point2offerspecial.four_list.three_shuangxianglianbiao;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-06 15:35
 * @Description:
 */
public class PTOS028_FlatternDoubleList {
  class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
  };

  public Node flatten(Node head) {
    flattenGetTail(head);
    return head;
  }

  private Node flattenGetTail(Node head){
    Node node = head;
    Node tail = null;
    while(node != null){
      Node next = node.next;
      if(node.child != null){
        Node child = node.child;
        Node childTail = flattenGetTail(node.child);

        node.child = null;
        node.next = child;
        child.prev = node;
        childTail.next = next;
        if(next != null){
          next.prev = childTail;
        }
        tail = childTail;
      } else {
        tail = node;
      }
      node = next;
    }
    return tail;
  }

}
