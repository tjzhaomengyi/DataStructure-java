package com.MCAAlgorithm.weeklyfactory.class_2022_12_1_week;

// 如果一个正整数每一个数位都是 互不相同 的，我们称它是 特殊整数 。
// 给你一个正整数 n ，请你返回区间 [1, n] 之间特殊整数的数目。
// 测试链接 : https://leetcode.cn/problems/count-special-integers/
public class Code01_CountSpecialIntegers {

	//类似NthDigit的思路又来了
	// 1到23644每个数字的每位数字不同，1位数有多少这种特殊数字，2位数里有多少特殊数字，3位数里有多少特殊数字，等等，然后求到最后目标数字的时候把前面的累加起来
	public static int[] offset = { 
			0,   // 0
			1,   // 1
			10,  // 2
			100, // 3
			1000, 
			10000, 
			100000, 1000000, 10000000, 100000000, 1000000000 };

	public static int countSpecialNumbers(int n) {
		int len = len(n); //统计n有几位数
		int ans = 0;
		for (int i = 1; i < len; i++) {//1到n-1位一共有多少个特殊数
			ans += all(i); //all直接算完 ，位数不够的直接算
		}
		// n -> 9位数  3456789124
		//            1 .....
		//            2 .....
		//            3 ______
		// 思路：例子：23647，最高位是2先算以1开头的情况，所以后面四位每位可能的情况是9（第二高位置只能选除1以外的9个数）*8（第三高位只能选除1和第二高位选的剩下8个数）*7*6

		int firstNumber = n / offset[len]; //（1）以46531为列子，把4提出来
		ans += (firstNumber - 1) * small(len - 1, 9); //（2）思路：firstNumber-1，因为只能选1、2和3，一共是两股；然后是剩下部分还有多少可能的数可以选，次高为肯定是除了最高位选的数之外都可以选，所以是9个
		ans += process(n, len, len - 1, 1 << firstNumber); //（3）剩下的以3开头的交给递归
		return ans;
	}

	// 返回n这个数字有几位
	public static int len(int n) {
		int ans = 0;
		while (n != 0) {
			ans++;
			n /= 10;
		}
		return ans;
	}

	//思路：理解下面两个函数就把这道题理解了
	// 返回所有bits位数，有几个特殊的
	// bits表示所有bits位的数
	//思路：【就是数学的排列组合】 有7位数，有几个特殊的，最高位只能填写1-9，有9种选择；第二位在0-9中进行选择，也有9种选择；第三位只有8种选择，第四位有7种选择，依次类推，
	// 所以总共这样的每位不相同的特殊数一共有9 * 8 * 7 * 6 * 5 * 4 * 3种选择，所以每次位数-1，ans*(cur - 1)
	public static int all(int bits) {
		int ans = 9;
		int cur = 9;
		while (--bits != 0) {
			ans *= cur--;
		}
		return ans;
	}

	// 65347，最高位可以是【1，2，3，4，5】，所以是5*9*8*7*6，然后6固定了，算5位置多少达标的4*8*7*6
	// bits : 8 7 6 5  还有4位
	// candidates : 8 可能性，还有多少可能性去选
	
	// bits : _ _ _ 3位
	// candidates : 5 可能性 所以bits对应的可能性 5 * 4 * 3
	// bits表示待定的位置有几个，candidate：到这个位的时候还有多少个数字是可以组合来选的，不是这些candites表示前面数已经把那些数字都选完了！
	// 思路：类似all（）方法，bits还是位数限制，但是最高位的候选candidates被卡住了，后面的选择
	public static int small(int bits, int candidates) {
		int ans = 1;
		for (int i = 0; i < bits; i++, candidates--) { //剩下有多少位就乘以对应多少个candidates
			ans *= candidates;
		}
		return ans;
	}

	// num : 假设原始数 46531 固定
	// len : 原始数有几位，5位，固定
	// rest : 还剩几位没决定，可变参数
	// num : 46531
	//       4 _ _ _ _ 
	//       4 0~5 
	//       4 6 _ _ _
	// status : 4 6 _ _ _
	// 哪些数字使用了，状态！在status里：
	// 体系学习班，状态压缩的动态规划！
	// int status 32位
	// 9 8 7 6 5 4 3 2 1 0
	//       1 0 1 0 0 0 0
	// 在4 6 _ _ _（4和6决定的情况下） 返回还有几个达标的！，1表示使用过的数，还有3为有多少没有确定
	// 哪些数字选了都在status里，用一个status变量表示数字选没选(位信息)

	public static int process(int num, int len, int rest, int status) {
		if (rest == 0) {
			return 1;
		}
		// 46531
		//   ___
		//   5
		// 46531 / 100 -> 465 % 10 -> 5
		
		// 比5小的有几股？0 1 2 3 4
		// 
		// n : 454012
		//     45_
		//       0...
		//       1...
		//       2...
		//       3...
		//       4 _ _ _
		int cur = (num / offset[rest]) % 10; //借助上面的offset数组，借助几位，提出当前位的数字
		int cnt = 0; //一共多少股
		for (int i = 0; i < cur; i++) {
			if ((status & (1 << i)) == 0) { //判断这个位置的数有没有用过
				cnt++; //只有没用过的才让股数+1
			}
		}
		//思路：有多少达标的 股数 * 后续的变化，候选位变成：len一共位数，rest是几位没有决定，len-rest表示使用过的数字有多少个，其实用status也可以算出来
		// 9 - (len - rest)表示有多少个数还能使用（候选 ）
		int ans = cnt * small(rest - 1, 9 - (len - rest));
		if ((status & (1 << cur)) == 0) { //思路：为什么有这个转移？ 看上面454012这个数，第二个4这位是不达标的，就不要加了，开头的4已经使用过了
			ans += process(num, len, rest - 1, status | (1 << cur));
		}
		return ans;
	}

}
