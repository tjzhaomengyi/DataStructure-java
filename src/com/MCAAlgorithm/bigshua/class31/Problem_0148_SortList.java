package com.MCAAlgorithm.bigshua.class31;

//这题确实难

public class Problem_0148_SortList {

	public static class ListNode {
		int val;
		ListNode next;

		public ListNode(int v) {
			val = v;
		}
	}

	// 链表的归并排序,
	// 时间复杂度O(N*logN), 因为是链表所以空间复杂度O(1)
	public static ListNode sortList1(ListNode head) {
		int N = 0;
		ListNode cur = head;
		while (cur != null) {
			N++;
			cur = cur.next;
		}
		ListNode h = head;
		ListNode teamFirst = head;
		ListNode pre = null;
		for (int len = 1; len < N; len <<= 1) {//思路：步长从2开始，然后每次2倍递增
			while (teamFirst != null) {
				// 左组从哪到哪 ls le
				// 右组从哪到哪 rs re
				// 最终顺序：左组 右组 next
				ListNode[] hthtn = hthtn(teamFirst, len);//注意：len表示当前组的长度
				// ls...le rs...re -> merge去
				// 整体的头、整体的尾
				ListNode[] mhmt = merge(hthtn[0], hthtn[1], hthtn[2], hthtn[3]);
				if (h == teamFirst) {
					h = mhmt[0];
					pre = mhmt[1];
				} else {
					pre.next = mhmt[0];
					pre = mhmt[1];
				}
				teamFirst = hthtn[4]; //思路：走到下一组
			}
			teamFirst = h; //思路：如果现在的步长是2，下一组还得从当前merge好的 h 位置开始
			pre = null;
		}
		return h;
	}

	//思路：如何理解hthtn()这个函数：
	// 功能：获得新组的左组头结点和尾节点、右组的头结点和尾结点、指向下一组的指针next。
	// 参数：teamfirst表示左组第一个节点【但是会利用teamfirst往下移动，但是我觉得还是用一个变量指针合适】。
	// 		len表示这一组的步长，比如len=8，表示左组长度为8，右组长度为8
	// 目标：需要把新组的结果连到前面组的尾部，同时还要生成指向下一组的next
	public static ListNode[] hthtn(ListNode teamFirst, int len) {
		ListNode ls = teamFirst;
		ListNode le = teamFirst;
		ListNode rs = null;
		ListNode re = null;
		ListNode next = null;
		int pass = 0;
		while (teamFirst != null) {
			//思路：1、下面的方法就是在找四个位置，当小于len的时候也就是一个左/右组的长度，le左侧end就一直往后移动，因为teamFirst移动
			pass++;
			//思路：1-1 le不断跟着teamFirst移动，直到正好等于len就是左组结束
			if (pass <= len) { //左组结束位
				le = teamFirst;//把左侧结束位跟着teamfirst往右侧推
			}
			//思路：1-2 确定右组开始位置
			if (pass == len + 1) {//右组开始位
				rs = teamFirst;
			}
			//思路：1-3 确定右组结束为止
			if (pass > len) {//右组结束位
				re = teamFirst;
			}
			//思路：1-4 如果现在pass已经是len的2倍了，结束
			if (pass == (len << 1)) { //如果刚好是长度，说明已经完了
				break;
			}
			teamFirst = teamFirst.next;
		}
		//思路：2、对左右组执行断连
		le.next = null;//左组和右组断连！
		if (re != null) {
			next = re.next;//但是要保存右组的下一个位置，要不找不到后面段位了
			re.next = null;//右组也断连
		}
		return new ListNode[] { ls, le, rs, re, next };
	}

