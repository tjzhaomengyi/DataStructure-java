package com.MCAAlgorithm.weeklyfactory.class_2022_11_3_week;

// n对情侣坐在连续排列的 2n 个座位上，想要牵到对方的手
// 人和座位由一个整数数组 row 表示，其中 row[i] 是坐在第 i 个座位上的人的ID
// 情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2n-2, 2n-1)
// 返回 最少交换座位的次数，以便每对情侣可以并肩坐在一起
// 每次交换可选择任意两人，让他们站起来交换座位
// 测试链接 : https://leetcode.cn/problems/couples-holding-hands/
public class Code02_CouplesHoldingHands {


	//多少年的下标循环怼没有题了【这道题有点擦边，但是没有用上】
	// ，终于来了，如果一个情侣一个在奇数位，一个在偶数位，这俩成了一个闭环
	// BACABCDEED ，这里面有两个环 ABC 和 DE 这两部分完全没有必要插在一起交换，使用并查集找到这两个环
	// {A} {B} {C} {D} {E}
	// {A}遍历图中到第四个A已经发现{ABC}在一个集合中了，然后发现{DE}集合
	// 思路：情侣们组成环来查看交换几次？
	// 数学结论：ACABBC，3-1交换两次，一个环里如果有m对情侣，交换m-1次，具体证明略。。。。。
	//
	public int minSwapsCouples(int[] row) {
		// n人数，偶数
		int n = row.length;
		// n/2 
		// 0 1 -> 0 0
		// 4 5 -> 2 2
		UnionFind uf = new UnionFind(n / 2);
		for (int i = 0; i < n; i += 2) { //这里是每对每对这样的情侣来看，看了情侣的第一个第二个就不用看了，看看这一对的情侣"代表"和谁挨着就行了
			uf.union(row[i] / 2, row[i + 1] / 2); //相邻的仍在一起
		}
		return n / 2 - uf.sets(); //数学结论：这里又做了下数学，有对情侣减去并查集个数，一个并查集就要交换一次，按照上面的数学结论不写这个推导结果也行
	}

	public static class UnionFind {
		public int[] father;
		public int[] size;
		public int[] help;
		public int sets;

		public UnionFind(int n) {
			father = new int[n];
			size = new int[n];
			help = new int[n];
			for (int i = 0; i < n; i++) {
				father[i] = i;
				size[i] = 1;
			}
			sets = n;
		}

		private int find(int i) {
			int hi = 0;
			while (i != father[i]) {
				help[hi++] = i;
				i = father[i];
			}
			while (hi != 0) {
				father[help[--hi]] = i;
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
				sets--;
			}
		}

		public int sets() {
			return sets;
		}

	}

}
