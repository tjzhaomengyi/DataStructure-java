package com.MCAAlgorithm.weeklyfactory.class_2023_05_3_week;

// 来自学员问题
// 假设每一次获得随机数的时候，这个数字大于100的概率是P
// 尝试N次，其中大于100的次数在A次~B次之间的概率是多少?
// 0 < P < 1, P是double类型
// 1 <= A <= B <= N <= 100
public class Code02_More100TimesBetweenAB {

	//概率dp问题
	public static double probability(double P, int N, int A, int B) {
		double[][] dp = new double[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				dp[i][j] = -1;
			}
		}
		double ans = 0;
		//扔出从A次到B次大于100的概率累加起来，N次可以扔，扔出大于100的次数必须是j次
		for (int j = A; j <= B; j++) {
			ans += process(P, 1D - P, N, j, dp);
		}
		return ans;
	}

	// 获得随机数大于100的概率是more
	// 获得随机数小于等于100的概率是less
	// 还有i次需要去扔
	// 扔出大于100的次数必须是j次
	// 返回概率
	public static double process(double more, double less, int i, int j, double[][] dp) {
		if (i < 0 || j < 0 || i < j) {
			return 0D;
		}
		// 如果上面没有中，那么往下跑的条件是： i >= 0 & j >= 0 & i >= j
		if (i == 0 && j == 0) { //还有0次可以扔，并且扔出大于100的次数是0次
			return 1D;
		}
		// i < 0
		// i == 0 j > 0，这情况在base case拦住了
		// i == 0 j < 0， 这情况在base case拦住了
		// i == 0 j == 0，这种情况也返回了
		// 如果是上面四种情况，都提前返回了
		// i > 0 & i >= j 只需要返回这个情况就行了，i>0说明还有次数可以扔
		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		// 1) > 100 情况，more * (i - 1, j - 1)
		// 2) <= 100 情况 , less * (i - 1, j)
		// 总体达成的概率 = 1) + 2)
		// 数学结论：如果扔出了less，那么后续一定要拿下（i-1，j）；如果这次扔了more，后面继续正常扔more，次数都减少；因为这两个条件都是成功的条件，所以相加即可
		double ans = more * process(more, less, i - 1, j - 1, dp) + less * process(more, less, i - 1, j, dp);
		dp[i][j] = ans;
		return ans;
	}

	public static void main(String[] args) {
		double P = 0.6;
		int N = 100;
		int A = 30;
		int B = 50;
		System.out.println(probability(P, N, A, B));
	}

}
