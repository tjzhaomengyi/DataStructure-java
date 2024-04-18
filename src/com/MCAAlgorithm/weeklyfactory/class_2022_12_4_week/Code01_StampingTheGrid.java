package com.MCAAlgorithm.weeklyfactory.class_2022_12_4_week;

// 给你一个 m x n 的二进制矩阵 grid
// 每个格子要么为 0 （空）要么为 1 （被占据）
// 给你邮票的尺寸为 stampHeight x stampWidth
// 我们想将邮票贴进二进制矩阵中，且满足以下 限制 和 要求 ：
// 覆盖所有空格子，不覆盖任何被占据的格子
// 可以放入任意数目的邮票，邮票可以相互有重叠部分
// 邮票不允许旋转，邮票必须完全在矩阵内
// 如果在满足上述要求的前提下，可以放入邮票，请返回 true ，否则返回 false
// 测试链接 : https://leetcode.cn/problems/stamping-the-grid/
public class Code01_StampingTheGrid {

	//一道二维差分问题，一个数组上每个范围加减很慢，我们就用差分，一维差分描述：如果在i到j上+5，我们用在原数组的i位置和j+1分别+5 和 -5，每步都这么操作，那么最后
	// 这个数组的每位的前缀和就是所有修改记录的结果
	// 二维差分，给左上坐标和右下坐标。
	// 思路：0、概念：左上累加和数组sum【sum数组多准备1行1列，减少越界判断】，怎么算左上累加和最快？每个位置的更新方式，左边的累加和+上边的累加和-左上的累加和
	// 1 2 1   =>  1 3 4
	// 3 2 1   =>  4 8 10
	// 思路：1、为什么要用左上累加和？在原始数组上这个区间范围做二维差分对应的处理：
	//  生成差分数组diff，二维差分的处理，在（1，1）到（2，2）这个区间上+2，在diff数组中对应位置进行处理，并处理越界问题
	//  0	0	0	0
	//	0	2	0	-2
	//	0   0   0   0
	//	0	-2	0	2
	// 思路：如何处理越界问题，添加第0行和第0列，在添加n行和m列，这样做的目的和一维差分一样，防止差分信息越界，上面的数组变成
	//  0	0	0	0	0	0
	//  0	0	0	0	0	0
	//	0	0	2	0	-2	0
	//	0	0   0   0   0	0
	//	0	0	-2	0	2	0
	//	0	0	0	0	0	0
	// 思路：3、如何使用左上角累加和数组，来求局部区域的累加和
	//  1	2	0						1	3	3
	//  3	0	4     转换得到左上累加和    4	6	10   想得到 0   4 这部分的累加和=13 - 3（0,2位置） - 6（2,0位置） + 1（0,0位置） = 5
	//	2	0 	1						6	8	13         0   1
	public static boolean possibleToStamp(int[][] grid, int h, int w) {
		//思路：整体上就是不使用grid原始数组，
		// 利用sum数组来判断邮票能够粘贴的区域是否为全0状态，如果为全0，使用diff数组粘贴，这样操作的量级最少，而且每次都是该邮票区域性信息的更新和获取
		int n = grid.length;
		int m = grid[0].length;
		// 看见0就做左上角，如果这个区域全是0，就在这个区域做粘贴，一旦这个区域有一个1都不做
		// 准备一个差分数组去做粘贴，看看最后这个区间
		// 左上累加和矩阵
		int[][] sum = new int[n + 1][m + 1];//和上面思路一样多行和列多准备出来
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sum[i + 1][j + 1] = grid[i][j];
			}
		}
		build(sum); //建立左上的数组
		int[][] diff = new int[n + 2][m + 2];// diff要多准备两行/列
		for (int a = 1, c = a + h - 1; c <= n; a++, c++) {
			for (int b = 1, d = b + w - 1; d <= m; b++, d++) {
				//（a，b） 根据邮票大小计算出右下角-> (c,d)
				if (empty(sum, a, b, c, d)) { //如果这个区域的累加和是0，就可以贴邮票
					set(diff, a, b, c, d); //在差分数组中更新配置
				}
			}
		}
		build(diff); //统计差分数组去，得到最终更新完的粘贴状态
		for (int i = 0; i < n; i++) { //根据grid数组对应位置
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 0 && diff[i + 1][j + 1] == 0) { //如果原始数组是0，这个位置的差分数组
					return false;
				}
			}
		}
		return true;
	}

	//建立左上角数组，sum数组
	public static void build(int[][] m) {
		for (int i = 1; i < m.length; i++) {
			for (int j = 1; j < m[0].length; j++) {
				m[i][j] += m[i - 1][j] + m[i][j - 1] - m[i - 1][j - 1];//左 + 上 - 左上
			}
		}
	}

	//借助sum数组判断（a，b）（c，d）这个区域是否是全白
	public static boolean empty(int[][] sum, int a, int b, int c, int d) {
		return sum[c][d] - sum[c][b - 1] - sum[a - 1][d] + sum[a - 1][b - 1] == 0;
	}

	// (3,4) (8,9)
	// 3,4 +1
	//diff差分数组进行更新
	public static void set(int[][] diff, int a, int b, int c, int d) {
		diff[a][b] += 1;
		diff[c + 1][d + 1] += 1;
		diff[c + 1][b] -= 1;
		diff[a][d + 1] -= 1;
	}

}
