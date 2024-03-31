package com.MCAAlgorithm.weeklyfactory.class_2022_08_4_week;

// 来自美团
// 8.20笔试
// 题目1
// 小美将要期中考试，有n道题，对于第i道题，
// 小美有pi的几率做对，获得ai的分值，还有(1-pi)的概率做错，得0分。
// 小美总分是每道题获得的分数。
// 小美不甘于此，决定突击复习，因为时间有限，她最多复习m道题，复习后的试题正确率为100%。
// 如果以最佳方式复习，能获得期望最大总分是多少？
// 输入n、m
// 接下来输入n个整数，代表pi%，为了简单期间，将概率扩大了100倍。
// 接下来输入n个整数，代表ai，某道题的分值
// 输出最大期望分值，精确到小数点后2位
// 数据 1m<=n<=50000
// 简单题, 课上提一下解法即可
// 题目2
// 小团在地图上放了3个定位装置，想依赖他们进行定位！
// 地图是一个n*n的棋盘，
// 有3个定位装置(x1,y1),(x2,y2),(x3,y3)，每个值均在[1,n]内。
// 小团在(a,b)位置放了一个信标，
// 每个定位装置会告诉小团它到信标的曼哈顿距离，也就是对于每个点，小团知道|xi-a|+|yi-b|
// 求信标位置，信标不唯一，输出字典序最小的。
// 输入n，然后是3个定位装置坐标，
// 最后是3个定位装置到信标的曼哈顿记录。
// 输出最小字典序的信标位置。
// 1 <= 所有数据值 <= 50000
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//以某个点按照其x，y坐标为半径，r=min（x，y），然后以这个半径r圆周上的某个点进行bfs看看到B的曼哈顿距离仍然是17（假设），然后看这个点到A和C的距离是不是也是17
public class Code03_FindPosition {

	public static int[] find(int n, 
			int[] a, int[] b, int[] c,
			int ad, int bd, int cd) { //ad、bd、cd是之间的满曼哈顿距离
		
		int[] x1 = null;
		int r1 = Integer.MAX_VALUE;
		int[] x2 = null;
		int r2 = 0;
		int[] x3 = null;
		int r3 = 0;
		//根据距离把要使用的r找出来
		if (ad < r1) {
			x1 = a;
			r1 = ad;
			x2 = b;
			r2 = bd;
			x3 = c;
			r3 = cd;
		}
		if (bd < r1) {
			x1 = b;
			r1 = bd;
			x2 = a;
			r2 = ad;
			x3 = c;
			r3 = cd;
		}
		if (cd < r1) {
			x1 = c;
			r1 = cd;
			x2 = a;
			r2 = ad;
			x3 = b;
			r3 = bd;
		}
		// x1 r1     x2 r2 x3 r3
		int[] cur = { x1[0] - r1, x1[1] };
		Queue<int[]> queue = new LinkedList<>();
		HashSet<String> visited = new HashSet<>();
		ArrayList<int[]> ans = new ArrayList<>();
		queue.add(cur);
		visited.add(cur[0] + "_" + cur[1]);
		
		while (!queue.isEmpty()) {
			// cur x1为圆心，r1为半径的圆周上
			cur = queue.poll();
			if (
					//如果不越界，并且距离为r2，也是r3，赶紧放入候选
					cur[0] >= 1 && cur[0] <= n 
					&& 
					cur[1] >= 1 && cur[1] <= n 
					&& 
					distance(cur[0], cur[1], x2) == r2
					&& 
					distance(cur[0], cur[1], x3) == r3) {
				ans.add(cur);
			}
			if (ans.size() == 2) { //结论：围着小半径找的时候一旦有两个，肯定就找全了，拿着比较去就行了
				break;
			}
			add(cur[0] - 1, cur[1] - 1, x1, r1, queue, visited);
			add(cur[0] - 1, cur[1], x1, r1, queue, visited);
			add(cur[0] - 1, cur[1] + 1, x1, r1, queue, visited);
			add(cur[0], cur[1] - 1, x1, r1, queue, visited);
			add(cur[0], cur[1] + 1, x1, r1, queue, visited);
			add(cur[0] + 1, cur[1] - 1, x1, r1, queue, visited);
			add(cur[0] + 1, cur[1], x1, r1, queue, visited);
			add(cur[0] + 1, cur[1] + 1, x1, r1, queue, visited);
		}
		if (ans.size() == 1 
				|| ans.get(0)[0] < ans.get(1)[0]
				|| (ans.get(0)[0] == ans.get(1)[0] && ans.get(0)[1] < ans.get(1)[1])) {
			return ans.get(0);
		} else {
			return ans.get(1);
		}
	}

	public static void add(int x, int y, 
			int[] c, int r, 
			Queue<int[]> queue, HashSet<String> visited) {
		String key = x + "_" + y;
		if (distance(x, y, c) == r && !visited.contains(key)) {
			queue.add(new int[] { x, y });
			visited.add(key);
		}
	}

	public static int distance(int x, int y, int[] c) {
		return Math.abs(x - c[0]) + Math.abs(y - c[1]);
	}

}
