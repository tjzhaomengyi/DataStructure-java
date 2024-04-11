package com.MCAAlgorithm.weeklyfactory.class_2022_09_2_week;

import java.util.HashMap;

// 来自微软 ，同体系学习班346节的题，这道题使用状态位state进行转移
// 给定一个字符串s，其中都是英文小写字母
// 如果s中的子串含有的每种字符都是偶数个
// 那么这样的子串就是达标子串，子串要求是连续串
// 返回s中达标子串的最大长度
// 1 <= s的长度 <= 10^5
// 字符种类都是英文小写
public class Code03_EvenTimesMaxSubstring {

	// 为了测试
	// 暴力方法
	public static int maxLen1(String s) {
		int n = s.length();
		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = n - 1; j >= i; j--) {
				if (ok(s, i, j)) {
					ans = Math.max(ans, j - i + 1);
					break;
				}
			}
		}
		return ans;
	}

	// 为了测试
	// 暴力方法
	public static boolean ok(String s, int l, int r) {
		if (((r - l + 1) & 1) == 1) {
			return false;
		}
		int[] cnts = new int[26];
		for (int i = l; i <= r; i++) {
			cnts[s.charAt(i) - 'a']++;
		}
		for (int cnt : cnts) {
			if ((cnt & 1) == 1) {
				return false;
			}
		}
		return true;
	}


	//以[i]结尾计算每个位置得到的状态，
	//abbccdde ，state统计每个位置的状态，1表示奇状态，0表示偶状态。用异或转状态，
	// 遇到a，state=10000，遇到b，state=11000，再遇到b，state=10000，出现过这个状态
	// 更新长度ans = 2-0 = 2；以此类推。假设0到17为出现了状态为state=1101（对应abcd），0到17出现的状态也是（1101），那么从8到17出现的肯定是（0000），合法的序列
	//使用map存储这个状态
	public static int maxLen2(String s) {
		HashMap<Integer, Integer> map = new HashMap<>();//map记录state状态最早出现在哪个位置
		map.put(0, -1);
		int status = 0;
		int ans = 0;
		int n = s.length();
		for (int i = 0; i < n; i++) {
			status ^= 1 << (s.charAt(i) - 'a');
			if (map.containsKey(status)) { //如果有这个状态，更新结果
				ans = Math.max(ans, i - map.get(status));
			} else {
				map.put(status, i); //如果没有这个状态更新
			}
		}
		return ans;
	}

	// 为了测试
	public static String randomString(int n, int v) {
		char[] s = new char[n];
		for (int i = 0; i < n; i++) {
			s[i] = (char) ((int) (Math.random() * v) + 'a');
		}
		return String.valueOf(s);
	}

	// 为了测试
	public static void main(String[] args) {
		int n = 50;
		int v = 6;
		int testTimes = 2000;
		System.out.println("测试开始");
		for (int i = 0; i < testTimes; i++) {
			String s = randomString(n, v);
			int ans1 = maxLen1(s);
			int ans2 = maxLen2(s);
			if (ans1 != ans2) {
				System.out.println(s);
				System.out.println(ans1);
				System.out.println(ans2);
				break;
			}
		}
		System.out.println("测试结束");
	}

}
