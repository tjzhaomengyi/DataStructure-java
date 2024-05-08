package com.MCAAlgorithm.weeklyfactory.class_2023_06_2_week;

// 给你一个长度为 n 下标从 0 开始的整数数组 nums
// 它包含 1 到 n 的所有数字，请你返回上升四元组的数目。
// 如果一个四元组 (i, j, k, l) 满足以下条件，我们称它是上升的：
// 0 <= i < j < k < l < n 且
// nums[i] < nums[k] < nums[j] < nums[l] 。
// 测试链接 : https://leetcode.cn/problems/count-increasing-quadruplets/
public class Code01_CountIncreasingQuadruplets {

	// 思路：到i位置前面有多少个"小大中"的子序列，再看j位置满足小大中。然后找这个j位置小大中这个信息
	//  来到i位置的时候，要0到i-1范围的信息更新对，
	//  例子： 1 5 9 3 2 6 4
	//  1做中点-0个，5-两个，9-4个，3-1个，2-0个，6-0个，4位置的信息比4小的只有3-1个四元组合理
	//  再以4做结尾更新小大中，比4小的不会得到更新，更新那些比4大的数可能会获得三元组信息的更新，5左边的数比4小就新增几个，5-3个；9左边有几个数比4小，
	//  9-5个，6的左边有3个数比4小，更新3个。
	// 非常强的思路和实现
	public static long countQuadruplets1(int[] nums) {
		int n = nums.length;
		long ans = 0;
		// dp[j]含义 :
		//    ............................l
		// 目前假设刚来到l位置，那么在l之前的范围上
		// 位置 : 0....i....j....k....l-1
		// 如果j做中间点，请问有多少三元组满足 : arr[i] < arr[k] < arr[j]
		// 就是 : 小 大(j位置的数) 中
		// 这种三元组的数量，就是dp[j]的含义
		long[] dp = new long[n];
		for (int l = 1; l < n; l++) {
			
			//          5           9
			//          j           l
			// 0 1 2 3 4 5 6 ....l-1
			for (int j = 0; j < l; j++) {
				if (nums[j] < nums[l]) {//只要l位置比以num[j]为中点的值大，就可以凑出新的四元组
					ans += dp[j];
				}
			}
			// dp[0...l-1]上的所有信息，有效的范围 : 0 .... l-1
			// dp[0...l-1]，扩充有效范围: 0........l
			// 思路：更新三元组信息，小大中，比num[l]结尾的三元组
			int cnt = 0;// 目前比[l]数小的数的个数
			for (int j = 0; j < l; j++) {
				if (nums[j] < nums[l]) { //技巧：这个道题就在这里使用了一个巧劲
					cnt++; //思路中的例子以4结尾，小于4的数量增加一个，但是正常的dp值并不改变
				} else { //nums[j] > nums[l]
					dp[j] += cnt;//因为前面已经统计过小于4的个数了，所以这里满足修改dp的条件，加上cnt的计数
				}
			}
		}
		return ans;
	}

	// 非常强的思路和实现，彻底看不懂版，其实就是上面的代码做了逻辑合并
	public static long countQuadruplets2(int[] nums) {
		int n = nums.length;
		long ans = 0;
		long[] dp = new long[n];
		for (int l = 1; l < n; l++) {
			int cnt = 0;
			for (int j = 0; j < l; j++) {
				if (nums[j] < nums[l]) {
					ans += dp[j];
					cnt++;
				} else {
					dp[j] += cnt;
				}
			}
		}
		return ans;
	}

}
