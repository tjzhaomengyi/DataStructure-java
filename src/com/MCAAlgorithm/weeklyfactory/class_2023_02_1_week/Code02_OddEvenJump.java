package com.MCAAlgorithm.weeklyfactory.class_2023_02_1_week;

import java.util.TreeMap;

// 给定一个整数数组 A，你可以从某一起始索引出发，跳跃一定次数
// 在你跳跃的过程中，第 1、3、5... 次跳跃称为奇数跳跃
// 而第 2、4、6... 次跳跃称为偶数跳跃
// 你可以按以下方式从索引 i 向后跳转到索引 j（其中 i < j）：
// 在进行奇数跳跃时（如，第 1，3，5... 次跳跃），你将会跳到索引 j
// 使得 A[i] <= A[j]，A[j] 是可能的最小值。如果存在多个这样的索引 j
// 你只能跳到满足要求的最小索引 j 上。
// 在进行偶数跳跃时（如，第 2，4，6... 次跳跃）
// 你将会跳到索引 j，使得 A[i] >= A[j]，A[j] 是可能的最大值
// 如果存在多个这样的索引 j，你只能跳到满足要求的最小索引 j 上。
//（对于某些索引 i，可能无法进行合乎要求的跳跃。）
// 如果从某一索引开始跳跃一定次数（可能是 0 次或多次）
// 就可以到达数组的末尾（索引 A.length - 1）
// 那么该索引就会被认为是好的起始索引
// 返回好的起始索引的数量
// 测试链接 : https://leetcode.cn/problems/odd-even-jump/
public class Code02_OddEvenJump {

	//思路：这道题要先把题意理解了，偶数步，奇数步，然后这一步往哪走？能走的话跳到哪？能跳到的下一步是根据[arr]上的值还有第偶数步还是第奇数步决定的
	public static int oddEvenJumps(int[] arr) {
		int n = arr.length;
		// 来到了i位置，如果要遵循奇数规则，应该去哪？odd[i]
		int[] odd = new int[n];
		// 来到了i位置，如果要遵循偶数规则，应该去哪？even[i]
		int[] even = new int[n];
		// 有序表，
		// key : 某个值
		// value : key这个值，出现的最左位置
		// >= key 且最小
		// <= key 且最大
		TreeMap<Integer, Integer> orderMap = new TreeMap<>();
		//搞定每个位置偶数次跳跃和奇数次跳跃下一步该去哪这两张表
		for (int i = n - 1; i >= 0; i--) { //从右边开始迭代，这样前面的就可以往后边找
			// i位置，arr[i]，奇数规则，>= arr[i]且最小的！
			Integer to = orderMap.ceilingKey(arr[i]); //大于等于这个位置数的，咱们就过去，填上
			odd[i] = to == null ? -1 : orderMap.get(to);
			// i位置，arr[i]，偶数规则，<= arr[i]且最大的！
			to = orderMap.floorKey(arr[i]);
			even[i] = to == null ? -1 : orderMap.get(to);
			orderMap.put(arr[i], i);
		}
		// dp[i][0] : 当来到i位置，且在奇数规则下，最终能不能到结尾？
		// dp[i][1] : 当来到i位置，且在偶数规则下，最终能不能到结尾？
		boolean[][] dp = new boolean[n][2];
		dp[n - 1][0] = true;
		dp[n - 1][1] = true;
		int ans = 1; //自己就是良好出发点，所以开始就是1
		for (int i = n - 2; i >= 0; i--) {
			// dp[i][0] : 当来到i位置，且在奇数规则下，最终能不能到结尾
			// odd[i] -> 右走！ -1
			dp[i][0] = odd[i] != -1 && dp[odd[i]][1];//odd[i]能往下走 并且 下一步（是偶数步）要走偶数能不能到最后
			// dp[i][1] : 当来到i位置，且在偶数规则下，最终能不能到结尾
			dp[i][1] = even[i] != -1 && dp[even[i]][0];
			ans += dp[i][0] ? 1 : 0; //i位置在奇数步能够到出发点，就是一个方法，否则没有方法，因为无论从哪个位置i上开始，第一步肯定是奇数步
		}
		return ans;
	}

}
