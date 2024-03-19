package com.MCAAlgorithm.weeklyfactory.class_2022_05_3_week;

// 来自京东
// 4.2笔试
// 给定一个数组arr，长度为N，arr中所有的值都在1~K范围上
// 你可以删除数字，目的是让arr的最长递增子序列长度小于K （LIS[从左往右选不能改相对位置，可以不连续]）
// 返回至少删除几个数字能达到目的
// 【注意】找到这个套路递归就写出来了：这道题比较傻逼的地方就是这个1-k范围上长度小于k说明子序列不能连续，一旦连续就要考虑要不要删除当前这个节点，
// 比如例子中1...3，现在碰上4，就要考虑删除或者不删除，如果是2或者5就直接保留因为5这里是个空洞！！！
// N <= 10^4，K <= 10^2
public class Code02_RemoveNumbersNotIncreasingAll {

	// 暴力方法
	// 为了验证
	public static int minRemove1(int[] arr, int k) {
		return process1(arr, 0, new int[arr.length], 0, k);
	}

	public static int process1(int[] arr, int index, int[] path, int size, int k) {
		if (index == arr.length) {
			return lengthOfLIS(path, size) < k ? (arr.length - size) : Integer.MAX_VALUE;
		} else {
			int p1 = process1(arr, index + 1, path, size, k);
			path[size] = arr[index];
			int p2 = process1(arr, index + 1, path, size + 1, k);
			return Math.min(p1, p2);
		}
	}

	public static int lengthOfLIS(int[] arr, int size) {
		if (size == 0) {
			return 0;
		}
		int[] ends = new int[size];
		ends[0] = arr[0];
		int right = 0;
		int l = 0;
		int r = 0;
		int m = 0;
		int max = 1;
		for (int i = 1; i < size; i++) {
			l = 0;
			r = right;
			while (l <= r) {
				m = (l + r) / 2;
				if (arr[i] > ends[m]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			right = Math.max(right, l);
			ends[l] = arr[i];
			max = Math.max(max, l + 1);
		}
		return max;
	}

	//arr[0.index-1]上凑齐了len长度的递增子序列，index和以后得可以做决策，前面不行了，参数k：
	// arr[0...index-1]上，选择了一些数字，之前的决定！
	// len长度了！len = 3 ： 1 2 3
	// arr[index....]是能够决定的，之前的，已经不能再决定了
	// 返回：让最终保留的数字，凑不足k长度的情况下，至少要删几个！
	public static int zuo(int[] arr, int index, int len, int k) {
		if (len == k) { //之前已经凑了k长度的递增子序列，这个是无效解，因为要求小于k
			return Integer.MAX_VALUE;
		}
		//从1...lenk长度，凑的(1...len)还不到(1...k)
		if (index == arr.length) {
			return 0;//已经都选择完了，不需要再选了
		}
		// 没凑到 len < k, 有数字!
		int cur = arr[index];
		// cur有两种情况可能性1：保留
		// （1）如果0到index-1获得的递增子序列是1到3，arr[index]=2或者3,那么就不删除，保留，这样可以省一次删除操作，因为前面已经有了1...3了这个2没有影响
		// （2）如果0到index-1获得的递增子序列是1到3, arr[index]=5,也不删除，保留，
		// 可能性2：删除
		// 1...3 3 ，这里想不出来
		if (len >= cur || len + 1 < cur) { //凑足的数字大于等于当前的数字
			return zuo(arr, index + 1, len, k);//保留
		}

		// 如果0到index-1获得的递增子序列是1到3，arr[index]=4
		// len + 1  == cur
		// 可能性1：保留
		int p1 = zuo(arr, index + 1, len + 1, k);
		// 可能性2：删除
		int p2 = Integer.MAX_VALUE;
		int next2 = zuo(arr, index + 1, len, k);
		if(next2 != Integer.MAX_VALUE) {//后续是有效解
			p2 = 1 + next2; //删除的数字是当前删除一次和后续删除次数
		}
		return Math.min(p1, p2);
	}

	// 正式方法
	// 时间复杂度O(N*K)
	public static int minRemove2(int[] arr, int k) {
		int n = arr.length;
		int[][] dp = new int[n][k];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < k; j++) {
				dp[i][j] = -1;
			}
		}
		return process2(arr, k, 0, 0, dp);
	}

	public static int process2(int[] arr, int k, int index, int range, int[][] dp) {
		if (range == k) {
			return Integer.MAX_VALUE;
		}
		if (index == arr.length) {
			return 0;
		}
		if (dp[index][range] != -1) {
			return dp[index][range];
		}
		int ans = 0;
		if (arr[index] == range + 1) {
			int p1 = process2(arr, k, index + 1, range, dp);
			p1 += p1 != Integer.MAX_VALUE ? 1 : 0;
			int p2 = process2(arr, k, index + 1, range + 1, dp);
			ans = Math.min(p1, p2);
		} else {
			ans = process2(arr, k, index + 1, range, dp);
		}
		dp[index][range] = ans;
		return ans;
	}

	// 为了验证
	public static int[] randomArray(int len, int k) {
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * k) + 1;
		}
		return arr;
	}

	// 为了验证
	public static void main(String[] args) {
		int N = 15;
		int K = 6;
		int testTime = 10000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * N) + 1;
			int k = (int) (Math.random() * K) + 1;
			int[] arr = randomArray(len, k);
			int ans1 = minRemove1(arr, k);
			int ans2 = minRemove2(arr, k);
			if (ans1 != ans2) {
				System.out.println("出错了！");
				for (int num : arr) {
					System.out.print(num + " ");
				}
				System.out.println();
				System.out.println("k : " + k);
				System.out.println("ans1 : " + ans1);
				System.out.println("ans2 : " + ans2);
				break;
			}
		}
		System.out.println("测试结束");
	}

}
