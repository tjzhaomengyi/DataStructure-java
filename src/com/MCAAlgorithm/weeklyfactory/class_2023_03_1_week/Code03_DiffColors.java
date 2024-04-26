package com.MCAAlgorithm.weeklyfactory.class_2023_03_1_week;

// HH有一串由各种漂亮的贝壳组成的项链
// HH 相信不同的贝壳会带来好运，所以每次散步完后，他都会随意取出一段贝壳，
// 思考它们所表达的含义。HH 不断地收集新的贝壳，因此，他的项链变得越来越长。
// 有一天，他突然提出了一个问题：某一段贝壳中，包含了多少种不同的贝壳？
// 这个问题很难回答... 因为项链实在是太长了
// 于是，他只好求助睿智的你，来解决这个问题
// 测试链接 : https://www.luogu.com.cn/problem/P1972
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下所有代码，把主类名改成Main
// 洛谷对java太不友好了，大量时间不是消耗在算法本身上，而是耗在了IO上
// 多提交几次能全通过
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

//思路：线段树：单点更新和范围上求累加和
// 例子：有如下查询[1，100]，[3,7],[5,23] 把查询任务根据查询结束位置从小到大排序，[3,7],[5,23],[1,100]
// [红 蓝 绿 红 蓝]，有一个开始的s下标
// 出现的颜色位置收集：【红：1 ， 蓝：2， 绿：3】
// 是否出现过：1 1 1      从左到右侧是红色、蓝色、绿色，1表示出现过了
// 来到红4的时候
// 出现的颜色位置收集【红：4，蓝：2， 绿：3】，
// 是否出现过 0 0 1 1 1，然后求这个出现过的累加和就是从哪到哪的不同颜色的数量是多少，每次出现重复的颜色就把上次出现的一样颜色的位置标记位0
public class Code03_DiffColors {

	public static int MAXN = 1000010;

	public static int[] arr = new int[MAXN];

	public static int[][] query = new int[MAXN][3];

	public static int[] ans = new int[MAXN];

	public static int[] map = new int[MAXN];

	public static int[] tree = new int[MAXN];

	public static int n, m;

	public static void buildTree() {
		Arrays.fill(tree, 1, n + 1, 0);
	}

	public static int sum(int l, int r) {
		return sum(r) - sum(l - 1);
	}

	public static int sum(int index) {
		int ret = 0;
		while (index > 0) {
			ret += tree[index];
			index -= index & -index;
		}
		return ret;
	}

	public static void add(int i, int d) {
		while (i <= n) {
			tree[i] += d;
			i += i & -i;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			n = (int) in.nval;
			for (int i = 1; i <= n; i++) {
				in.nextToken();
				arr[i] = (int) in.nval;
			}
			// arr[i] : i位置的颜色
			in.nextToken();
			m = (int) in.nval;
			for (int i = 1; i <= m; i++) {
				in.nextToken();
				// left
				query[i][0] = (int) in.nval;
				in.nextToken();
				// right
				query[i][1] = (int) in.nval;
				// 第i个问题
				query[i][2] = i;
			}
			// map[5] = 8
			// 5这个颜色，上次出现在8位置
			// map[5] = 0 //indextree序号从1开始
			// 5这个颜色，之前没出现过
			Arrays.fill(map, 0);
			buildTree();
			Arrays.sort(query, 1, m + 1, (a, b) -> a[1] - b[1]);//根据right排序，问题结束编号越大最后处理
			for (int s = 1, j = 1; j <= m; j++) {
				int l = query[j][0];
				int r = query[j][1];
				int index = query[j][2];//第几个问题
				for (; s <= r; s++) { //s先把r追上
					int color = arr[s]; //这个color出现过
					if (map[color] != 0) {
						add(map[color], -1);//把上次出现位置变成0
					}
					add(s, 1); //现在出现的位置标记为
					map[color] = s;//修改这个颜色最近出现的位置
				}
				ans[index] = sum(l, r);//在index tree中查询l到r的累加和，表示总共有多少个不重复的颜色
			}
			for (int i = 1; i <= m; i++) {
				out.println(ans[i]);
			}
			out.flush();
		}
	}

}
