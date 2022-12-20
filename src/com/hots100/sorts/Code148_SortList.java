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

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  public ListNode sortList(ListNode head) {
    int N = 0;
    ListNode cur = head;
    while (cur != null) {
      N++;
      cur = cur.next;
    }
    ListNode h = head;
    ListNode pre = null;//思路：只要目的明确是想返回一个链表，就要有 head 和 pre 帮助往后指
    ListNode teamFirst = head; //思路：让teamfirst不断分配组，这是个冤大头
    //思路：开始从头开始，自底向上进行归并排序
    for(int len = 1; len < N; len <<= 1){
      while(teamFirst != null){//思路：只要第一组的元素不为null，我们就往后顺 hthtn 和 merge的操作
        ListNode[] hthtn = hthtn(teamFirst, len);
        ListNode[] mhmt = merge(hthtn[0], hthtn[1], hthtn[2], hthtn[3]);
        //思路：往整体链上连
        if(h == teamFirst){//扣边界：第一组
          h = mhmt[0]; //
          pre = mhmt[1];
        } else {
          pre.next = mhmt[0];
          pre = mhmt[1];
        }
        teamFirst = hthtn[4];
      }
      teamFirst = h;//进行下一个步长的迭代
      pre = null;
    }
    return h;


  }

  private static ListNode[] hthtn(ListNode teamFirst, int len){
    ListNode ls = teamFirst;
    ListNode le = teamFirst;
    ListNode rs = null;
    ListNode re = null;
    ListNode next = null;//记录re后面的元素
    int pass = 0;//记录在当前组内走的步数
    while(teamFirst != null){
      pass++;
      //思路：1、找四个点位
      if(pass <= len){
        le = teamFirst;
      }
      if(pass == len + 1){
        rs = teamFirst;
      }
      if(pass > len){
        re = teamFirst;
      }
      if(pass == (len << 1)){
        break;
      }
      teamFirst = teamFirst.next;
    }

    //思路：2、断连
    le.next = null;
    if(re != null){
      next = re.next;
      re.next = null;
    }
    return new ListNode[] {ls, le, rs, re, next};
  }

  public static ListNode[] merge(ListNode ls, ListNode le, ListNode rs, ListNode re){
    if(rs == null){//扣边界
      return new ListNode[]{ls, le};
    }
    ListNode head = null;
    ListNode pre = null;
    ListNode cur = null;
    ListNode tail = null;
    while(ls != null && rs != re.next) { //todo:ls不为空即可，因为le.next就是空，之前已经被断连了
      if(ls.val <= rs.val){
        cur = ls;
        ls = ls.next;
      } else {
        cur = rs;
        rs = rs.next;
      }
      if(pre == null){
        head = cur;
        pre = cur;
      } else {
        pre.next = cur;
        pre = cur;
      }
    }
    if(ls != null){//todo：ls不为空即可，因为le.next就是空，之前已经被断连了
      while(ls != null){
        pre.next = ls;//移动链表上的指针往后续
        pre = ls;
        tail = ls;
        ls = ls.next;
      }
    } else {
      while(rs != null){
        pre.next = rs;
        pre = rs;
        tail = rs;
        rs = rs.next;
      }
    }
    return new ListNode[]{head, tail};
  }
}
