package com.MCAAlgorithm.weeklyfactory.class_2023_05_3_week;

import java.util.ArrayList;


// 难题：讲了一个多小时，最低公共祖先问题 + tarjan + dfs + 树形dp，代码很短，但是每一步都是一环套一环，tarjan算法一个非常深的使用，左神太强了
// 现有一棵无向、无根的树，树中有 n 个节点，按从 0 到 n - 1 编号
// 给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，
// 其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条边。
// 每个节点都关联一个价格。给你一个整数数组 price ，其中 price[i] 是第 i 个节点的价格。
// 给定路径的 价格总和 是该路径上所有节点的价格之和。
// 另给你一个二维整数数组 trips ，其中 trips[i] = [starti, endi] 表示
// 从节点 starti 开始第 i 次旅行，并通过任何你喜欢的路径前往节点 endi 。
// 在执行第一次旅行之前，你可以选择一些 非相邻节点 并将价格减半。
// 返回执行所有旅行的最小价格总和。
// 测试链接 : https://leetcode.cn/problems/minimize-the-total-price-of-the-trips/
public class Code05_MinimizeTheTotalPriceOfTheTrips {


	// 思路：1、题目描述类似参加聚会快乐最大值问题（相邻节点不能同时减半），这个改成了最小，
	// 	2、把问题转化成，图的节点有次数，有价格，如何设计让消耗最小
	//  3、优化：如何把树的词频和价值，决定每个点到底几个词频？说白了如何处理问题描述每个节点的词频数量？
	//  4、数学结论：使用tarjan + 并查集标记解决如下问题
	//   思路：【这道题就难在这里，如何通过查询找到每个节点要走过的次数！】如何解决3的优化，有一条路径是a到b，从a到b的公共祖先是谁？下图a和b的公共祖先是t，把a和b设置词频位1，t节点设置-1词频，t的父节点设置词频-1
	//    从上往下跑汇报每个点的词频， ab分别想s和e汇报词频，s和e向t汇报，t再向f汇报词频，整体更新完词频结果，按照这个标记和汇报过程，最后得到每个点的词频统计
	//       f         f1-1
	//    t          t2-1
	//  s   e      s1  e1
	// a1    b1   a1     b1


