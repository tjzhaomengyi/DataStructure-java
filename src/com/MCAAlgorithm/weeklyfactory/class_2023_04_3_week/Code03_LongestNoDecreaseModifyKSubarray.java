package com.MCAAlgorithm.weeklyfactory.class_2023_04_3_week;

// 来自学员问题，蓝桥杯练习题
// 给定一个长度为n的数组arr
// 现在你有一次机会, 将其中连续的K个数全修改成任意一个值
// 请你计算如何修改可以使修改后的数 列的最长不下降子序列最长
// 请输出这个最长的长度。
// 最长不下降子序列:子序列中的每个数不小于在它之前的数
// 1 <= k, n <= 10^5
// 1 <= arr[i] <= 10^6
// 测试链接 : https://www.luogu.com.cn/problem/P8776
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的所有代码，并把主类名改成"Main"
// 可以直接通过
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

//最长递增子序列，大厂刷题09，"以这个数结尾，最长递增子序列是多长"
// 利用ends数组，每个位置i开始最长不下降递增子序列是多长
//[            4 6 5 6]，k=3
// 0 1 2 3 4 5 6 7 8 9
//[5       6 3 7 8 3 8 ]
//
// 思路：看从3开始到9的最长不递减子序列是多长，必须要以3开头！，开始的时候大小为k的窗口中是0到2
//  窗口移动到从1到3，如果只有0和4组成不降序子序列，只需要修改123,如果在上面的例子中忽略掉1到3位置的数，从0到9组成的最长不递减子序列是：
//  以4将数组分成两半，前一半的不降序子序列是5 6 和 后半部是 6 7 8 8，这里多算了一个6，然后中间k可以修改成5或者6，那么就是5+3 = 8
//  就这样不断往下递推窗口，看两侧的窗口长度，把中间部分扣掉！！！01 （）56789这样看
// 思路：上面的过程一定要靠着靠着靠着，反例：1 2 （6 5 4） 3 2 2 3 4 ，这样看似漏算1 2 2| 2 2 3 4 这个并没有贴着（窗口没有贴着被选择的第二个2），但是不会漏掉正确答案的！
//  在其他步骤已经算出来来了 【在 1 2 6 （5 4 3） 2 2 3 4】 1 2 2 | 2 2 3 4这一步不就算出来了么
// 思路：最长递增子序列的ends，窗口在最左侧的时候，ends没有东西，查询3开始能够多长的最长递增子序列，
//  滑动到123窗口的时候0位置的数进ends，查询4开始到最后最长递增子序列，窗口往右滑动，左侧漏出来谁把水加到ends数组中。这样一点点往右推
//ends:倒着从结尾往前看，定义还是原来的意思[6 6 4],以这里要在原数组找到递减，从右往左的结尾
//                                   1 2 3
public class Code03_LongestNoDecreaseModifyKSubarray {

	public static int MAXN = 100001;

	public static int[] arr = new int[MAXN];

	public static int[] right = new int[MAXN];

	public static int[] ends = new int[MAXN];

	public static int n, k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			n = (int) in.nval;
			in.nextToken();
			k = (int) (in.nval);
			for (int i = 1; i <= n; i++) {
				in.nextToken();
				arr[i] = (int) in.nval;
			}
			if (k >= n) {
				out.println(n);
			} else {
				right();
				out.println(getAns());
			}
			out.flush();
		}
	}

	public static void right() {
		right[n] = 1;
		ends[1] = arr[n];
		int len = 1;
		for (int i = n - 1; i > 0; i--) {
			int l = 1;
			int r = len;
			int m, find = len + 1;
			while (l <= r) {
				m = (l + r) / 2;
				if (ends[m] < arr[i]) {
					find = m;
					r = m - 1;
				} else {
					l = m + 1;
				}
			}
			ends[find] = arr[i];
			len = Math.max(len, find);
			right[i] = find;
		}
	}

	public static int getAns() {
		int ans = 0;
		int len = 0;
		for (int i = k + 1, j = 1; i <= n; i++, j++) {
			int l = 1;
			int r = len;
			int m, find = len + 1;
			while (l <= r) {
				m = (l + r) / 2;
				// 当前的arr[i], 利用之前的ends，求左边长度！
				if (ends[m] > arr[i]) {
					find = m;
					r = m - 1;
				} else {
					l = m + 1;
				}
			}
			ans = Math.max(ans, find + right[i] - 1 + k);
			l = 1;
			r = len;
			find = len + 1;
			while (l <= r) {
				m = (l + r) / 2;
				if (ends[m] > arr[j]) {
					find = m;
					r = m - 1;
				} else {
					l = m + 1;
				}
			}
			len = Math.max(len, find);
			ends[find] = arr[j];
		}
		ans = Math.max(ans, len + k);
		return ans;
	}

}