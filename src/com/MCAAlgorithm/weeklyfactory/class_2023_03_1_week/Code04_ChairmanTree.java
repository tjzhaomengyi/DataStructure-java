package com.MCAAlgorithm.weeklyfactory.class_2023_03_1_week;

// 主席树详解
//	思路：带版本的线段树，多棵数是多棵什么，从上到下撸一个log区间，增加节点，看下面的例子详解，然后利用最小增量问题
//  如何统计每段区间上的个数，一段区间按照"版本"更新，版本
//  1-31范围上出现一棵线段树，1-54更新的时候也出来一颗线段树，所以32 到54 版本上 用V54-V32，两个版本的线段树相减，得到区间的线段树。
// 思路：如何在有限空间表示上面这些版本的线段树,每个节点只更新变更的节点
//  例子：值1-4范围
//  	       1-4 （1号）
//	   1-2（2号）          3-4（5号）
//  1（3号）	2（4号）	  3（6号）		4（7号）
//  对应record数组： 0  0  0  0  0  0  0 ，record数组初始的时候全是0，表示每个节点范围上一个数都没有
//                 1  2  3  4  5  6  7
//  当2这个数加进来，生成1版本线段树，1-4号节点生成了8号，然后往左滑！左侧有变化了！8和9代表新的版本
//  对应record数组：0  0  0  0  0  0  0  1  1  1
//                1  2  3  4  5  6  7  8  9 10
//  	         1-4 （8号）
//	    1-2（9号）                 3-4（5号）
//  1（3号）	2（10号）	  3（6号）		4（7号）
// 测试链接 : https://www.luogu.com.cn/problem/P3834
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下所有代码，把主类名改成Main，可以通过
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;


public class Code04_ChairmanTree {

	public static int MAXN = 200010;

	// 输入数据离散化操作
	// 数组 : {100, 35, 4,  800}
	// 排序 : {4 , 35, 100, 800}
	//        1    2    3    4
	// 1  2   3   4
	// 1 ~ 800
	// 1 ~ 4
	public static int[] origin = new int[MAXN];
	public static int[] sorted = new int[MAXN];
	
	
	// 每个版本的线段树头部
	// 线段树不是基于下标的，基于值，基于值转化之后的rank
	// root[i] : i位置的数，加入之后，线段树的头部节点编号
	public static int[] root = new int[MAXN];

	// 建树相关，当你的数组个数是n，一般来讲开32 * n，left和right记录每个节点的左右子节点序号，数组的大小32倍空间大小即可，这是一个best practice
	public static int[] left = new int[MAXN << 5];
	public static int[] right = new int[MAXN << 5];
	public static int[] sum = new int[MAXN << 5];//每个范围内中有几个数

	public static int cnt, n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			cnt = 0;
			n = (int) in.nval;
			in.nextToken();
			m = (int) in.nval;
			for (int i = 1; i <= n; i++) {
				in.nextToken();
				origin[i] = (int) in.nval;
				sorted[i] = origin[i];
			}
			Arrays.sort(sorted, 1, n + 1);
			root[0] = build(1, n);
			for (int i = 1; i <= n; i++) {
				int x = rank(origin[i]);
				root[i] = insert(root[i - 1], 1, n, x);
			}
			for (int i = 1; i <= m; i++) {
				in.nextToken();
				int L = (int) in.nval;
				in.nextToken();
				int R = (int) in.nval;
				in.nextToken();
				int K = (int) in.nval;
				// L..R K
				// R - (L-1)
				int ansIndex = query(root[L - 1], root[R], K, 1, n);//查询L-1到R版本
				out.println(sorted[ansIndex]);
				out.flush();
			}
		}
	}

	// 1、构建线段树
	// 对于0版本的线段树来说，值的范围l~r, rt, 0个
	public static int build(int l, int r) {
		int rt = ++cnt;
		sum[rt] = 0; //开始的时候进来了0个数
		if (l < r) {
			int mid = (l + r) / 2; //中点
			left[rt] = build(l, mid); //去左边build
			right[rt] = build(mid + 1, r); //去右边build
		}
		return rt; //把自己的编号返回
	}

	// 2、在l到r范围上更新一个值x，把x加入到l到r的范围上
	// 当前范围是 l ~ r
	// pre：这个范围在上一个版本的编号
	// 当前版本，在l~r上加数，加x
	// 返回：当前版本，l~r范围，编号返回
	public static int insert(int pre, int l, int r, int x) {
		//在100到200区间上新增了数，这个版本要更新，更新区间如果是右孩子需要更新，左孩子不需要更新，那么left需要上一个版本的编号pre，
		// 右孩子节点进行更新
		int rt = ++cnt; //rt肯定要增加
		left[rt] = left[pre];//rt的左边是谁pre的left
		right[rt] = right[pre];//右侧和左边一样
		sum[rt] = sum[pre] + 1; //当前rt范围内增加了
		// 看看增加的x往左侧还是往右侧滑动
		if (l < r) {
			int mid = (l + r) / 2;
			if (x <= mid) {
				left[rt] = insert(left[pre], l, mid, x);
			} else {
				right[rt] = insert(right[pre], mid + 1, r, x);
			}
		}
		return rt;
	}

	// 3、查询某个范围上的值
	// 请查询  下标！在 : 32 ~ 54 范围上，排名第9的数字！
	// 54版本 - 31版本的线段树，请加工出来！排名第9的数字！
	// u : 31版本，之前的版本
	// v : 54版本，要查的最新版本
	// l ~ r : 值的范围，在线段树上!
	public static int query(int u, int v, int k, int l, int r) {
		if (l == r) {
			return l;//查到l等于r就是这个数了
		}
		int leftSize = sum[left[v]] - sum[left[u]];//v版本左侧编号，u版本的编号，得到
		int mid = (l + r) / 2;
		if (leftSize >= k) { //leftsize大于k（第9名假设），在左侧找
			return query(left[u], left[v], k, l, mid);
		} else { //左侧不够9个，往右侧找
			return query(right[u], right[v], k - leftSize, mid + 1, r);
		}
	}

	public static int rank(int v) {
		int l = 1;
		int r = n;
		int m = 0;
		int ans = 0;
		while (l <= r) {
			m = (l + r) / 2;
			if (sorted[m] <= v) {
				ans = m;
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return ans;
	}

}
