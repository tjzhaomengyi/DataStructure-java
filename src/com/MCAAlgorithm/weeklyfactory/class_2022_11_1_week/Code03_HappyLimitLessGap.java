package com.MCAAlgorithm.weeklyfactory.class_2022_11_1_week;

import java.util.Arrays;

// 来自蚂蚁金服 -- 好题！这题非常好！
// 小红有n个朋友, 她准备开个宴会，邀请一些朋友
// i号朋友的愉悦值为a[i]，财富值为b[i]
// 如果两个朋友同时参加宴会，这两个朋友之间的隔阂是其财富值差值的绝对值
// 宴会的隔阂值，是财富差距最大的两人产生的财富值差值的绝对值
// 宴会的愉悦值，是所有参加宴会朋友的愉悦值总和
// 小红可以邀请任何人，
// 希望宴会的愉悦值不能小于k的情况下， 宴会的隔阂值能最小是多少
// 如果做不到，返回-1
// 1 <= n <= 2 * 10^5
// 1 <= 愉悦值、财富值 <= 10^9
// 1 <= k <= 10^14
public class Code03_HappyLimitLessGap {

	// 暴力方法
	// 为了验证
	public static int lessGap1(int[] a, int[] b, int k) {
		long ans = process(a, b, 0, k, Integer.MAX_VALUE, Integer.MIN_VALUE);
		return ans < Integer.MAX_VALUE ? (int) ans : -1;
	}

	// 暴力方法
	// 为了验证
	public static long process(int[] a, int[] b, int i, int rest, int min, int max) {
		if (rest <= 0) {
			return (long) max - (long) min;
		}
		if (i == a.length) {
			return (long) Integer.MAX_VALUE;
		}
		long p1 = process(a, b, i + 1, rest, min, max);
		long p2 = process(a, b, i + 1, rest - a[i], Math.min(min, b[i]), Math.max(max, b[i]));
		return Math.min(p1, p2);
	}

	// 正式方法
	// 二分答案
	//思路：假设宴会愉悦度不少小于120，，隔阂值可以先确定最大最富-最穷
	// （1）假设隔阂值最大40，能产生的愉悦值最大是a；假设隔阂值最大是50，能产生的愉悦值为b，那么b肯定大于等于a
	// 	如果是隔阂度为0，只能选一样的人来参加，如果是10，那么欢乐值只会变大或者不变，就是可选的人范围变大
	// int happy= f（limit）//limit是隔阂度，limit=25，欢乐度到了120，那么可以往小再二分limit；如果欢乐度不到120，只能在25右侧进行二分
	public static int lessGap2(int[] a, int[] b, long k) {
		int n = a.length;
		// a : 20 30 17
		// b : 5 10 36
		// 0 1 2
		// [ 20, 5] [30, 10] [17, 36]
		// 0 1 2
		int[][] f = new int[n][2]; //封装两个数组
		int min = b[0]; //记录财富值的最大和最小值
		int max = b[0];
		for (int i = 0; i < n; i++) {
			f[i][0] = a[i];
			f[i][1] = b[i];
			min = Math.min(min, b[i]);
			max = Math.max(max, b[i]);
		}
		// 排序和大流程，没关系
		// 是子函数，maxHappy函数，需要用到，排了序
		// 根据财富排序，少 -> 多
		Arrays.sort(f, (x, y) -> x[1] - y[1]);
		// 隔阂值的范围 l ~ r
		int l = 0;
		int r = max - min; //只可能在这个范围上去找快乐值
		int m = 0;
		int ans = -1;
		while (l <= r) {
			// 0.........50
			//       25
			m = (l + r) / 2;
			if (maxHappy(f, m) >= k) { //最大快乐度大于了要求，去左边可能更小的答案
				ans = m;//当前这个隔阂度还行，欢乐了
				r = m - 1;
			} else {
				l = m + 1; //这个隔阂度不能凑成要求的欢乐度，继续找把
			}
		}
		return ans;
	}

	//牛逼！！！所有人在limit隔阂度下找最大的欢乐值，这又来了个滑动窗口
	//【2 6 3 6 10。。。】
	//【1 5 9 12 15 16。。。。】
	//以第0个人最多能话到第2个人，第一个人参加宴会欢乐值11
	//窗口把第0个人吐了，他不参加了，看第二个人6+3+6+10
	//。。窗口就这样不断扩，每个人参加的欢乐值就出来了
	public static long maxHappy(int[][] f, int limit) {
		int n = f.length;
		long sum = 0;
		long ans = 0;
		for (int l = 0, r = 0; l < n; l++) { //遍历每个人
			while (r < n && f[r][1] - f[l][1] <= limit) { //开始滑窗，看r是否符合隔阂度
				sum += f[r++][0];
			}
			ans = Math.max(ans, sum);
			sum -= f[l][0]; //把窗口的吐出去，给下一个位置作准备！！！
			r = Math.max(r, l + 1);//r更新，滑窗的常规操作
		}
		return ans;
	}

	// 正式方法
	// 排序.txt + 窗口
	public static int lessGap3(int[] a, int[] b, long k) {
		int n = a.length;
		int[][] f = new int[n][2];
		for (int i = 0; i < n; i++) {
			f[i][0] = a[i];
			f[i][1] = b[i];
		}
		Arrays.sort(f, (x, y) -> x[1] - y[1]);
		int ans = Integer.MAX_VALUE;
		for (int l = 0, r = 0, happy = 0; l < n; l++) {
			while (r < n && happy < k) {
				happy += f[r++][0];
			}
			if (happy >= k) {
				ans = Math.min(ans, f[r - 1][1] - f[l][1]);
			}
			happy -= f[l][0];
		}
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	// 为了验证
	public static int[] randomArray(int n, int v) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = (int) (Math.random() * v) + 1;
		}
		return arr;
	}

	// 为了验证
	public static void main(String[] args) {
		int N = 15;
		int V = 20;
		int testTime = 5000;
		System.out.println("功能测试开始");
		for (int i = 0; i < testTime; i++) {
			int n = (int) (Math.random() * N) + 1;
			int[] a = randomArray(n, V);
			int[] b = randomArray(n, V);
			int k = (int) (Math.random() * n * V) + 1;
			int ans1 = lessGap1(a, b, k);
			int ans2 = lessGap2(a, b, k);
			int ans3 = lessGap3(a, b, k);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("出错了!");
			}
		}
		System.out.println("功能测试结束");
	}

}
