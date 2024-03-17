package com.MCAAlgorithm.weeklyfactory.class_2022_03_3_week;

// 来自bilibili
// 现在有N条鱼，每条鱼的体积为Ai，从左到右排列，数组arr给出
// 每一轮，左边的大鱼一定会吃掉右边比自己小的第一条鱼，
// 并且每条鱼吃比自己小的鱼的事件是同时发生的。
// 返回多少轮之后，鱼的数量会稳定
// 注意：6 6 3 3
// 第一轮过后 : 
// 对于两个6来说，右边比自己小的第一条鱼都是第1个3，所以只有这个3被吃掉，
// 数组变成 : 6 6 3（第2个3）
// 第二轮过后 : 6 6
// 返回2
public class Code05_EatFish {


	public static int minTurns1(int[] arr) {
		int ans = 0;
		for (;; ans++) {
			int[] rest = eatRest(arr);
			if (arr.length == rest.length) {
				break;
			}
			arr = rest;
		}
		return ans;
	}

	public static int[] eatRest(int[] arr) {
		if (arr.length == 0) {
			return new int[0];
		}
		int n = arr.length;
		boolean[] delete = new boolean[n];
		int len = n;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (arr[i] > arr[j]) {
					if (!delete[j]) {
						delete[j] = true;
						len--;
					}
					break;
				}
			}
		}
		int[] rest = new int[len];
		for (int i = 0, j = 0; i < n; i++) {
			if (!delete[i]) {
				rest[j++] = arr[i];
			}
		}
		return rest;
	}

	//单调栈，从后往前压栈，小根堆
	// 6 5 1 2 3
	// 小根堆1,0 | 2,0| 3,0
	// 5进来，吃掉1， 5,1  2,0|3,0
	// 然后还能吃2，5，2 3|0，就让5一直吃 5,3
	// 然后6进来，6肯定能吃5这么多步，所以6,3 6在进来时候此时就可以把5吃掉，max(0,3)=3
	//例子：9,0轮 栈3，7|4，5|6，9 （1）3，7出，turn=max(0,7),(2)4,5出来turn=max(7,5),(3)(6,9)出来 turn=max(7,9)=9都吃完了，9，9进栈
	public static int minTurns2(int[] arr) {
		int n = arr.length;
		int[][] stack = new int[n][2];
		int stackSize = 0;
		int ans = 0;
		for (int i = n - 1; i >= 0; i--) {
			int curAns = 0;
			while (stackSize > 0 && stack[stackSize - 1][0] < arr[i]) {
				curAns = Math.max(curAns + 1, stack[--stackSize][1]);
			}
			stack[stackSize][0] = arr[i];
			stack[stackSize++][1] = curAns;
			ans = Math.max(ans, curAns);
		}
		return ans;
	}

	// 为了测试
	public static int[] randomArray(int n, int v) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = (int) (Math.random() * v);
		}
		return arr;
	}

	// 为了测试
	public static void main(String[] args) {
		int len = 50;
		int value = 20;
		int testTime = 20000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int n = (int) (Math.random() * len) + 1;
			int[] arr = randomArray(n, value);
			int ans1 = minTurns1(arr);
			int ans2 = minTurns2(arr);
			if (ans1 != ans2) {
				System.out.println("出错了!");
				for (int num : arr) {
					System.out.print(num + " ");
				}
				System.out.println();
				System.out.println(ans1);
				System.out.println(ans2);
				break;
			}
		}
		System.out.println("测试结束");
	}

}
