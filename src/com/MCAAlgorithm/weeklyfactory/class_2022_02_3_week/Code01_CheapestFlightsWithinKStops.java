package com.MCAAlgorithm.weeklyfactory.class_2022_02_3_week;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

// 测试链接 : https://leetcode.com/problems/cheapest-flights-within-k-stops/
public class Code01_CheapestFlightsWithinKStops {

	// 方法1类似宽度优先遍历
	// n，城市数量，0...n-1
	// flights = { {0,7,100},  {15,3,300} .... }
	// src -> 源点
	// dst -> 目标点
	// k -> 最多能中转几次
	public static int findCheapestPrice1(int n, int[][] flights, int src, int dst, int k) {
		
		// 图
		// 0 :  {1, 500}   {7, 20}    {13, 70}
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<int[]>());
		}
		for (int[] line : flights) {
			// [0, 7 , 200]
			// 0 : {    {7, 200}   }
			graph.get(line[0]).add(new int[] { line[1], line[2] });
		}
		// distance[i] :  从源点src出发的情况下，到达i号点，最少费用是多少
		int[] cost = new int[n];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[src] = 0;
		HashMap<Integer, Integer> curMap = new HashMap<>();
		curMap.put(src, 0);
		for (int i = 0; i <= k; i++) {
			HashMap<Integer, Integer> nextMap = new HashMap<>();
			for (Entry<Integer, Integer> entry : curMap.entrySet()) {
				// A -> from  100
				// from -> ???? 
				int from = entry.getKey();
				int preCost = entry.getValue();
				for (int[] line : graph.get(from)) { //把from能跳到下一个点都拿到
					int to = line[0];
					int curCost = line[1];
					cost[to] = Math.min(cost[to], preCost + curCost); //A到from是100，from到下一个节点是curCost
					nextMap.put(to,
							Math.min(nextMap.getOrDefault(to, Integer.MAX_VALUE),
									preCost + curCost)); //下一层放入，并且把代价更新为最小值，下一层节点最低代价要保证
				}
			}
			curMap = nextMap;
		}
		return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
	}

	// Bellman Ford，因为Dijstra算法无法处理负数路径的问题
	// 快！
	public static int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
		int[] cost = new int[n];
		Arrays.fill(cost, Integer.MAX_VALUE);
		cost[src] = 0;
		for (int i = 0; i <= k; i++) {
			int[] next = Arrays.copyOf(cost, n); //拷贝出新的cost
			for (int[] f : flights) { //考察每一个航班
				if (cost[f[0]] != Integer.MAX_VALUE) { //f[0]是from， f[1]是到达的点， f[2】是消耗，先考察from到底能不能用，
					next[f[1]] = Math.min(next[f[1]], cost[f[0]] + f[2]); //更新到达下一个航班点的消耗
				}
			}
			cost = next; //广播k次cost
		}
		return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
	}

}
