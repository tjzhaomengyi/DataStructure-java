package com.MCAAlgorithm.weeklyfactory.class_2022_03_4_week;

// 来自字节
// 一开始在0位置，每一次都可以向左或者向右跳
// 第i次能向左或者向右跳严格的i步
// 请问从0到x位置，至少跳几次可以到达
// 字节考的问题其实就是这个问题
// leetcode测试链接 : https://leetcode.com/problems/reach-a-number/
public class Code04_JumpToTargets {

	//结论 利用差值的奇偶性，因为跳反的话肯定是变化偶数的长度，类似大厂刷题class38——
	// （1）跳反目标差是偶数情况：1+2+3+4+5+6=21,如果第一步跳反19，如果第二步跳反17，如果第三部跳反15
	//如果x到y跳到了10，现在本来想到0，那么久在5步跳反。
	//(2)跳反目标差是奇数情况1+2+3+4=10，目标是7，现在从在10的时候执行跳反是不能到7的，偶数-偶数=偶数,解决办法继续往后跳，直到该位置到7的距离是偶数，就能找到好的跳反点
	public static int reachNumber(long target) {
		if (target == 0) {
			return 0;
		}
        target = Math.abs(target);
		long l = 0;
		long r = target;
		long m = 0;
		long near = 0;
		while (l <= r) {
			m = (l + r) / 2;
			if (sum(m) >= target) {
				near = m;
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		if (sum(near) == target) {
			return (int)near;
		}
		if (((sum(near) - target) & 1) == 1) {
			near++;
		}
		if (((sum(near) - target) & 1) == 1) {
			near++;
		}
		return (int)near;
	}

	public static long sum(long n) {
		return (n * (n + 1)) / 2;
	}

}
