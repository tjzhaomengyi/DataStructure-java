package com.MCAAlgorithm.weeklyfactory.class_2023_02_3_week;

import java.util.ArrayList;
import java.util.Arrays;

// 来自TikTok美国笔试
// 给定一个长度为N的数组arr，arr[i]表示宝石的价值
// 你在某天遇到X价值的宝石，
// X价值如果是所有剩余宝石价值中的最小值，你会将该宝石送人
// X价值如果不是所有剩余宝石价值中的最小值，你会将该宝石放到所有宝石的最后
// 返回把宝石都送人需要多少天
// 比如arr = [3,1,4,3,1,2]
// 在第1天，你遇到了价值3的宝石，但是3并不是所有剩余宝石的价值最小值
// 所以你把3放在了所有宝石的最后，arr = [1,4,3,1,2,3]
// 在第2天，你遇到了价值1的宝石，1是所有剩余宝石的价值最小值
// 所以你把价值1的宝石送人，arr = [4,3,1,2,3]
// 在第3天，你把价值4的宝石放到最后，arr = [3,1,2,3,4]
// 在第4天，你把价值3的宝石放到最后，arr = [1,2,3,4,3]
// 在第5天，你送出了价值1的宝石，arr = [2,3,4,3]
// 在第6天，你送出了价值2的宝石，arr = [3,4,3]
// 在第7天，你送出了价值3的宝石，arr = [4,3]
// 在第8天，你把价值4的宝石放到最后，arr = [3,4]
// 在第9天，你送出了价值3的宝石，arr = [4]
// 在第10天，你送出了价值4的宝石，宝石已经没有了
// 所以返回10
// 1 <= N <= 10的5次方
// 1 <= 宝石价值 <= 10的9次方
public class Code05_SendOutDiamondDays {

	// 思路：index tree  + segment tree【segment tree中找某个范围的最小值的最左侧位置】，
	// 分析：[3 2 1 4 5 2 4]要告诉我，数组里最小值和最小值最左的所在位置，
	//  s   f
	// 在第几天把f位置给出去 day += f - s，给出去后，把这个位置的改成最大值max
	// s来到max位置，找到右侧最小的
	// 3 2 max 4 5 2 4  ｜ 3 2
	//     s       f
	// day += f - s，改成max
	// 3 2 max 4 5 max 4  ｜ 3  2 max 4 5
	//              s          f
	// day += f - s
	// 思路：1、线段树维护数组中最小值最左位置的查询
	// 思路：2、index tree维护是否把宝石给出去，1表示没给，0表示给出了，index tree的0对应线段树中的max，
	// 思路：3、使用index tree来计算给出去用几天，不会真的往后放，但是维持的范围是对的
	// 例子：
	// 3 1 4 3 1 2
	// 1 1 1 1 1 1
	// max 4 3 1 2 3
	// 0   1 1 1 1 1
	// max 2 3 max 4 3
	// 0   1 1  0  1 1
	// max max 4 3 max max
	// 0   0   1 1 0    0
	// 暴力方法
	// 为了验证
	public static int days1(int[] diamonds) {
		ArrayList<Integer> arr = new ArrayList<>();
		for (int num : diamonds) {
			arr.add(num);
		}
		int ans = 0;
		while (!arr.isEmpty()) {
			ans++;
			deal(arr);
		}
		return ans;
	}

	// 暴力方法
	// 为了验证
	public static void deal(ArrayList<Integer> arr) {
		int head = arr.remove(0);
		int min = head;
		for (int i = 0; i < arr.size(); i++) {
			min = Math.min(min, arr.get(i));
		}
		if (head > min) {
			arr.add(head);
		}
	}

	// 正式方法
	// 时间复杂度O(N * (logN)的平方)
	public static int days2(int[] diamonds) {
		// n : 位置
		int n = diamonds.length;
		// 1 ~ n : 1
		//  1 1 1 1 1 1 1
		//  1 2 3 4 5 6 7
		IndexTree it = new IndexTree(n);//index tree
		//  7 6 2...
		//  1 2 3....
		SegmentTree st = new SegmentTree(diamonds);//线段树
		int days = 0;
		int find, start = 1;
		while (it.sum(1, n) != 0) { //从1下标还没有给完
			// start ..... find(后续....最小值，最左的位置)，从start点出发，找find最小值的最左位置
			find = find(st, start, n);
			days += days(it, start, find, n); //从start到find把天数算出来
			//  1
			// find
			it.add(find, -1);//变成0
			st.update(find, Integer.MAX_VALUE);//变成系统最大
			start = find;//来到find继续找下一个位置
		}
		return days;//从1到n累加和是0，都给完了！返回
	}

