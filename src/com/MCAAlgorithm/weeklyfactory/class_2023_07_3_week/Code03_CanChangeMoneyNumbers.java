package com.MCAAlgorithm.weeklyfactory.class_2023_07_3_week;

// 每一种货币都给定面值val[i]，和拥有的数量cnt[i]
// 想知道目前拥有的货币，在钱数为1、2、3...m时，能找零成功的钱数有多少
// 也就是说当钱数的范围是1~m，返回这个范围上有多少可以找零成功的钱数
// 比如只有3元的货币，数量是5张
// m = 10
// 那么在1~10范围上，只有钱数是3、6、9时，可以成功找零
// 所以返回3，表示有3种钱数可以找零成功
// 测试链接 : http://poj.org/problem?id=1742
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Code03_CanChangeMoneyNumbers {
   //思路：如何不实用zhang的枚举理解找零问题，dp[i][j] 0到i号货币是否能够找开j元，如果上面的格子dp[i-1][j]=true,那么不用i货币dp[i][j]=true，
   //  如果是false，尝试1张i号货币看看能不能找开，然后去问问dp[i-1][j-i]是不是true，如果是false，那么就看dp[i-2][j-2i]是不是能找开
	// 思路：2、【下标分组】把钱模3，模结果不同的数放入不同的组，这样可以把遍历的时候往左侧寻找的过程变成窗口，比如0,3,6,9,12,15,18,21,来到21的时候窗口维持住，看看窗口里面有几个true
	//  就是利用窗口滑动省掉枚举！！！
	public static int MAXN = 101;

	public static int[] val = new int[MAXN];

	public static int[] cnt = new int[MAXN];

	public static int MAXM = 100001;

	public static boolean[] dp = new boolean[MAXM]; //用一维做空间压缩

	public static int n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			n = (int) in.nval;
			in.nextToken();
			m = (int) in.nval;
			if (n != 0 || m != 0) {
				for (int i = 1; i <= n; i++) {
					in.nextToken();
					val[i] = (int) in.nval;
				}
				for (int i = 1; i <= n; i++) {
					in.nextToken();
					cnt[i] = (int) in.nval;
				}
				out.println(compute());
			} else {
				out.flush();
			}
		}
	}

	public static int compute() {
		Arrays.fill(dp, 1, m + 1, false);
		dp[0] = true;
		// 每一种货币，遍历
		for (int i = 1; i <= n; i++) {
			if (cnt[i] == 1) {
				// // 等于当前货币就1张
				for (int j = m; j >= val[i]; j--) {
					if (dp[j - val[i]]) {
						dp[j] = true;
					}
				}
			} else if (val[i] * cnt[i] > m) { //等价于随便用，等同于完全背包问题
				// 等于当前货币无限张
				for (int j = val[i]; j <= m; j++) {
					if (dp[j - val[i]]) {
						dp[j] = true;
					}
				}
			} else {
				// 既不是1张，也不是无限张
				for (int mod = 0; mod < val[i]; mod++) { //根据余数分组，在一组计算答案
					// val[i] = 3元
					// 0 : ....
					// 1 : ....
					// 2 : ....
					int trueCnt = 0;
					// 0 : m元 m-3元 m-6元 m-9元
					// 1 : m-1元 m-4元 m-7元 m-10元 
					// 2 : m-2元 
					//
					// 3元，4张
					//
					//
					//     9  12  【15  18  21  24】
					//   j是要凑的钱数
					for (int j = m - mod, size = 0; j >= 0 && size <= cnt[i]; j -= val[i], size++) {
						trueCnt += dp[j] ? 1 : 0;
					}
//				    // 9  12  【15  18  21  24】，思路：让24出去，让12进来，算此时24是true还是false
					// 9 【12  15  18  21】 24
					// 每次窗口出去一个下标，j=m-mod就是最后一个值
					// 进来一个下标
					for (int j = m - mod, l = j - val[i] * (cnt[i] + 1); j >= 1; j -= val[i], l -= val[i]) {
						// dp[j] = 上一行的值
						// dp[j] 更新成 本行的值
						
						if (dp[j]) { // 思路：让24出去，如果此时24是true，那么24的值不变，改变truecnt数量的统计就完了
							trueCnt--;
						} else { //思路：dp[24]不等于true
							if (trueCnt != 0) { //思路：但是，truecnt不等于0，说明15、18、21也有true，可以凑到24！！！！！24是有救的！！！把dp[24]改成true！！！
								dp[j] = true;
							}
						}
						if (l >= 0) {//思路：把l边界拉进来！窗口左移动，回到上面循环中，窗口右侧挪到21了
							trueCnt += dp[l] ? 1 : 0;
						}
					}
				}
			}
		}
		int ans = 0;
		for (int i = 1; i <= m; i++) {
			if (dp[i]) {
				ans++;
			}
		}
		return ans;
	}

}
