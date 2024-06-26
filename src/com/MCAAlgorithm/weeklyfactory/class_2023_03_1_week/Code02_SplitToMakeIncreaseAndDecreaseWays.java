package com.MCAAlgorithm.weeklyfactory.class_2023_03_1_week;

// 来自学员问题，国外算法面经帖子上的题
// 给定一个数组A, 把它分成两个数组B和C
// 对于数组A每个i位置的数来说，
// A[i] = B[i] + C[i]
// 也就是一个数字分成两份，然后各自进入B和C
// 要求B[i], C[i] >= 1
// 最终B数组要求从左到右不能降序
// 最终C数组要求从左到右不能升序
// 比如
// A = { 5, 4, 5 }
// 可以分成
// B = { 2, 2, 3 }
// C = { 3, 2, 2 }
// 这是一种有效的划分
// 返回有多少种有效的划分方式
// 1 <= A的长度 <= 10^7
// 1 <= A[i] <= 10^7
// 最终结果可能很大，请返回对1000000007取余的结果
public class Code02_SplitToMakeIncreaseAndDecreaseWays {

	// 数学结论：1如果所有值都相同
	// [5, 5, 5, 5],下面的分解括号中是方法数这个位置的数和右侧位置组合的方法数，每一次都是自己的左加自己的上
	// 4,1(1)  4,1(1)  4,1(1)  4,1(1)
	// 3,2(4)  3,2(3)  3,2(2)  3,2(1)
	// 2,3(10) 2,3(6)  2,3(3)  2,3(1)
	// 1,4(20) 1,4(10) 1,4(4)  1,4(1)
	// 这个结果就是一个杨辉三角形，每一列的最后一个值代表杨辉三角形，杨辉三角形每一行的每个位置就是对应行C(n,m)的结果，n表示行，m表示列，都是从0开始

	//数学结论：2如果有不同的值
	//  5	8
	// 4,1  7,1
	// 3,2  6,2
	// 2,3  5,3
	// 1,4  4,4  所有的组合方法数还是这四行之间匹配
	//      3,5 从这行开始就不能用了！
	//      2,6
	//      1,7

	// 数学结论：3如果三个值怎么减少范围，减少两组 8 - 6
	//  5 	 8 	  6
	// 4 1（不能用）  7 1（不能用）  5 1
	// 3 2（不能用）  6 2（不能用）  4 2
	// 2 3          5 3
	// 1 4          4 4
	// 数学结论：总结，如果后面的数大于前面的数以前面数位基准，如果后面的数小于前面的数，前面的数-后面的数
	// 暴力方法
	// 为了验证
	// 假设答案永远不会溢出
	// 小数据量可用
	public static int ways1(int[] arr) {
		int ans = 0;
		for (int increase = 1, decrease = arr[0] - 1; increase < arr[0]; increase++, decrease--) {
			ans += process1(arr, 1, increase, decrease);
		}
		return ans;
	}

	public static int process1(int[] arr, int i, int preIncrease, int preDecrease) {
		if (i == arr.length) {
			return 1;
		}
		int ans = 0;
		for (int increase = 1, decrease = arr[i] - 1; increase < arr[i]; increase++, decrease--) {
			if (preIncrease <= increase && preDecrease >= decrease) {
				ans += process1(arr, i + 1, increase, decrease);
			}
		}
		return ans;
	}

	// 最优解法
	// 转化成杨辉三角形不容易想
	// 大数据量需要取余
	// 需要用到乘法逆元
	// 时间复杂度O(N + M)
	// N是数组长度，M大概可以认为是数值范围
	public static int ways2(int[] arr) {
		int n = arr.length;
		// [ 6 
		//   5
		int k = arr[0] - 1; //一上来的连接数arr[0]-1,对应杨辉三角形的行号，杨辉三角形从0开始，也表示拆分的个数
		for (int i = 1; i < n && k > 0; i++) {
			if (arr[i - 1] > arr[i]) {//如果前面的数字大于后面的数字
				k -= arr[i - 1] - arr[i];//k缩减！看看最后剩下多少连接！
			}
		}
		if (k <= 0) {
			return 0;
		}
		return pascalTriangleModulus(k - 1 + n, n);// 杨辉三角形求某一项
	}

	// 组合公式，n个元素取r个的方法数
	// n! / (r! * (n-r)!)
	// 用乘法逆元并且有mod时候的做法
	public static int pascalTriangleModulus(int n, int r) {
		int mod = 1000000007;
		long up = 1;
		long inv1 = 1;
		long inv2 = 1;
		for (int i = 1; i <= n; i++) {
			up = (up * i) % mod;
			if (i == r) {
				inv1 = power(up, mod - 2, mod); //除法的逆原《2023-2-2》
			}
			if (i == n - r) {
				inv2 = power(up, mod - 2, mod);
			}
		}
		return (int) ((((up * inv1) % mod) * inv2) % mod);
	}

	// x的n次方，% mod之后，返回是多少
	// 费马小定理
	public static long power(long x, int n, int mod) {
		long ans = 1;
		while (n > 0) {
			if ((n & 1) == 1) {
				ans = (ans * x) % mod;
			}
			x = (x * x) % mod;
			n >>= 1;
		}
		return ans;
	}

	// 为了测试
	public static int[] randomArray(int n, int v) {
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = (int) (Math.random() * v) + 1;
		}
		return ans;
	}

	// 为了测试
	public static void main(String[] args) {
		// 展示一下pascalTriangleModulus的用法
		System.out.println("打印部分杨辉三角形");
		for (int n = 0; n <= 10; n++) {
			for (int r = 0; r <= n; r++) {
				System.out.print(pascalTriangleModulus(n, r) + " ");
			}
			System.out.println();
		}
		int N = 10;
		int V = 20;
		int testTimes = 20000;
		System.out.println("功能测试开始");
		for (int i = 0; i < testTimes; i++) {
			int n = (int) (Math.random() * N) + 1;
			int[] arr = randomArray(n, V);
			int ans1 = ways1(arr);
			int ans2 = ways2(arr);
			if (ans1 != ans2) {
				System.out.println("出错了!");
			}
		}
		System.out.println("功能测试结束");

		System.out.println("性能测试开始");
		int n = 10000000;
		int v = 10000000;
		long start, end;
		int[] arr = new int[n];
		System.out.println("随机生成的数据测试 : ");
		System.out.println("数组长度 : " + n);
		System.out.println("数值范围 : [" + 1 + "," + v + "]");
		for (int i = 0; i < n; i++) {
			arr[i] = (int) (Math.random() * v) + 1;
		}

		start = System.currentTimeMillis();
		ways2(arr);
		end = System.currentTimeMillis();
		System.out.println("运行时间 : " + (end - start) + " 毫秒");

		System.out.println("运行最慢的数据测试 : ");
		System.out.println("数组长度 : " + n);
		System.out.println("数值都是 : " + v);
		System.out.println("这种情况其实是复杂度最高的情况");
		for (int i = 0; i < n; i++) {
			arr[i] = v;
		}
		start = System.currentTimeMillis();
		ways2(arr);
		end = System.currentTimeMillis();
		System.out.println("运行时间 : " + (end - start) + " 毫秒");
		System.out.println("性能测试结束");
	}

}
