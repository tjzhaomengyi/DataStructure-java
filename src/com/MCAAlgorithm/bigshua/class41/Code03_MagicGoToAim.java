package com.MCAAlgorithm.bigshua.class41;

import java.util.PriorityQueue;

// 来自 网易互娱
// N个结点之间，表世界存在双向通行的道路，里世界存在双向通行的传送门.
// 若走表世界的道路，花费一分钟.
// 若走里世界的传送门，不花费时间，但是接下来一分钟不能走传送门.
// 输入： T为测试用例的组数，对于每组数据:
// 第一行：N M1 M2 N代表结点的个数1到N
// 接下来M1行 每行两个数，u和v，表示表世界u和v之间存在道路
// 接下来M2行 每行两个数，u和v，表示里世界u和v之间存在传送门
// 现在处于1号结点，最终要到达N号结点，求最小的到达时间 保证所有输入均有效，不存在环等情况
//技巧: BFS算法 繁衍题:Dijtra算法简化
public class Code03_MagicGoToAim {

	// 城市编号从0开始，编号对应0~n-1
	// roads[i]是一个数组，表示i能走路达到的城市有哪些，每条路都花费1分钟
	// gates[i]是一个数组，表示i能传送达到的城市有哪些
	// 返回从0到n-1的最少用时
	public static int fast(int n, int[][] roads, int[][] gates) {
		int[][] distance = new int[2][n];
		// 因为从0开始走，所以distance[0][0] = 0, distance[1][0] = 0
		// distance[0][i] -> 0 : 前一个城市到达i，是走路的方式, 最小代价，distance[0][i]
		// distance[1][i] -> 1 : 前一个城市到达i，是传送的方式, 最小代价，distance[1][i]
		for (int i = 1; i < n; i++) {
			distance[0][i] = Integer.MAX_VALUE;
			distance[1][i] = Integer.MAX_VALUE;
		}
		// 小根堆，根据距离排序，距离小的点，在上！
		PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.cost - b.cost);
		heap.add(new Node(0, 0, 0));
		boolean[][] visited = new boolean[2][n];//走过记录别忘了
		while (!heap.isEmpty()) {
			Node cur = heap.poll();
			if (visited[cur.preTransfer][cur.city]) {
				continue;
			}
			visited[cur.preTransfer][cur.city] = true;
			// 走路的方式
			for (int next : roads[cur.city]) {
				//技巧:这里要注意如果到next距离变小了，如何处理？下面三行给出解释
				if (distance[0][next] > cur.cost + 1) { //之前收集的距离大于当前的距离
					distance[0][next] = cur.cost + 1; //用当前的距离更新之前到next的距离
					heap.add(new Node(0, next, distance[0][next]));//然后往堆里塞这个到next的小距离
				}
			}
			// 传送的方式
			if (cur.preTransfer == 0) { //之前到的方式是走路，就可以进行传送
				for (int next : gates[cur.city]) {
					if (distance[1][next] > cur.cost) {
						distance[1][next] = cur.cost;
						heap.add(new Node(1, next, distance[1][next]));
					}
				}
			}
		}
		return Math.min(distance[0][n - 1], distance[1][n - 1]);//要么到n-1走路，要么到n-1开始的时候是传送，二选一
	}

	public static class Node {
		public int preTransfer; //前一个位置到当前位置的方式
		public int city; //当前城市
		public int cost; //之前到达当前城市的代价

		public Node(int a, int b, int c) {
			preTransfer = a;
			city = b;
			cost = c;
		}
	}

}