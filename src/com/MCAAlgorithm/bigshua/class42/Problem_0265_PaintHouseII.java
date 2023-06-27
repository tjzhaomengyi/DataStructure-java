package com.MCAAlgorithm.bigshua.class42;

public class Problem_0265_PaintHouseII {

	// costs[i][k] i号房子用k颜色刷的花费
	// 要让0...N-1的房子相邻不同色
	// 返回最小花费

	// 繁衍题 : dp + 线段树，这道题想法比较奇葩
	//技巧:如果使用dp表示，dp[i][j]表示到i相邻房子不同色，并且i的颜色是j，最少代价，返回最后一行的最小值。
	// 在填表的时候，找上一行中除了自己值的最小值，这里要有枚举行为。左边部分求最小值和右边部分求最小值，使用线段树
	//技巧 进阶: 每步只需要最优是多少，使用什么颜色，次优是多少，使用什么颜色
	// 技巧 进阶:思路: 使用最优代价和次优代价信息往下递推，如果只传最优信息是传不动的可能出来不是最好的结果，这种实现省略掉枚举行为
	public static int minCostII(int[][] costs) {
		int N = costs.length;
		if (N == 0) {
			return 0;
		}
		int K = costs[0].length;
		// 之前取得的最小代价、取得最小代价时的颜色
		int preMin1 = 0;
		int preEnd1 = -1;
		// 之前取得的次小代价、取得次小代价时的颜色
		int preMin2 = 0;
		int preEnd2 = -1;
		for (int i = 0; i < N; i++) { // i房子
			int curMin1 = Integer.MAX_VALUE;
			int curEnd1 = -1;
			int curMin2 = Integer.MAX_VALUE;
			int curEnd2 = -1;
			for (int j = 0; j < K; j++) { // j颜色！
				if (j != preEnd1) { //和最优颜色不一样，
					if (preMin1 + costs[i][j] < curMin1) {
						curMin2 = curMin1;//次优等于现在的最优
						curEnd2 = curEnd1;
						curMin1 = preMin1 + costs[i][j];//当前最优更新
						curEnd1 = j;
					} else if (preMin1 + costs[i][j] < curMin2) {
						curMin2 = preMin1 + costs[i][j]; //干过了次优，只更新次优
						curEnd2 = j;
					}
				} else if (j != preEnd2) { //和次优不同
					if (preMin2 + costs[i][j] < curMin1) {
						curMin2 = curMin1; //把最优干掉了，次优等于当前最优
						curEnd2 = curEnd1;
						curMin1 = preMin2 + costs[i][j]; //更新最优
						curEnd1 = j;
					} else if (preMin2 + costs[i][j] < curMin2) {
						curMin2 = preMin2 + costs[i][j];
						curEnd2 = j;
					}
				}
			}
			preMin1 = curMin1;
			preEnd1 = curEnd1;
			preMin2 = curMin2;
			preEnd2 = curEnd2;
		}
		return preMin1;
	}

}
