package com.mikemyzhao.SomeSkills_0.TwoPntAndBSearch.twopnts;

import com.mikemyzhao.list_1.ListNode;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-12 12:51
 * @Description:1->2->null:false，1—>2->1->null:true
 */
public class CycleTextList {
  /**给一个单链表看是否对称
   * 方法1：将链表反转，对比
   * 方法2：借助二叉树后续遍历，不需要显式反转原始链表也可以倒序遍历链表
   * **/
  ListNode left;
  boolean isPalindromeRecurse(ListNode head){
    left = head;
    return travseRecurse(left);
  }

  //利用递归，倒序遍历单链表,时间复杂度O(n),利用的是递归函数的堆栈往上回弹
  boolean travseRecurse(ListNode right){
    if(right==null) return true;
    boolean res = travseRecurse(right.next);
    //后续遍历代码
    res = res && (right.val == left.val);
    left=left.next;
    return res;
  }

  //使用双指针解决
  boolean isPalindromeDoublePnt(ListNode head){
    ListNode slow,fast;
    slow=fast=head;
    //如果fast指针没有指向null，说明链表长度为奇数，slow还要再进一步
    //从slow反转链表
    while(fast !=null && fast.next!=null){
      slow=slow.next;
      fast =fast.next.next;
    }
    if(fast!=null){
      slow=slow.next;
    }
    ListNode left = head;
    ListNode  right = reverse(slow);
    while(right!=null){
      if(left.val != right.val){
        return false;
      }
      left = left.next;
      right = right.next;
    }
    return true;

  }

  //反转链表
  ListNode reverse(ListNode head){
    ListNode pre = null,cur = null;
    while(cur!=null){
      ListNode next = cur.next;
      cur.next = pre;
      pre = cur;
      cur = next;
    }
    return pre;
  }


}
