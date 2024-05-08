package com.MCAAlgorithm.weeklyfactory.class_2023_07_1_week;

import java.util.Arrays;

// 小扣在探索丛林的过程中，无意间发现了传说中"落寞的黄金之都"
// 而在这片建筑废墟的地带中，小扣使用探测仪监测到了存在某种带有「祝福」效果的力场
// 经过不断的勘测记录，小扣将所有力场的分布都记录了下来
// forceField[i] = [x,y,side] 
// 表示第 i 片力场将覆盖以坐标 (x,y) 为中心，边长为 side 的正方形区域。
// 若任意一点的 力场强度 等于覆盖该点的力场数量
// 请求出在这片地带中 力场强度 最强处的 力场强度
// 注意：力场范围的边缘同样被力场覆盖。
// 测试链接 : https://leetcode.cn/problems/xepqZ5/
public class Code03_StrongestForceField {

	//思路：有点难这题两步整理比较烦人，整体思路就是一个二维差分，四周加一圈（a,b)(c,d)范围内+4，（a,b）+4, (a,d+1)-4,(c+1,b)-4,(c+1,d+1)+4,最后更新的每个结果点= 自己的上 + 自己的左 - 自己的左上角
	public static int fieldOfGreatestBlessing(int[][] fields) {
		int n = fields.length;
		// n : 矩形的个数，所以有 2*n个坐标
		long[] xs = new long[n << 1];
		long[] ys = new long[n << 1];
		for (int i = 0, k = 0, p = 0; i < n; i++) {
			long x = fields[i][0];
			long y = fields[i][1];
			long r = fields[i][2];
			//优化：把原始坐标系放大，位置关系不变（原始点是0.5,变长是1，如果是（0,0）,转成）
			xs[k++] = (x << 1) - r;
			xs[k++] = (x << 1) + r;
			ys[p++] = (y << 1) - r;
			ys[p++] = (y << 1) + r;
		}

		//优化：如果距离过大，前面只有100，200，后面他妈的搞了100万，玩蛋去吧，收集起来排序就完了，减少二维差分的空间
		int sizex = sort(xs);//把x方向和y方向排序，然后进行离散化
		int sizey = sort(ys);
		int[][] diff = new int[sizex + 2][sizey + 2];
		for (int i = 0, a, b, c, d; i < n; i++) {
			long x = fields[i][0];
			long y = fields[i][1];
			long r = fields[i][2];
			//左上角点
			a = rank(xs, (x << 1) - r, sizex);
			b = rank(ys, (y << 1) - r, sizey);
			//右下角点
			c = rank(xs, (x << 1) + r, sizex);
			d = rank(ys, (y << 1) + r, sizey);
			set(diff, a, b, c, d);
		}
		int ans = 0;
		for (int i = 1; i < diff.length; i++) {
			for (int j = 1; j < diff[0].length; j++) {
				diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1];
				ans = Math.max(ans, diff[i][j]);
			}
		}
		return ans;
	}

	public static int sort(long[] nums) {
		Arrays.sort(nums);
		int size = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[size - 1]) {
				nums[size++] = nums[i];
			}
		}
		return size;
	}

	public static int rank(long[] nums, long v, int size) {
		int l = 0;
		int r = size - 1;
		int m, ans = 0;
		while (l <= r) {
			m = (l + r) / 2;
			if (nums[m] >= v) {
				ans = m;
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return ans + 1;
	}

	// 二维差分
	public static void set(int[][] diff, int a, int b, int c, int d) {
		diff[a][b] += 1;
		diff[c + 1][d + 1] += 1;
		diff[c + 1][b] -= 1;
		diff[a][d + 1] -= 1;
	}

}
