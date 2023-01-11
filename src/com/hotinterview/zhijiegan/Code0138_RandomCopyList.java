package com.hotinterview.zhijiegan;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-21 8:52 下午
 * @Description:
 */
public class Code0138_RandomCopyList {
  class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }
  }
  public Node copyRandomList(Node head) {
    HashMap<Node, Node> map = new HashMap<>();
    Node cur = head;
    while(cur != null){
      map.put(cur, new Node(cur.val));
      cur = cur.next;
    }
    cur = head;
    while(cur != null){
      //cur老的，map.get(cur)是新的结点
      map.get(cur).next = map.get(cur.next);
      map.get(cur).random = map.get(cur.random);
      cur = cur.next;
    }
    return map.get(head);
  }
}
