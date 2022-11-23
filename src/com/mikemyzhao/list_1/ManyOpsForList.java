package com.mikemyzhao.list;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-29 17:20
 * @Description:1、删除链表中指定节点2、删除链表中重复节点
 */
public class ManyOpsForList {

  public ListNode deleteNode(ListNode head, int val) {
    if(head.val==val) return head.next;
    ListNode pre = head,cur =head.next;//删除的时候一定要给pre作为head
    while(cur!=null && cur.val!=val){//注意:在删除的时候直接遍历cur即可
      pre=cur;
      cur=cur.next;
    }
    if(cur.val==val) {
      pre.next = cur.next;
    }
    return head;
  }

  public ListNode deleteDuplicates(ListNode head) {
    Set<Integer> tmp = new HashSet<>();
    if(head==null) return null;
    ListNode pre = head,cur=pre.next;
    tmp.add(head.val);
    while(cur!=null){
      if(!tmp.contains(cur.val)){
        tmp.add(cur.val);
        pre=cur;
        cur=cur.next;
      }else{
        pre.next=cur.next;
        cur=cur.next;
      }
    }
    return head;
  }

}
