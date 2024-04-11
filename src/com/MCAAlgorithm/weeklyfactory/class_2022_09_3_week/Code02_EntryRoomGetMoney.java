package com.MCAAlgorithm.weeklyfactory.class_2022_09_3_week;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// 来自美团
// 某天小美进入了一个迷宫探险，根据地图所示，这个迷宫里有无数个房间
// 序号分别为1、2、3、...入口房间的序号为1
// 任意序号为正整数x的房间，都与序号 2*x 和 2*x + 1 的房间之间各有一条路径
// 但是这些路径是单向的，即只能从序号为x的房间去到序号为 2*x 或 2*x+1 的房间
// 而不能从 2*x 或 2*x+1 的房间去到序号为x的房间
// 在任何时刻小美都可以选择结束探险并离开迷宫，但是离开之后将无法再次进入迷宫
// 小美还提前了解了迷宫中宝藏的信息
// 已知宝藏共有n个，其中第i个宝藏在序号为pi的房间，价值为wi
// 且一个房间中可能有多个宝藏
// 小美为了得到更多的宝藏，需要精心规划路线，她找到你帮忙
// 想请你帮她计算一下，能获得的宝藏价值和最大值为多少
// 第一行一个正整数n，表示宝藏数量。
// 第二行为n个正整数p1, p2,...... pn，其中pi表示第 i 个宝藏在序号为pi的房间。
// 第三行为n个正整数w1, w2,...... wn，其中wi表示第i个宝藏的价值为wi。
// 1 <= n <= 40000, 1 <= pi < 2^30, 1 <= wi <= 10^6。
public class Code02_EntryRoomGetMoney {

	// 为了测试
	// 普通动态规划
	public static int maxMoney1(int n, int[] p, int[] w) {
		int[][] rooms = new int[n][2];
		for (int i = 0; i < n; i++) {
			rooms[i][0] = p[i];
			rooms[i][1] = w[i];
		}
		Arrays.sort(rooms, (a, b) -> a[0] - b[0]);
		int ans = 0;
		int[] dp = new int[n];
		Arrays.fill(dp, -1);
		for (int i = 0; i < n; i++) {
			ans = Math.max(ans, process1(i, rooms, n, dp));
		}
		return ans;
	}

	public static int process1(int index, int[][] rooms, int n, int[] dp) {
		if (dp[index] != -1) {
			return dp[index];
		}
		int next = 0;
		for (int i = index + 1; i < n; i++) {
			if (reach(rooms[index][0], rooms[i][0])) {
				next = Math.max(next, process1(i, rooms, n, dp));
			}
		}
		int ans = rooms[index][1] + next;
		dp[index] = ans;
		return dp[index];
	}

	public static boolean reach(int from, int to) {
		while (to >= from) {
			if (from == to) {
				return true;
			} else {
				to /= 2;
			}
		}
		return false;
	}

	// 正式方法
	// 时间复杂度O(N)的动态规划
	// 利用图来优化枚举
	// 用room二维数组表示房间号和房间物品的价值
	// 1、根据房间号排序
	// 2、从右往左挂节点[2 5 6 7 12 20]建图，20不停除2就能回到父节点20/2=10没有，10/2=5有，说明5节点下有孩子20；推到下一个12/2=6有，
	// 6/2=3，3/2=1 1/2=0，5/2=2
	// 得到挂载节点2-》5-》20，6-》12，根-》7 ，三条路，然后选择一条路返回
	public static int maxMoney2(int n, int[] p, int[] w) {
		int[][] rooms = new int[n][2];
		for (int i = 0; i < n; i++) {
			rooms[i][0] = p[i];
			rooms[i][1] = w[i];
		}
		Arrays.sort(rooms, (a, b) -> a[0] - b[0]); //房间好排序
		HashMap<Integer, Integer> first = new HashMap<>();//first表示房间号对应在rooms排序的下标
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int to = rooms[i][0];
			while (to > 0) {
				if (first.containsKey(to)) {
					graph.get(first.get(to)).add(i); //建立图，根据rooms数组的下标把图建立起来
					break;
				} else { //如果起始的节点中没有/2继续找根，找到room[i][0]这个房间的根节点
					to >>= 1; //每次除2
				}
			}
			graph.add(new ArrayList<>());//不管找到没找到，都把自己加入图中；如果to一直找不到根节点，那么只能是当前节点自己做根
			if (!first.containsKey(rooms[i][0])) {
				first.put(rooms[i][0], i); //把自己的房号放在first节点中
			}
		}
		//todo：拿宝藏，dfs
		int ans = 0;
		int[] dp = new int[n];//以i号房间开始最多可以拿多少宝藏
		for (int i = n - 1; i >= 0; i--) { //从后往前的的一个dp过程
			int post = 0;//后续房间宝藏价值
			for (int next : graph.get(i)) { //往下拿房间号
				if (rooms[next][0] == rooms[i][0]) { //是同一个房子，就拿自己
					dp[i] += dp[next]; //自己
				} else {
					post = Math.max(post, dp[next]); //post 或者后续
				}
			}
			dp[i] += post + rooms[i][1];//post+当前room的宝藏价值
			ans = Math.max(ans, dp[i]);
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

	public static void main(String[] args) {
		int N = 100;
		int P = 5000;
		int W = 5000;
		int testTimes = 5000;
		System.out.println("功能测试开始");
		for (int i = 0; i < testTimes; i++) {
			int n = (int) (Math.random() * N) + 1;
			int[] p = randomArray(n, P);
			int[] w = randomArray(n, W);
			int ans1 = maxMoney1(n, p, w);
			int ans2 = maxMoney2(n, p, w);
			if (ans1 != ans2) {
				System.out.println("出错了");
			}
		}
		System.out.println("功能测试结束");

		System.out.println("性能测试开始");
		N = 50000;
		P = 2000000000;
		W = 1000000;
		int[] p = randomArray(N, P);
		int[] w = randomArray(N, W);
		System.out.println("房间个数 : " + N);
		System.out.println("位置范围 : " + P);
		System.out.println("价值范围 : " + W);
		long start = System.currentTimeMillis();
		maxMoney2(N, p, w);
		long end = System.currentTimeMillis();
		System.out.println("运行时间 : " + (end - start) + " 毫秒");
		System.out.println("性能测试结束");

	}

}
