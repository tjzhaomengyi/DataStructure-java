package com.mikemyzhao.list;

import com.mikemyzhao.list.ListNode;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-12 16:31
 * @Description:反转链表操作
 */
public class ReverseList {
  //使用递归进行链表反转,这个是其他子链表反转的基础模板

  /**
   * 给定链表：
   * 1-->2-->3-->4-->5-->6-->NULL
   * 进行递归反转ListNode last = reverse(head.next),除去head进行反转
   * 1    -->【2<--3<--4<--5<--6】除去head已经完成反转
   * head                    last
   * 这时候只需要处理一下head的指针然后返回last即可
   * head.next.next=head//表示当前head的后一个的下一个指针指向head(head.next=2,然后2.next=head)
   * head.next=null//完事
   **/

  ListNode reverse(ListNode head) {
    if (null == head || head.next == null) {
      return head;
    }
    ListNode last = reverse(head.next);
    head.next.next = head;//表示当前head的后一个的下一个指针指向head(head.next=2,然后2.next=head)
    head.next = null;
    return last;
  }

  /**
   * 延伸1、使用第一个模板反转链表前n个元素
   **/
  ListNode successor = null;//加入后驱节点的记录，好让反转后的头指针指向它

  ListNode reverseN(ListNode head, int n) {
    if (n == 1) {
      //这里处理一下successor的赋值，这里在递归的时候也需要这个记录，这里因为只是反转前n个所以最后还是要返回head
      successor = head.next;
      return head;
    }
    //所以只调整后面n-1
    ListNode last = reverseN(head.next, n - 1);
    head.next.next = head;
    head.next = successor;
    return last;
  }

  /**
   * 延伸2、翻转部分区间元素
   * **/
  ListNode reverseBetween(ListNode head,int m,int n){
    if(m==1){
      //相当于反转前N元素
      return reverseN(head,n);
    }
    head.next = reverseBetween(head.next,m-1,n-1);
    return head;
  }

  /**
   * 延伸3、分区块反转链表
   * **/
  //普通反转链表代码
  ListNode reverseNormal(ListNode a){
    ListNode pre,cur,nxt;
    pre = null;cur = a;nxt=a;//前为空，当前和后为头
    while(cur.next!=null){
      //反转三部曲
      //1、获取下一个节点位置，一会指针好移动
      nxt=cur.next;
      //2、操作一下当前的节点即可，指向前
      cur.next = pre;
      //移动指针下标往后移动
      pre = cur;
      cur = nxt;
    }
    return pre;//pre最后一个到结尾收场
  }

  //普通反转是反转到末尾null，如果反转到b
  ListNode reverseToB(ListNode a,ListNode b){
    ListNode pre,cur,nxt;
    pre=null;cur=a;nxt=a;//前为空，当前和后为头
    while(cur.next!=b){
      //三部曲
      nxt = cur.next;//拿到下一个
      cur.next = pre;//操作当前节点指针
      pre = cur;cur = nxt;//挪动指针
    }
    return pre;
  }
  //每k个反转一次
  ListNode reverseGroup(ListNode head,int k){
    if(head==null){
      return null;
    }
    ListNode a,b;
    a=b=head;
    for(int i=0;i<k;i++){//不到k个，快指针往后走，找b
      if(b==null) return head;
      b=b.next;
    }
    ListNode newHead = reverseToB(a,b);
    //递归从newhead继续
    a.next = reverseGroup(b,k);
    return newHead;//第一个反转得到的作为头
  }

}
