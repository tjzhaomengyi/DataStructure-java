package com.MCAAlgorithm.weeklyfactory.class_2023_03_5_week;

// 来自百度
// 用r、e、d三种字符，拼出一个回文子串数量等于x的字符串
// 1 <= x <= 10^5
public class Code01_CharRedToPalindromeString {
	//	思路：这题挺傻逼啊
	// r-1个，rr-3个，rrr-6个，rrrr-4+3+2+1个，rrrrr-15个这是个等差数列
	// 求回文子串=19的字符串 rrrrr15个， ee-3个 d-1个 ，rrrrreed，找到一个尽量接近的数量，然后一点点网上不
	public static String palindromeX(int x) {
		StringBuilder builder = new StringBuilder();
		char cur = 'r';
		while (x > 0) { //x没有被分解干净就继续
			// 根据x，找到当前需要几个相同字符来凑回文子串数量，比如上面的19，找到的near()=15对应的5个字符
			// x = 12,  1 3 6 10 15,返回4个字符
			//          1 2 3 4  5
			int number = near(x);//关键在这个
			for (int i = 0; i < number; i++) {
				builder.append(cur);//同一个字符加进去
			}
			// 4 -> 10
			x -= number * (number + 1) / 2; //对应等差数列的和 4 * 5 /2 ，x拆解出去这个等差数列的结果
			cur = cur == 'r' ? 'e' : (cur == 'e' ? 'd' : 'r');//下一个字符是什么？red来回折腾
		}
		return builder.toString();
	}

	//二分找最近的，小于等于x且最靠近x的
	public static int near(int x) {
		int l = 1;
		int r = x;
		int m, ans = 0;
		while (l <= r) {
			m = (l + r) / 2;
			if (ok(m, x)) {
				ans = m;
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return ans;
	}

	public static boolean ok(int n, int x) {
		return ((long) n * (n + 1) / 2) <= x;
	}

	public static void main(String[] args) {
		int x = 13;
		System.out.println(palindromeX(x));
	}

}