	//思路：对左右组执行merge操作，返回merge链表的左右结点位置
	// L L L L | R R R R
	public static ListNode[] merge(ListNode ls, ListNode le, ListNode rs, ListNode re) {
		if (rs == null) {
			return new ListNode[] { ls, le };
		}
		//技巧：使用head保存存的链表，使用pre往后移动
		ListNode head = null;
		ListNode pre = null;
		ListNode cur = null;
		ListNode tail = null;
		while (ls != le.next && rs != re.next) { // le.next和re.next都是null
			if (ls.val <= rs.val) {
				cur = ls;
				ls = ls.next;//思路：ls小，往后移动
			} else {
				cur = rs;
				rs = rs.next;
			}
			if (pre == null) {
				head = cur;
				pre = cur;
			} else {
				pre.next = cur;
				pre = cur;
			}
		}
		if (ls != le.next) {
			while (ls != le.next) {
				pre.next = ls;
				pre = ls;
				tail = ls;
				ls = ls.next;
			}
		} else {
			while (rs != re.next) {
				pre.next = rs;
				pre = rs;
				tail = rs;
				rs = rs.next;
			}
		}
		return new ListNode[] { head, tail };
	}


	//技巧:代替左神的递归版本，但是这里使用了递归方法，系统会为递归开辟递归栈，也不是常数的空间
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode fast = head.next, slow = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		ListNode tmp = slow.next;
		slow.next = null;
		ListNode left = sortList(head);
		ListNode right = sortList(tmp);
		ListNode h = new ListNode(0);
		ListNode res = h;
		while (left != null && right != null) {
			if (left.val < right.val) {
				h.next = left;
				left = left.next;
			} else {
				h.next = right;
				right = right.next;
			}
			h = h.next;
		}
		h.next = left != null ? left : right;
		return res.next;
	}


	// 链表的快速排序
	// 时间复杂度O(N*logN), 空间复杂度O(logN)
	public static ListNode sortList2(ListNode head) {
		int n = 0;
		ListNode cur = head;
		while (cur != null) {
			cur = cur.next;
			n++;
		}
		return process(head, n).head;
	}

	public static class HeadAndTail {
		public ListNode head;
		public ListNode tail;

		public HeadAndTail(ListNode h, ListNode t) {
			head = h;
			tail = t;
		}
	}

	public static HeadAndTail process(ListNode head, int n) {
		if (n == 0) {
			return new HeadAndTail(head, head);
		}
		int index = (int) (Math.random() * n);
		ListNode cur = head;
		while (index-- != 0) {
			cur = cur.next;
		}
		Record r = partition(head, cur);
		HeadAndTail lht = process(r.lhead, r.lsize);
		HeadAndTail rht = process(r.rhead, r.rsize);
		if (lht.tail != null) {
			lht.tail.next = r.mhead;
		}
		r.mtail.next = rht.head;
		return new HeadAndTail(lht.head != null ? lht.head : r.mhead, rht.tail != null ? rht.tail : r.mtail);
	}

	public static class Record {
		public ListNode lhead;
		public int lsize;
		public ListNode rhead;
		public int rsize;
		public ListNode mhead;
		public ListNode mtail;

		public Record(ListNode lh, int ls, ListNode rh, int rs, ListNode mh, ListNode mt) {
			lhead = lh;
			lsize = ls;
			rhead = rh;
			rsize = rs;
			mhead = mh;
			mtail = mt;
		}
	}

	public static Record partition(ListNode head, ListNode mid) {
		ListNode lh = null;
		ListNode lt = null;
		int ls = 0;
		ListNode mh = null;
		ListNode mt = null;
		ListNode rh = null;
		ListNode rt = null;
		int rs = 0;
		ListNode tmp = null;
		while (head != null) {
			tmp = head.next;
			head.next = null;
			if (head.val < mid.val) {
				if (lh == null) {
					lh = head;
					lt = head;
				} else {
					lt.next = head;
					lt = head;
				}
				ls++;
			} else if (head.val > mid.val) {
				if (rh == null) {
					rh = head;
					rt = head;
				} else {
					rt.next = head;
					rt = head;
				}
				rs++;
			} else {
				if (mh == null) {
					mh = head;
					mt = head;
				} else {
					mt.next = head;
					mt = head;
				}
			}
			head = tmp;
		}
		return new Record(lh, ls, rh, rs, mh, mt);
	}

}
