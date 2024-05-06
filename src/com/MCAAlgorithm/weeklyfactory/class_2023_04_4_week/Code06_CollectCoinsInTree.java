package com.MCAAlgorithm.weeklyfactory.class_2023_04_4_week;

import java.util.ArrayList;

// 给你一个 n 个节点的无向无根树，节点编号从 0 到 n - 1
// 给你整数 n 和一个长度为 n - 1 的二维整数数组 edges ，
// 其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间有一条边。
// 再给你一个长度为 n 的数组 coins ，其中 coins[i] 可能为 0 也可能为 1 ，
// 1 表示节点 i 处有一个金币。
// 一开始，你需要选择树中任意一个节点出发。你可以执行下述操作任意次：
// 收集距离当前节点距离为 2 以内的所有金币，或者 移动到树中一个相邻节点。
// 你需要收集树中所有的金币，并且回到出发节点，请你返回最少经过的边数。
// 如果你多次经过一条边，每一次经过都会给答案加一。
// 测试链接 : https://leetcode.cn/problems/collect-coins-in-a-tree/
public class Code06_CollectCoinsInTree {

	// 砍树 + 拓扑排序，分析
	//数学结论：把金币下面的空点删掉，内部的核心点不管从哪个点开始去拿金币距离都是一样的，内部核心点就是不能直接捡到金币的有效点
	// 思路：跑拓扑排序两遍。结论：入度为1的邻接点是子节点，入度为1的点作为拓扑排序的起始点
	public static int collectTheCoins(int[] coins, int[][] edges) {
		int n = coins.length;
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();//构建图的邻接表
		int[] inDegree = new int[n];
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}
		for (int[] edge : edges) {
			// 8 - 13，双向构建，建立入度表
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
			inDegree[edge[0]]++;
			inDegree[edge[1]]++;
		}
		int[] queue = new int[n];
		int l = 0, r = 0;
		//跑拓扑排序，并且入度为1，且没有金币的点进入队列，那么这些叶子节点就是拓扑排序的起始点，这点和拓扑排序从入度为0的点开始遍历是不一样的
		for (int i = 0; i < n; ++i) {
			if (inDegree[i] == 1 && coins[i] == 0) { //叶节点，并且没有金币，放入队列准备拓扑
				queue[r++] = i;
			}
		}
		while (l < r) {//推进
			int cur = queue[l++];
			for (int next : graph.get(cur)) {
				//只有入度不为1的点，且金币为0的点放入队列，思路：相当于把那些没有金币的叶子支路剪枝，砍树！！！！
				// 其他条件的不放入！相当于碰上金币的话停止！！！！完全消掉了没有金币的影响
				if (--inDegree[next] == 1 && coins[next] == 0) {
					queue[r++] = next;
				}
			}
		}
		//跑有金币的支路
		for (int i = 0; i < n; ++i) {
			if (inDegree[i] == 1 && coins[i] == 1) {//入度为1且有金币的节点进队列
				queue[r++] = i;
			}
		}
		//思路：从金币的叶子节点往上跑。rank大于等于2的节点肯定是核心节点，肯定往返走两次！！！
		int[] rank = new int[n];//每个点的rank值，还是从叶子节点往上跑，
		while (l < r) {
			int cur = queue[l++];
			for (int next : graph.get(cur)) {
				if (--inDegree[next] == 1) { //进队列
					rank[next] = rank[cur] + 1; //next是往上推，rank+1
					queue[r++] = next; //
				}
			}
		}
		int ans = 0;
		for (int[] edge : edges)//思路：rank大于等于2的肯定是核心树的成员，肯定是核心节点的成员
			if (rank[edge[0]] >= 2 && rank[edge[1]] >= 2) {
				ans += 2;
			}
		return ans;
			//另一种思路是，把树砍完（第一次拓扑排序后），进行一次树的遍历也能求出来。
	}

}
