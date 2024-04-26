package com.MCAAlgorithm.weeklyfactory.class_2023_01_1_week;

// 一个正整数如果能被 a 或 b 整除，那么它是神奇的。
// 给定三个整数 n , a , b ，返回第 n 个神奇的数字。
// 因为答案可能很大，所以返回答案 对 10^9 + 7 取模 后的值。
// 测试链接 : https://leetcode.cn/problems/nth-magical-number/
public class Code02_NthMagicalNumber {

	//把第一个最大范围，假设a=2，b=3，n=100，最大可能是200，3只会让这个数提前到来
	//例子a=2，b=3，n=60，能被2整除的数30个，能被3整除的数20个，
	// 思路：一个简单的容斥原理来了，一共有既能被2整除的数量 + 既能被3整除的数量 - 既能被2整除又能被3整除的数量
	// a=10，b=15，有既能被2整除的数量 + 既能被3整除的数量 - 能被10和15的最小公倍数（30）整除的数量
	public static int nthMagicalNumber(int n, int a, int b) {
		// 求a和b的最小公倍数
		long lcm = (long) a / gcd(a, b) * b;
		long ans = 0;
		// l = 0
		// r = (long) n * Math.min(a, b)
		for (long l = 0, r = (long) n * Math.min(a, b), m = 0; l <= r;) {
			m = (l + r) / 2;
			if (m / a + m / b - m / lcm >= n) {
				ans = m;
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return (int) (ans % 1000000007);
	}

	public static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

}
