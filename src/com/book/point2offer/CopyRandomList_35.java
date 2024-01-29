package com.book.point2offer;

import com.mikemyzhao.list_1.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-07 17:00
 * @Description:复杂链表的复制，除了有一个next节点，还有一个random节点指向任意个元素
 */
public class CopyRandomList_35 {
  //使用map进行映射
  public ListNode copyRandomList(ListNode head) {
    if(head==null) return null;
    Map<ListNode,ListNode> map = new HashMap<>();
    ListNode cur = head;
    //复制节点，建立原节点->新节点的map映射
    while(cur!=null){
      map.put(cur,new ListNode(cur.val));
      cur=cur.next;
    }
    cur=head;
    //构建新链表radom和next的指向
    while(cur!=null){
      map.get(cur).next=map.get(cur.next);
      map.get(cur).random=map.get(cur.random);
      cur=cur.next;
    }
    return map.get(head);
  }
}
