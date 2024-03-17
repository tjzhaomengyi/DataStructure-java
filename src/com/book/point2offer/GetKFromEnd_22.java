package com.book.point2offer;

import com.mikemyzhao.list_1.ListNode;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-06 15:42
 * @Description:获取导数第k个节点
 */
public class GetKFromEnd_22 {
  public ListNode getKthFromEnd(ListNode head, int k) {
    if(head==null) return null;
    ListNode pre = head,cur=pre;
    for(int i=0;i<k;i++){
      if(cur==null){
        return null;
      }
      cur=cur.next;
    }
    while(cur!=null){
      pre=pre.next;
      cur=cur.next;
    }

    return pre;
  }
}
