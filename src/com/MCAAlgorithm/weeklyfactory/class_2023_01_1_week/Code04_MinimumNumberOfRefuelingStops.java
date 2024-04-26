package com.MCAAlgorithm.weeklyfactory.class_2023_01_1_week;

import java.util.PriorityQueue;

// 汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
// 沿途有加油站，每个 station[i] 代表一个加油站，
// 它位于出发位置东面 station[i][0] 英里处，并且有 station[i][1] 升汽油。
// 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。
// 它每行驶 1 英里就会用掉 1 升汽油。
// 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
// 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
// 注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。
// 如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
// 测试链接 : https://leetcode.cn/problems/minimum-number-of-refueling-stops/
public class Code04_MinimumNumberOfRefuelingStops {

	// station里，英里数一定递增！
	//初始油箱20个油，在20会趴窝走不到34，用大根堆依次取油，大根堆就是用来返回的，就是用来怼油的，就是知道未来提前规划好在某些位置加好油
	//  7	9	17	34
	//	5	10	6
	public static int minRefuelStops(int target, int startFuel, int[][] stations) {
		if (startFuel >= target) {
			return 0;
		}
		// 大根堆
		// 维持的是：最值得加油的加油站，有多少油
		// 最值得：加得次数少，跑的还最远
		PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
		int to = startFuel; // 当前车里的油，能达到的位置
		int cnt = 0;
		for (int[] station : stations) {
			int position = station[0];//当前油站的位置
			int fuel = station[1];
			if (to < position) { //到不了当前的加油站
				while (!heap.isEmpty() && to < position) { //大根堆还有油站，并且还没有到当前达油站
					to += heap.poll();
					cnt++;
					if (to >= target) { //这个to一旦超过了target，别算了，直接返回加油次数
						return cnt;
					}
				}
				if (to < position) { //如果到不了当前加油站，
					return -1;
				}
			}
			heap.add(fuel);
		}
		// 最后一个加油站的位置，都达到了 ，还有反悔的机会
		// 但还没有到target
		while (!heap.isEmpty()) {
			to += heap.poll(); //一直加油往前跑
			cnt++;
			if (to >= target) {
				return cnt;
			}
		}
		return -1; //反悔都不行，彻底不行了
	}

}
