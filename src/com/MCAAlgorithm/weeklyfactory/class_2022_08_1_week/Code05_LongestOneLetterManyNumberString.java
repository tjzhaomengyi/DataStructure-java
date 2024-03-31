package com.MCAAlgorithm.weeklyfactory.class_2022_08_1_week;

// 给定一个只由小写字母和数字字符组成的字符串str
// 要求子串必须只含有一个小写字母，数字字符数量随意
// 求这样的子串最大长度是多少
public class Code05_LongestOneLetterManyNumberString {

	// 一个绝对正确的暴力方法
	public static int right(String s) {
		char[] str = s.toCharArray();
		int ans = 0;
		for (int i = 0; i < str.length; i++) {
			for (int j = i; j < str.length; j++) {
				if (check(str, i, j)) {
					ans = Math.max(ans, j - i + 1);
				}
			}
		}
		return ans;
	}

	public static boolean check(char[] str, int l, int r) {
		int letterNumber = 0;
		for (int i = l; i <= r; i++) {
			if (str[i] >= 'a' && str[i] <= 'z') {
				letterNumber++;
			}
		}
		return letterNumber == 1;
	}

	// 用窗口
	// 时间复杂度O(N)
	public static int zuo(String s) {
		char[] str = s.toCharArray();
		int n = str.length;
		// 窗口内有几个小写字母了
		int letters = 0;
		// 窗口的右边界，老生常谈的右边界设定
		// [left, right)
		int right = 0;
		int ans = 0;
		// for枚举了每一个窗口的开始位置，0... 1...... 2.....
		for (int left = 0; left < n; left++) {
			while (right < n) { // right不能越界，一旦越界不用再往右了
				if (letters == 1 && str[right] >= 'a' && str[right] <= 'z') { //窗口里面已经有英文小写字母了，现在又来一个，跳过
					break;
				}
				// letters == 0
				if (str[right] >= 'a' && str[right] <= 'z') { //当前位置是小写
					letters++;
				}
				right++; //right往外扩展
			}
			// [left.....right)窗口区间
			// [left.....right-1]
			if (letters == 1) {
				ans = Math.max(ans, right - left);
			}
			//右侧完事了，左侧收，中等面试，课里面弟弟
			if (str[left] >= 'a' && str[left] <= 'z') { //左边缩小，缩窗口
				letters--;
			}
		}
		return ans;
	}

	// 为了测试
	public static char[] chars = { 
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'v', 'w', 'x', 'y', 'z' };

	// 为了测试
	public static String randomString(int n) {
		char[] str = new char[n];
		for (int i = 0; i < n; i++) {
			str[i] = chars[(int) (Math.random() * chars.length)];
		}
		return String.valueOf(str);
	}

	// 为了测试
	public static void main(String[] args) {
		int N = 100;
		int testTimes = 10000;
		System.out.println("测试开始");
		for (int i = 0; i < testTimes; i++) {
			int n = (int) (Math.random() * N) + 1;
			String str = randomString(n);
			int ans1 = right(str);
			int ans2 = zuo(str);
			if (ans1 != ans2) {
				System.out.println(str);
				System.out.println(ans1);
				System.out.println(ans2);
				break;
			}
		}
		System.out.println("测试结束");
	}

}
