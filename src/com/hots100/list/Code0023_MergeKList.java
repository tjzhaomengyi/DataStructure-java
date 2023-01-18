package com.hots100.list;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: zhaomengyi
 * @Date: 2022-11-30 4:10 下午
 * @Description:技巧:比较器的使用 + 小根堆priorityQueue结构
 */
public class Code0023_MergeKList {
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

  public static class ListNodeComparator implements Comparator<ListNode> {
    @Override
    public int compare(ListNode o1, ListNode o2) {
      return o1.val - o2.val;
    }
  }

  //技巧：priorityqueue就是堆结构，可以自动调整内部排序的顺序，
  // 每次弹出小根堆顶，都是最小的然后，在把弹出元素所在链表中的下一个元素再放进来，调整后再弹
  public ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparator());
    for (int i = 0; i < lists.length; i++) {
      //先把每个链表的头节点放入优先级队列中，即小根堆
      if (lists[i] != null) {
        heap.add(lists[i]);
      }
    }

    if (heap.isEmpty()) {
      return null;
    }
    ListNode head = heap.poll();//弹出小根堆的第一个元素，即最小
    ListNode pre = head;
    if (pre.next != null) {
      heap.add(pre.next);
    }
    while (!heap.isEmpty()) {
      ListNode cur = heap.poll();
      pre.next = cur;
      pre = cur;
      if (cur.next != null) {
        heap.add(cur.next);
      }
    }
    return head;
  }
}

