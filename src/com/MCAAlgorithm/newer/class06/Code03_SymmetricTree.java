package com.MCAAlgorithm.newer.class06;

import java.util.LinkedList;
import java.util.Queue;

// 测试链接：https://leetcode.com/problems/symmetric-tree
public class Code03_SymmetricTree {

	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	public static boolean isSymmetric(TreeNode root) {
		return isMirror(root, root);
	}

	public static boolean isMirror(TreeNode h1, TreeNode h2) {
		if (h1 == null ^ h2 == null) {
			return false;
		}
		if (h1 == null && h2 == null) {
			return true;
		}
		return h1.val == h2.val && isMirror(h1.left, h2.right) && isMirror(h1.right, h2.left);
	}

	//1、 使用递归方法
	public boolean isMirror_nice(TreeNode h1, TreeNode h2){
		//把两个节点都为空写在前面
		if(h1 == null && h2 == null){
			return true;
		}
		//把两个有一个为空的写在后面，这样就不用写抑或条件了
		if(h1 == null || h2 == null){
			return false;
		}
		return h1.val == h2.val && isMirror_nice(h1.left, h2.right) && isMirror_nice(h1.right, h2.left);
	}

	// 2、使用按照层级遍历的方法
	public boolean isMirror_level(TreeNode root){
		if(root == null) return true;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root.left);
		queue.offer(root.right);
		while(!queue.isEmpty()){
			TreeNode left = queue.poll();
			TreeNode right = queue.poll();
			if(left == null && right == null){
				continue; //继续检查
			}
			if(left == null || right == null){
				return false;
			}
			if(left.val != right.val){
				return false;
			}
			//两两一组进行比较
			queue.offer(left.left);
			queue.offer(right.right);

			queue.offer(left.right);
			queue.offer(right.left);
		}
		return true;
	}


}
