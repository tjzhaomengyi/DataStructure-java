package com.MCAAlgorithm.bigshua.class06;

public class Code05_Nim {

	// 保证arr是正数数组
	public static void printWinner(int[] arr) {
		int eor = 0;
		for (int num : arr) {
			eor ^= num;
		}
		if (eor == 0) {
			System.out.println("后手赢");
		} else {
			System.out.println("先手赢");
		}
	}

	public static void main(String[] args) {
		printWinner(new int[]{1,2,3,4});
	}
}
