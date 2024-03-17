package com.MCAAlgorithm.weeklyfactory.class_2022_03_3_week;

import java.util.HashMap;

// 来自银联编程比赛-这个是最后一题挺难
// 为了不断提高用户使用的体验，开发团队正在对产品进行全方位的开发和优化。
// 已知开发团队共有若干名成员，skills[i] 表示第 i 名开发人员掌握技能列表。
// 如果两名成员各自拥有至少一门对方未拥有的技能，则这两名成员可以「合作开发」。
// 请返回当前有多少对开发成员满足「合作开发」的条件。
// 由于答案可能很大，请你返回答案对 10^9 + 7 取余的结果。
// 测试链接 : https://leetcode-cn.com/contest/cnunionpay-2022spring/problems/lCh58I/
public class Code07_CoopDevelop {

	// 所有数量-不合法的数量
	//如果有n个人，两两一组一共有N*(N-1)/2【数学结论】，找到这些不合法的，不合法的有两种情况：要不a=b，要不ab其中一个是另外一个的子集
	//生成一个的所有子集，然后后面的去找是否属于这个自己，子集不能是整体，一个元素没有子集
	//{2,3},{3},{1,3}，2的子集一个，3的子集两个，1的子集1个，当访问到第二个员工时候只有3技能，那么这两组AB和BC不能生成这两个组合是无效的，所以从所有组合数量-2减去两组
	//技巧：一个long类型64位，一个人只有四个技能，用long类型的bit位表示技能列表
	public static long mod = 1000000007L;

	public static int coopDevelop(int[][] skills) {
		int n = skills.length;
		// key : 子集
		// value : 个数
		HashMap<Long, Long> noFullSetsNums = new HashMap<>();//每个人的技能子集的数量，key代表子集，value代表该子集所有人统计出来的个数
		for (int[] people : skills) { //每个人生成子集
			fillNoFullMap(people, 0, 0, true, noFullSetsNums);
		}
		HashMap<Long, Long> cntsNums = new HashMap<>();//之前有多少和当前人技能一样
		long minus = 0L;
		for (int[] people : skills) {
			long status = 0L;
			for (int skill : people) {
				status = (status << 10) | skill; //每个人技能是1-1000个，1000的二进制需要10个二进制位，每个人最多四个技能可以用一个long来表示他的技能列表
			}
			minus += noFullSetsNums.getOrDefault(status, 0L); //不算相等的
			minus += cntsNums.getOrDefault(status, 0L); //之前有多少人和我一样
			cntsNums.put(status, cntsNums.getOrDefault(status, 0L) + 1);
		}
		long ans = (long) n * (long) (n - 1) / 2L;
		return (int) ((ans - minus) % mod);
	}

	//递归求子集，如果不是该人技能的全集，把这个技能组合放入并统计，【如何理解这个自己递归就挺难的】
	//int[] people表示某个人掌握的技能（这个people应该改成skill比较合适），noFullSetsNums，某个技能组合的统计数量，status表示当前这个人掌握的技能
	//full表示当前status是否为"完整"子集，完整意味着这个子集实际上就是原始技能集，没有排除任何技能，如果少了一个不能视为完整
	public static void fillNoFullMap(int[] people, int i, long status, boolean full,
			HashMap<Long, Long> noFullSetsNums) {
		if (i == people.length) { //如果把这个人所有技能都遍历完了
			if (!full) { //并且不是原始技能的全部集合
				noFullSetsNums.put(status, noFullSetsNums.getOrDefault(status, 0L) + 1);
			}
		} else { //todo:这里怎么理解，左神专答https://www.mashibing.com/question/detail/95489
			//1、不向status添加技能，full为false，因为开始创建不包括所有原始技能的子集，先调用这个，准备抽出这个技能，但是不使用
			fillNoFullMap(people, i + 1, status, false, noFullSetsNums); //不要这个技能，不往status填充
			//2、这里full保持原始值，这个变量值关注是否已经从集合中排除了任何技能，而不是我们是否添加了新的技能，这里感觉把full写成false也可以，没啥影响，反正都使用了
			fillNoFullMap(people, i + 1, (status << 10) | people[i], full, noFullSetsNums); //要这个技能往status填充
		}
	}

}
