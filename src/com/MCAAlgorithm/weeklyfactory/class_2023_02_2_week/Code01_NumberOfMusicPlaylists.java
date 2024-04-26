package com.MCAAlgorithm.weeklyfactory.class_2023_02_2_week;

// 你的音乐播放器里有 N 首不同的歌
// 在旅途中，你的旅伴想要听 L 首歌（不一定不同，即，允许歌曲重复
// 请你为她按如下规则创建一个播放列表
// 每首歌至少播放一次
// 一首歌只有在其他 K 首歌播放完之后才能再次播放
// 返回可以满足要求的播放列表的数量
// 由于答案可能非常大，请返回它模 10^9 + 7 的结果
// 测试链接 : https://leetcode.cn/problems/number-of-music-playlists/
public class Code01_NumberOfMusicPlaylists {

	// 减法不能直接模：a->甲，b->乙，要求（a-b）%m 要变成（甲-乙+m）% m
	// 数学结论：为什么特别大的数最后每次都要对10^9+7取模
	// 数学结论：1/x = (x^(mod-2)) % mod [公式]除数x和mod一定要互制，即最大公约数是1，所以最大公约数是10^9+7,这个质数和谁都互制
	// 20 / 5 % 7 = 4  => 20 %7 = 6,1/5替换为(5^5)%7

	//题意处理：f(n,l,k)有n首歌可以用，组成长度为l的列表，相同的歌间隔至少是k，求方法数?那么前k+1肯定不能重复，前面的方法数 乘以 后续的变化
	// An(k+1) * 【（n-k）^(l-k-1)】 ，【】中每次都是n-k首歌，后续的方法数
	// 上面式子转换：n！/（n-k）！ *（n-k）^(l-k)
	// 某一首歌i不在备选歌中，组成长度为l的列表，相同间隔至少是k，f(n-1,l,k)，所以每首歌不在被选中，C(n,1)*f(n-1,l,k)
	// 数学结论：使用容斥原理，不合法的方法数 = C(n,1)*f(n-1,l,k) - C(n,2)*f(n-2,l,k) + C(n,3)*f(n-3,l,k) - C(n,4)*f(n-4,l,k)...
	//
	public static int mod = 1000000007;

	public static int limit = 100;

	// 阶乘表
	public static long[] fac = new long[limit + 1];

	// 阶乘结果的乘法逆元表
	public static long[] inv = new long[limit + 1];

	static {
		fac[0] = inv[0] = 1;
		for (int i = 1; i <= limit; i++) {
			fac[i] = ((long) i * fac[i - 1]) % mod;
		}
		// 费马小定理计算乘法逆元
//		for (int i = 1; i <= limit; i++) {
//			inv[i] = power(fac[i], mod - 2);
//		}
		// 费马小定理计算乘法逆元，优化如下
		// 这一块叫：阶乘的逆元倒推
		inv[limit] = power(fac[limit], mod - 2);
		for (int i = limit; i > 1; i--) {
			inv[i - 1] = ((long) i * inv[i]) % mod;
		}
	}

	// x的n次方，% mod之后，是多少？
	public static long power(long x, int n) {
		long ans = 1;
		while (n > 0) {
			if ((n & 1) == 1) {
				ans = (ans * x) % mod;
			}
			x = (x * x) % mod;
			n >>= 1;
		}
		return ans;
	}

	// n * logn
	public static int numMusicPlaylists(int n, int l, int k) {
		long cur, ans = 0, sign = 1;
		for (int i = 0; i <= n - k; i++, sign = sign == 1 ? (mod - 1) : 1) {
			// cur -> 
			// fac[n] -> n! % mod 的结果！
			// inv[i] -> i! 的逆元！ 就是开头分析的倒数怎么求
			// inv[n - k - i] -> (n - k - i)! 的逆元
			// sign * 1 -> 1
			//      * -1 -> mod - 1
			cur = (sign * power(n - k - i, l - k)) % mod;
			cur = (cur * fac[n]) % mod;
			cur = (cur * inv[i]) % mod;
			cur = (cur * inv[n - k - i]) % mod;
			ans = (ans + cur) % mod;
		}
		return (int) ans;
	}

}
