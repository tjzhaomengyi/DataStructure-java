package com.MCAAlgorithm.weeklyfactory.class_2023_05_4_week;

import java.util.ArrayList;
import java.util.Arrays;

// 来自招商银行
// 原始题目描述
// 假如某公司目前推出了N个在售的金融产品(1<=N<=100)
// 对于张三，用ai表示他购买了ai(0<=ai<=10^4)份额的第i个产品(1<=i<=N)
// 现给出K(1<=K<=N)个方案，通过这些方案，能够支持将多个不同的产品进行整合
// (也可以对单个产品进行优化)形成新的产品。
// 新的产品形成后，若用户持有了组成新产品所需的全部的原产品份额，
// 则能够将用户持有的原产品份额转换为新产品的份额，各原产品份额与新产品份额比例均为1:1
// 我们保证对于每个产品最多存在一个方案使用旧产品整合成该产品
// 并且根据方案产出的新产品的产品编号均大于各旧产品的产品编号
// 现计划根据这些方案，帮助部分愿意升级到最新产品的用户对产品进行升级
// 请协助工作人员计算当前用户能够转换出的最新产品份额的最大值
// 输入描述
// 第一行包含整数N，第二行包含N个整数ai，第三行包含整数K
// 接下来的K行，每一行代表一个方案，每一行包含整数1和M(M>=1)
// L为该方案产生的新产品的编号，M代表方案所需原产品个数
// 接下来的M个整数代表了该方案所需的每个原产品的个数
// 输出描述
// 根据日前的份额和给出的方案，经过若干次转换，输出当前用户能够得到产品N的份额最大值
// 举例
// 输入:
// 5
// 2 0 0 1 0
// 3
// 5 2 3 4
// 2 1 1
// 3 1 2
// 输出:
// 1
// 解释:
// 第一步将1份1产品转化为1份2产品
// 第二步将1份2产品转化为1份3产品
// 第三步将1份3产品和1份4产品，转成为1份5产品
// 然后不能得到更多的5产品了，所以返回1
// 实在是太困惑了，上文说的意思可谓不做人，那么我们改写一下意思，变得好理解
// 如下是改写后的题目描述
// 给定一个数组arr，长度为n，产品编号从0~n-1
// arr[i]代表初始时i号产品有多少份
// 存在一系列的产品转化方案的数组convert，长度为k，代表k个方案
// 比如具体某一个方案，convert[j] = {a, b, c, d, ...}
// 表示当前的j号方案转化出来的产品是a，转化1份a需要：1份b、1份c、1份d...
// 其中a、b、c、d...一定都在0~n-1范围内
// 并且题目保证a > Math.max(b, c, d, ....)
// 而且题目保证所有方案转化出来的产品编号一定是不重复的
// 请返回最终能得到的第n-1号商品的最大值
// 1 <= n <= 100
// 0 <= arr[i] <= 10^4
// k < n
public class Code06_ConversionOfFinancialProducts {
	//思路：二分答案 + 拓扑排序 这样的题不多，好题，这样的题不多
	// 1、二分答案：n-1最大上线从0到所有商品的和，如果n-1的份额是100，先看0-50能不能做出来
	public static int MAXN = 101;

	public static int[] indegree = new int[MAXN];//indegree不变

	public static int[] help = new int[MAXN];//help把indegree拷贝出来，来执行拓扑排序的入度调整，因为整个过程会要折腾好几次反复使用原有的indegree信息

	public static int[] zeroQueue = new int[MAXN];

	public static int[] need = new int[MAXN];

	public static int n;

	public static int maxValue(int[] arr, int[][] convert) {
		n = arr.length;
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}
		//反向按照任务下发的方法转换成图
		Arrays.fill(indegree, 0, n, 0);
		for (int[] relation : convert) {
			for (int i = 1; i < relation.length; i++) {
				graph.get(relation[0]).add(relation[i]);
				indegree[relation[i]]++;
			}
		}
		// arr[n-1] 初始就有100份
		// 101 ~ 整体累加和
		int l = arr[n - 1] + 1;//看看能不能在arr[n-1]+1的份额+累加和的份额上得到新的答案
		int r = 0;//二分边界是整体累加和
		for (int num : arr) {
			r += num;
		}
		int m = 0, ans = arr[n - 1];//如果二分不出更好的结果，继续保持ans的结果
		while (l <= r) {
			m = (l + r) / 2;
			if (ok(arr, graph, m)) { //
				ans = m;
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return ans;
	}

	// arr里，是每一种商品的初始份额
	// 思路：这个下发的思路参考Code06的下发需求图解
	// graph就是图，3 -> 0 1 2 ，3往210下发任务，看看0够不够决定3能不能到aim
	// aim目标，一定要转化出这么多份！n-1号商品
	public static boolean ok(int[] arr, ArrayList<ArrayList<Integer>> graph, int aim) {
		int l = 0;
		int r = 0;
		//把indegree拷贝到help
		for (int i = 0; i < n; i++) {
			help[i] = indegree[i];
			//技巧：这里肯定是最后一个点n-1先入队列！！！！
			if (help[i] == 0) { //入度为0的点，进入拓扑排序的队列中，注意：先进队列的是arr[n-1]的点，反着进队列，他需求最多，要把需求往下下发
				zeroQueue[r++] = i;
			}
		}
		Arrays.fill(need, 0, n, 0);//所有商品默认初始需要0份
		need[n - 1] = aim;//反着的只有n-1号商品总目标需要aim份
		while (l < r) {
			// 当前商品的编号!
			int cur = zeroQueue[l++]; //把入度为0的点拿出来，本质就是后面要出来的n-1第一个先出队列
			// 需要底层给它供应的量
			int supplement = Math.max(need[cur] - arr[cur], 0);//需要的量和已有的量
			if (graph.get(cur).isEmpty() && supplement > 0) { //如果当前是最底层，还需要供应，不行了
				return false;
			}
			for (int next : graph.get(cur)) {
				need[next] += supplement; //所有的底层加上
				if (--help[next] == 0) { //入度--
					zeroQueue[r++] = next;//继续拓扑排序
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int[] arr1 = { 2, 0, 0, 1, 0 };
		int[][] convert1 = { { 4, 2, 3 }, { 1, 0 }, { 2, 1 } };
		System.out.println(maxValue(arr1, convert1));

		// 我构造了一个非常好的例子
		// 课上说明一下
		int[] arr2 = { 100, 5, 5, 0 };
		int[][] convert2 = { { 1, 0 }, { 2, 0, 1 }, { 3, 0, 1, 2 } };
		System.out.println(maxValue(arr2, convert2));
	}

}
