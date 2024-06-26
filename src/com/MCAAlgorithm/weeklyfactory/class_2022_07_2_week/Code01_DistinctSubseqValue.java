package com.MCAAlgorithm.weeklyfactory.class_2022_07_2_week;

import java.util.HashMap;

// 给定一个字符串 s，计算 s 的 不同非空子序列 的个数
// 因为结果可能很大，所以返回答案需要对 10^9 + 7 取余 。
// 字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符，子序列不连续
// 但不改变剩余字符相对位置的一个新字符串。找出字面值不同的子序列。
// 本题来自大厂刷题班17节
// 但是为了讲述一个最新题目，不得不重提这个题
// 本题测试链接 : https://leetcode.com/problems/distinct-subsequences-ii/
public class Code01_DistinctSubseqValue {

	//以每个字符结尾推出新增，再加上老的结果，然后获得当前字符结尾获得的字符串
	//怎么修正出来的这个结果，假设来到字符a，前面的集合是s，来到s就是 cnt【s + a】 + s，意思就是s和a组成的新的子序列 加上 s自己带着（a需要继承过来的那部分）
	//如果后面还遇到a，还按照之前思路继承老的【s+a+a】 + 【s+a】 + 【s+a】+ 【s】，all-上一次以a结尾的数量【这样来修正！！！】
	public static int distinctSubseqII(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int m = 1000000007;
		char[] str = s.toCharArray();
		// a -> 0 -> 
		// b -> 1 -> 
		// c -> 2 -> 
		// z -> 25 -> 
		// count[i] = 0
		int[] count = new int[26];
		// { }
		int all = 1; // 算空集，所有不相同字面的子序列
		for (char x : str) {
			// x
			// 纯新增
			// add = all - count[x]
			// all += add
			// count[x] + add
			int add = (all - count[x - 'a'] + m) % m; //纯新增，all-上一次以字符x结尾的记录
			all = (all + add) % m; //all + add新增
			count[x - 'a'] = (count[x - 'a'] + add) % m;
		}
		// {} 去掉！
		return (all - 1 + m) % m;
	}

	public static int zuo(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int m = 1000000007;
		char[] str = s.toCharArray();
		HashMap<Character, Integer> map = new HashMap<>();
		int all = 1; // 一个字符也没遍历的时候，有空集
		for (char x : str) {
			int newAdd = all;
//			int curAll = all + newAdd - (map.containsKey(x) ? map.get(x) : 0);
			int curAll = all;
			curAll = (curAll + newAdd) % m;
			curAll = (curAll - (map.containsKey(x) ? map.get(x) : 0) + m) % m;
			all = curAll;
			map.put(x, newAdd);
		}
		return all;
	}

	public static void main(String[] args) {
		String s = "bccaccbaabbc";
		System.out.println(distinctSubseqII(s) + 1);
		System.out.println(zuo(s));
	}

}
