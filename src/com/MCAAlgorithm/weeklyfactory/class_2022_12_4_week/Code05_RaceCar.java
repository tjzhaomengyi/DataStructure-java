package com.MCAAlgorithm.weeklyfactory.class_2022_12_4_week;

import java.util.PriorityQueue;

// 你的赛车可以从位置 0 开始，并且速度为 +1 ，在一条无限长的数轴上行驶
// 赛车也可以向负方向行驶
// 赛车可以按照由加速指令 'A' 和倒车指令 'R' 组成的指令序列自动行驶。
// 当收到指令 'A' 时，赛车这样行驶：
// position += speed
// speed *= 2
// 当收到指令 'R' 时，赛车这样行驶：
// 如果速度为正数，那么speed = -1
// 否则 speed = 1
// 当前所处位置不变。
// 例如，在执行指令 "AAR" 后，赛车位置变化为 0 --> 1 --> 3 --> 3
// 速度变化为 1 --> 2 --> 4 --> -1
// 给你一个目标位置 target ，返回能到达目标位置的最短指令序列的长度。
// 测试链接 : https://leetcode.cn/problems/race-car/
public class Code05_RaceCar {

	// speed=1 -> 在1位置收到A指令，速度变为2，所以从1跳到了 -> 3
	// pos ：  0  1（A） 3（A） 7（A）
	//speed：  1  2	    4     8
	// 收到Return指令，位置不变，把速度反向变-1，如果是负数变成1
	// 思路：来到x的状态是什么，就是一个点的扩点！每个点可能的速度，并且速度会限制在一个范围，这样形成过了一个表
	//  整个解体过程：每个点通过状态借助Dijstra算法到达target
	//  表的优化：正速度表和负速度表，并且把速度拉到档位，这样空间就没有二倍关系了
	public static int racecar1(int target) {
		int maxp = 0; // 开始位置
		int maxs = 1; //速度档位最大多少，不要给速度乘以2啦，用档位号算速度，省到几档
		while (maxp <= target) { // 档位肯定不能超过最远距离
			maxp += 1 << (maxs - 1);  //位置加一下速度
			maxs++;
		}
		//Dijkstra算法
		// 0 : 几倍速
		// 1 : 花费了几步
		// 2 : 当前位置
		PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		boolean[][] positive = new boolean[maxs + 1][maxp + 1];
		boolean[][] negative = new boolean[maxs + 1][maxp + 1];
		heap.add(new int[] { 1, 0, 0 }); //这部就是Dijkstra标准么，以1倍速往下走
		while (!heap.isEmpty()) {
			int[] cur = heap.poll();
			int speed = cur[0];
			int cost = cur[1];
			int position = cur[2];
			if (position == target) {
				return cost;//碰到target马上返回代价，Dijkstra标准
			}
			if (speed > 0) {
				if (positive[speed][position]) { //这个位置和速度DJ过了，直接跳出
					continue;
				}
				positive[speed][position] = true;
				//接下来两个状态继续往后走
				add(speed + 1, cost + 1, position + (1 << (speed - 1)), maxp, heap, positive);
				add(-1, cost + 1, position, maxp, heap, negative);
			} else {
				speed = -speed;
				if (negative[speed][position]) { //这个位置来过了跳走
					continue;
				}
				negative[speed][position] = true;
				add(-speed - 1, cost + 1, position - (1 << (speed - 1)), maxp, heap, negative);
				add(1, cost + 1, position, maxp, heap, positive);
			}
		}
		return -1;
	}

	public static void add(int speed, int cost, int position, int limit, PriorityQueue<int[]> heap,
			boolean[][] visited) {
		if (position >= 0 && position <= limit && !visited[Math.abs(speed)][position]) {
			heap.add(new int[] { speed, cost, position });
		}
	}

	// 动态规划 + 数学，这个解法没用，扔了
	public static int racecar2(int target) {
		int[] dp = new int[target + 1];
		return process(target, dp);
	}

	public static int process(int target, int[] dp) {
		if (dp[target] > 0) {
			return dp[target];
		}
		int steps = 0;
		int speed = 1;
		while (speed <= target) {
			speed <<= 1;
			steps++;
		}
		int ans = 0;
		int beyond = speed - 1 - target;
		if (beyond == 0) {
			ans = steps;
		} else {
			ans = steps + 1 + process(beyond, dp);
			steps--;
			speed >>= 1;
			int lack = target - (speed - 1);
			int offset = 1;
			for (int back = 0; back < steps; back++) {
				ans = Math.min(ans, steps + 1 + back + 1 + process(lack, dp));
				lack += offset;
				offset <<= 1;
			}
		}
		dp[target] = ans;
		return ans;
	}

}
