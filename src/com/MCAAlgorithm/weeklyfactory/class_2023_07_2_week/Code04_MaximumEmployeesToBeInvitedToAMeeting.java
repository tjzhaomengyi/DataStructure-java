package com.MCAAlgorithm.weeklyfactory.class_2023_07_2_week;

// 一个公司准备组织一场会议，邀请名单上有 n 位员工
// 公司准备了一张 圆形 的桌子，可以坐下 任意数目 的员工
// 员工编号为 0 到 n - 1 。每位员工都有一位 喜欢 的员工
// 每位员工 当且仅当 他被安排在喜欢员工的旁边，他才会参加会议
// 每位员工喜欢的员工 不会 是他自己。
// 给你一个下标从 0 开始的整数数组 favorite
// 其中 favorite[i] 表示第 i 位员工喜欢的员工。请你返回参加会议的 最多员工数目
// 测试链接 : https://leetcode.cn/problems/maximum-employees-to-be-invited-to-a-meeting/
public class Code04_MaximumEmployeesToBeInvitedToAMeeting {

	//数学结论：肯定有一个环，不能有两个环，有两个环就会出现一个人喜欢两个人；如果核心环有两个人以上，只能摆核心链里面的人
	// 思路：使用拓扑排序，把不在环里面的节点擦掉，擦到删不掉为止.跑一遍拓扑排序，就可以把不是环上的点都删除掉了！！如果环内有两个点，可以都搞出来
	public static int maximumInvitations(int[] favorite) {
		int[][] loved = beLoved(favorite);
		int[] degree = degree(loved); //生成每个点的入度
		int n = favorite.length;
		// 跑拓扑排序
		int[] queue = new int[n];
		int l = 0;
		int r = 0;
		for (int i = 0; i < n; i++) {
			if (degree[i] == 0) {
				queue[r++] = i;
			}
		}
		boolean[] zeroVisited = new boolean[n];
		while (l < r) {
			int cur = queue[l++];
			zeroVisited[cur] = true;
			if (--degree[favorite[cur]] == 0) {
				queue[r++] = favorite[cur];
			}
		}
		boolean[] cycleVisited = new boolean[n];
		int arrangeTwoCycle = 0;
		int arrangeMoreCycle = 0;
		for (int i = 0; i < n; i++) {
			if (!zeroVisited[i] && !cycleVisited[i]) {
				cycleVisited[i] = true;
				if (favorite[favorite[i]] == i) {//这里的意思就是现在的环大小为2
					cycleVisited[favorite[i]] = true;
					//这两个人假设是x和i，i去寻找不在环上最长的链，x去寻找不在环上最长的链
					arrangeTwoCycle += maxFollow(i, zeroVisited, loved) + maxFollow(favorite[i], zeroVisited, loved);
				} else {
					int cur = favorite[i];
					int curAns = 1;
					while (cur != i) {
						cycleVisited[cur] = true;
						curAns++;
						cur = favorite[cur];
					}
					arrangeMoreCycle = Math.max(arrangeMoreCycle, curAns);
				}
			}
		}
		return Math.max(arrangeTwoCycle, arrangeMoreCycle);
	}

	// 生成被喜欢表
	// favorite[3] = 7
	// favorite[5] = 7
	// size[
	public static int[][] beLoved(int[] favorite) {
		int n = favorite.length;
		int[] size = new int[n];
		// size[7] : 有多少人喜欢7
		for (int love : favorite) {
			size[love]++;
		}
		// 7 : 3 5
		// 9 : 0 1 4
		int[][] loved = new int[n][];
		for (int i = 0; i < n; i++) {
			loved[i] = new int[size[i]];
		}
		for (int i = 0; i < n; i++) {
			loved[favorite[i]][--size[favorite[i]]] = i;
		}
		return loved;
	}

	// 求每个点的入度
	public static int[] degree(int[][] loved) {
		int n = loved.length;
		int[] degree = new int[n];
		for (int i = 0; i < n; i++) {
			degree[i] = loved[i].length;
		}
		return degree;
	}

	// cur不在环上的追随者链条最大长度,这些点都是拓扑排序中被删掉的点
	public static int maxFollow(int cur, boolean[] zeroed, int[][] from) {
		if (from[cur].length == 0) {
			return 1;
		}
		int ans = 0;
		for (int pre : from[cur]) {
			if (zeroed[pre]) {
				ans = Math.max(ans, maxFollow(pre, zeroed, from)); //递归往上找，因为可能是不同的链
			}
		}
		return ans + 1;
	}

}
