package com.MCAAlgorithm.weeklyfactory.class_2023_02_1_week;

import java.util.TreeSet;

// 给你一个由 n 个正整数组成的数组 nums
// 你可以对数组的任意元素执行任意次数的两类操作
// 如果元素是 偶数 ，除以 2
// 例如，如果数组是 [1,2,3,4]
// 那么你可以对最后一个元素执行此操作使其变成 [1,2,3,2]
// 如果元素是 奇数 ，乘上 2
// 例如，如果数组是 [1,2,3,4] ，那么你可以对第一个元素执行此操作，使其变成 [2,2,3,4]
// 数组的 偏移量 是数组中任意两个元素之间的 最大差值
// 返回数组在执行某些操作之后可以拥有的 最小偏移量
// 测试链接 : https://leetcode.cn/problems/minimize-deviation-in-array/
public class Code01_MinimizeDeviationInArray {

	//弹出一个最大值的，看看能不能/2，让差值变更小。先让奇数抬一抬，可能会让这个值变小，也可能不变，每次拎出来最大值，看看这个最大值能不能/2，看看能不能
	// 让值变小，不停检查
	public int minimumDeviation(int[] nums) {
		// 有序表，小 -> 大 组织
		// 得到集合中最大值 set.last()
		TreeSet<Integer> set = new TreeSet<>();
		for (int num : nums) { //加数组的时候，是奇数的时候先抬高一下，偶数直接加，就是奇数抬高的那下可能搞出来最小差值
			set.add(num % 2 == 0 ? num : num * 2);
		}
		// 答案
		int ans = set.last() - set.first(); // 目前set中最大值-最小值是啥
		//每次最大值是偶数就往下降一点
		while (ans > 0 && set.last() % 2 == 0) { // ans=0，直接返回吧。如果最大值是偶数才继续，如果是奇数没办法往下掉了
			// 最大值
			int max = set.last();
			set.remove(max);
			set.add(max / 2);
			ans = Math.min(ans, set.last() - set.first());
		}
		return ans;
	}

}
