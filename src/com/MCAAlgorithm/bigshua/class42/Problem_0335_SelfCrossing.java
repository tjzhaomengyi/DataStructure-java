package com.MCAAlgorithm.bigshua.class42;

public class Problem_0335_SelfCrossing {

	//儿童趣味益智:画图，最多到i-5的情况，i-6其实和i-5情况一样的
	public static boolean isSelfCrossing(int[] x) {
		if (x == null || x.length < 4) {
			return false;
		}
		if ((x.length > 3 && x[2] <= x[0] && x[3] >= x[1])
				|| (x.length > 4 
						&& ((x[3] <= x[1] && x[4] >= x[2]) || (x[3] == x[1] && x[0] + x[4] >= x[2])))) {
			return true;
		}
		for (int i = 5; i < x.length; i++) {
			if (x[i - 1] <= x[i - 3] && ((x[i] >= x[i - 2])
					|| (x[i - 2] >= x[i - 4] && x[i - 5] + x[i - 1] >= x[i - 3] && x[i - 4] + x[i] >= x[i - 2]))) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[] arr = { 2, 2, 3, 2, 2 };
		System.out.println(isSelfCrossing(arr));
	}

}
