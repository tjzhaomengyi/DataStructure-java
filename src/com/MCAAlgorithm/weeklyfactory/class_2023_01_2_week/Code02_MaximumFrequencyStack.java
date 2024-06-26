package com.MCAAlgorithm.weeklyfactory.class_2023_01_2_week;

import java.util.ArrayList;
import java.util.HashMap;

// 设计一个类似堆栈的数据结构，将元素推入堆栈，并从堆栈中弹出出现频率最高的元素。
// 实现 FreqStack 类:
// FreqStack() 构造一个空的堆栈。
// void push(int val) 将一个整数 val 压入栈顶。
// int pop() 删除并返回堆栈中出现频率最高的元素。
// 如果出现频率最高的元素不只一个，则移除并返回最接近栈顶的元素。
// 测试链接 : https://leetcode.cn/problems/maximum-frequency-stack/
public class Code02_MaximumFrequencyStack {

	//空间换时间，O（1）
	class FreqStack {

		// 出现的最大次数
		private int topTimes;
		// 每层节点，出现的次数和对应的数塞入到key和数组中，每次新进来的从列表的尾部进入
		private HashMap<Integer, ArrayList<Integer>> cntValues = new HashMap<>();//次数
		// 每一个数出现了几次
		private HashMap<Integer, Integer> valueTopTime = new HashMap<>();

		public void push(int val) {
			// 当前数词频+1
			valueTopTime.put(val, valueTopTime.getOrDefault(val, 0) + 1);
			// 当前数是什么词频 5 7次
			int curTopTimes = valueTopTime.get(val);//为了往词频链表里面塞对应元素
			if (!cntValues.containsKey(curTopTimes)) {
				cntValues.put(curTopTimes, new ArrayList<>());
			}
			ArrayList<Integer> curTimeValues = cntValues.get(curTopTimes);
			curTimeValues.add(val);
			topTimes = Math.max(topTimes, curTopTimes);
		}

		public int pop() {
			// 最大词频的那一层的链表(动态数组)
			ArrayList<Integer> topTimeValues = cntValues.get(topTimes);
			int ans = topTimeValues.remove(topTimeValues.size() - 1); //弹出的值
			if (topTimeValues.size() == 0) {
				cntValues.remove(topTimes--);//肯定是先把大的弹完，所以topTimes--，减少了一个统计次数
			}
			int times = valueTopTime.get(ans);
			//弹出值对应出现次数表的更新
			if (times == 1) {
				valueTopTime.remove(ans);//
			} else {
				valueTopTime.put(ans, times - 1); //更新值的频表
			}
			return ans;
		}
	}

}
