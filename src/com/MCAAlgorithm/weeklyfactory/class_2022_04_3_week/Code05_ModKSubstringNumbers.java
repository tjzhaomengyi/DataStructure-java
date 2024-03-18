package com.MCAAlgorithm.weeklyfactory.class_2022_04_3_week;

// 来自微众
// 4.11笔试
// 给定n位长的数字字符串和正数k，求该子符串能被k整除的子串个数
// (n<=1000，k<=100)
public class Code05_ModKSubstringNumbers {

	// 暴力方法
	// 为了验证
	public static int modWays1(String s, int k) {
		int n = s.length();
		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (Long.valueOf(s.substring(i, j + 1)) % k == 0) {
					ans++;
				}
			}
		}
		return ans;
	}

	// 正式方法
	// 时间复杂度O(N * k)，这道题比较烦人，主要是next数组来接新的一位变动后cur数组的调整，然后再计算余数的时候再把cur调整为cur，一次for循环后再进行这样的来回调整
	// abcde %k = 0 ,如果a0000 % k = 0，那么bcde%k=0；如果ab000%k=0,那么cde%k=0
	// k=7,2%7=2,23=>20%7=6,找到规律：该位数每加一位0，那么当前余数=(余数*10)%k,这样往后递推
	// 余数表,抛掉abcd(i),k=4，余数表0:100,1:50，2：60，3：40，这样可以进行迁移比如从a到b，
	// 253 k = 5,
	//余数表0-0,1-0,2-1,3-0,4-0,
	//为什么要迁移余数？因为每进来一个数，整体的余数表是要改变的！
	// 例子：253第一步开始的时候是2，余数表里面只有map[2]=1,第二步就是25了，此时原来的map[2]对应的2的余数现在要变成20%k的值map[0]=map[0] + map[2]
	//
	public static int modWays2(String s, int k) {
		int[] cur = new int[k];//统计当前各个模的数量，然后要和next迁移数组进行统计
		// 帮忙迁移
		int[] next = new int[k];
		// 0...i 整体余几？
		int mod = 0;
		// 答案：统计有多少子串的值%k == 0
		int ans = 0;
		for (char cha : s.toCharArray()) {
			for (int i = 0; i < k; i++) {//余数表迁移，因为有来了一个新的位数
				// i -> 10个
				// (i * 10) % k
				//注意从刚才循环过来后next数组是52行的next<--tmp<--cur（这个cur在48行都被清0了），现在重新开始记录
				next[(i * 10) % k] += cur[i]; //前缀不要的余数信息，如果原来i的余数有10，那么迁移到next就是i*10%k这个余数
				cur[i] = 0;//当前的余数表cur[i]这个值清零
			}
			int[] tmp = cur;//使用next迁移表来计算当前进来字符的mod情况，注意：此时cur所有位置都为0，
			cur = next; //迁移后的余数表就是当前的cur余数表，此时cur变成next迁移数组正常使用
			next = tmp; //这个对后续没有影响，此时next也就是原来的cur限制
			mod = (mod * 10 + (cha - '0')) % k; //整体余数当前余数 = (余数*10)%k，这个时候才算这个新的字符cha加进来后对应的余数！！！
			ans += (mod == 0 ? 1 : 0) + cur[mod];
			cur[mod]++;
		}
		return ans;
	}

	// 为了测试
	public static String randomNumber(int n) {
		char[] ans = new char[n];
		for (int i = 0; i < n; i++) {
			ans[i] = (char) ((int) (Math.random() * 10) + '0');
		}
		return String.valueOf(ans);
	}

	// 为了测试
	public static void main(String[] args) {
		int N = 18;
		int K = 20;
		int testTime = 10000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			String str = randomNumber((int) (Math.random() * N) + 1);
			int k = (int) (Math.random() * K) + 1;
			int ans1 = modWays1(str, k);
			int ans2 = modWays2(str, k);
			if (ans1 != ans2) {
				System.out.println("出错了!");
				System.out.println(str);
				System.out.println(k);
				System.out.println(ans1);
				System.out.println(ans2);
				break;
			}
		}
		System.out.println("测试结束");
	}

}
