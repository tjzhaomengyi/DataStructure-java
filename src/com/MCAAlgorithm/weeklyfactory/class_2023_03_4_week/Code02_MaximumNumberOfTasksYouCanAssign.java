package com.MCAAlgorithm.weeklyfactory.class_2023_03_4_week;

import java.util.Arrays;
import java.util.TreeMap;

// 来自华为
// 给你 n 个任务和 m 个工人
// 每个任务需要一定的力量值才能完成
// 需要的力量值保存在下标从 0 开始的整数数组 tasks 中
// 第 i 个任务需要 tasks[i] 的力量才能完成
// 每个工人的力量值保存在下标从 0 开始的整数数组 workers 中
// 第 j 个工人的力量值为 workers[j]
// 每个工人只能完成 一个 任务
// 且力量值需要 大于等于 该任务的力量要求值, 即 workers[j] >= tasks[i]
// 除此以外，你还有 pills 个神奇药丸
// 可以给 一个工人的力量值 增加 strength
// 你可以决定给哪些工人使用药丸
// 但每个工人 最多 只能使用 一片 药丸
// 给你下标从 0 开始的整数数组tasks 和 workers 以及
// 两个整数 pills 和 strength ，请你返回 最多 有多少个任务可以被完成。
// 测试链接 : https://leetcode.cn/problems/maximum-number-of-tasks-you-can-assign/
public class Code02_MaximumNumberOfTasksYouCanAssign {

	// 思路：1、最多完成多少个任务0..n(task数量）
	//  2、n个任务，如果只要求其中k个任务完成，选最小工作量的k个任务（排序）
	//  3、n个任务，如果只要求k个任务完成，让能力最大的k个工人去做 （排序）
	//  如果6个人，10个任务，返回最大值就完了，完不成。
	// 时间复杂度O(N * (logN)平方)
	public static int maxTaskAssign1(int[] tasks, int[] workers, int pills, int strength) {
		// l......r
		// 0 n
		int l = 0;
		int r = tasks.length;
		int m, ans = 0;
		// n个任务，如果只要求其中K个任务完成，选最小工作量的K个任务
		// O(n * log n)
		Arrays.sort(tasks);
		// n个任务，如果只要求其中K个任务完成，让能力最大的K个工人去执行
		// O(m * log m)
		Arrays.sort(workers);
		// 0....n
		// log n * n * log n
		while (l <= r) {
			// m是，当前要完成的任务数量
			m = (l + r) / 2;
			// tasks[0...m-1] //task选小的
			// workers [ m个人]//工人选大的
			// yeah1，至少要吃多少药！
			if (yeah1(tasks, 0, m - 1, workers, workers.length - m, workers.length - 1, strength) <= pills) {
				ans = m;
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return ans;
	}

	//任务：【3 6 7 9】
	//人：【6 6 6 7】
	// 药丸3，能力为6的人不吃药丸尽量完成比自己小的且最近的
	public static int yeah1(int[] tasks, int tl, int tr, int[] workers, int wl, int wr, int strength) {
		if (wl < 0) { //工人的左边界小于0了，代表工人数量不够完成这些任务
			return Integer.MAX_VALUE;
		}
		TreeMap<Integer, Integer> taskMap = new TreeMap<>();// 放每个任务的个数
		// tasks[tl....tr]
		for (int i = tl; i <= tr; i++) {
			taskMap.put(tasks[i], taskMap.getOrDefault(tasks[i], 0) + 1);
		}
		int ans = 0;
		// works[wl..wr]
		for (int i = wl; i <= wr; i++) {
			Integer job = taskMap.floorKey(workers[i]);//工人左任务，卡着工人的能力极限，小于等于工人能力最大的任务
			if (job != null) {
				int times = taskMap.get(job);
				if (times == 1) { //如果最后任务了
					taskMap.remove(job);
				} else {
					taskMap.put(job, times - 1);//任务数量-1
				}
			} else {
				// 吃药了！
				job = taskMap.floorKey(workers[i] + strength);//吃药
				if (job == null) {
					return Integer.MAX_VALUE;//吃了药找不到任务
				}
				ans++;
				int times = taskMap.get(job);
				if (times == 1) {
					taskMap.remove(job);
				} else {
					taskMap.put(job, times - 1);
				}
			}
		}
		return ans;
	}

	// 时间复杂度O(N * logN)
	// 贪心：不吃药丸做最小的
	public static int maxTaskAssign2(int[] tasks, int[] workers, int pills, int strength) {
		// 思路：不吃药丸就做小的
		//【6，8，8，9，10】，当前工人4，药丸=5，吃了药丸可以把6 8 8 9，吃完药丸选择大任务做，吃了药丸不要浪费
		int[] help = new int[tasks.length];//双端队列，如果工人不吃药，把任务都加进队列，一旦进了队列，工人就按照吃药丸和不吃药的规则来从队列中出队
		//如果队列中有任务，来了员工，可以把对头清了，也可以吃药去清理队尾
		int l = 0;
		int r = tasks.length;
		int m, ans = 0;
		Arrays.sort(tasks);
		Arrays.sort(workers);
		while (l <= r) {
			m = (l + r) / 2;
			if (yeah2(tasks, 0, m - 1, workers, workers.length - m, workers.length - 1, strength, help) <= pills) {
				ans = m;
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return ans;
	}

	//O（N），不回退
	public static int yeah2(int[] tasks, int tl, int tr, int[] workers, int wl, int wr, int strength, int[] help) {
		if (wl < 0) {
			return Integer.MAX_VALUE;
		}
		int l = 0;
		int r = 0;
		int ti = tl;
		int ans = 0;
		// help : 辅助队列，双端队列！双端队列用数组实现！
		for (int wi = wl; wi <= wr; wi++) {
			//       4 6 7
			//       0 1 2
			//           ti   ti    ti
			// 工人  6
			// help : 0 1
			//        0 1 2 3 ...
			//        l   r
			for (; ti <= tr && tasks[ti] <= workers[wi]; ti++) {
				help[r++] = ti;
			}
			if (l < r && tasks[help[l]] <= workers[wi]) { //当前任务的任务量小于等于员工能力，做掉，l++
				l++;
			} else {
				// workers[wi] + strength
				for (; ti <= tr && tasks[ti] <= workers[wi] + strength; ti++) {//让当前员工吃药，扩充任务队列
					help[r++] = ti;//加任务
				}
				if (l < r) {
					ans++;//吃了一个药丸
					r--;//把最大的任务处理掉
				} else {
					return Integer.MAX_VALUE;
				}
			}
		}
		return ans;
	}

}
