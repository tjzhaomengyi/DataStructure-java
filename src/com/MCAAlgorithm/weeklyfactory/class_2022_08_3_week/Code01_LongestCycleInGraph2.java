package com.MCAAlgorithm.weeklyfactory.class_2022_08_3_week;

// 给你一个 n 个节点的 有向图 ，节点编号为 0 到 n - 1 ，
// 其中每个节点 至多 有一条出边。
// 图用一个大小为 n 下标从 0 开始的数组 edges 表示，
// 节点 i 到节点 edges[i] 之间有一条有向边。
// 如果节点 i 没有出边，那么 edges[i] == -1 。
// 请你返回图中的 最长 环，如果没有任何环，请返回 -1 。
// 一个环指的是起点和终点是 同一个 节点的路径。
// 测试链接 : https://leetcode.cn/problems/longest-cycle-in-a-graph/
public class Code01_LongestCycleInGraph2 {

	//这题挺好，把Tarjan算法的DFN编号应用了一下
	public int longestCycle(int[] edges) {
		// edges[i] = j  i -> j
		// edges[i] = -1  i X
		int n = edges.length;
		int[] ids = new int[n];
		// 发现的最大环，有几个点！
		int ans = -1;
		for (int from = 0, cnt = 1; from < n; from++) { //cnt是当前节点的分配号，然后用dfn编号来处理，
			if (ids[from] == 0) { //如果发现处理过的点的就不出来
				for (int cur = from, fromId = cnt; cur != -1;  // from固定，让cur去跳，真正遍历的变量是cur，没有边的话cur=-1
						cur = edges[cur]) {
					// from -> -> cur ->cur从from开始跳了
					if (ids[cur] > 0) { //这个有两种情况：这个点是之前处理过的点（以前环的点），这个点是当前环里已经发现的点，怎么知道这个点是新环的还是老环的，看他的fromID，这个好像是在Tarjan算法里面的DFN编号里面讲过
						// 0 -> 1 -> 4->6->1
						//对应编号:(1) (2) (3) (4)跳回去了，就是之前编号过的一旦是
						// 访问过，此时的环，之前遍历过的点
						if (ids[cur] >= fromId) { // 发现有ID，并且当前已经形成了新环，结算一下答案
							ans = Math.max(ans, cnt - ids[cur]);
						}
						// 如果上面的if不成立，老的点，直接跳出
						break;
					}
					ids[cur] = cnt++;
				}
			}
		}
		return ans;
	}

}
