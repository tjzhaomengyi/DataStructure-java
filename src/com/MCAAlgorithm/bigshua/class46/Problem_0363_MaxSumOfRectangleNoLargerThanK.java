package com.MCAAlgorithm.bigshua.class46;

import java.util.TreeSet;

public class Problem_0363_MaxSumOfRectangleNoLargerThanK {

	//技巧:使用前缀和来求最接近k的值,引子，在数组中使用前缀和找出最接近k的前缀和
	public static int nearK(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return Integer.MIN_VALUE;
		}
		//set统计前缀和
		TreeSet<Integer> set = new TreeSet<>();//因为要找到最接近的和是多少，所以用treeset
		set.add(0);
		int ans = Integer.MIN_VALUE;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			// 讨论子数组必须以i位置结尾，最接近k的累加和是多少？
			sum += arr[i];
			// 找之前哪个前缀和 >= sum - k  且最接近
			// 有序表中，ceiling(x) 返回>=x且最接近的！
			// 有序表中，floor(x) 返回<=x且最接近的！
			Integer find = set.ceiling(sum - k);
			if(find != null) {
				int curAns = sum - find;
				ans = Math.max(ans, curAns);
			}
			set.add(sum); //更新新得到的sum
		}
		return ans;
	}

	//技巧:分情况讨论，【技巧】压缩数组，包含1行，包含1行和2行，包含2和3
	public static int maxSumSubmatrix(int[][] matrix, int k) {
		if (matrix == null || matrix[0] == null) {
			return 0;
		}
		if (matrix.length > matrix[0].length) {
			matrix = rotate(matrix);
		}
		int row = matrix.length;
		int col = matrix[0].length;
		int res = Integer.MIN_VALUE;
		TreeSet<Integer> sumSet = new TreeSet<>();
		for (int s = 0; s < row; s++) {
			int[] colSum = new int[col];
			for (int e = s; e < row; e++) {
				// s ~ e 这些行  选的子矩阵必须包含、且只包含s行~e行的数据
				// 0 ~ 0  0 ~ 1  0 ~ 2 。。。
				// 1 ~ 2  1 ~ 2  1 ~ 3 。。。
				sumSet.add(0);
				int rowSum = 0;
				for (int c = 0; c < col; c++) {
					colSum[c] += matrix[e][c];
					rowSum += colSum[c];
					Integer it = sumSet.ceiling(rowSum - k);
					if (it != null) {
						res = Math.max(res, rowSum - it);
					}
					sumSet.add(rowSum);
				}
				sumSet.clear();
			}
		}
		return res;
	}

	public static int[][] rotate(int[][] matrix) {
		int N = matrix.length;
		int M = matrix[0].length;
		int[][] r = new int[M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				r[j][i] = matrix[i][j];
			}
		}
		return r;
	}

}
