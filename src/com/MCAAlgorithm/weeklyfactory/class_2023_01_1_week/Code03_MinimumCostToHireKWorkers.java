package com.MCAAlgorithm.weeklyfactory.class_2023_01_1_week;

import java.util.Arrays;
import java.util.PriorityQueue;

// 有 n 名工人。 给定两个数组 quality 和 wage ，
// 其中，quality[i] 表示第 i 名工人的工作质量，其最低期望工资为 wage[i] 。
// 现在我们想雇佣 k 名工人组成一个工资组。在雇佣 一组 k 名工人时，
// 我们必须按照下述规则向他们支付工资：
// 对工资组中的每名工人，应当按其工作质量与同组其他工人的工作质量的比例来支付工资。
// 工资组中的每名工人至少应当得到他们的最低期望工资。
// 给定整数 k ，返回 组成满足上述条件的付费群体所需的最小金额
// 测试链接 : https://leetcode.cn/problems/minimum-cost-to-hire-k-workers/
public class Code03_MinimumCostToHireKWorkers {

	//题目解释：给的钱要按照能力比例给出，并且给的钱要大于它们要的价格
	// 力	100 	200		50
	// 价	60		70		40
	// 思路：以50为基准，80 + 160 + 40 ，【核心】价格/能力最高的为基准来算【这个标准就是每个力的价值是多少】，劣币驱逐良币
	//  在n中选择k个人，按照价格/能力从小到大排序一下，ABCDEFG， k=3，一旦选了一个垃圾，那么左边最值的k-1个选谁，假如选了D，那么D最垃圾，
	//  肯定在D的左侧继续再选剩下两个人，如果选了ABD三个人，那么最后总价格（A力+B力+D力）* （D价格/D力）
	// 解法：使用门槛堆（维持力量前k-1小的人） + 一个排序（价格/力量的标准从小到大）
	public static class Employee {
		//通过分析我们知道原本的要价根本没有用！
		public double rubbishDegree; //垃圾指数
		public int quality;

		public Employee(int w, int q) {
			rubbishDegree = (double) w / (double) q; //薪水/能力
			quality = q;
		}
	}

	public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
		int n = quality.length;
		Employee[] employees = new Employee[n];
		for (int i = 0; i < n; i++) {
			employees[i] = new Employee(wage[i], quality[i]);
		}
		// 只根据垃圾指数排序
		// 要价 / 能力
		Arrays.sort(employees, (a, b) -> a.rubbishDegree <= b.rubbishDegree ? -1 : 1);
		// 请维持力量最小的前K个力量
		// 大根堆！门槛堆！把力量最小的放在下面
		PriorityQueue<Integer> minTops = new PriorityQueue<Integer>((a, b) -> b - a);
		double ans = Double.MAX_VALUE;
		for (int i = 0, qualitySum = 0; i < n; i++) {
			// i : 依次所有员工的下标
			// qualitySum : 进入堆的力量总和！
			// curQuality当前能力
			int curQuality = employees[i].quality;
			if (minTops.size() < k) { // 堆没满
				qualitySum += curQuality;
				minTops.add(curQuality);
				if (minTops.size() == k) {
					ans = Math.min(ans, qualitySum * employees[i].rubbishDegree);
				}
			} else { // 来到当前员工的时候，堆是满的！
				// 当前员工的能力，可以把堆顶干掉，自己进来！
				if (minTops.peek() > curQuality) {
//					qualitySum -= minTops.poll();
//					qualitySum += curQuality;
//					minTops.add(curQuality);
					qualitySum += curQuality - minTops.poll();
					minTops.add(curQuality);
					ans = Math.min(ans, qualitySum * employees[i].rubbishDegree);
				}
			}
		}
		return ans;
	}

}
