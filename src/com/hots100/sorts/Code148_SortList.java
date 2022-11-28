package com.hots100.sorts;


/**
 * @Author: zhaomengyi
 * @Date: 2022-11-25 12:28 下午
 * @Description:
 */
public class Code148_SortList {

  //思路：因为要使用O(1)的空间复杂度，首先想到排序的空间复杂度，如果是数组归并排序就是O(n)的空间复杂，需要开辟数组空间
  //  这个题是链表，不需要开格外空间。使用非递归版本的merge sort.非递归版本的merge sort是在折腾步长.step=1，2，,4，,8.....依次下去
  public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

 public ListNode sortList(ListNode head) {
    //扣边界：边界很多，（1）分组个数不够 （2）组不够的，有几个就是几个
    //思路：hthtn（）函数，表示左组的头尾、右组的头尾和下一组的指向谁
   return null;
  }
}
