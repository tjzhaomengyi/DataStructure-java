package com.MCAAlgorithm.weeklyfactory.class_2023_05_4_week;

import java.util.Arrays;
import java.util.HashMap;

// 先来一个智力题，来自美团面试题
// 给定n个二维坐标，表示在二维平面的n个点，
// 坐标为double类型，精度最多小数点后两位
// 希望在二维平面上画一个圆，圈住其中的k个点，其他的n-k个点都要在圆外
// 返回一个圆心和半径，表示哪个圆可以圈住其中的k个点
// 坐标和半径都是double类型，最多保留小数点后两位
//思路：如果从某一个点出发到到每个点的距离都不同，两两点连接最垂线，某个点不在这个垂线上就是这个点，但是我们不能这样做，直接在（Xmin到Xmax，Ymin到Ymax）
//  上随机选点，肯定会有这样的点，然后正好让k个点进来，然后以囊括k个点的半径，半径就是这个点到第k个点的距离
//-------------------我是分割线。。。。。--------------------
// 下面是正式题目
// 给你一个整数数组 arr 和一个整数 k
// 现需要从数组中恰好移除 k 个元素
// 请找出移除后数组中不同整数的最少数目
// 测试链接 : https://leetcode.cn/problems/least-number-of-unique-integers-after-k-removals/
public class Code01_LeastNumberOfUniqueAfterRemovals {

	//删词频少的不就完了
	public static int findLeastNumOfUniqueInts(int[] arr, int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int num : arr) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		// 2 : 5次
		// 4 : 9次
		// 7 : 2次
		// 5 : 6次
		int n = map.size();
		int[] cnts = new int[n];
		int i = 0;
		for (int cnt : map.values()) {
			cnts[i++] = cnt;
		}
		// [5, 9, 2, 6]
		// [2, 5, 6, 9]
		Arrays.sort(cnts);
		for (i = 0; i < n; i++) {
			k -= cnts[i];
			if (k <= 0) {
				// 该结束了
				if (k == 0) {
					// i位置词频，彻底耗费完了，如果k<0，说明cnt[i]多于k，这个组字符没有删完
					i++;
				}
				break;
			}
		}
		return n - i;
	}

}
