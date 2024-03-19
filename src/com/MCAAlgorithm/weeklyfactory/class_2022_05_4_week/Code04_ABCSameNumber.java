package com.MCAAlgorithm.weeklyfactory.class_2022_05_4_week;

// 来自京东考了两次【这个滑动窗口有点欢乐，可以看看，情况分析也挺有意思】，阿左36岁生日掏出来的
// 4.2笔试
// 给定一个长度为3N的数组，其中最多含有0、1、2三种值
// 你可以把任何一个连续区间上的数组，全变成0、1、2中的一种
// 目的是让0、1、2三种数字的个数都是N
// 返回最小的变化次数
public class Code04_ABCSameNumber {

	// 暴力方法
	// 为了验证不会超过2次
	public static int minTimes1(int[] arr) {
		int[] set = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			set[i] = arr[i];
		}
		return process1(set, 0, arr);
	}

	public static int process1(int[] set, int time, int[] origin) {
		int[] cnt = new int[3];
		for (int num : set) {
			cnt[num]++;
		}
		if (cnt[0] == cnt[1] && cnt[0] == cnt[2]) {
			return time;
		} else {
			if (time == 2) {
				return 3;
			}
			int ans = Integer.MAX_VALUE;
			for (int L = 0; L < set.length; L++) {
				for (int R = L; R < set.length; R++) {
					set(set, L, R, 0);
					ans = Math.min(ans, process1(set, time + 1, origin));
					set(set, L, R, 1);
					ans = Math.min(ans, process1(set, time + 1, origin));
					set(set, L, R, 2);
					ans = Math.min(ans, process1(set, time + 1, origin));
					rollback(set, L, R, origin);
				}
			}
			return ans;
		}
	}

	public static void set(int[] set, int L, int R, int v) {
		for (int i = L; i <= R; i++) {
			set[i] = v;
		}
	}

	public static void rollback(int[] set, int L, int R, int[] origin) {
		for (int i = L; i <= R; i++) {
			set[i] = origin[i];
		}
	}

	// 正式方法
	// 时间复杂度O(N)，数组长度3*n，从左往右撸，找到正好是n个的，然后左边剩下两个数看下差值，最后在这个位置的右边刷两次即可
	// 然后考察是否是需要在这右部分刷一下就行？如果剩下的两种数都小于n，那肯定要刷两次，
	// 最后剩下的情况就是只有一种数小于n，考察这种情况
	public static int minTimes2(int[] arr) {
		int[] cnt = new int[3];
		for (int num : arr) {
			cnt[num]++;
		}
		if (cnt[0] == cnt[1] && cnt[0] == cnt[2]) { //不用刷
			return 0;
		}
		int n = arr.length;
		int m = n / 3;
		if ((cnt[0] < m && cnt[1] < m) || (cnt[0] < m && cnt[2] < m) || (cnt[1] < m && cnt[2] < m)) { //任何两个数小于m就刷2次
			return 2;
		} else { // 只有一种数的个数是小于m的
			return once(arr, cnt, m) ? 1 : 2; //刷一次做的到么？
		}
	}

	// 只有一种数是少于N/3
	public static boolean once(int[] arr, int[] cnt, int m) {
		int lessV = cnt[0] < m ? 0 : (cnt[1] < m ? 1 : 2); //只有一种数是少于n/3的
		int lessT = lessV == 0 ? cnt[0] : (lessV == 1 ? cnt[1] : cnt[2]); //小于n/3这个数的个数是多少
		//较大的数和最小的数分别尝试，如果可以最多有两个可以正常返回true
		if (cnt[0] > m && modify(arr, 0, cnt[0], lessV, lessT)) {
			return true;
		}
		if (cnt[1] > m && modify(arr, 1, cnt[1], lessV, lessT)) {
			return true;
		}
		if (cnt[2] > m && modify(arr, 2, cnt[2], lessV, lessT)) {
			return true;
		}
		return false;
	}

	// 目标：
	// 0 -> 10个
	// 1 -> 10个
	// 2 -> 10个
	// ==========
	// 0 -> 7个
	// 2 -> 12个   1 -> 11个
	// 多的数 2
	// 少的数 0
	//是否可以通过刷一次完成！
	public static boolean modify(int[] arr,
			int more, int moreT, 
			int less, int lessT) {
		int[] cnt = new int[3];
		cnt[less] = lessT;
		cnt[more] = moreT;
		// 目标
		int aim = arr.length / 3;
		int L = 0;
		int R = 0;//窗口是左闭右开的！窗口是左闭右开的的！窗口是左闭右开的！因为初始的窗口什么都没有，所以！右边是开的，初始窗口[0,0）这表示啥鸡巴都没有
		while (R < arr.length || cnt[more] <= aim) { //窗口如何移动
			// cnt[more] 窗口之外，多的数有几个？
			if (cnt[more] > aim) { //如果窗口之外的2有12个，说明窗口扩的不够大
				// R++ 窗口右边界，右移
				 cnt[arr[R++]]--;//窗口之外的这个数减少了！//这个执行的过程是cnt[arr[R]]--,最后R++，开始的时候[0,0)把[0]位置的数从窗口外的统计cnt中先--，然后执行完R++，R走到1，形成窗口[0,1)里面一个数[0]
			} else if (cnt[more] < aim) { //窗口外的数小于了目标数，窗口缩减并吐出数字
				cnt[arr[L++]]++;//窗口外的数字++，同理cnt[arr[L]++;L++;先把窗口内的数字统计还给窗口外的cnt，然后L向右移动
			} else { // 在窗口之外，多的数(more)等于aim，够了！ cnt[more]等于aim，说明此时窗口里面已经没有more这个数了 !!!!
				// 少的数less，和，另一种数other，能不能平均！都是10个！
				if (cnt[less] + R - L < aim) { //让less也达到aim，看看窗口这个数能不能搞出来，[L,R),如何把少的数刷成aim，窗口里面的数+外面的
					cnt[arr[R++]]--; //less数的统计外面和窗口整个大小还小于aim，说明赶紧补less吧，扩充右边界
				} else if (cnt[less] + R - L > aim) { //窗口外面的数和窗口里面的数 大于了 aim，赶紧从左边缩小窗口吧
					cnt[arr[L++]]++;
				} else { // less more 和 other都是n
					return true;
				}
			}
		}
		return false;
	}

	// 为了验证
	public static int[] randomArray(int len) {
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * 3);
		}
		return arr;
	}

	// 为了验证
	public static void main(String[] args) {
		// 数组长度一定是3的整数倍，且 <= 3*n
		// 如下代码是验证操作次数一定不大于2次
		// 24个，8个
		int n = 8;
		int testTime = 2000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int m = ((int) (Math.random() * n) + 1) * 3;
			int[] arr = randomArray(m);
			int ans1 = minTimes1(arr);
			int ans2 = minTimes2(arr);
			if (ans1 != ans2) {
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
