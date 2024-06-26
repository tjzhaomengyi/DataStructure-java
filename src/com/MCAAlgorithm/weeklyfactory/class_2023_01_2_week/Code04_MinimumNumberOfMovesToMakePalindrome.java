package com.MCAAlgorithm.weeklyfactory.class_2023_01_2_week;

import java.util.ArrayList;

// 给你一个只包含小写英文字母的字符串 s 。
// 每一次 操作 ，你可以选择 s 中两个 相邻 的字符，并将它们交换。
// 请你返回将 s 变成回文串的 最少操作次数 。
// 注意 ，输入数据会确保 s 一定能变成一个回文串。
// 测试链接 : https://leetcode.cn/problems/minimum-number-of-moves-to-make-palindrome/
public class Code04_MinimumNumberOfMovesToMakePalindrome {

	// 思路：从左往右看，最先满足某个字母最左侧端点和其对应字母最右侧组成自己的回文
	// AABBCC -- 找到1位置A，那么就直接推出对应的A在n-1位置，
	public static int minMovesToMakePalindrome(String s) {
		int n = s.length();
		ArrayList<ArrayList<Integer>> indies = new ArrayList<>();
		// a -> 0 -> 含有a的所有位置{...}
		// b -> 1 -> 含有b的所有位置{...}
		for (int i = 0; i < 26; i++) {
			indies.add(new ArrayList<>());
		}
		// AABAA...
		// 12345
		// A -> 0 : {1 2 4 5}
		// B -> 1 : {3... }
		for (int i = 0, j = 1; i < n; i++, j++) {//在indies中的下标从1开始目的是为了和IndexTree对应，因为IndexTree下标从1开始
			int c = s.charAt(i) - 'a';//当前字符
			indies.get(c).add(j); //
		}
		// 原始下标 对应着 该去往的下标 存在arr中
		int[] arr = new int[n + 1];
		// 建立好indexTree，初始时，下标1~n上认为全是1
		IndexTree it = new IndexTree(n);
		for (int i = 0, l = 1; i < n; i++, l++) {
			// 例子：AAAABB，第1个A找到对应的第4位A，把第4位的A掏空，下次遍历碰到的时候就不用了
			// i -> 拿字符 从下标0开始
			// l -> 从下标1开始
			// arr[l] != 0
			// 当前的l，曾经作为姘头之一的右侧（遍历第1个A填写的最右侧的第4个字符A），之前填过了！
			if (arr[l] == 0) {
				int c = s.charAt(i) - 'a';
				// l......r
				int r = indies.get(c).remove(indies.get(c).size() - 1);//把删除的对应位置的删除掉，这里肯定是最后一个！！！
				if (l == r) { //中间的哪个字符！
					arr[l] = (1 + n) / 2;
					it.add(l, -1); //把对应位置搞成洞
				} else {//既有左又有右能陪上的
					// l != r
					// l -> 左边的序号！ 0...l累加和！
					int kth = it.sum(l); //通过累加和得到
					arr[l] = kth;
					arr[r] = n - kth + 1;
					it.add(r, -1);
				}
			}
		}
		//数学结论：如果一个无序对可以两两交换，就是这个数组中逆序对的个数。。。。。
		return number(arr, new int[n + 1], 1, n); //这里求交换次数，就是求这个数组的逆序对，逆序对在归并排序中讲解过
	}

	public static class IndexTree {

		public int[] tree;
		public int n;

		public IndexTree(int size) {
			tree = new int[size + 1];
			n = size;
			for (int i = 1; i <= n; i++) {
				add(i, 1);
			}
		}

		public int sum(int i) {
			int ans = 0;
			while (i > 0) {
				ans += tree[i];
				i -= i & -i;
			}
			return ans;
		}

		public void add(int i, int v) {
			while (i < tree.length) {
				tree[i] += v;
				i += i & -i;
			}
		}
	}

	public static int number(int[] arr, int[] help, int l, int r) {
		if (l >= r) {
			return 0;
		}
		int mid = l + ((r - l) >> 1);
		return number(arr, help, l, mid) + number(arr, help, mid + 1, r) + merge(arr, help, l, mid, r);
	}

	public static int merge(int[] arr, int[] help, int l, int m, int r) {
		int i = r;
		int p1 = m;
		int p2 = r;
		int ans = 0;
		while (p1 >= l && p2 > m) {
			ans += arr[p1] > arr[p2] ? (p2 - m) : 0;
			help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
		}
		while (p1 >= l) {
			help[i--] = arr[p1--];
		}
		while (p2 > m) {
			help[i--] = arr[p2--];
		}
		for (i = l; i <= r; i++) {
			arr[i] = help[i];
		}
		return ans;
	}

}
