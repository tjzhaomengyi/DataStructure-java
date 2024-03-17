package com.MCAAlgorithm.weeklyfactory.class_2022_04_1_week;

import java.util.Arrays;

// 来自阿里笔试
// 牛牛今年上幼儿园了，老师叫他学习减法
// 老师给了他5个数字，他每次操作可以选择其中的4个数字减1
// 减一之后的数字不能小于0，因为幼儿园的牛牛还没有接触过负数
// 现在牛牛想知道，自己最多可以进行多少次这样的操作
// 扩展问题来自leetcode 2141，掌握了这个题原始问题就非常简单了
// leetcode测试链接 : https://leetcode.com/problems/maximum-running-time-of-n-computers/
// 该问题同房子供电问题，电池[7,7,3,9],房子a\b\c，让abc一直有电,电池切换可以无缝衔接
// a	b	c
// 7    3	9
// 三分钟后，把c的9电池给b，然后7给c
// a	b	c
// 4	6	7
public class Code01_FourNumbersMinusOne {

	//标准技巧，二分答案法，答案肯定在所有电量和这个区间内，f(m,arr,limit),m个房子用arr的电池，必须要撑到limit，二分到尽量大的分钟数
	//例子如果有abcdefg这些房子，有电池[3,3,5,9,10,12,14]，让这些电池撑到10分钟，
	//结论（1）如果使用14的电池只给一个房子供电，不用给别的房子，撑到10min就结束了，只要电池大于等于limit就单独给一个房子供电，剩下的房子组合给小房子使用
	// 问题变为剩下的电池能不能给剩下的房子至少供电10min
	// 从上面的问题引出结论（2）只要剩下电池的累加和大于等于40，一定能让剩下的房子撑到10min【核心结论】
	//证明结论（2）a b c d, [3,3,6,6,7,7]
	//           3 2 5 2
	//			 3 6 5
	//			 4 2
	//前缀和数组加二分
	public static long maxRunTime(int n, int[] arr) {
		Arrays.sort(arr);
		int size = arr.length;
		long[] sums = new long[size];
		sums[0] = arr[0];
		for (int i = 1; i < size; i++) {
			sums[i] = sums[i - 1] + arr[i];
		}//前缀和
		long l = 0;
		long m = 0;
		long r = sums[size - 1] / n;
		long ans = -1;
		while (l <= r) {
			m = (l + r) / 2;
			if (ok(arr, sums, m, n)) {
				ans = m;
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return ans;
	}

	//看看大于Time的有几个，然后看看钱回合能不能二分找到在time时间内跑完
	public static boolean ok(int[] arr, long[] sum, long time, int num) {
		int l = 0;
		int m = 0;
		int r = arr.length - 1;
		int left = arr.length;
		// >= time 最左
		while (l <= r) {
			m = (l + r) / 2;
			if (arr[m] >= time) {
				left = m;
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		num -= arr.length - left;
		long rest = left == 0 ? 0 : sum[left - 1];
		return time * (long) num <= rest;
	}

}
