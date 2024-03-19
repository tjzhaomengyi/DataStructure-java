package com.MCAAlgorithm.weeklyfactory.class_2022_06_2_week;

// 作为国王的统治者，你有一支巫师军队听你指挥。【有点麻烦，推一下思路，单调栈 + 中间子数组的求和】
// 给你一个下标从 0 开始的整数数组 strength ，
// 其中 strength[i] 表示第 i 位巫师的力量值。
// 对于连续的一组巫师（也就是这些巫师的力量值是 strength 的 子数组），
// 总力量 定义为以下两个值的 乘积 ：
// 巫师中 最弱 的能力值 * 组中所有巫师的个人力量值 之和 。（min * sum）
// 请你返回 所有 巫师组的 总 力量之和。由于答案可能很大，请将答案对 109 + 7 取余 后返回。
// 子数组 是一个数组里 非空 连续子序列。
// 测试链接 : https://leetcode.cn/problems/sum-of-total-strength-of-wizards/
public class Code02_SumOfTotalStrengthOfWizards {

	//[5,2,6,3]=>5|5,2|5,3,6|5,2,5,3|6|6,3|2,6|2,3......所有子数组的累加和
	//子数组必须以5\2\6\4做最小值有哪些子数组,
	//谁最小最后要乘以谁，所以最后问题边成了计算各个子数组的累加和是多少
	//如果有相同的数，我们卡主每个位置那个相同的数
	//2 大 5 大 5 大 5 大 5 大2 ,第一个大到5停止，第一个5后面的不考虑。卡到第二个5，左侧全算，第二个5右侧不算，后面的5一次类推，右侧只要遇到小于等于5就算
	//使用单调栈左边小于最近的卡主，右边小于等于的卡主，在这个范围内蹂出子数组，并计算
	//计算子数组的和，使用前缀和 + 前缀和的前缀和数组
	public static final long mod = 1000000007;

	public static int totalStrength(int[] arr) {
		int n = arr.length;
		long preSum = arr[0];
		long[] sumSum = new long[n];
		sumSum[0] = arr[0];
		for (int i = 1; i < n; i++) {
			preSum += arr[i];
			sumSum[i] = (sumSum[i - 1] + preSum) % mod;
		}
		int[] stack = new int[n];//直接改成的栈吧，别麻烦了
		int size = 0;
		long ans = 0;
		for (int i = 0; i < n; i++) {
			while (size > 0 && arr[stack[size - 1]] >= arr[i]) {
				int m = stack[--size];
				int l = size > 0 ? stack[size - 1] : -1;
				// l（<当前值，且最近，到不了）        m(当前数，做为最小值)      i(<=当前数，到不了的！)
				ans += magicSum(arr, sumSum, l, m, i);//求出各个子数组的和，
				ans %= mod;
			}
			stack[size++] = i;
		}
		while (size > 0) {
			int m = stack[--size];
			int l = size > 0 ? stack[size - 1] : -1;
			ans += magicSum(arr, sumSum, l, m, n);
			ans %= mod;
		}
		return (int) ans;
	}

	//l到r子数组区间各个子数组的累加和
	public static long magicSum(int[] arr, long[] sumSum, int l, int m, int r) {
		long left = (long) (m - l) * (sumSum[r - 1] - (m - 1 >= 0 ? sumSum[m - 1] : 0) + mod) % mod;
		long right = (long) (r - m) * ((m - 1 >= 0 ? sumSum[m - 1] : 0) - (l - 1 >= 0 ? sumSum[l - 1] : 0) + mod) % mod;
		return (long) arr[m] * ((left - right + mod) % mod);
	}

}
