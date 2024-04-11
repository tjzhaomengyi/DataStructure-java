package com.MCAAlgorithm.weeklyfactory.class_2022_09_3_week;

// 来自字节
// 给定一个只由小写字母组成的字符串str，长度为N
// 给定一个只由0、1组成的数组arr，长度为N
// arr[i] == 0表示str中i位置的字符不许修改
// arr[i] == 1表示str中i位置的字符允许修改
// 给定一个正数m，表示在任意允许修改的位置
// 可以把该位置的字符变成a~z中的任何一个
// 可以修改m次
// 返回在最多修改m次的情况下，全是一种字符的最长子串是多长
// 1 <= N, M <= 10^5
// 所有字符都是小写
public class Code05_MaxLengthSameCharMChanges {

	// 暴力方法
	// 为了测试
	public static int maxLen1(String str, int[] arr, int m) {
		char[] s = str.toCharArray();
		int n = s.length;
		int ans = 0;
		for (char c = 'a'; c <= 'z'; c++) {
			for (int i = 0; i < n; i++) {
				for (int j = n - 1; j >= i; j--) {
					if (ok(s, i, j, c, arr, m)) {
						ans = Math.max(ans, j - i + 1);
						break;
					}
				}
			}
		}
		return ans;
	}

	// 为了测试
	public static boolean ok(char[] s, int l, int r, char c, int[] arr, int m) {
		for (int i = l; i <= r; i++) {
			if (s[i] == c) {
				continue;
			}
			if (arr[i] == 0 || m == 0) {
				return false;
			}
			m--;
		}
		return true;
	}

	//窗口问题
	// a b a a c c a
	// 1 0 0 0 1 1 1 结果返回5，对应位置为0的不可以修改，为1对应的可以改，改两个c变成a
	// 只有26个字母，枚举都变成26个字母每个字母的情况，26次
	// 例子：cbaabafc
	//      11010111，这个例子m=3，不会用完
	// 以a为例，从每个位置出发，看看能不能变a，然后根据m，不断调整改动后的窗口大小
	// 第一次窗口L=0，R=5，然后用一个欠债表滑动 m=1
	// 第二次窗口移动L左侧缩小，开始往外吐，还给m一次，这样以每个位置开头看有多长
	// 例子 afeaakaat
	//     011001111 ，这个例子m=3，这个m在b的时候耗尽了，这个时候停止！
	// 正式方法
	public static int maxLen2(String str, int[] arr, int m) {
		char[] s = str.toCharArray();
		int n = s.length;
		int ans = 0;
		for (char aim = 'a'; aim <= 'z'; aim++) {//26次
			// 右边界
			// [l..r)左闭右开
			int r = 0;
			// 用了几次修改了
			// change == m 用完的时候
			int change = 0; //修改了几次
			for (int l = 0; l < n; l++) {
				// l......r -> l来到了，r要往右扩充
				while (r < n) {
					if (s[r] == aim) { //如果是aim字符继续右扩
						r++;
						continue;
					}
					// s[r] != aim
					if (arr[r] == 0 || change == m) { //必须要停了，没有m修改次数了，arr【r】表示不能修改了
						break;
					}
					// s[r] != aim && arr[r] == 1 && change < m
					change++; //
					r++;
				}
				// l....r-1 r
				// X l+1
				ans = Math.max(ans, r - l); //左闭右开长度
				if (s[l] != aim && arr[l] == 1) { //l往右动，吐出字符，【l】不是aim 并且是可以修改的
					change--;
				}
				// r r
				// l l
				// X
				r = Math.max(r, l + 1); //体系学习班数组三连这么写过，L已经超过了R，让r往右移动！
			}
		}
		return ans;
	}

	// 为了测试
	public static String randomString(int n, int r) {
		char[] ans = new char[n];
		for (int i = 0; i < n; i++) {
			ans[i] = (char) ((int) (Math.random() * r) + 'a');
		}
		return String.valueOf(ans);
	}

	// 为了测试
	public static int[] randomArray(int n) {
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = (int) (Math.random() * 2);
		}
		return ans;
	}

	// 为了测试
	public static void main(String[] args) {
		int N = 100;
		int R = 5;
		int testTimes = 5000;
		System.out.println("测试开始");
		for (int i = 0; i < testTimes; i++) {
			int n = (int) (Math.random() * N) + 1;
			int m = (int) (Math.random() * n) + 1;
			String str = randomString(n, R);
			int[] arr = randomArray(n);
			int ans1 = maxLen1(str, arr, m);
			int ans2 = maxLen2(str, arr, m);
			if (ans1 != ans2) {
				System.out.println("出错了!");
				break;
			}
		}
		System.out.println("测试结束");
	}

}
