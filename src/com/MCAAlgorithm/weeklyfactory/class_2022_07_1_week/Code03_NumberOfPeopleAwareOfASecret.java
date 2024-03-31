package com.MCAAlgorithm.weeklyfactory.class_2022_07_1_week;

// 在第 1 天，有一个人发现了一个秘密。
// 给你一个整数 delay ，表示每个人会在发现秘密后的 delay 天之后，
// 每天 给一个新的人 分享 秘密。
// 同时给你一个整数 forget ，表示每个人在发现秘密 forget 天之后会 忘记 这个秘密。
// 一个人 不能 在忘记秘密那一天及之后的日子里分享秘密。
// 给你一个整数 n ，请你返回在第 n 天结束时，知道秘密的人数。
// 由于答案可能会很大，请你将结果对 109 + 7 取余 后返回。
// 测试链接 : https://leetcode.cn/problems/number-of-people-aware-of-a-secret/
public class Code03_NumberOfPeopleAwareOfASecret {

	public static int peopleAwareOfSecret(int n, int delay, int forget) {
		long mod = 1000000007;
		// dpKnow[i], 第i天知道秘密的人
		long[] dpKnow = new long[n + 1];
		// dpForget[i], 第i天将要忘记秘密的人
		long[] dpForget = new long[n + 1];
		// dpShare[i], 第i天可以分享秘密的人
		long[] dpShare = new long[n + 1];
		// 第1天的时候，知道秘密的人1个，A
		// 第1天的时候，将要忘记秘密的人0个
		// 第1天的时候，可以分享秘密的人0个
		dpKnow[1] = 1;
		if (1 + forget <= n) {
			dpForget[1 + forget] = 1; //A第一天知道秘密，forget=4，第五天A这个人要减掉
		}
		if (1 + delay <= n) {
			dpShare[1 + delay] = 1; //A第一天知道秘密，delay=2，第三天A这个人能拉一个人进来，加一个人头
		}
		// 从第2天开始！i，开始更新这三个数组！
		for (int i = 2; i <= n; i++) {
			// 第i天 知道秘密的人等于前一天知道秘密的的人 - 这一天忘记秘密的人 + 这一天分享给的人
			// dpKnow[i - 1] - dpForget[i] + dpShare[i]
			dpKnow[i] = (mod + dpKnow[i - 1] - dpForget[i] + dpShare[i]) % mod;
			if (i + forget <= n) {
				// dpShare[i] 是第i天，刚知道秘密的人！
				// 这批人，会在i + forget天，都忘了!
				dpForget[i + forget] = dpShare[i];
			}
			if (i + delay <= n) {
				// dpShare[i + delay - 1] + dpShare[i] - dpForget[i + delay]
				// i + delay 天 , 100天后，会分享秘密的人
				// 第i+delay分享人数有两部分组成：
				// 第一部分：第i天，有一些新人，这些新人会在i + delay天分享，这只是一部分, dpShare[i]，这部分人在i+delay前都不会散播。
				// 第二部分：第i+delay天的时候还有一部分是在第i+delay那天没有忘记的那些人也会散播 所以就是
				// i + delay - 1天中知道秘密并且会散播的人（光知道不行还要去散播），- dpForget[i + delay]这一天忘记的秘密的人
				dpShare[i + delay] = (mod + dpShare[i + delay - 1] - dpForget[i + delay] + dpShare[i]) % mod;
			}
		}
		return (int) dpKnow[n];
	}

}
