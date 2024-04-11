package com.MCAAlgorithm.weeklyfactory.class_2022_10_1_week;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 力扣数据中心有 n 台服务器，分别按从 0 到 n-1 的方式进行了编号
// 它们之间以「服务器到服务器」点对点的形式相互连接组成了一个内部集群
// 其中连接 connections 是无向的
// 从形式上讲，connections[i] = [a, b] 表示服务器 a 和 b 之间形成连接
// 任何服务器都可以直接或者间接地通过网络到达任何其他服务器。
// "关键连接"是在该集群中的重要连接，也就是说，假如我们将它移除
// 便会导致某些服务器无法访问其他服务器。
// 请你以任意顺序返回该集群内的所有"关键连接"
// 测试链接 : https://leetcode.cn/problems/critical-connections-in-a-network/
// tarjan求桥的经典算法
public class Code01_CriticalConnectionsInANetwork {

	public static int[] dfn = new int[100010];

	public static int[] low = new int[100010];

	public static int dfnCnt = 0;

	//啥也别说了 tarjan算法 +抓口袋
	public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}
		for (List<Integer> edge : connections) {
			graph.get(edge.get(0)).add(edge.get(1));
			graph.get(edge.get(1)).add(edge.get(0));
		}
		Arrays.fill(dfn, 0, n, 0);
		Arrays.fill(low, 0, n, 0);
		dfnCnt = 0;
		List<List<Integer>> ans = new ArrayList<>();
		tarjan(0, -1, graph, ans);
		return ans;
	}

	// tarjan  dfs过程
	// 点的编号是cur，dfn X low X，Tarjan的关键是更新low
	public static void tarjan(int cur, int father,
			ArrayList<ArrayList<Integer>> graph,
			List<List<Integer>> ans) {
		// 第一次来到cur
		// 分配dfn、low序号
		dfn[cur] = low[cur] = ++dfnCnt; //开始的时候把low序号也更新了
		//low值是跑到当前这个点之前，可以联通上的最小序号Math.min(low[cur],dfn[next])
		//      a
		//      b
		// c(3,3) -----d(4,2)
		for (Integer next : graph.get(cur)) {
			if (next != father) { //不去father节点，只跑下级节点
				if (dfn[next] == 0) { // 下级的节点没跑过，就去跑
					tarjan(next, cur, graph, ans);
					low[cur] = Math.min(low[cur], low[next]);//跑完d会回到c，更新c的low，开看是自己的值小还是上面的值小，递归的时候更新
				} else { // 下级的节点跑过了，直接更新low，递归进来的时候也会到这里
					low[cur] = Math.min(low[cur], dfn[next]);//low值之前跑过哪个点最小
				}
			}
		}
		if (low[cur] == dfn[cur] && cur != 0) { //结论：怎么样看能不能扎起口袋，如果low值和dfn序号一样就是口袋
			ans.add(Arrays.asList(father, cur));
		}
	}

}
