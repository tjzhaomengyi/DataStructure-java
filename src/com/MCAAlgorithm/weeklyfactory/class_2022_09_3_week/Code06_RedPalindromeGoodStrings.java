package com.MCAAlgorithm.weeklyfactory.class_2022_09_3_week;

// 来自阿里
// 小红定义一个仅有r、e、d三种字符的字符串中
// 如果仅有一个长度不小于2的回文子串，那么这个字符串定义为"好串"
// 给定一个正整数n，输出长度为n的好串有多少个
// 好串定义 errd
// 结果对10^9 + 7取模， 1 <= n <= 10^9【直接把动态规划卡死，从数据量直接用公式去推】
// 示例：
// n = 1, 输出0
// n = 2, 输出3
// n = 3, 输出18 观察规律
// https://www.mashibing.com/question/detail/37485
public class Code06_RedPalindromeGoodStrings {

	// 暴力方法
	// 为了观察规律
	// 具体方法论，在体系学习班，章节39 : 根据对数器找规律
	public static int num1(int n) {
		char[] path = new char[n];
		return process1(path, 0);
	}

	public static int process1(char[] path, int i) {
		if (i == path.length) {
			int[] dp = getManacherDP(path);
			int cnt = 0;
			for (int p : dp) {
				if (p - 1 > 3) {
					return 0;
				}
				if (p - 1 >= 2) {
					cnt++;
				}
				if (cnt > 1) {
					return 0;
				}
			}
			return cnt == 1 ? 1 : 0;
		} else {
			int ans = 0;
			path[i] = 'r';
			ans += process1(path, i + 1);
			path[i] = 'e';
			ans += process1(path, i + 1);
			path[i] = 'd';
			ans += process1(path, i + 1);
			return ans;
		}
	}

	public static int[] getManacherDP(char[] s) {
		char[] str = manacherString(s);
		int[] pArr = new int[str.length];
		int C = -1;
		int R = -1;
		for (int i = 0; i < str.length; i++) {
			pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
			while (i + pArr[i] < str.length && i - pArr[i] > -1) {
				if (str[i + pArr[i]] == str[i - pArr[i]])
					pArr[i]++;
				else {
					break;
				}
			}
			if (i + pArr[i] > R) {
				R = i + pArr[i];
				C = i;
			}
		}
		return pArr;
	}

	public static char[] manacherString(char[] s) {
		char[] res = new char[s.length * 2 + 1];
		int index = 0;
		for (int i = 0; i != res.length; i++) {
			res[i] = (i & 1) == 0 ? '#' : s[index++];
		}
		return res;
	}

	// 正式方法
	// 观察规律之后，把规律变成代码
	// 长度为1, 答案:0,0
	//长度为2, 答案:3,3
	//长度为3, 答案:18,18
	//长度为4, 答案:30,30
	//长度为5, 答案:36,36
	//长度为6, 答案:42,42
	//长度为7, 答案:48,48
	//长度为8, 答案:54,54
	//长度为9, 答案:60,60
	//长度为10, 答案:66,66
	// 结论：只可以是两种模式xx和xyx，
	// 1、xx模式
	// （1）xx模式在开头 rred.....或者rrde，所以这种模式六种可能性，
	// （2）xx模式在中间，drre/errd，计算有几种中间情况xx在中间不同位置挪动，如果n=5，不同位置一共n-3=2，中间情况一共有（n-3）*6
	// （3）xx模式在结尾，和开头模式一样六种
	// 2、xyx模式
	// （1）开头 rerder。。。 一旦确定后面是确定的，xyx一种有几种情况6种
	// （2）结尾 同开头，6种情况
	//  （3）中间，没有情况，什么都不能填
	// sum = 24 + （n - 3） * 6 = 6 * （n + 1）
	public static int num2(int n) {
		if (n == 1) {
			return 0;
		}
		if (n == 2) {
			return 3;
		}
		if (n == 3) {
			return 18;
		}
		return 6 * (n + 1);
	}

	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			System.out.println("长度为" + i + ", 答案:" + num1(i) + "," + num2(i));
		}
	}

}
