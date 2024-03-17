package com.MCAAlgorithm.weeklyfactory.class_2022_03_2_week;

// 如何时间复杂度O(N)，额外空间复杂度O(1)，解决最低公共祖先问题?
// 测试链接 : https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
public class Code08_TimeNSpace1LowestCommonAncestor {
	//情况（1）a或者b是另一个的祖先，如果中了直接返回
	//(2)把a和b分出左右，使用先序遍历，先把a或者b找出来，看谁是较左的节点，如果a是较左的节点，b肯定在a的父亲树的右侧，肯定不在a的父亲树的左侧
	//b只能在左拐节点的右树上，利用morris遍历，查a的父亲右树下是否有b，利用morris的后续遍历

	// 这个类不要提交
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	// 提交以下的方法
	// 该方法亮点在于：时间复杂度O(N)，额外空间复杂度O(1)
	public static TreeNode lowestCommonAncestor(TreeNode head, TreeNode o1, TreeNode o2) {
		if (findFirst(o1.left, o1, o2) != null || findFirst(o1.right, o1, o2) != null) {
			return o1;
		}
		if (findFirst(o2.left, o1, o2) != null || findFirst(o2.right, o1, o2) != null) {
			return o2;
		}
		TreeNode leftAim = findFirst(head, o1, o2);
		TreeNode cur = head;
		TreeNode mostRight = null;
		TreeNode ans = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
					if (findLeftAim(cur.left, leftAim)) {
						if (ans == null && findFirst(leftAim.right, o1, o2) != null) {
							ans = leftAim;
						}
						leftAim = cur;
					}
				}
			}
			cur = cur.right;
		}
		return ans != null ? ans : (findFirst(leftAim.right, o1, o2) != null ? leftAim : head);
	}

	public static boolean findLeftAim(TreeNode head, TreeNode leftAim) {
		TreeNode tail = reverseEdge(head);
		TreeNode cur = tail;
		boolean ans = false;
		while (cur != null) {
			if (cur == leftAim) {
				ans = true;
			}
			cur = cur.right;
		}
		reverseEdge(tail);
		return ans;
	}

	public static TreeNode reverseEdge(TreeNode from) {
		TreeNode pre = null;
		TreeNode next = null;
		while (from != null) {
			next = from.right;
			from.right = pre;
			pre = from;
			from = next;
		}
		return pre;
	}

	public static TreeNode findFirst(TreeNode head, TreeNode o1, TreeNode o2) {
		if (head == null) {
			return null;
		}
		TreeNode cur = head;
		TreeNode mostRight = null;
		TreeNode first = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					if (first == null && (cur == o1 || cur == o2)) {
						first = cur;
					}
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			} else {
				if (first == null && (cur == o1 || cur == o2)) {
					first = cur;
				}
			}
			cur = cur.right;
		}
		return first;
	}

}
