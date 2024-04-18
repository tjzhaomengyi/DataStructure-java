package com.MCAAlgorithm.weeklyfactory.class_2022_12_2_week;

// 给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表
// 如果在二叉树中，存在一条一直向下的路径
// 且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True
// 否则返回 False 。
// 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
// 测试链接 : https://leetcode.cn/problems/linked-list-in-binary-tree/
// 最优解是KMP算法来解
// 官方题解都没有写的最优解
// 如果二叉树节点数是N，链表节点数M，时间复杂度为O(M+N)
public class Code05_LinkedListInBinaryTree {

	// 不提交这个类
	public class ListNode {
		int val;
		ListNode next;
	}

	// 不提交这个类
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	// 最优解
	// 官方题解都没有写的最优解
	// KMP算法来解 ，打死也想不出来这方法
	// 如果二叉树节点数是N，链表节点数M，时间复杂度为O(M+N)
	// 提交如下的所有方法，可以直接通过
	//思路：使用KMP算法不匹配回退的方法 ababa（target串） 和 ababc（match串），到a和c的位置比对发现两个人不一样了，那么c下标位置的就要往前回退回去，
	// 如果不一样了，让match串回退。在树遍历的时候
	public static boolean isSubPath(ListNode head, TreeNode root) {
		int n = 0;
		ListNode tmp = head;
		while (tmp != null) {
			n++;
			tmp = tmp.next;
		}
		int[] match = new int[n];
		n = 0;
		while (head != null) {
			match[n++] = head.val;
			head = head.next;
		}
		int[] next = getNextArray(match);
		return find(root, 0, match, next);
	}

	public static int[] getNextArray(int[] match) {
		if (match.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[match.length];
		next[0] = -1;
		next[1] = 0;
		int i = 2;
		int cn = 0;
		while (i < next.length) {
			if (match[i - 1] == match[cn]) {
				next[i++] = ++cn;
			} else if (cn > 0) {
				cn = next[cn];
			} else {
				next[i++] = 0;
			}
		}
		return next;
	}

	// 当前目标串 来到 TreeNode cur 这个字符
	// 一个叫match串，match来到mi位置的字符，cur字符，如果匹配不上match，去next找后续修改的匹配字符，next数组提前生成好
	// 返回cur后续的路能不能把match串配完！
	public static boolean find(TreeNode cur, int mi, int[] match, int[] next) {
		if (mi == match.length) { //mi到了终止位置，
			return true;
		}
		if (cur == null) { //当前节点是空了，目标串没有字符了，但是mi没完，那就是配不出来了
			return false;
		}
		// 当前目标串的字符 :  cur.val
		// 当前match串的字符 : match[mi]
		while (mi >= 0 && cur.val != match[mi]) { //但是要保证
			mi = next[mi];//如果配不上，就往前蹦，其实是在match串往前蹦，所以要保证mi不能在左侧越界
		}
		// 后续的字符，先走左，配去！
		// 后续的字符，分裂可能性了！走右，配去！
		return find(cur.left, mi + 1, match, next) || find(cur.right, mi + 1, match, next);
	}

}
