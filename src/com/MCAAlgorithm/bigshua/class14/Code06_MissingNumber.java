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

	public int firstMissingByTwoStep(int[] arr){
		// 这道题就是折腾数组的过程，就是让数组下标和数映射上的题
		// 上面左神的解法优化了，最普通的解法就是两次遍历，第一次把映射作对即可
		// arr长度为n，那么如果正常存放的话，arr存放的是从1到n按顺序存放，
		// 下标和值的对应关系为arr[arr[i] - 1] = arr[i]
		int n = arr.length;
		for(int i = 0; i < n; i++){
			//这里要用 while 不能用 if，每次交换过来，arr[arr[i] - 1]的数不停变换
			while(arr[i] > 0 && arr[i] <=n && arr[arr[i] - 1] != arr[i]){
				int tmp = arr[arr[i] - 1];
				arr[arr[i] - 1] = arr[i];
				arr[i] = tmp;
			}
		}
		for(int i = 0; i < n; i++){
			if(arr[i] != i + 1){
				return i + 1;
			}
		}
		return n + 1;
	}

}
