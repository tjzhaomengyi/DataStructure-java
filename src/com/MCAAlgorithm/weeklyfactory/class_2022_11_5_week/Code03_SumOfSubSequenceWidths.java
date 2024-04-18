package com.MCAAlgorithm.weeklyfactory.class_2022_11_5_week;

import java.util.Arrays;

// 一个序列的 宽度 定义为该序列中最大元素和最小元素的差值。
// 给你一个整数数组 nums ，返回 nums 的所有非空 子序列 的 宽度之和
// 由于答案可能非常大，请返回对 109 + 7 取余 后的结果。
// 子序列 定义为从一个数组里删除一些（或者不删除）元素，
// 但不改变剩下元素的顺序得到的数组
// 例如，[3,6,2,7] 就是数组 [0,3,1,6,2,2,7] 的一个子序列。
// 测试链接 : https://leetcode.cn/problems/sum-of-subsequence-widths/
public class Code03_SumOfSubSequenceWidths {

	//n长的数组，每个位的数要或者不要，可以组成一共2^n,每个序列长度必须大于等于2，【1，2，3】长度有哪些【1，2】【1，3】【2，3】【1，2，3】，和顺序没有关系
	// 思路：一个数组的宽度之和 与 这个数组的顺序无关
	// abcdefg，以a、b、c、d分别结尾的子序列各有多少个 ,以b结尾：（b-a） * 2（尾-头-1），以c结尾：【bc、ac、abc】（c-b）*2^0 + (c-a)*2^1，
	// 以d结尾 【abcd、cd、bcd、ad、bd、acd。。。】（d-c）*2^0 + (d-b)*2^1 + (d-a)*2^2,以此类推，但是这样每个数都要往前这样遍历一遍，这样的时间复杂度O（n^2)
	// 思路：优化：我们要把上面思路的速度加速！
	// 以b结尾只有ab，（b-a） * 2^0 = b * 2^0 - a * 2^0
	// 以c结尾 c*(2^1 + 2^0) - b * 2^0 - a * 2^1
	// 以d结尾 （d-c）* 2^2 +(d - b) * 2^1 + (d-a)*2^2 = d*(2^2+2^1+2^0) - c*2^0 - b*2^1 - a * 2^2
	// 以e结尾，被减部分是 （c*2^0 + b*2^1 + a * 2^2） * 2 + e*2^0
	// 数学结论：于是我们得到了递推公式，减号左部分直接迭代算出来(最后一位数)，被剪部分也可以递推出来。最终就是上面一坨 * 2 + 这一步的数
	public static int sumSubseqWidths(int[] nums) {
		Arrays.sort(nums);
		int mod = 1000000007;
		long ans = 0;
		long A = 0;
		long B = 0;
		long C = 1;
		long D = C;
		// B表示上面传下来的一坨
		for (int i = 1; i < nums.length; i++) {
			A = (D * nums[i]) % mod;
			B = (B * 2 + nums[i - 1]) % mod; //被减的部分
			ans = (ans + A - B + mod) % mod; //ans不用看，这部分是为了和mod求余数用的，只看A-B，A就对应上面公式的左半部分
			C = (C * 2) % mod;//注意：这个递推就是公式左半侧的那个，自己划拉几下就出来了。有点恶心
			D = (D + C) % mod;
		}
		return (int) (ans);
	}

}
