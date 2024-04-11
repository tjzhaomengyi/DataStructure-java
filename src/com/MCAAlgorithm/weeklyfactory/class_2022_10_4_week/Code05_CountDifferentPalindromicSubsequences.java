package com.MCAAlgorithm.weeklyfactory.class_2022_10_4_week;

import java.util.HashMap;

// 给定一个字符串 s，返回 s 中不同的非空 回文子序列 个数，这他妈是道难题
// 通过从 s 中删除 0 个或多个字符来获得子序列
// 如果一个字符序列与它反转后的字符序列一致，那么它是 回文字符序列
// 如果有某个 i , 满足 ai != bi ，则两个序列 a1, a2, ... 和 b1, b2, ... 不同
// 注意：结果可能很大，你需要对 10^9 + 7 取模
// 测试链接 : https://leetcode.cn/problems/count-different-palindromic-subsequences/
public class Code05_CountDifferentPalindromicSubsequences {

	// 支持任意字符集
	// 时间复杂度O(N^2)
	//dp[l][r],表示l到r上有多少不同的回文子序列dp[l][r] = dp[l][r-1] + dp[l+1][r]-dp[l+1][r-1],减去的这部分是重复的不要的
	//情况1；s[l]!=s[r]对上面的分析加个例子
	// cdaacd
	// l    r
	// l到r-1：a、aa、cac、caac。。。。。。。
	// l+1到r。。。。
	//情况2：（1）abckkaakkfa ，aa里面还有两个或两个以上的a，（2）内部有1个aabckkakkfa （3）内部没有a，aabckkakkfa
	// （3）-a情况，dp[l][r] //l和r位置的字符都不要！=》dp[l+1][r-1],把内部的回文子串继承回来
	// （3）-b情况，在内部回文中把外面把【l】【r】这个框加上 =》dp[l+1][r-1]
	// (3)-c情况，内部的字符全不要，只要【L】【R】，=》1
	//（3）-d情况，【L】【R】这两个只要一个，这样去凑的话只有一种结果=》1，如果内部有a，新加进来的也是a，新增的那一个为aa，如果没有出现过a，那么新增的一个还是啊
	public static int countPalindromicSubsequences(String str) {
		int mod = 1000000007;
		char[] s = str.toCharArray();
		int n = s.length;
		int[] right = new int[n];//这个是表示拿离i位置右边最近的l
		int[] left = new int[n];//这个是表示拿离j位置左边最近的r
		HashMap<Character, Integer> last = new HashMap<>();
		//填写左右记录
		for (int i = 0; i < n; i++) {
			left[i] = last.getOrDefault(s[i], -1);//某个字符左侧没有该字符，填写-1
			last.put(s[i], i);//把这个字符的位置放入last中，表示这个字符s【i】最后一次出现的位置
		}
		last.clear();//清空表记录
		for (int i = n - 1; i >= 0; i--) { //从右往左遍历，生成每个字符右侧字符的最近位置
			right[i] = last.getOrDefault(s[i], n);//如果没有放最大值，这里就是数组长度，这个位置是到不了的
			last.put(s[i], i);
		}
		long[][] dp = new long[n][n];
		for (int i = 0; i < n; i++) {
			dp[i][i] = 1;
		}
		for (int i = n - 2; i >= 0; i--) {
			for (int j = i + 1; j < n; j++) {
				if (s[i] == s[j]) { //注意下标的区别
					//上面是填写的过程，注意思路即可
					int l = Math.min(j, right[i]);//这个是表示拿离i位置右边最近的l
					int r = Math.max(i, left[j]); //这个是表示拿离j位置左边最近的r
					//todo：这个l和r还没有讲解
					if (l > r) { // 情况3：内部不再有i和j位置的字符了！上面分析的情况（3）的那四个情况
						dp[i][j] = dp[i + 1][j - 1] * 2 + 2;
					} else if (l == r) { // 情况2：内部仅有一个【i】/【j】位置的字符！
						dp[i][j] = dp[i + 1][j - 1] * 2 + 1; //情况【3】的d情况不要了，单独算的时候中间内部已经算好了
					} else { // 情况1：内部有>=2个【i】/【j】字符
						// a 。。a。。。。a 。。a
						// i 。。l。。。。r 。。j
						// x和y是离 l和r 最近的a，
						//（1）dp[i+1][j-1]继承过来
						// (2) [x-1][y-1]内部包裹住一个[a]...[a]（l和r）的框了，这次这一部分还会要加同样的框，所以把这部分减掉，所以这部分是
						// dp[i+1][j-1] - dp[x-1][y-1]
						dp[i][j] = dp[i + 1][j - 1] * 2 - dp[l + 1][r - 1] + mod;
					}
				} else { //cdaacd，arr[l]的字符和arr[r]的字符不相等
					dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1] + mod;
				}
				dp[i][j] %= mod;
			}
		}
		return (int) dp[0][n - 1];
	}

}
