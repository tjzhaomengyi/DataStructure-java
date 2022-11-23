package com.MCAAlgorithm.bigshua.class14;

// 测试链接：https://leetcode.com/problems/first-missing-positive/
public class Code06_MissingNumber {

	public static int firstMissingPositive(int[] arr) {
		// l是盯着的位置
		// 0 ~ L-1有效区
		int L = 0;
		int R = arr.length;
		while (L != R) {
			if (arr[L] == L + 1) {
				L++;
			} else if (arr[L] <= L || arr[L] > R || arr[arr[L] - 1] == arr[L]) { // 垃圾的情况
				//arr[l]-1表示这个值该去的位置即arr[arr[l]-1]此时要去的位置上的值，正好和当前的值一样死吧，那个位置已经有合适的值了
				swap(arr, L, --R);
			} else {
				swap(arr, L, arr[L] - 1);
			}
		}
		return L + 1;
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}
