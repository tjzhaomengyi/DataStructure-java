package com.MCAAlgorithm.bigshua.class37;

public class Problem_0221_MaximalSquare {

	//繁衍题 动态规划，这题有点特殊
	//思路: 以i行j列的1做右下角最大的正方形有多大，每一个位置的1做右下角都求。
	//	如果以这个1为右下角，存在一个4*4的正方形，那么它的左上角斜对角线上必然存在一个3 * 3 的，左边的有一个3*3的，上面存在一个3*3的
	// 技巧:这个动态规划就是看左上、上和左三个点的最大推出当前点能够形成正方形的最大值
	public static int maximalSquare(char[][] m) {
		if (m == null || m.length == 0 || m[0].length == 0) {
			return 0;
		}
		int N = m.length;
		int M = m[0].length;
		int[][] dp = new int[N + 1][M + 1];
		int max = 0;
		for (int i = 0; i < N; i++) {
			if (m[i][0] == '1') {
				dp[i][0] = 1;
				max = 1;
			}
		}
		for (int j = 1; j < M; j++) {
			if (m[0][j] == '1') {
				dp[0][j] = 1;
				max = 1;
			}
		}
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				if (m[i][j] == '1') {
					dp[i][j] = Math.min(
							Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		return max * max;
	}

}
