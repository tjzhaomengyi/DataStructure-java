package com.MCAAlgorithm.base.class40;

public class Code05_PrintMatrixSpiralOrder {

	/**
	 * 左神这个题写麻烦了，查看niuke.BM99的写法吧，没有必要用递归，
	 * 这里已经推出来从外围到内部来推的过程了，不知道为什么还要用一下递归，明显麻烦了
	 * @param matrix
	 */
	public static void spiralOrderPrint(int[][] matrix) {
		//先找到四个角的规律
		int tR = 0;
		int tC = 0;
		int dR = matrix.length - 1;
		int dC = matrix[0].length - 1;
		while (tR <= dR && tC <= dC) {
			printEdge(matrix, tR++, tC++, dR--, dC--);
		}
	}

	//对每个要旋转的四个角进行分组，一共n-1组
	public static void printEdge(int[][] m, int tR, int tC, int dR, int dC) {
		if (tR == dR) {
			for (int i = tC; i <= dC; i++) {
				System.out.print(m[tR][i] + " ");
			}
		} else if (tC == dC) {
			for (int i = tR; i <= dR; i++) {
				System.out.print(m[i][tC] + " ");
			}
		} else {
			int curC = tC;
			int curR = tR;
			while (curC != dC) {
				System.out.print(m[tR][curC] + " ");
				curC++;
			}
			while (curR != dR) {
				System.out.print(m[curR][dC] + " ");
				curR++;
			}
			while (curC != tC) {
				System.out.print(m[dR][curC] + " ");
				curC--;
			}
			while (curR != tR) {
				System.out.print(m[curR][tC] + " ");
				curR--;
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 },
				           { 5, 6, 7, 8 },
				           { 9, 10, 11, 12 },
				           { 13, 14, 15, 16 } };
		spiralOrderPrint(matrix);

	}

}
