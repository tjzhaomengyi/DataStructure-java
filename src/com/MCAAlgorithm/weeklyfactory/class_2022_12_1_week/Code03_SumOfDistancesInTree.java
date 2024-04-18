package com.MCAAlgorithm.weeklyfactory.class_2022_12_1_week;

import java.util.ArrayList;

// 给定一个无向、连通的树
// 树中有 n 个标记为 0...n-1 的节点以及 n-1 条边 。
// 给定整数 n 和数组 edges ，
// edges[i] = [ai, bi]表示树中的节点 ai 和 bi 之间有一条边。
// 返回长度为 n 的数组 answer ，其中 answer[i] : 
// 树中第 i 个节点与所有其他节点之间的距离之和。
// 测试链接 : https://leetcode.cn/problems/sum-of-distances-in-tree/
public class Code03_SumOfDistancesInTree {

	public int N = 30001;
	public int[] size = new int[N];
	public int[] distance = new int[N];

	// edges : {3,1}、{1,2}、{3,0}

	// 0 : {3}
	// 2 : {1}
	// 3 : {1,0}
	// 1 : {3,2}
	public int[] sumOfDistancesInTree(int n, int[][] edges) {
		//使用邻接表方法，生成图
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}
		for (int[] edge : edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}
		//思路：直接收集每个节点的子树到该节点的距离，从root点开始，默认设置0节点为root节点
		collect(0, -1, graph); //收集所有信息
		int[] ans = new int[n];
		setAns(0, -1, 0, graph, ans);
		return ans;
	}

	// 收集distance和size信息
	// 思路：如果当前节点为x，x的一个子节点为y，现在收集y的子树节点到y的距离累加为y_distance，并且包括y在内y子树包括的节点一共size，那么！
	//  y带领的子树到x的总距离为x_distance = y_distance + size * 1,因为每个节点从y到x又都多加了一个1的距离
	// cur : 当前节点号
	// father : 当前节点的父节点
	// graph : 图
	public void collect(int cur, int father, ArrayList<ArrayList<Integer>> graph) {
		size[cur] = 1; //只有自己
		distance[cur] = 0; // 还没有统计粗来
		// 当前节点 7
		// graph 7 -> {4, 8 , 13}
		for (int next : graph.get(cur)) {
			if (next != father) { //别往上调用哈，临接表是互相建立的
				collect(next, cur, graph); //递归收集，next子树信息，father是cur节点
				distance[cur] += distance[next] + size[next]; //当前节点的距离 distacne[x] = distance[y] + size[y]
				size[cur] += size[next];
			}
		}
	}

	// 这个方法是根据收集的信息得到最终的答案
	// 思路：例子ans[0] = distance[0],但是问题其他点不能像0点一样，如果0的下一级是1号点，ans[1] = 1号点子树的距离之和 + 父节点扔给1节点的所有距离之和
	//   汇聚到某个非根节点的距离 = 该非根子节点距离之和 + 父节点剩下那半边给该节点的距离之和！
	//   该非根节点剩下那半边的距离 = distance[father] - distance[该节点编号] - size[该节点] + 总节点数 - size[该节点的编号]，(不是1子树的节点要到1的节点要迈一步到1)
	//   解析：以1为例子 ans[1] = distance[0] - distance[1] - size[1] + size[0] - size[1],
	//  注意：distance[0] - distance[1] - size[1] 因为distance[0]整体中从1到0的距离是distance[1]+size[1]所以要把这一部分整体都减掉，不要忘掉size[1]
	// cur -> 当前节点！
	// father -> 当前节点的父
	// upDistance -> 父亲扔给cur的第二部分距离！
	// graph -> 图
	// int[] ans 答案填写进ans
	public void setAns(int cur, int father, int upDistance, ArrayList<ArrayList<Integer>> graph, int[] ans) {
		ans[cur] = distance[cur] + upDistance; // 当前节点的答案
		for (int next : graph.get(cur)) {
			if (next != father) {
				// 思路： 下面的长式子就是 ans[cur] - distance[next] - size[next] + size[0] - size[next]  加号左侧部分，
				setAns(next, cur, ans[cur] - distance[next] + size[0] - (size[next] << 1), graph, ans);
			}
		}
	}

}
