package com.MCAAlgorithm.weeklyfactory.class_2023_05_5_week;

// 字符串哈希+二分的例题
// 给定长为 n 的源串 s，以及长度为 m 的模式串 p，
// 要求查找源串中有多少子串与模式串匹配
// s' 与 s 匹配，当且仅当 s' 与 s 长度相同，且最多有 k 个位置字符不同
// 其中 1 <= n, m <= 10^6，0 <= k <= 5，思路：一看这个m别犹豫了，二分吧
public class Code05_DiffLessKMatchNumber {

	// s=accbcc，p=abc，k=1，从s上每3个数，看看每部分差几个字符，差的字符超不超过k
	// 思路：二分答案，如果两个字符串长度为100，如果0-50一样，再看50-99长度一样不一样
	//  例子：s=abckftesti， p=abcfftekti，怎么二分发现，前三个位置是一样abc，来到下标3是不一样的k和f，不一样+1，再二分找找下一个不同的位置s和k，不一样+1，每次都二分
	// 暴力方法
	// 为了测试
	public static int howMany1(String str1, String str2, int k) {
		int n = str1.length();
		int m = str2.length();
		if (n < m) {
			return 0;
		}
		char[] s = str1.toCharArray();
		char[] p = str2.toCharArray();
		int ans = 0;
		for (int i = 0; i <= n - m; i++) {
			if (diffLessK1(s, i, p, k, m)) {
				ans++;
			}
		}
		return ans;
	}

	// s[i...]和p，有多少字符不一样
	public static boolean diffLessK1(char[] s, int i, char[] p, int k, int m) {
		int diff = 0;
		for (int j = 0; j < m; j++) {
			if (s[i + j] != p[j]) {
				diff++;
			}
		}
		return diff <= k;
	}

	// 正式方法
	// 数学结论：时间复杂度O(N*K*logM)
	public static int MAXN = 100001;

	public static int base = 1000000007;

	public static long[] pow = new long[MAXN];

	static {
		pow[0] = 1;
		for (int j = 1; j < MAXN; j++) {
			pow[j] = pow[j - 1] * base;
		}
	}

	public static long[] hashs = new long[MAXN];

	public static long[] hashp = new long[MAXN];

	public static void buildHash(char[] s, int n, char[] p, int m) {
		hashs[0] = s[0] - 'a' + 1;
		for (int j = 1; j < n; j++) {
			hashs[j] = hashs[j - 1] * base + s[j] - 'a' + 1;
		}
		hashp[0] = p[0] - 'a' + 1;
		for (int j = 1; j < m; j++) {
			hashp[j] = hashp[j - 1] * base + p[j] - 'a' + 1;
		}
	}

	public static int howMany2(String str1, String str2, int k) {
		int n = str1.length();
		int m = str2.length();
		if (n < m) {
			return 0;
		}
		char[] s = str1.toCharArray();
		char[] p = str2.toCharArray();
		buildHash(s, n, p, m);
		int ans = 0;
		for (int i = 0; i <= n - m; i++) {
			if (diffLessK2(i, i + m - 1, 0, m - 1, k)) {
				ans++;
			}
		}
		return ans;
	}

	// s[l1......r1]
	// p[l2......r2]
	// 保证这两段一定等长!
	// 返回，这两段上字符不一样的位置，是不是 <= k个的！
	// 下面代码的行为也就执行k次
	public static boolean diffLessK2(int l1, int r1, int l2, int r2, int k) {
		int diff = 0; //不一样的个数
		// l1 <= r1 : 目前还剩下一些字符串
		// diff <= k: 不一样的数量没有超!
		while (l1 <= r1 && diff <= k) { //如果剩余字符串，并且diff小于等于目标值
			// 二分 : s[l1.......] p[l2........] 最长的相等长度!
			// s : abcdefgiii....
			// p : abcedfgihh....
			int l = 1;//长度！长度！
			int r = r1 - l1 + 1;//总长度！！！
			int m, len = 0;
			while (l <= r) {
				m = (l + r) / 2;
				// ok(l1, l2, m)
				// s[l1...数m长度(包括l1)] 
				// 是不是等于
				// p[l2...数m长度(包括l2)] 
				if (ok(l1, l2, m)) { //ok含义是从l1出发长度m的子串是不是等于p从l2出发出发m长度字符串是否相等
					len = m;//记录长度答案
					l = m + 1; //找更大的长度
				} else {
					r = m - 1; //二分小的长度
				}
			}
			//s：abcdefg
			//p：abcdtfg
			// 马勒戈壁的加你妈越界了
			if (l1 + len <= r1) {//长度撸完了
				diff++;
			}
			//l1跳过不一样的部分（f，g）到后面部分再比较
			// abcdefh
			// abcdegh
			l1 += len + 1;
			l2 += len + 1;
		}
		return diff <= k;
	}

	public static boolean ok(int l1, int l2, int len) {
		//关于s字符串和p字符串求hash
		return hash(hashs, l1, l1 + len - 1) == hash(hashp, l2, l2 + len - 1);
	}

	public static long hash(long[] hash, int l, int r) {
		long ans = hash[r];
		ans -= l == 0 ? 0 : (hash[l - 1] * pow[r - l + 1]);
		return ans;
	}

	// 为了测试
	// 生成随机子串
	public static String randomString(int len, int range) {
		char[] str = new char[len];
		for (int i = 0; i < len; i++) {
			str[i] = (char) ((int) (Math.random() * range) + 'a');
		}
		return String.valueOf(str);
	}

	// 为了测试
	public static void main(String[] args) {
		int N = 100;
		int M = 50;
		int K = 10;
		// a b c
		// R =4 abcd
		int R = 3;
		int testTimes = 10000;
		System.out.println("测试开始");
		for (int i = 0; i < testTimes; i++) {
			int n = (int) (Math.random() * N) + 1;
			int m = (int) (Math.random() * M) + 1;
			int k = (int) (Math.random() * K);
			String str1 = randomString(n, R);
			String str2 = randomString(m, R);
			int ans1 = howMany1(str1, str2, k);
			int ans2 = howMany2(str1, str2, k);
			if (ans1 != ans2) {
				System.out.println("出错了！");
			}
		}
		System.out.println("测试结束");
	}

}
