package com.MCAAlgorithm.weeklyfactory.class_2022_08_3_week;

// 这里有 n 个航班，它们分别从 1 到 n 进行编号。
// 有一份航班预订表 bookings ，
// 表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi]
// 意味着在从 firsti 到 lasti 
//（包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
// 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
// 测试链接 : https://leetcode.cn/problems/corporate-flight-bookings/
public class Code04_CorporateFlightBookings {

	//使用差分技巧，这个问题完全可以用线段树做，但是这个比线段树要轻量
	// 【0 0 0 0 0】在【1-4】上加3，在差分数组上第一位+3，在【5】-3，【3 0 0 0 -3】，前缀和就是最终结果
	// 要求在【2-3】加+3，差分数组在【2】+3， 在【4】-3，【3 2 0 -2 -3】，统一撸一遍前缀和，就是最后结果
	public static int[] corpFlightBookings(int[][] bookings, int n) {
		// 1 2 3 4 n
		// 0 1 2 3 .. n n+1
		int[] cnt = new int[n + 2];//差分数组，根据题意0位置不用，n+1位置防止越界
		for (int[] book : bookings) {
			// start book[0]
			// end   book[1]
			// 票    book[2]
			cnt[book[0]] += book[2];
			cnt[book[1] + 1] -= book[2];
		}
		for (int i = 1; i < cnt.length; i++) {
			cnt[i] += cnt[i - 1];
		}
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = cnt[i + 1];
		}
		return ans;
	}

}
