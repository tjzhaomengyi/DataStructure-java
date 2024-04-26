package com.MCAAlgorithm.weeklyfactory.class_2023_03_5_week;

// 来自腾讯音乐，不错的一道题，但是竟然放在了第一题，还是非常贱，但是23两题非常简单，从问题开始，到中间过程的转化
// 给定一棵树，一共有n个点
// 每个点上没有值，请把1~n这些数字，不重复的分配到二叉树上
// 做到 : 奇数层节点的值总和 与 偶数层节点的值总和 相差不超过1
// 返回奇数层节点分配值的一个方案
// 2 <= n <= 10^5 
// 所以课上会讲怎么转化，然后如下的代码实现
public class Code02_OddLevelEvenLevelSumClosed {

	//补充  ：k需要奇数层和偶数层的个数，自己补代码

	// 思路：这道题把一个动态规划的场景放入的树的结构中，但是10^5会卡住。
	//  奇数层 - 偶数层 <= 1, 如果奇数层+偶数层=偶数，这个差是0；如果和是奇数，那么这个差是1
	//  转化为问题：假设有10个数，1到10，选4个数，让最终这4个数的和等于30。
	// 1 ~ n 奇数节点的个数是k个
	// 例子：1 2 3 4 5 6 7 8 。。。。 17 18 19 20，从1加到8等于36
	//      a b c d e f g h
	//      a b c     d             e  f  g  h
	// 选择 8个数凑sum=86，差86-36=50，去凑这些差的，把8推到20，差减少到50-12=38，把7推到19，差减少到38-12=26，把6推到18，差减少到26-12=14
	// 把5推到17，差减少到14-12=2，最后,把4推到6 = 0，凑齐；
	// 先找到合适的last，泛化公式 = （sum-k*(k-1)/2) / (last - k)   得到有几个数跳到last及其之前的。（sum-n*(n-1)/2) % (last - n)表示剩下的最后一个跳到哪里
	// 返回奇数节点的值有哪些
	public static int[] team(int n, int k) {
		// 1 ~ n ,  sum = 10   k个奇数 一定要达到 5
		// 1 ~ n ,  sum = 15   k个奇数  有两种选择，组成7 或 8
		int sum = (n + 1) * n / 2;
		int p1 = sum / 2; //第一种选择
		int p2 = (sum + 1) / 2; //第二种选择
		int[] ans = generate(p1, n, k);
		if (ans == null && (sum & 1) == 1) { //如果是奇数，还能生成一遍答案
			ans = generate(p2, n, k);
		}
		return ans != null ? ans : new int[] { -1 };
	}

	// 一共 1 ~ n 这些数字
	// 其中选k个数字
	// 一定要让k个数字的累加和是wantSum
	// 返回，哪k个数字，只要返回一种方法就可以
	public static int[] generate(int wantSum, int n, int k) {
		// k个数字，和最小的情况，1 2 3 ... k
		int sumMinK = (k + 1) * k / 2;//1到k的和
		// 每个数提升的幅度
		int range = n - k;
		//先来个特例排除，没有解的情况，如果要的和小于这k个数的初始位置和，不行；如果把k个数都迁移到最后还凑不上wantSum也不行
		if (wantSum < sumMinK || wantSum > sumMinK + k * range) {
			return null;
		}
		int add = wantSum - sumMinK; //add
		int rightSize = add / range;//1到k后面那几个去右边
		int midIndex = (k - rightSize) + (add % range);//中间挪动的
		int leftSize = k - rightSize - (add % range == 0 ? 0 : 1);//左边到哪里
		int[] ans = new int[k];
		//填写答案，ans每个位置填写哪几个下标，填充
		for (int i = 0; i < leftSize; i++) {
			ans[i] = i + 1;
		}
		if (add % range != 0) {
			ans[leftSize] = midIndex;
		}
		for (int i = k - 1, j = 0; j < rightSize; i--, j++) {
			ans[i] = n - j;
		}
		return ans;
	}

	public static void main(String[] args) {
		// n是最大值，1~n这些数字都有
		int n = 100;
		// k是个数
		int k = 33;
		// 1~n这些数字，选k个，能不能求和逼近一半
		// 返回方案
		int[] ans = team(n, k);
		System.out.println("总和 : " + (n + 1) * n / 2);
		System.out.println("长度 : " + ans.length);
		int sum = 0;
		System.out.print("数字 : ");
		for (int num : ans) {
			System.out.print(num + " ");
			sum += num;
		}
		System.out.println();
		System.out.println("求和 : " + sum);
		System.out.println("剩余 : " + ((n + 1) * n / 2 - sum));
	}

}
