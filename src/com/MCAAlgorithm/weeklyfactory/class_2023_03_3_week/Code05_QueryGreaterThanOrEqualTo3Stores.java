package com.MCAAlgorithm.weeklyfactory.class_2023_03_3_week;

import java.util.Arrays;

// 来自学员问题，大厂笔试面经帖子，todo：这是道综合题
// 假设一共有M个车库，编号1~M，时间点从早到晚是从1~T
// 一共有N个记录，每一条记录如下{a, b, c}
// 表示一辆车在b时间点进入a车库，在c时间点从a车库出去
// 一共有K个查询，每个查询只有一个数字X，表示请问在X时刻，
// 有多少个车库包含车的数量>=3，请返回K个查询的答案
// 1 <= M, N, K <= 10^5
// 1 <= T <= 10^9
public class Code05_QueryGreaterThanOrEqualTo3Stores {

	// 暴力方法
	// 为了验证
	public static int[] getAns1(int m, int[][] records, int[] queries) {
		int maxT = 0;
		for (int[] r : records) {
			maxT = Math.max(maxT, Math.max(r[1], r[2]));
		}
		for (int t : queries) {
			maxT = Math.max(maxT, t);
		}
		int[][] stores = new int[m + 1][maxT + 1];
		for (int[] record : records) {
			int s = record[0];
			int l = record[1];
			int r = record[2] - 1;
			for (int i = l; i <= r; i++) {
				stores[s][i]++;
			}
		}
		int k = queries.length;
		int[] ans = new int[k];
		for (int i = 0; i < k; i++) {
			int curAns = 0;
			for (int j = 1; j <= m; j++) {
				if (stores[j][queries[i]] >= 3) {
					curAns++;
				}
			}
			ans[i] = curAns;
		}
		return ans;
	}

	//思路：1、先对时间进行离散化
	// 2、把属于每个车库的信息分堆存放，然后某几个时间点对于一号车库的描述，[17,23]>=3，表示一号车库的存车数量大于等于3；[56,72]>=3,[84,109]>=3;
	// 3、线段树的index是时间段，然后把上面的信息往里加，加什么大于等于3的车库数量！！！！把线段树上[17,23][56,72][84,109]范围上
	//  更新+1
	// 正式方法
	// O((N + K)*log(N + K))
	public static int[] getAns2(int m, int[][] records, int[] queries) {
		int n = records.length;//10^5,记录数量
		int k = queries.length;//查询数量
		// n*2 + k
		int tn = (n << 1) + k;
		int[] times = new int[tn + 1];
		int ti = 1;
		for (int[] record : records) {
			times[ti++] = record[1];
			times[ti++] = record[2] - 1;
		}
		for (int query : queries) {
			times[ti++] = query;
		}
		Arrays.sort(times);
		for (int[] record : records) {
			record[1] = rank(times, record[1]);
			record[2] = rank(times, record[2] - 1);
		}
		for (int i = 0; i < k; i++) {
			queries[i] = rank(times, queries[i]);
		}
		// 思路：1、所有记录，根据车库编号排序，相同车库的记录就在一起了
		// record  车库   入库时间    出库时间-1
		//         0      1          2（2位置在，3位置就没了）
		Arrays.sort(records, (a, b) -> a[0] - b[0]);//根据车库记录排序
		SegmentTree st = new SegmentTree(tn);
		for (int l = 0; l < n;) { //把同一号的车库同时处理
			int r = l;
			while (r < n && records[l][0] == records[r][0]) { //如果r车库号和l车库好一样
				r++;
			}
			// 思路：核心代码同一个车库的记录，records[l.....r-1] l......  l.......
			countRange(records, l, r - 1, st); //同一个车库的记录加入到线段树中
			l = r;//l跳到下一个车库号
		}
		int[] ans = new int[k];
		for (int i = 0; i < k; i++) {
			ans[i] = st.query(queries[i]); //
		}
		return ans;
	}

	// records[l.....r]，同一车号的记录，统计在l到r上哪些时间段这个车库存车数量是大于等于3的
	// 上面的记录都属于当前的车库
	// 找到哪些时间段，车的数量是 >= 3的
	// 改写线段树!
	//思路：提到了扫面线算法，把进车和出车分成两个事件，然后对事件根据事件进行排序
	// [3,6][2,9][4,13]
	// 2 3 4 6 9 13 ,从4开始是3辆车了，4到6是大于等于3的，改写线段树，没有大于三的就往下撸整理完的事件数组
	// + + + - - -
	// 2 3 4 5 8 9 13 14，4到14时间段大于等于3，把4-14改写线段树+1
	// + + + + - + -  -
	// 剩下的就是不能把时间加重复，
	public static void countRange(int[][] records, int l, int r, SegmentTree st) {
		int n = r - l + 1;
		int[][] help = new int[n << 1][2];
		int size = 0;
		for (int i = l; i <= r; i++) {
			if (records[i][1] <= records[i][2]) {
				help[size][0] = records[i][1];
				help[size++][1] = 1;
				help[size][0] = records[i][2];
				help[size++][1] = -1;
			}
		}
		//根据时间点排序
		Arrays.sort(help, 0, size, (a, b) -> a[0] != b[0] ? (a[0] - b[0]) : (b[1] - a[1]));
		int count = 0;
		int start = -1; //注意这里还没有开始，一旦不等于-1就开始了，把start恢复成-1表示现在没有大于等于的情况
		//思路：这道题的核心部分，更新线段树，并且这些时间段不能添加重复
		for (int i = 0; i < size; i++) {
			int point = help[i][0];
			int status = help[i][1];
			if (status == 1) { //加车
				if (++count >= 3) { //等到了
					start = start == -1 ? point : start; //start等于-1，就在point点开始统计，如果start！=-1，维持最早的时间点下去就行了
				}
			} else { //减车
				if (start != -1 && start <= point) { //只有在减车的时候会发生更新线段树的行为，start开始位从这开始大于等于3，
					st.add(start, point); //
				}
				if (--count >= 3) {//如果减减完还大于等于3，以point+1作为开头，这样避免重复添加满足条件的时间段
					start = point + 1;
				} else {
					start = -1;
				}
			}
		}
	}

