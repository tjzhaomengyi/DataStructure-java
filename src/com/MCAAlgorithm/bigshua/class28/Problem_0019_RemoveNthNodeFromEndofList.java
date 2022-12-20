package com.MCAAlgorithm.bigshua.class28;

public class Problem_0019_RemoveNthNodeFromEndofList {

	public static class ListNode {
		public int val;
		public ListNode next;
	}

	//思路：1、让 cur 不断往链表的后面走直到结束，一边走一边做 n--
	// 如果结果 n>0 说明 n 大于了链表长度，返回头节点
	// 如果 n=0 表示正好删除头节点
	// 如果 n < 0 表示
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null || n < 1) return head;
		ListNode cur = head;
		ListNode pre = null;
		while(cur != null){
			n--;
			cur = cur.next;
		}
		if(n == 0){
			head = head.next;
		}
		if(n < 0){
			cur = head;
			while(++n != 0){//技巧：这里要找待删除的前一个节点
				cur = cur.next;

			}
			cur.next = cur.next.next;
		}
		return head;
	}

}
