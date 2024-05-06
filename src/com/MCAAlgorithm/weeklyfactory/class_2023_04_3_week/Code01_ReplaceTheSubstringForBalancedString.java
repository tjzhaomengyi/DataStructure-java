package com.MCAAlgorithm.weeklyfactory.class_2023_04_3_week;

// 来自华为OD
// 完美走位问题
// 给定一个由'W'、'A'、'S'、'D'四种字符组成的字符串，长度一定是4的倍数
// 你可以把任意连续的一段子串，变成'W'、'A'、'S'、'D'组成的随意状态
// 目的是让4种字符词频一样
// 返回需要修改的最短子串长度
// 找到了出处，是leetcode原题
// 测试链接 : https://leetcode.cn/problems/replace-the-substring-for-balanced-string/
public class Code01_ReplaceTheSubstringForBalancedString {

	// 去年刷的这道题，这种题给学历比较差，但是算法可以的学生，但凡简历好一点勤一点，华为OD的性价比非常低
	//思路：窗口，这道题如何建立窗口中的单调性，在A窗口的四个字符能做到整体四种字符一样多，当有B窗口囊括A窗口，也可以做到四种字符一样多，这个就是这道题窗口的单调性
	// B窗口是通过A窗口左扩充或者右扩充获得的，里面调整好的不变，调整外围的。
	// 整体过程：从0开始让子串中四个字符一样多，0-3能做到，1滑动能让四个字符一样多多长。窗口中，四种字符可以随意变换。如果窗口内是L到R，窗口以外W=10，A=8，S=10，D=6
	// 数学结论：窗口内部满足两个条件就符合条件：（1）让词频最少的追满，6->10,8->10;(2)剩下的位置可以被4整除！就做到了
	// Q W E R
	// 0 1 2 3
	public static int balancedString(String str) {
		int n = str.length();
		int[] arr = new int[n];
		int[] cnts = new int[4];
		//统计方向出现的词频
		for (int i = 0; i < n; i++) {
			char c = str.charAt(i);
			arr[i] = c == 'W' ? 1 : (c == 'E' ? 2 : (c == 'R' ? 3 : 0));//把字符变成数字
			cnts[arr[i]]++;
		}
		int ans = n;
		// L = 0......
		//      1.....
		//       2....r
		//        3......
		for (int l = 0, r = 0; l < n; l++) {//窗口从每个左边界开始扩到r能不能满足要求
			// !ok(cnts, l, r) , 当前窗口[l....r)，如果不能让四种字符一样多！
			// && r < n，虽然没达标，但是还有努力空间
			while (!ok(cnts, l, r) && r < n) {
				cnts[arr[r++]]--;//因为r要进来，所以外面的词频要--
			}
			//思路：从上面的while跳出来只有两种可能：ok了，依然不ok，没有努力空间了
			// 1) ok了
			// 2) 依然不ok，而且也没努力空间了
			if (ok(cnts, l, r)) { //更新全局最小的ans
				ans = Math.min(ans, r - l);
			} else { //思路：【这个条件其实不好想】不ok，也没有空间了，大的范围搞定不了，小的范围也不行了【单调性！单调性！】，这里使用了思路的单调性，
				break;
			}
			cnts[arr[l]]++;
		}
		return ans;
	}

	// 数学结论：这个ok方法直接用思路里面的数学结论
	// 窗口，str[l.....r)【窗口传统套路了，左闭右开】，你可以自由变化，但是窗口外的不能变化
	// l = 3, r = 10
	// [3....9]
	// 窗口长度 = r - l
	// cnts，窗口之外每一种字符的词频统计，不能算窗口内的统计的！
	// w : cnts[0]
	// a : cnts[1]
	// s : cnts[2]
	// d : cnts[3]
	// cnts只有4长度
	public static boolean ok(int[] cnts, int l, int r) {
		// 窗口外最大词频是多少
		int maxCnt = Math.max(Math.max(cnts[0], cnts[1]), Math.max(cnts[2], cnts[3]));
		// 需要多少空间，拉平？changes
		// maxCnt - cnts[0] + maxCnt - cnts[1] maxCnt - cnts[2] maxCnt - cnts[3]   
		int changes = maxCnt * 4 - cnts[0] - cnts[1] - cnts[2] - cnts[3];
		int rest = r - l - changes; //窗口减掉changes，剩余空间能不能把四个字符平分掉
		return rest >= 0 && rest % 4 == 0;
	}

}
