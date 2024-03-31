package com.MCAAlgorithm.weeklyfactory.class_2022_06_4_week;

// 有n个动物重量分别是a1、a2、a3.....an，【挺好的一道二分题】
// 这群动物一起玩叠罗汉游戏，
// 规定从左往右选择动物，每只动物左边动物的总重量不能超过自己的重量
// 返回最多能选多少个动物，求一个高效的算法。
// 比如有7个动物，从左往右重量依次为：1，3，5，7，9，11，21
// 则最多能选5个动物：1，3，5，9，21
// 注意本题给的例子是有序的，但是实际给定的动物数组，可能是无序的，
// 要求从左往右选动物，且不能打乱原始数组
public class Code03_MaxAnimalNumber {

	// 普通动态规划
	// 非常一般的方法
	// 来自背包的思路
	public static int maxAnimals1(int[] arr) {
		int sum = 0;
		for (int num : arr) {
			sum += num;
		}
		int[][] dp = new int[arr.length][sum + 1];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j <= sum; j++) {
				dp[i][j] = -1;
			}
		}
		return process1(arr, 0, 0, dp);
	}

	public static int process1(int[] arr, int index, int pre, int[][] dp) {
		if (index == arr.length) {
			return 0;
		}
		if (dp[index][pre] != -1) {
			return dp[index][pre];
		}
		int p1 = process1(arr, index + 1, pre, dp);
		int p2 = 0;
		if (arr[index] >= pre) {
			p2 = 1 + process1(arr, index + 1, pre + arr[index], dp);
		}
		int ans = Math.max(p1, p2);
		dp[index][pre] = ans;
		return ans;
	}

	// 最优解
	// 如果arr长度为N，时间复杂度O(N*logN)
	//关键是这个ends数组，ends数组表示以i结尾最多可以叠i层时候，最小的重量和是多少
	//aninmals:[1,3,5,7,9,11,21];
	//叠0层的时候当然是0重量，1层的时候最小重量只有1自己；叠两层的时候是1+3=4;叠3层的时候4+5=9;叠4层的时候，7来了，不能用9来冲，只能用2层来冲，4+7=11，
	//这个11不要更新9，因为就要最小重量跳过
	//ends:[0,1,4,9]
	public static int maxAnimals2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		// ends数组，
		int[] ends = new int[arr.length + 1];//i表示当前数组结果扩充的长度
		ends[0] = 0;//0层只有自己
		int endsSize = 1;
		int max = 1;
		//求一下最长递增子序列,ends数组在这个遍历过程中可以边填写边获得最后结果
		for (int i = 0; i < arr.length; i++) {
			int l = 0;
			int r = endsSize - 1;
			int m = 0;
			int find = 0;//要找当前ends左侧可以往上叠的位置
			while (l <= r) {
				m = (l + r) / 2;
				if (ends[m] <= arr[i]) { //当前的数大于endsm,找到了可能的点，把l往上推，可能找到更高的
					find = m;  //find = m;
					l = m + 1;
				} else { //如果当前值比ends[m]小，说明要往m的左侧去找
					r = m - 1;
				}
			}
			if (find == endsSize - 1) { //如果find的下标是当前层级-1
				ends[endsSize] = ends[endsSize - 1] + arr[i];//把当前层级设置为前面层级+当前层级
				endsSize++;
			} else {
				if (ends[find + 1] > ends[find] + arr[i]) { //如果当前找到的层+现在的动物重量形成find+1层的总重量 小于原来find+1的重量，更新！！！让这个重量更小！！！
					ends[find + 1] = ends[find] + arr[i];
				}
			}
			max = Math.max(max, find + 1);
		}
		return max;
	}

	public static int[] randomArray(int n, int v) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = (int) (Math.random() * v) + 1;
		}
		return arr;
	}

	public static void main(String[] args) {
		int N = 100;
		int V = 1000;
		int testTime = 2000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int n = (int) (Math.random() * N) + 1;
			int[] arr = randomArray(n, V);
			int ans1 = maxAnimals1(arr);
			int ans2 = maxAnimals2(arr);
			if (ans1 != ans2) {
				System.out.println("出错了");
				for (int num : arr) {
					System.out.print(num + " ");
				}
				System.out.println();
				System.out.println(ans1);
				System.out.println(ans2);
				break;
			}
		}
		System.out.println("测试结束");
	}

}
