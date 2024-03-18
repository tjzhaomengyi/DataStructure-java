package com.MCAAlgorithm.weeklyfactory.class_2022_05_2_week;

// 来自网易
// 小红拿到了一个长度为N的数组arr，她准备只进行一次修改
// 可以将数组中任意一个数arr[i]，修改为不大于P的正数（修改后的数必须和原数不同)
// 并使得所有数之和为X的倍数
// 小红想知道，一共有多少种不同的修改方案
// 1 <= N, X <= 10^5
// 1 <= arr[i], P <= 10^9
public class Code02_ModifyOneNumberModXWays {

	public static int ways1(int[] arr, int p, int x) {
		long sum = 0;
		for (int num : arr) {
			sum += num;
		}
		int ans = 0;
		for (int num : arr) {
			sum -= num;
			for (int v = 1; v <= p; v++) {
				if (v != num) {
					if ((sum + v) % x == 0) {
						ans++;
					}
				}
			}
			sum += num;
		}
		return ans;
	}

	public static int ways2(int[] arr, int p, int x) {
		long sum = 0;
		for (int num : arr) {
			sum += num;
		}
		int ans = 0;
		for (int num : arr) {
			//如果x=3，剩下的数字%3=1，(sum-num)%3=1，所以目标的模=(x-mod)%x,再模一遍就是为了防止mod=0的情况
			ans += cnt(p, x, num, (x - (int) ((sum - num) % x)) % x);
		}
		return ans;
	}

	// 当前数字num，妈了个逼的这个题再调整每位1到P的时候不停的修正！！！
	// 1~p以内随意选择数字，但是这个数字不能是num的情况下，这个数字% x == mod，这样的数字有几个
	// O(1)
	public static int cnt(int p, int x, int num, int mod) {
		// p/x 至少有几个
		// (p % x) >= mod ? 1 : 0
		// 在不考虑变出来的数，是不是num的情况下，算一下有几个数，符合要求
		//（1）p/x求出来有几组，每一组肯定有一个符合要求的。
		// （2）如果p%x大于等于mod说明还有一个，如果不够mod说明不够了
		// （3）要求%3=0有几个，1到7有3,6两个，7/3+7%3-1=3-1,【作为修正。。。。】
		int ans = (p / x) + ((p % x) >= mod ? 1 : 0) - (mod == 0 ? 1 : 0); //这里起始最好是分成mod为0和mod不为0两种情况给出答案，避免答案修正这么复杂
		// 不能等于num！
		return ans - ((num <= p && num % x == mod) ? 1 : 0); //还得再修正一下，如果num>P,修正个你麻痹改，如果num<=p并且这个num自己符合要求
	}

	// 为了测试
	public static int[] randomArray(int n, int v) {
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = (int) (Math.random() * v) + 1;
		}
		return ans;
	}

	public static void main(String[] args) {
		int len = 100;
		int value = 100;
		int testTime = 100000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int n = (int) (Math.random() * len) + 1;
			int[] arr = randomArray(n, value);
			int p = (int) (Math.random() * value) + 1;
			int x = (int) (Math.random() * value) + 1;
			int ans1 = ways1(arr, p, x);
			int ans2 = ways2(arr, p, x);
			if (ans1 != ans2) {
				System.out.println("出错了！");
			}
		}
		System.out.println("测试结束");
	}

}
