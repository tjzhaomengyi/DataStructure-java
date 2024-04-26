package com.MCAAlgorithm.weeklyfactory.class_2023_03_5_week;

// 来自学员问题，蓝桥杯练习题
// 小青蛙住在一条河边, 它想到河对岸的学校去学习
// 小青蛙打算经过河里 的石头跳到对岸
// 河里的石头排成了一条直线, 小青蛙每次跳跃必须落在一块石头或者岸上
// 给定一个长度为n的数组arr，表示每块儿石头的高度数值
// 每块石头有一个高度, 每次小青蛙从一块石头起跳
// 这块石头的高度就会下降1, 当石头的高度下降到0时
// 小青蛙不能再跳到这块石头上(跳跃后使石头高度下降到0是允许的)
// 小青蛙一共需要去学校上x天课, 所以它需要往返x次(去x次，回x次)
// 当小青蛙具有 一个跳跃能力y时, 它能跳不超过y的距离
// 请问小青蛙的跳跃能力至少是多少才能用这些石头上完x次课
// 1 <= n <= 10^5
// 1 <= arr[i] <= 10^4
// 1 <= x <= 10^9
// 测试链接 : https://www.luogu.com.cn/problem/P8775
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

//  15 20 10 5 ,这些石头是有耐久度的，
//  如果是1的距离，如果跳的距离是1，最后的石头5就不行了，一次降到1，石头就没了，回不来了；如果跳的距离是2，表示2和1的距离都可以，这样就
//  可以多跳几次，距离定到多少时候，可以往返x次
// 类似《捡樱桃问题》，这道题的思路转化非常秒
// 思路：问题转化：假设1只青蛙往返100次（200次消耗），可以把问题转换成2只青蛙过去100次，【往返的题都是这么干的，把人进行分身】
// 思路：距离定的越大消耗越小，所以在石头的长度二分出来。【数学结论：任意相邻的k个石头耐久和不能小于2x！！！！】
//  例子：假设跳跃是3 [0 1 2 3 4 5 6] 2 3 4 上一定要来2x个人，躲不过去！！！所有人都要这三个位置上过去，任何连续3个位置都不能躲掉这个条件，
//   因为要从这3个连续的位置迁移到接着的连续3个位置，任意位置开始连着3个都要满足大于等于2x，这样使用前缀和数组即可完成，每次二分一下利用前缀和数组

// 思路：优化：利用窗口，不用二分，来到0的时候看看到哪个位置大于等于2x，记录长度，来到1的时候看看到哪个位置看看哪个位置大于等于2x，这些距离长度求max，
//  就是最后的结果距离。要保证处处如此！如果r大于n就不讨论了，最后达不到的长度就直接上岸了
public class Code04_FrogGoToSchool {

	public static int MAXN = 100001;

	public static long[] help = new long[MAXN];

	public static int n, x;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			n = (int) in.nval;
			in.nextToken();
			x = (int) (in.nval);
			for (int i = 1; i < n; i++) { //n从1开始，0位置前缀和为0，1开始得到前缀和
				in.nextToken();
				help[i] = help[i - 1] + (int) in.nval;
			}
			out.println(minAbility());
			out.flush();
		}
	}

	// O(N)的最优解
	public static int minAbility() {
		int ans = 0;
		for (int l = 1, r = 1; l < n; l++) {
			while (r < n && help[r] - help[l - 1] < 2L * x) {
				r++;
			}
			ans = Math.max(ans, r - l + 1);
		}
		return ans;
	}

}