	// 使用二分，利用线段树二分
	public static int find(SegmentTree st, int start, int n) {
		// start....n 左部分  1 ~ start-1 右
		int l, r, min = st.min(1, n);
		if (st.min(start, n) == min) {
			l = start;
			r = n;
		} else {
			l = 1;
			r = start - 1;
		}
		int m, ans = -1;
		while (l <= r) {
			m = (l + r) / 2;
			if (st.min(l, m) == min) {
				ans = m;
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return ans;
	}

	//在index tree中
	public static int days(IndexTree sumIt, int start, int find, int n) {
		if (start <= find) {
			// 1 2 3 4 5 6
			//   S     F
			return sumIt.sum(start, find);//直接减
		} else {
			// 1 2 3 4 5 6 转回去了，一共这个从s到f移动3天，泛化公式从start到n + 从1到find
			//   F     S
			return sumIt.sum(start, n) + sumIt.sum(1, find);
		}
	}

	// 支持查询累加和
	public static class IndexTree {

		private int[] tree;
		private int n;

		public IndexTree(int size) {
			n = size;
			tree = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				add(i, 1);
			}
		}

		private int sum(int i) {
			int ret = 0;
			while (i > 0) {
				ret += tree[i];
				i -= i & -i;
			}
			return ret;
		}

		public int sum(int l, int r) {
			return sum(r) - sum(l - 1);
		}

		public void add(int i, int d) {
			while (i <= n) {
				tree[i] += d;
				i += i & -i;
			}
		}

	}

	// 支持查询最小值
	public static class SegmentTree {

		private int n;
		private int[] min;

		public SegmentTree(int[] arr) {
			n = arr.length;
			min = new int[(n + 1) << 2];
			Arrays.fill(min, Integer.MAX_VALUE);
			for (int i = 1; i <= n; i++) {
				update(i, arr[i - 1]);
			}
		}

		public void update(int i, int v) {
			update(i, i, v, 1, n, 1);
		}

		public int min(int l, int r) {
			return min(l, r, 1, n, 1);
		}

		private void pushUp(int rt) {
			min[rt] = Math.min(min[rt << 1], min[rt << 1 | 1]);
		}

		private void update(int L, int R, int C, int l, int r, int rt) {
			if (L <= l && r <= R) {
				min[rt] = C;
				return;
			}
			int mid = (l + r) >> 1;
			if (L <= mid) {
				update(L, R, C, l, mid, rt << 1);
			}
			if (R > mid) {
				update(L, R, C, mid + 1, r, rt << 1 | 1);
			}
			pushUp(rt);
		}

		private int min(int L, int R, int l, int r, int rt) {
			if (L <= l && r <= R) {
				return min[rt];
			}
			int mid = (l + r) >> 1;
			int ans = Integer.MAX_VALUE;
			if (L <= mid) {
				ans = Math.min(ans, min(L, R, l, mid, rt << 1));
			}
			if (R > mid) {
				ans = Math.min(ans, min(L, R, mid + 1, r, rt << 1 | 1));
			}
			return ans;
		}

	}

	// 为了测试
	public static int[] randomArray(int n, int v) {
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = (int) (Math.random() * v);
		}
		return ans;
	}

	// 为了测试
	public static void main(String[] args) {
		System.out.println("例子测试开始");
		int[] arr = { 3, 1, 4, 3, 1, 2 };
		System.out.println(days1(arr));
		System.out.println(days2(arr));
		System.out.println("例子测试结束");

		int N = 100;
		int V = 100000;
		int testTimes = 1000;
		System.out.println("随机测试开始");
		for (int i = 0; i < testTimes; i++) {
			int n = (int) (Math.random() * N) + 1;
			int[] diamonds = randomArray(n, V);
			int ans1 = days1(diamonds);
			int ans2 = days2(diamonds);
			if (ans1 != ans2) {
				System.out.println("出错了!");
			}
		}
		System.out.println("随机测试结束");

		System.out.println("性能测试开始");
		int n = 100000;
		int v = 1000000000;
		int[] diamonds = randomArray(n, V);
		System.out.println("宝石数量 : " + n);
		System.out.println("价值范围 : " + v);
		long start = System.currentTimeMillis();
		days2(diamonds);
		long end = System.currentTimeMillis();
		System.out.println("运行时间 : " + (end - start) + " 毫秒");
		System.out.println("性能测试结束");
	}

}