	// 所有的时间点，在sorted数组中排好序了
	// 给我一个其中的时间点，v
	// 返回，离散化之后的编号！
	public static int rank(int[] sorted, int v) {
		int l = 1;
		int r = sorted.length;
		int m = 0;
		int ans = 0;
		while (l <= r) {
			m = (l + r) / 2;
			if (sorted[m] >= v) {
				ans = m;
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return ans;
	}

	// 线段树
	// 维持任何时刻存车数量>=3的车库有几个
	// 支持范围时刻的增加
	// 支持单点时刻的查询
	public static class SegmentTree {
		private int tn;
		private int[] sum;
		private int[] lazy;

		public SegmentTree(int n) {
			tn = n;
			sum = new int[(tn + 1) << 2];
			lazy = new int[(tn + 1) << 2];
		}

		private void pushUp(int rt) {
			sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
		}

		private void pushDown(int rt, int ln, int rn) {
			if (lazy[rt] != 0) {
				lazy[rt << 1] += lazy[rt];
				sum[rt << 1] += lazy[rt] * ln;
				lazy[rt << 1 | 1] += lazy[rt];
				sum[rt << 1 | 1] += lazy[rt] * rn;
				lazy[rt] = 0;
			}
		}

		// l...r范围上每个时刻对应的数值+1
		public void add(int l, int r) {
			add(l, r, 1, tn, 1);
		}

		private void add(int L, int R, int l, int r, int rt) {
			if (L <= l && r <= R) {
				sum[rt] += r - l + 1;
				lazy[rt] += 1;
				return;
			}
			int mid = (l + r) >> 1;
			pushDown(rt, mid - l + 1, r - mid);
			if (L <= mid) {
				add(L, R, l, mid, rt << 1);
			}
			if (R > mid) {
				add(L, R, mid + 1, r, rt << 1 | 1);
			}
			pushUp(rt);
		}

		// 查询单点时刻存车数量>=3的车库有几个
		public int query(int index) {
			return query(index, 1, tn, 1);
		}

		private int query(int index, int l, int r, int rt) {
			if (l == r) {
				return sum[rt];
			}
			int m = (l + r) >> 1;
			pushDown(rt, m - l + 1, r - m);
			int ans = 0;
			if (index <= m) {
				ans = query(index, l, m, rt << 1);
			} else {
				ans = query(index, m + 1, r, rt << 1 | 1);
			}
			return ans;
		}

	}

	// 为了测试
	public static int[][] randomRecords(int n, int m, int t) {
		int[][] records = new int[n][3];
		for (int i = 0; i < n; i++) {
			records[i][0] = (int) (Math.random() * m) + 1;
			int a = (int) (Math.random() * t) + 1;
			int b = (int) (Math.random() * t) + 1;
			records[i][1] = Math.min(a, b);
			records[i][2] = Math.max(a, b);
		}
		return records;
	}

	// 为了测试
	public static int[] randomQueries(int k, int t) {
		int[] queries = new int[k];
		for (int i = 0; i < k; i++) {
			queries[i] = (int) (Math.random() * t) + 1;
		}
		return queries;
	}

	// 为了测试
	public static boolean same(int[] ans1, int[] ans2) {
		for (int i = 0; i < ans1.length; i++) {
			if (ans1[i] != ans2[i]) {
				return false;
			}
		}
		return true;
	}

	// 为了测试
	public static void main(String[] args) {
		int M = 20;
		int N = 300;
		int K = 500;
		int T = 5000;
		int testTimes = 5000;
		System.out.println("功能测试开始");
		for (int i = 0; i < testTimes; i++) {
			int m = (int) (Math.random() * M) + 1;
			int n = (int) (Math.random() * N) + 1;
			int k = (int) (Math.random() * K) + 1;
			int t = (int) (Math.random() * T) + 1;
			int[][] records = randomRecords(n, m, t);
			int[] queries = randomQueries(k, t);
			int[] ans1 = getAns1(m, records, queries);
			int[] ans2 = getAns2(m, records, queries);
			if (!same(ans1, ans2)) {
				System.out.println("出错了!");
				break;
			}
		}
		System.out.println("功能测试结束");

		System.out.println("性能测试开始");
		int m = 100000;
		int n = 100000;
		int k = 100000;
		int t = 1000000000;
		int[][] records = randomRecords(n, m, t);
		int[] queries = randomQueries(k, t);
		System.out.println("车库规模 : " + m);
		System.out.println("记录规模 : " + n);
		System.out.println("查询条数 : " + k);
		System.out.println("时间范围 : " + t);
		long start = System.currentTimeMillis();
		getAns2(m, records, queries);
		long end = System.currentTimeMillis();
		System.out.println("运行时间 : " + (end - start) + " 毫秒");
		System.out.println("性能测试结束");
	}
}
