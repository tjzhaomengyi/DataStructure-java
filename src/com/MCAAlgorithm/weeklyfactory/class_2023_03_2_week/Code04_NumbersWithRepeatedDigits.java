package com.MCAAlgorithm.weeklyfactory.class_2023_03_2_week;

// 给定正整数 n
// 返回在 [1, n] 范围内具有 至少 1 位 重复数字的正整数的个数。
// 测试链接 : https://leetcode.cn/problems/numbers-with-repeated-digits/
public class Code04_NumbersWithRepeatedDigits {

	//思路：整体这套磕儿，就是一个标准的数位DP，看似代码很短，但是讲了挺长时间，很长时间，很长时间，21：13讲到了21:54这个是数位dp最难的了把
	public static int numDupDigitsAtMostN(int n) {
		if (n <= 10) {
			return 0;
		}
		// n   = 23645
		// len = 5长度
		// n       = 723645
		// offset  = 100000
		int len = 1;
		int offset = 1;
		int tmp = n / 10;
		while (tmp > 0) {
			len++;
			offset *= 10;
			tmp /= 10;
		}
		// n = 23645  5长度
		// 1长度的有几个、2长度的有几个、3长度的有几个、4长度的有几个
		// 思路：1长度10个，2长度的第一位9种，第二位9种，一共81种；3长度的第一位9种，第二位9种，第三位8种；4长度-9*9*8*7
		//  没有重复数字的直接公式计算出来
		int noRepeat = 0;
		for (int i = 1; i < len; i++) {
			noRepeat += numAllLength(i); //计算i为长度（i小于len）有多少个不出现重复的数字
		}
		// num = 723645
		// 单独求6长度的，数字不重复的有几个
		if (len <= 10) {
			// int a = 0b 11
			int status = 0b1111111111;//0b开头表示下面的数字以2进制表示，从0到9赋值
			// n =    732645
			// offset 100000
			// n / offset = 7
			// 1 2 3 ... 6  7-1，思路：小于7有6股
			// ====
			// n =    732645
			// offset 100000
			//         10000
			//思路：（n/offset）-1表示最高位剩下的股数，offset/10表示剩余多长，
			// status：注意注意注意这里的status不关注是哪个位值拿掉的，只关注个数，numberRest往后算的时候，直接根据
			// status的个数求解每个结果有多少个不相同的值，和具体使用的哪个位置的数没有关系，保证status位中少一个就行
			noRepeat += ((n / offset) - 1) * numberRest(offset / 10, status ^ 1);
			// n =    732645
			// offset 100000
			//        7..... 思路：7开头不超过范围的有多少数字，递归，这个是最后process的任务
			// offset / 10 表示这个位就用7，status^(1<<(n / offset))把7标记上用过了
			noRepeat += process(offset / 10, status ^ (1 << (n / offset)), n);
		}
		return n + 1 - noRepeat; //不重复的数量
	}

	// 10进制长度必须为len的所有数中
	// 每一位数字都不重复的数有多少
	// 10
	// 9 * 9
	// 9 * 9 * 8
	// 9 * 9 * 8 * 7
	public static int numAllLength(int len) {
		if (len > 10) {
			return 0;
		}
		if (len == 1) {
			return 10;
		}
		int ans = 9;
		int cur = 9;
		while (--len > 0) {
			ans *= cur;
			cur--;
		}
		return ans;
	}

	//思路：这个部分是处理最后最高位开头的结果
	// n = 732645，n永远不变就是这个数的长度
	//        100
	// offset表示当前来到了哪位数字，对应小于等于n的index，offset=1000代表来到了n的第4位
	// status : 还剩哪些数字可以选，这里必须是具体对应的数值，上面的例子中732三个数不能用了。
	// 返回值 : 前面的数字都和n一样，剩下的位里有多少数字，剩下的数字都不同？
	public static int process(int offset, int status, int n) {
		if (offset == 0) {
			// n = 732645
			//           0
			return 1; //表示前面数字和n都一样，并且n也是每位数字都不一样
		}
		int ans = 0;
		// n = 732645
		// off    100
		//     732....
		//     7320... ,...可以随便
		//     7321..., ...可以随便
		//     7322... X 
		//     7323... X
		//     7324...
		//     7325...
		// 
		// n = 732645
		// off    100
		//        6
		// 7326 % 10 = 6
		int first = (n / offset) % 10;
		for (int cur = 0; cur < first; cur++) { //思路：当前数字是01234...能不能公式
			if ((status & (1 << cur)) != 0) { //思路:status没用过就能往下走
				ans += numberRest(offset / 10, status ^ (1 << cur));//套公式累加股数
			}
		}
		// n = 732645
		// off     10
		//     7326 ..
		if ((status & (1 << first)) != 0) { //思路：当前数字是6，这个数字之前没用过；如果是7根本就不会调这里，这个条件一定要和base case结合看
			ans += process(offset / 10, status ^ (1 << first), n);
		}
		return ans;
	}

	// 这种题烦人的地方，拱啊拱啊拱啊拱！！！
	// offset : 1000, 还剩4长度
	// offset : 100000, 还剩7长度
	// status : 还有哪些数字可以选
	//          状态!
	//          9 8 7 6 5 4 3 2 1 0
	//          0 1 1 1 0 0 0 1 1 1
	public static int numberRest(int offset, int status) {
		// c 还有几种数字可以选！
		// offset = 1000; 100 10 1 0
		// c = 7  6  5 4 3
		// ans = 1 * 7 * 6 * 5 * 4
		int c = hammingWeight(status);
		int ans = 1;
		while (offset > 0) {
			ans *= c; //ans方法数网上拱，这种题就这么烦人
			c--;//每一次c减少一个可以选的数
			offset /= 10;
		}
		return ans;
	}

	//这种题套路的地方，求还有多少个数字可以选择，求2进制中有多少个1，《大厂刷题班32-191》
	public static int hammingWeight(int n) {
		n = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
		n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
		n = (n & 0x0f0f0f0f) + ((n >>> 4) & 0x0f0f0f0f);
		n = (n & 0x00ff00ff) + ((n >>> 8) & 0x00ff00ff);
		n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);
		return n;
	}

}
