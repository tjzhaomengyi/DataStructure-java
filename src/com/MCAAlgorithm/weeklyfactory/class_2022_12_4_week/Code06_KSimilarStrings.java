package com.MCAAlgorithm.weeklyfactory.class_2022_12_4_week;

import java.util.HashSet;
import java.util.PriorityQueue;

// 对于某些非负整数 k ，如果交换 s1 中两个字母的位置恰好 k 次
// 能够使结果字符串等于 s2 ，则认为字符串 s1 和 s2 的 相似度为 k
// 给你两个字母异位词 s1 和 s2 ，返回 s1 和 s2 的相似度 k 的最小值
// 测试链接 : https://leetcode.cn/problems/k-similar-strings/
public class Code06_KSimilarStrings {

	//解法：A*算法
	//思路：如果前缀一样就不要换了，aaaaccb ｜ aaacacb，a和c不一样了，
	// c	a	   a	b
	// a	a	   b	c
	// 如果交换上面ac对应的就不要碰，不要将换，碰哪个ab那列把c和哪个a交换
	public static class Node {
		public int cost; // 代价，已经换了几回了！
		public int guess;// 猜测还要换几回，能变对！这个用evaluate估计
		public int where;// 有必须去比对的下标，左边不再换了！到哪位不一样了才考虑交换，直到撸到越界
		public String str; // 当前的字符

		public Node(int r, int g, int i, String s) { //Dijkstra的点
			cost = r;
			guess = g;
			where = i;
			str = s;
		}
	}

	public static int kSimilarity(String s1, String s2) {
		int len = s1.length();
		PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> (a.cost + a.guess) - (b.cost + b.guess));//真实的代价和猜测的代价谁小
		HashSet<String> visited = new HashSet<>();
		heap.add(new Node(0, 0, 0, s1));
		while (!heap.isEmpty()) {
			Node cur = heap.poll();
			if (visited.contains(cur.str)) {
				continue;
			}
			if (cur.str.equals(s2)) { //一样了
				return cur.cost;
			}
			visited.add(cur.str);
			int firstDiff = cur.where;
			while (cur.str.charAt(firstDiff) == s2.charAt(firstDiff)) { //只要一样一直往后推，不考虑交换
				firstDiff++;
			}
			char[] curStr = cur.str.toCharArray();
			for (int i = firstDiff + 1; i < len; i++) { //碰到了firstDiff不一样了，考虑把后面的位置的字符交换到firstDiff上把这个位置的两个字符变成一样的
				//思路：
				// c	a	   a
				// a	c	   a
				// 如果交换上面ac对应的就不要碰，不要将换，碰哪个ab那列把c和哪个a交换
				if (curStr[i] == s2.charAt(firstDiff) && curStr[i] != s2.charAt(i)) { //1、字符一样，2、
					swap(curStr, firstDiff, i);
					add(String.valueOf(curStr), s2, cur.cost + 1, firstDiff + 1, heap, visited);
					swap(curStr, firstDiff, i);
				}
			}
		}
		return -1;
	}

	public static void swap(char[] s, int i, int j) {
		char tmp = s[i];
		s[i] = s[j];
		s[j] = tmp;
	}

	//这里有个估值距离，相当于一个未来函数，会把价值好的先处理，cost
	public static void add(String add, String s2, int cost, int index, PriorityQueue<Node> heap,
			HashSet<String> visited) {
		if (!visited.contains(add)) {
			heap.add(new Node(cost, evaluate(add, s2, index), index, add));
		}
	}

	// 估值函数
	// 看每周有营养的大厂算法面试题，2022年1月第3周
	// 估值函数的估计值要绝对 <= 真实距离
	// 但又要确保估计值足够大足够接近真实距离，这样效果最好
	public static int evaluate(String s1, String s2, int index) {
		int diff = 0;
		for (int i = index; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				diff++;
			}
		}
		return (diff + 1) / 2; //就用最差情况，能够完成估计就行，能保证正确性
	}

}
