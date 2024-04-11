package com.MCAAlgorithm.weeklyfactory.class_2022_10_1_week;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 来自Leetcode周赛
// 魔物了占领若干据点，这些据点被若干条道路相连接，
// roads[i] = [x, y] 表示编号 x、y 的两个据点通过一条道路连接。
// 现在勇者要将按照以下原则将这些据点逐一夺回：
// 在开始的时候，勇者可以花费资源先夺回一些据点，
// 初始夺回第 j 个据点所需消耗的资源数量为 cost[j]
// 接下来，勇者在不消耗资源情况下，
// 每次可以夺回一个和「已夺回据点」相连接的魔物据点，
// 并对其进行夺回
// 为了防止魔物暴动，勇者在每一次夺回据点后（包括花费资源夺回据点后），
// 需要保证剩余的所有魔物据点之间是相连通的（不经过「已夺回据点」）。
// 请返回勇者夺回所有据点需要消耗的最少资源数量。
// 输入保证初始所有据点都是连通的，且不存在重边和自环
// 测试链接 : https://leetcode.cn/problems/s5kipK/
public class Code02_CaptureStrongHold {

	//点双连通分量：一定是在无向图中，指的是存在两个以上集团 断开任何两个点的连线后，各自剩下的部分仍然是强连通图（彼此能够相互叨叨），可以是共享点
	// 点双连同分量的叶节点和非叶节点，以及割点
	// a      b      c
	//   。
	// 。  d       e
	//  。
	//e       f      g
	public static long minimumCost(int[] cost, int[][] roads) {
		int n = cost.length;
		if (n == 1) {
			return cost[0];
		}
		int m = roads.length;
		DoubleConnectedComponents dc = new DoubleConnectedComponents(n, m, roads);
		long ans = 0;
		// dcc {a,b,c} {c,d,e}
		if (dc.dcc.size() == 1) { //只有一个点双连通分量
			ans = Integer.MAX_VALUE;
			for (int num : cost) {
				ans = Math.min(ans, num);
			}
		} else { // 不只一个点双连通分量
			ArrayList<Integer> arr = new ArrayList<>();
			for (List<Integer> set : dc.dcc) {
				int cutCnt = 0;
				int curCost = Integer.MAX_VALUE;
				for (int nodes : set) {
					if (dc.cut[nodes]) { //统计割点数量
						cutCnt++;
					} else {
						curCost = Math.min(curCost, cost[nodes]); //如果不是割点，统计一下代价
					}
				}
				if (cutCnt == 1) { //如果是叶的点双连通分量，考虑进去，如果不是叶的点双连通分量（中间割点形成的双连通分量），不统计
					arr.add(curCost); //可以考虑攻克的
				}
			}
			arr.sort((a, b) -> a - b); //攻克代价小的
			for (int i = 0; i < arr.size() - 1; i++) { //只攻克m-1个即可，根据题意
				ans += arr.get(i);
			}
		}
		return ans;
	}

	public static class DoubleConnectedComponents {
		// 链式前向星建图
		public int[] head;
		public int[] next;
		public int[] to;

		public int[] dfn;
		public int[] low;
		public int[] stack;
		public List<List<Integer>> dcc;
		public boolean[] cut;
		public static int edgeCnt;
		public static int dfnCnt;
		public static int top;
		public static int root;

		public DoubleConnectedComponents(int n, int m, int[][] roads) {
			init(n, m);
			createGraph(roads);
			creatDcc(n);
		}

		//链式前向星的表示方法
		private void init(int n, int m) {
			head = new int[n];
			Arrays.fill(head, -1);
			next = new int[m << 1];
			to = new int[m << 1];
			dfn = new int[n];
			low = new int[n];
			stack = new int[n];
			dcc = new ArrayList<>();
			cut = new boolean[n];
			edgeCnt = 0;
			dfnCnt = 0;
			top = 0;
			root = 0;
		}

		private void createGraph(int[][] roads) {
			for (int[] edges : roads) {
				add(edges[0], edges[1]);
				add(edges[1], edges[0]);
			}
		}

		private void add(int u, int v) {
			to[edgeCnt] = v;
			next[edgeCnt] = head[u];
			head[u] = edgeCnt++;
		}

		private void creatDcc(int n) {
			for (int i = 0; i < n; i++) {
				// 0 1 2 3 n-1
				if (dfn[i] == 0) {
					root = i;
					tarjan(i);
				}
			}
		}

		// 开始遍历得到dfn序号的时候放入栈中，
		//点双连通分量扎口袋过程，如果遍历的到点的low值小于等于当前点的dfn序号，往上回退（都是递归过程），直到发现某个点的low=dfn序号，就是口袋入口
		//然后开始把大于入口的点弹出，但是口袋节点不出，等着它的大哥再把它弹出
		private void tarjan(int x) {
			dfn[x] = low[x] = ++dfnCnt;
			stack[top++] = x;//遍历到谁把谁放入栈中，把小于当前low的值点都弹出形成该点组成点双连通分量
			int flag = 0;
			if (x == root && head[x] == -1) {
				dcc.add(new ArrayList<>());
				dcc.get(dcc.size() - 1).add(x);
			} else {
				// 当前来到的节点是x
				// x {a,b,c}
				for (int i = head[x]; i >= 0; i = next[i]) {
					// y是下级节点
					int y = to[i];
					if (dfn[y] == 0) { // y点没遍历过！
						tarjan(y); //递归tarjan去
						if (low[y] >= dfn[x]) { // 正在扎口袋，如果下级节点的low大于等于x的dfn，x就可以扎起口袋
							flag++; //发现了扎起口袋的支路，并且只要扎起口袋的点就是割点，但是头节点至少要扎起两个口袋才是割点，只有一个口袋的头节点不是割点
							if (x != root || flag > 1) { //收集割点信息
								cut[x] = true;
							}
							List<Integer> curAns = new ArrayList<>();
							// 从栈里一次弹出节点
							// 弹到y停！
							// 弹出的节点都加入集合，x也加入，x不弹出
							for (int z = stack[--top]; z != y; z = stack[--top]) {
								curAns.add(z);
							}
							curAns.add(y);
							curAns.add(x); //但是
							dcc.add(curAns);
						}
						low[x] = Math.min(low[x], low[y]);
					} else { // y点已经遍历过了！
						low[x] = Math.min(low[x], dfn[y]); //调整x的low序号
					}
				}
			}

		}

	}

}