	// trips : a,b   c,k   s,t ....用这个去生成那个两两节点的最低公共父节点
	// x -> y  x : y  y : x
	public static int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		ArrayList<ArrayList<int[]>> queries = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
			queries.add(new ArrayList<>());//生成问题数组
		}
		for (int[] edge : edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}
		int m = trips.length;
		int[] lcs = new int[m];//答案数组
		for (int i = 0; i < m; i++) {
			// a a
			if (trips[i][0] == trips[i][1]) {
				lcs[i] = trips[i][0];//滚滚滚滚，捣乱的
			} else { //如果出发节点和结束节点不一样，
				// a b
				// a [b,i]
				// b [a,i]
				queries.get(trips[i][0]).add(new int[] { trips[i][1], i });//双向结果都生成
				queries.get(trips[i][1]).add(new int[] { trips[i][0], i });//双向询问都生成
			}
		}
		UnionFind uf = new UnionFind(n);//并查集准备好
		int[] fathers = new int[n];
		tarjan(graph, 0, -1, uf, queries, fathers, lcs);//跑tarjan，得到最低公共祖先
		int[] cnts = new int[n];
		//思路：参考大体思路上那个关于每个节点如何计算经过次数的
		for (int i = 0; i < m; i++) {
			// a -> b lcs[i] -> father[lcs[i]]
			cnts[trips[i][0]]++;
			cnts[trips[i][1]]++;
			cnts[lcs[i]]--;//最低公共祖先--
			if (fathers[lcs[i]] != -1) {//最低公共祖先的父节点还得--
				cnts[fathers[lcs[i]]]--;
			}
		}
		dfs(graph, 0, -1, cnts);//跑一个dfs得到每个点经过次数，从下面节点往上面的节点进行汇总
		int[] ans = dp(graph, 0, -1, cnts, price);//树形dp
		return Math.min(ans[0], ans[1]);//头节点减半的情况下，和头节点不减半的情况下选最小值
	}

	// 思路：使用tarjan算法求两个节点的最低公共祖先,这段看稿纸的截图，讲解基本都在稿纸上
	// 整张图 : graph
	// 当前来到cur点，父节点father
	// uf : 并查集，一开始所有节点，独立的结合
	// {a} {b} {c} .....
	// uf.union(a,b) : a所在的集合，和b所在的集合，合并
	// uf.setTag(a,x) : a所在的集合，整个集合打上tag x！
	// uf.getTag(a) : a所在的集合，tag是啥
	// ====
	// queries查询描述，a到b的公共祖先，a到k的公共祖先...x到x的公共祖先（被洗掉），query表示一个点和哪些点有查询，以及答案填写的位置
	// 这里如果在查询的时候某个节点还没有打tag可以先跳过，因为在后面的反向查询时候，仍然可以查到
	// {a,b}, {a,k}, {f,t}, {x, x}(被洗掉)
	//  0      1      2   
	// a(0) : {b, 0} 、{k, 1}
	// b : {a, 0}，b和a有一个查询，把答案填写到1位置
	// k : {a, 1} k和a有一个查询，把答案填写到1位置
	// f : {t, 2} f和t有一个查询，把答案填写到2位置
	// t : {f, 2} t和f有一个查询，把答案填写到2位置
	// int[] fathers : 所有节点的父亲节点，填好，顺手就填充了，这样求的时候好吧思路里面那个父节点-1
	// int[] lcs : 答案数组
	public static void tarjan(
			ArrayList<ArrayList<Integer>> graph,
			int cur,
			int father,
			UnionFind uf,
			ArrayList<ArrayList<int[]>> queries,
			int[] fathers,
			int[] lcs) {
		fathers[cur] = father;
		//          cur
		// next1   next2   next1
		for (int next : graph.get(cur)) {
			if (next != father) {
				tarjan(graph, next, cur, uf, queries, fathers, lcs);//跑tarjan算法
				uf.union(cur, next);//cur集合和next所在集合合并
				uf.setTag(cur, cur);//打tag，把自己自己自己的tag打在该集合的tag上！！！！！，这样在uf.getTag(x),就可以得到x
			}
		}
		// 处理cur自己的问题!
		for (int[] query : queries.get(cur)) {
			// query : query[0] cur和它有问题！
			//  query[1] 
			int tag = uf.getTag(query[0]);//找对应有没有tag
			if (tag != -1) { //tag不是-1，塞答案
				lcs[query[1]] = tag;
			}
			//如果没有tag，用query[1]目标进行反查！
		}
	}

	// 思路：dfs深度遍历搞定每个点子节点的个数
	// a -> b a和b的最低公共祖先假设是x，x的父亲假设是f
	// 当初一定做了这件事:
	// cnts[a]++
	// cnts[b]++
	// cnts[x]--
	// cnts[f]--
	public static void dfs(ArrayList<ArrayList<Integer>> graph, int cur, int father, int[] cnts) {
		for (int next : graph.get(cur)) {
			if (next != father) {
				dfs(graph, next, cur, cnts);
				cnts[cur] += cnts[next];//把子节点的个数汇报上来就行
			}
		}
	}


	//思路：1、先看最后收益累加的过程
	// 当前节点来到cur，cur的父节点是father
	// cnts[cur]，当前节点的次数
	// price[cur]，当前节点的价值
	// 你可以把一些节点，价值减半！但是，减半的节点不能相邻!
	// 返回整棵树的最小价值
	// graph : 整棵树
	public static int[] dp(ArrayList<ArrayList<Integer>> graph, 
			int cur, int father, int[] cnts, int[] price) { //树形dp
		// 当前节点价值不减半! 当前节点的价值 : price[cur] * cnts[cur]，不减半的子节点可以减半也可以不减
		int no = price[cur] * cnts[cur];
		// 当前节点价值决定减半! 当前节点的价值 : (price[cur] / 2) * cnts[cur]
		int yes = (price[cur] / 2) * cnts[cur];
		for (int next : graph.get(cur)) {
			if (next != father) {
				int[] nextAns = dp(graph, next, cur, cnts, price);//子节点往下递归
				//不减半获得的收益
				no += Math.min(nextAns[0], nextAns[1]);//后续的决策中可以减半也可以不减半，哪个小获得哪个
				//减半获得到的收益，
				yes += nextAns[0];
			}
		}
		return new int[] { no, yes };//往上返回两种情况的解，当前值不减半，当前值减半
	}

	public static class UnionFind {
		public int[] father;
		public int[] size;
		public int[] tag;
		public int[] help;

		public UnionFind(int n) {
			father = new int[n];
			size = new int[n];
			tag = new int[n];
			help = new int[n];
			for (int i = 0; i < n; i++) {
				father[i] = i;
				size[i] = 1;
				tag[i] = -1;
			}
		}

		public int find(int i) {
			int size = 0;
			while (i != father[i]) {
				help[size++] = i;
				i = father[i];
			}
			while (size > 0) {
				father[help[--size]] = i;
			}
			return i;
		}

		public void union(int i, int j) {
			int fi = find(i);
			int fj = find(j);
			if (fi != fj) {
				if (size[fi] >= size[fj]) {
					father[fj] = fi;
					size[fi] += size[fj];
				} else {
					father[fi] = fj;
					size[fj] += size[fi];
				}
			}
		}

		public void setTag(int i, int t) {
			tag[find(i)] = t;
		}

		public int getTag(int i) {
			return tag[find(i)];
		}

	}

}
