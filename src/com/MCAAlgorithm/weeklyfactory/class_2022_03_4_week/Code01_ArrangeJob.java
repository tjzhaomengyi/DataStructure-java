package com.MCAAlgorithm.weeklyfactory.class_2022_03_4_week;

import java.util.Arrays;
import java.util.PriorityQueue;

// 来自学员的考试
// 来自华为
// 给定一个n*2的二维数组，表示有n个任务
// 一个信息是任务能够开始做的时间，另一个信息是任务的结束期限，后者一定大于前者，且数值上都是正数
// 你作为单线程的人，不能并行处理任务，但是每个任务都只需要一个单位时间完成
// 你需要将所有任务的执行时间，位于开始做的时间和最后期限之间
// 返回你能否做到这一点
public class Code01_ArrangeJob {

	//解法，把n个任务设计成TimePoint点，然后根据TimePoint点进行小根堆，紧着先结束的任务先做
	// 开 开 开 开 开 闭 开 开 开 闭 开 闭
	// 开始的时候start时间为0，一旦遇到闭时间，那么就结算栈里的工作是否能执行完，然后把当前的闭时间设置为开始时间，再执行到下一个闭，看看这个
	//时间段是否能把栈中的任务执行完（观察当前闭的时间是否大于heap.poll（),如果栈顶小于等于闭时间，说明到了这个闭时间还有没有完成的任务），
	// 一旦不能执行完，返回false，说明整套工作安排无法实现


	// 1 开 7
	// 5 闭 end没有用！
	public static class TimePoint {
		// 时间
		public int time;
		public int end;
		// add = true time 任务的添加时间
		// add = false time 任务的结束时间
		public boolean add;

		public TimePoint(int t, int e, boolean a) {
			time = t;
			end = e;
			add = a;
		}

	}

	public static boolean canDo(int[][] jobs) {
		if (jobs == null || jobs.length < 2) {
			return true;
		}
		int n = jobs.length;
		TimePoint[] arr = new TimePoint[n << 1];
		for (int i = 0; i < n; i++) {
			arr[i] = new TimePoint(jobs[i][0], jobs[i][1], true);
			arr[i + n] = new TimePoint(jobs[i][1], jobs[i][1], false);
		}
		Arrays.sort(arr, (a, b) -> a.time - b.time);
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		// 经过一个一个的时间点，遭遇事件：添加时间、检查时间
		for (int i = 0, lastTime = arr[0].time; i < arr.length; i++) {
			if (arr[i].add) {
				heap.add(arr[i].end);
			} else { // 检查时间
				int curTime = arr[i].time;
				for (int j = lastTime; j < curTime; j++) {
					if (heap.isEmpty()) {
						break;
					}
					heap.poll();
				}
				if (heap.peek() <= curTime) { //栈中的
					return false;
				}
				lastTime = curTime;
			}
		}
		return true;
	}

}
