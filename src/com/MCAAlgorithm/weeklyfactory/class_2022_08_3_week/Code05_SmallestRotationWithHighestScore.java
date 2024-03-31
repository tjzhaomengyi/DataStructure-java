package com.MCAAlgorithm.weeklyfactory.class_2022_08_3_week;

// 给你一个数组 nums，我们可以将它按一个非负整数 k 进行轮调，
// 例如，数组为 nums = [2,4,1,3,0]，
// 我们按 k = 2 进行轮调后，它将变成 [1,3,0,2,4]。
// 这将记为 3 分，
// 因为 1 > 0 [不计分]、3 > 1 [不计分]、0 <= 2 [计 1 分]、
// 2 <= 3 [计 1 分]，4 <= 4 [计 1 分]。
// 在所有可能的轮调中，返回我们所能得到的最高分数对应的轮调下标 k 。
// 如果有多个答案，返回满足条件的最小的下标 k 。
// 测试链接 : 
// https://leetcode.cn/problems/smallest-rotation-with-highest-score/
public class Code05_SmallestRotationWithHighestScore {

	//把整体状况搞成得分数组，最后就是k个的整体信息，score数组的下标表示所有数移动下标距离的得分总和
	//例子  ？ ？ ？ ？ 2  ？ ？ ？ ，把2移动0到7位
	// score +1 +1 +1 +1 0 0 +1 + 1
	//然后再用差分数组加工这个score数组
	public static int bestRotation(int[] nums) {
		int n = nums.length;
		
		// cnt : 差分数组
		// cnt最后进行前缀和的加工！
		// 加工完了的cnt[0] :  整体向右移动0的距离, 一共能得多少分
		// 加工完了的cnt[i] :  整体向右移动i的距离, 一共能得多少分
		int[] cnt = new int[n + 1];
		for (int i = 0; i < n; i++) {
			// 遍历每个数！
			// 看看每个数，对差分数组哪些范围，会产生影响!
			if (nums[i] < n) { //这个值只有小于n才能产生得分，
				if (i <= nums[i]) {//下标小于等于当前的值，只要差分影响数值，肯定使用用连续的
					add(cnt, nums[i] - i, n - i - 1); //这两个后面的值用观察就行，num[i]-i整体移动步数到n-i-1步数给cnt这部分+1
				} else {//下面这个也是纯观察得到
					add(cnt, 0, n - i - 1);
					add(cnt, n - i + nums[i], n - 1);
				}
			}
		}
		//差分好了，加工出前缀和
		for (int i = 1; i <= n; i++) {
			cnt[i] += cnt[i - 1];
		}
		// 最大得分是啥！已经求出来了
		int max = cnt[0];
		int ans = 0;
		for (int i = n - 1; i >= 1; i--) { //这其实无所谓，直接推就行，和整体思路不影响
			// 整体移动的i 0 n-1 n-2 n-3 1
			//         k 0  1   2   3   n-1
 			if (cnt[i] > max) {
				max = cnt[i];
				ans = i; //返回下标
			}
		}
		return ans == 0 ? 0 : (n - ans); //题目是向左移动，但是我们做的时候是向右推的
	}

	public static void add(int[] cnt, int l, int r) { //差分数组l位置+1，r+1位置减1
		cnt[l]++;
		cnt[r + 1]--;
	}

}
