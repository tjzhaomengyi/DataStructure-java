package com.hots100.list;

/**
 * @Author: zhaomengyi
 * @Date: 2022-11-27 10:49 上午
 * @Description:
 */
public class Code2_AddTwoNumbers {
  public static class ListNode {
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

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int len1 = getNumLength(l1);
    int len2 = getNumLength(l2);
    ListNode longer = len1 >= len2 ? l1 : l2;
    ListNode shorter = longer == l1 ? l2 : l1;
    ListNode lp = longer;
    ListNode sp = shorter;
    //技巧：使用一个小标志符跟踪是否是最后一位
    int carry = 0;
    ListNode last = lp;
    boolean isShortEnd = false;
    while (lp != null) {
      //扣边界
      int shorterVal = isShortEnd ? 0 : sp.val;
      int sum = shorterVal + lp.val + carry;
      int tmp = sum % 10;
      int pos = sum / 10;
      carry = pos > 0 ? 1 : 0;
      lp.val = tmp;
      last = lp;
      lp = lp.next;
      if(sp.next != null) {
        sp = sp.next;
      } else {
        isShortEnd = true;
      }
    }
    if(carry > 0){
      last.next = new ListNode(1);
    }
    return l1;
  }

  private int getNumLength(ListNode l) {
    if(l == null){
      return 0;
    }
    int len = 0;
    ListNode tmp = l;
    while (tmp.next != null) {
      len++;
      tmp = tmp.next;
    }
    return len;
  }

  public static void main(String[] args) {
    ListNode l1 = new ListNode(2);
    l1.next = new ListNode(3);
    l1.next.next = new ListNode(4);

    ListNode l2 = new ListNode(2);
    l2.next = new ListNode(3);
    l2.next.next = new ListNode(4);

    new Code2_AddTwoNumbers().addTwoNumbers(l1,l2);
    ListNode tmp = l1;
    while(tmp != null){
      System.out.println(tmp.val);
      tmp = tmp.next;
    }

//    System.out.println(l1.val);
//    System.out.println(l1.next.val);
//    System.out.println(l1.next.next.val);
  }

}
