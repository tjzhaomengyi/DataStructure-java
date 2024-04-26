package com.MCAAlgorithm.weeklyfactory.class_2023_03_5_week;

import java.util.ArrayList;
import java.util.Arrays;

// 来自谷歌
// 给你一棵 n 个节点的树（连通无向无环的图）
// 节点编号从 0 到 n - 1 且恰好有 n - 1 条边
// 给你一个长度为 n 下标从 0 开始的整数数组 vals
// 分别表示每个节点的值
// 同时给你一个二维整数数组 edges
// 其中 edges[i] = [ai, bi] 表示节点 ai 和 bi 之间有一条 无向 边
// 一条 好路径 需要满足以下条件：
// 开始节点和结束节点的值 相同 。
// 开始节点和结束节点中间的所有节点值都 小于等于 开始节点的值
//（也就是说开始节点的值应该是路径上所有节点的最大值）
// 请你返回不同好路径的数目
// 注意，一条路径和它反向的路径算作 同一 路径
// 比方说， 0 -> 1 与 1 -> 0 视为同一条路径。单个节点也视为一条合法路径
// 测试链接 : https://leetcode.cn/problems/number-of-good-paths/
public class Code05_NumberOfGoodPaths {

	//这题太难想了
	//       2
	//  1          3
	//2   2      2   4
	//             3   2
	//               4

	//例子：
	//                 3a
	//      2b					2c
	//   1d    1e            1f    1g
	//2h   2i 2j 2k                  2l

	// 连接图：{a}{b}{c}{d}{e}{f}{g}{h}{i}{j}{k}{l}
	// 先把节点按照大小排序：degf ijkbclh a,相同的值谁在前面无所谓，邻居比我大的都不连，前四个什么都没做，没法连；
	// 来到i找到比自己小的,合并{d，i}，{e,j}，此时来到k，k找到e，e有一条连接了，所以这个时候产生一条好路径！jek，合并{e,j,k},
	// 来到b，b和{e,j,k}合并{b2,e1,j2,k2},产生三条好路径；b和d集合产生一条好路径，合并{b2,d1,i2,e1,j2,k2}
	// 然后时时刻刻在问，这个集合里最大标签有几个！



	// [1,2]
	// 1点 -> 7
	// 2点 -> 3
	public static int numberOfGoodPaths(int[] vals, int[][] edges) {
		int n = vals.length;
		// 1) 当前这一种，最经典的建图方式
		// 2) 邻接矩阵， N * N, 非常废空间，用于点的数量N不大的时候
		// 3) 链式前向星，固定数组就可以建图，省空间，不用动态结构，实现麻烦！
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}
		// a -> b  b -> a
		// a {b}
		// b {a}
		for (int[] e : edges) {
			graph.get(e[0]).add(e[1]);
			graph.get(e[1]).add(e[0]);
		}
		// 所有节点
		int[][] nodes = new int[n][2];
		for (int i = 0; i < n; i++) {
			// 编号，4号点
			nodes[i][0] = i;
			// 值，4号点的值
			nodes[i][1] = vals[i];
		}
		Arrays.sort(nodes, (a, b) -> a[1] - b[1]);
		UnionFind uf = new UnionFind(n);
		// 标签只有maxIndex数组
		//记录UnionFind中的代表点，如果{7,0,3,9}这个集合7是并查集的代表点maxIndex[7]=3表示：以7做代表点的并查集集合中的最大值是下标3的哪个点的值即arr[3]
		int[] maxIndex = new int[n];//一个二阶传导的数组，从maxIndex传到arr
		for (int i = 0; i < n; i++) {
			maxIndex[i] = i;//并查集初始化后，以自己作为最大点
		}
		// maxCnts不是标签
		// 单纯的最大值次数统计，就是频率统计
		// [0,3,7,9],maxCnts[7]=10,表示7这个序号代表最大值出现了10次
		int[] maxCnts = new int[n];
		Arrays.fill(maxCnts, 1);//初始化，每个并查集初始化的时候，自己这个最大值只有自己一个
		int ans = n;
		// 已经根据值排序了！一定是从值小的点，遍历到值大的点
		for (int[] node : nodes) {
			int curi = node[0];
			int curv = vals[curi];// node[1]
			//连邻居，找cur的集合，find找到自己所在的集合
			int curCandidate = uf.find(curi);
			int curMaxIndex = maxIndex[curCandidate];//我所在集合(curCandidate)的最大值的下标是啥逼玩意
			// 遍历邻居
			for (int nexti : graph.get(curi)) {
				// 邻居值
				int nextv = vals[nexti];
				// 邻居的集合代表点
				int nextCandidate = uf.find(nexti);//我邻居集合的代表点
				//思路：只有当前点比邻居点大才有连接合并的必要，从大往小连接，如果
				if (curCandidate != nextCandidate && curv >= nextv) {//如果我和我邻居是一个集合，求个鸡吧，滚吧，不是同一个才有讨论必要，并且我的值要小于等于邻居的值才有讨论的必要
					// 邻居集合最大值的下标
					int nextMaxIndex = maxIndex[nextCandidate];
					if (curv == vals[nextMaxIndex]) { //思路：什么时候可以构建出新的好路径，想象上面的例子2b自己和[1e,2j,2k]整体构成好路径的例子，只有当前节点是连接点集合的最大值的时候才会连接！！！！！！！！
						//对于并查集内部bej、bek，jek，三条路径，怎么算呢，当前节点是b，maxCnts[curMaxIndex]当前节点的
						//思路：这两个思路太恶心了
						ans += maxCnts[curMaxIndex] * maxCnts[nextMaxIndex];//思路：对于整体来说b和ej、b和ek产生两条新的路径
						maxCnts[curMaxIndex] += maxCnts[nextMaxIndex];//思路：对于内部就是当前并查集最大节点个数 + 邻接并查集最大节点个数
					}
					//思路：这里解决能够构建好路径和合并，也可以构建从大往小的并查集合并的连接
					int candidate = uf.union(curi, nexti);//思路：如果没有好路径构建，curv只要大于等于nextv邻居节点，那么它们就进行union联合的操作
					maxIndex[candidate] = curMaxIndex;//思路：标记上，合并出来的代表点candidate（这个candidate在unionfind不一定是最大点的下表）对应的curMaxIndex（curMaxIndex这个集合里最大值的下标点）！
				}
			}
		}
		return ans;
	}

	public static class UnionFind {
		private int[] parent;
		private int[] size;
		private int[] help;

		public UnionFind(int n) {
			parent = new int[n];
			size = new int[n];
			help = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
				size[i] = 1;
			}
		}

		public int find(int i) {
			int hi = 0;
			while (i != parent[i]) {
				help[hi++] = i;
				i = parent[i];
			}
			for (hi--; hi >= 0; hi--) {
				parent[help[hi]] = i;
			}
			return i;
		}

		public int union(int i, int j) {
			int f1 = find(i);
			int f2 = find(j);
			if (f1 != f2) {
				if (size[f1] >= size[f2]) {
					size[f1] += size[f2];
					parent[f2] = f1;
					return f1;
				} else {
					size[f2] += size[f1];
					parent[f1] = f2;
					return f2;
				}
			}
			return f1;
		}

	}

}
