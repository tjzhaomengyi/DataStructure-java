package com.MCAAlgorithm.weeklyfactory.class_2022_05_3_week;

import java.util.Arrays;

// 来自字节【常考】,拼出小于limit的数字，【这题其实挺烦人的】
// 输入:
// 去重数组arr，里面的数只包含0~9
// limit，一个数字
// 返回:
// 要求比limit小的情况下，能够用arr拼出来的最大数字
public class Code01_MaxNumberUnderLimit {

	public static int tmp = 0;

	// 暴力尝试的方法
	public static int maxNumber1(int[] arr, int limit) {
		tmp = 0;
		Arrays.sort(arr);
		limit--;
		int offset = 1;
		while (offset <= limit / 10) {
			offset *= 10;
		}
		process1(arr, 0, offset, limit);
		if (tmp == 0) {
			int rest = 0;
			offset /= 10;
			while (offset > 0) {
				rest += arr[arr.length - 1] * offset;
				offset /= 10;
			}
			return rest;
		}
		return tmp;
	}

	public static void process1(int[] arr, int num, int offset, int limit) {
		if (offset == 0) {
			if (num <= limit) {
				tmp = Math.max(tmp, num);
			}
		} else {
			for (int cur : arr) {
				process1(arr, num * 10 + cur, offset / 10, limit);
			}
		}
	}

	// 正式方法
	public static int maxNumber2(int[] arr, int limit) {
		// arr里面是不重复的数字，且只包含0~9
		Arrays.sort(arr);
		limit--;
		// <= limit 且最大的数字
		// 68886
		// 10000
		// 为了取数而设计的！
		// 457
		// 100
		int offset = 1; //就是下标！！！！！表示数字从第几位取出来的68886 ,取第2个8，68000/1000%10
		while (offset <= limit / 10) {//把offset涨到limit的最高位
			offset *= 10;
		}
		int ans = process2(arr, limit, offset);
		if (ans != -1) { //如果主函数和limit漫位都拼不成功，使用比limit少一位的数，拼成最大的就行
			return ans;
		} else {
			offset /= 10;
			int rest = 0;
			while (offset > 0) {
				rest += arr[arr.length - 1] * offset;
				offset /= 10;
			}
			return rest;
		}
	}

	// 可以选哪些数字，都在arr里，arr是有序的，[3,6,8,9]
	// limit : <= limit 且尽量的大！  68886
	// offset :                     10000 来到了6来做决定
	//                               1000 来到了8来做决定
	//                                100
	// offset 下标用！
	public static int process2(int[] arr, int limit, int offset) { //单决策递归
		// 之前的数字和limit完全一样，且limit所有数字都一样
		if (offset == 0) { //68886,每一步都一样offset才能等于0
			return limit;
		}
		// 当前的数字
		// 68886
		// 10000
		// 6
		int cur = (limit / offset) % 10; //当前的数字
		// 6 arr中 <=6 最近的！
		int near = near(arr, cur); //在数组中找小于最近的
		if (near == -1) {
			return -1;
		} else if (arr[near] == cur) { // 找出来的数字，真的和当前数字cur一样!这个事就多了
			int ans = process2(arr, limit, offset / 10);
			if (ans != -1) {
				return ans; //后续目标达成
			} else if (near > 0) { // 虽然后续没成功，但是我自己还能下降！还能选更小的数字
				near--;
				//前面数字 + 当前数字 + 剩下最大部分
				return (limit / (offset * 10)) * offset * 10 + (arr[near] * offset) + rest(arr, offset / 10);
			} else { // 后续没成功，我自己也不能再下降了！宣告失败，往上返回！
				return -1;
			}
		} else { // arr[near] < cur,拿出来的数小于当前位的cur
			//65632,【3，6，8】 63888，
			//p1=要把前面的数都取出来，如果是最高位，这个部分就是0，比如上面例子offset=1000，65632/(1000*10)=6,还原6*1000*10，所以是limit/(offset*10)
			//p2=拿着找到的数继续拼arr[near]*offset
			//p3=rest(arr,offset/10),剩下部分求最大即可
 			return (limit / (offset * 10)) * offset * 10 + (arr[near] * offset) + rest(arr, offset / 10);
		}
	}

	// 比如offset = 100
	// 一共3位数
	// 那么就把arr中最大的数字x，拼成xxx，返回
	// 比如offset = 10000
	// 一共5位数
	// 那么就把arr中最大的数字x，拼成xxxxx，返回
	public static int rest(int[] arr, int offset) {
		int rest = 0;
		while (offset > 0) {
			rest += arr[arr.length - 1] * offset;
			offset /= 10;
		}
		return rest;
	}

	// 在有序数组arr中，找到<=num，且最大的数字，在arr中的位置返回
	// 如果所有数字都大于num，返回-1
	// [3,6,9] num = 4  3
	// [5,7,9] num = 4  -1
	public static int near(int[] arr, int num) {
		int l = 0;
		int r = arr.length - 1;
		int m = 0;
		int near = -1;
		while (l <= r) {
			m = (l + r) / 2;
			if (arr[m] <= num) {
				near = m;
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return near;
	}

	// 为了测试
	public static int[] randomArray() {
		int[] arr = new int[(int) (Math.random() * 10) + 1];
		boolean[] cnt = new boolean[10];
		for (int i = 0; i < arr.length; i++) {
			do {
				arr[i] = (int) (Math.random() * 10);
			} while (cnt[arr[i]]);
			cnt[arr[i]] = true;
		}
		return arr;
	}

	public static void main(String[] args) {
		int max = 3000;
		int testTime = 100;
		System.out.println("测试开始");
		for (int i = 0; i < max; i++) {
			int[] arr = randomArray();
			for (int j = 0; j < testTime; j++) {
				int ans1 = maxNumber1(arr, i);
				int ans2 = maxNumber2(arr, i);
				if (ans1 != ans2) {
					System.out.println("出错了!");
					System.out.println("数组为 ：");
					for (int num : arr) {
						System.out.print(num + " ");
					}
					System.out.println();
					System.out.println("数字为 ：" + i);
					System.out.println(ans1);
					System.out.println(ans2);
				}
			}
		}
		System.out.println("测试结束");

	}

}
