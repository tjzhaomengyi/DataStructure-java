package com.MCAAlgorithm.weeklyfactory.class_2022_11_1_week;

import java.util.Arrays;

// 来自CISCO
// 给定两个正整数x、y，都是int整型(java里)
// 返回0 ~ x以内，每位数字加起来是y的数字个数
// 比如，x = 20、y = 5，返回2
// 因为0 ~ x以内，每位数字加起来是5的数字有：5、14
// x、y范围是java里正整数的范围
// x <= 2 * 10^9，Integer.MAX_Value最多10位
// y <= 90
public class Code04_ValueNoMoreXDigitsToY {

	// 暴力方法
	// 为了测试
	public static int num1(int x, int y) {
		int ans = 0;
		for (int i = 0; i <= x; i++) {
			if (check1(i, y)) {
				ans++;
			}
		}
		return ans;
	}

	public static boolean check1(int num, int y) {
		int sum = 0;
		while (num != 0) {
			sum += num % 10;
			num /= 10;
		}
		return sum == y;
	}

	// 正式方法
	// 时间复杂度O(整数有几个十进制位的平方 * 9)
	// x=3267293 y=30
	// 1位有几个凑到30，2位数可以凑到30。。。。。
	// （1）打表：看static里面
	// （2）len和offset，把offset和数的最高位先对其
	// （3）
	public static int num2(int x, int y) {
		if (x < 0 || y > 90) { //Integer.MAX_VALUE最多九位
			return 0;
		}
		if (x == 0) {
			return y == 0 ? 1 : 0;
		}
		//具体过程详解
		// x      : 352764
		// len    : 6
		// offset : 100000
		int offset = 1;
		int len = 1;
		// x / offset % 10 = x在offset位的数
		while (offset <= x / 10) {
			offset *= 10; //offset 和这个数的长度对其
			len++;
		}
		int[][] dp = new int[len + 1][y + 1];
		for (int i = 0; i <= len; i++) {
			Arrays.fill(dp[i], -1);
		}
		return count(x, offset, len, y, dp);
	}

	// x, 0~x 范围，固定的！
	//         abcdef
	// offset, 100000, 当前关心的是哪位数字
	//          10000
	//           1000
	// len, 还剩几位数字没处理完！
	// rest, 每位数字的和加起来，要求是多少
	// 0~x ，在之前决定的数字不管，还剩下的数字能变出多少达标的！
	// 返回0到x之前决定不管，剩下的数字能变出多少达标的（就是rest，即每位数字的和加起来要起是多少）
	public static int count(int x, int offset, int len, int rest, int[][] dp) {
		if (len == 0) { //处理完了
			return rest == 0 ? 1 : 0; //如果rest=0表示前面找的已经达标了，如果不等于0说明有剩余，之前凑出来的并没有找到合理的方法
		}
		if (dp[len][rest] != -1) {
			return dp[len][rest];
		}
		int ans = 0;
		// x = 3457
		//     3
		// cur = 3
		//       0 ? len-1  rest - 0
		//       1 ? len-1  rest - 1
		//       2 ? len-1  rest - 2
		int cur = (x / offset) % 10;
		//比当前cur小的直接从表里拿
		for (int i = 0; i < cur && i <= rest; i++) {
			ans += form[len - 1][rest - i];
		}
		// cur = 3    2
		//       3    5
		if (cur <= rest) { // cur!，只有cur小于等于rest才能拿
			ans += count(x, offset / 10, len - 1, rest - cur, dp); //
		}
		dp[len][rest] = ans;
		return ans;
	}

	//（1） 打表了
	// from[i][j]:一共有i位，所有的数字中，每一位加起来是j的数，有几个，91表示0到90，11表示从0个到10个//f（i，j）
	// from[i][j] : 一共有i位，所有的数字中，每一位加起来是j的数，有几个
	public static int[][] form = new int[11][91];
	static {//生成表
		form[0][0] = 1;
		for (int len = 1; len <= 10; len++) {
			for (int sum = 0; sum <= len * 9; sum++) { //数
				for (int cur = 0; cur <= 9 && cur <= sum; cur++) { //每一位数字枚举0到9
					form[len][sum] += form[len - 1][sum - cur];
				}
			}
		}
	}

	// 为了测试
	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		
		int x = 88739128;
		int y = 37;
		System.out.println(num1(x, y));
		System.out.println(num2(x, y));
	}

}
