package com.MCAAlgorithm.weeklyfactory.class_2022_08_5_week;

// 给定一个由 '[' ，']'，'('，‘)’ 组成的字符串
// 请问最少插入多少个括号就能使这个字符串的所有括号左右配对
// 例如当前串是 "([[])"，那么插入一个']'即可满足
// 输出最少插入多少个括号
// 测试链接 : https://www.nowcoder.com/practice/e391767d80d942d29e6095a935a5b96b
// 提交以下所有代码，把主类名改成Main，可以直接通过
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Code02_MinAddToMatch {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(minAdd(line.toCharArray()));
		}
	}




	public static int minAdd(char[] s) {
		int n = s.length;
		int[][] dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				dp[i][j] = -1;
			}
		}
		return process(s, 0, s.length - 1, dp);
	}

	//课上憋的
	//f（str，0，str.length - 1)
	// str[L..R]范围上，让这个范围上的字符串变合法，至少填几个字符
	// 让s[l...r]都完美匹配
	// 至少需要加几个字符
	public static int process(char[] s, int l, int r, int[][] dp) {
		// 两个Base case
		// 只有一个字符，不管是什么，要想配对，都需要添加一个字符
		if (l == r) { //str上l到r上只有一个字符了！所以至少要添加一个字符
			return 1;
		}
		// 只有两个字符，
		// 如果是()、[]，那什么也不需要添加
		// 否则，都需要添加2个字符
		if (l == r - 1) { //str的l到r上还有两个字符
			if ((s[l] == '(' && s[r] == ')') || (s[l] == '[' && s[r] == ']')) { //这两个情况什么都不用添加了，合理
				return 0;
			}
			return 2; //）], (]类似这种情况都要添加两个字符
		}

		//从l到r有若干个字符的情况：
		// 1）L到R 是嵌套的括号关系[()()()]
		// 2）L到R 是并列的括号关系(()) [] []
		if (dp[l][r] != -1) {
			return dp[l][r];
		}
		// 重点是如下的过程
		// 可能性1嵌套关系，先搞定l+1...r，然后搞定l
		// 比如s[l...r] = ([][]
		// 先搞定[][]，需要添加0个，然后搞定(，需要添加1个
		// 整体变成([][])搞定
		// l....r -> l l+1....r ?
		int p1 = 1 + process(s, l + 1, r, dp); //这个1表示让l和r处理的，剩下的由l+1到r来确认
		// 可能性2另外一种嵌套情况，先搞定l...r-1，然后搞定r
		// 和可能性1同理
		// l...r -> ? l...r-1 r
		// [][])
		int p2 = 1 + process(s, l, r - 1, dp); //l到r-1变合法，然后再让l到r合法。上面的例子中，process部分已经合法了，所以L到R变合法+1即可。
		// l( ...r) l+1..r-1
		// l[    r] l+1..r-1
		// 可能性3嵌套外围可能性合理，s[l]和s[r]天然匹配，需要搞定的就是l+1..r-1
		// 比如([[)，搞定中间的[[，就是最优解了
		int p3 = Integer.MAX_VALUE;
		if ((s[l] == '(' && s[r] == ')') || (s[l] == '[' && s[r] == ']')) {
			p3 = process(s, l + 1, r - 1, dp);
		}

		//并列的可能性
		// l......r
		// l..l   l+1..r
		// l..l+1 l+2..r
		// l...l+2 l+3..r
		// 可能性后续：可能，最优解并不是l....r整体变成最大的嵌套
		// 而是，并列关系！
		// l....split 先变成合法
		// split+1...r 再变成合法
		// 是并列的关系！
		// 比如(())[[]]
		// l...split : (())
		// split+1...r : [[]]
		// 这种并列关系下，有可能出最优解
		// 所以，枚举每一个可能的并列划分点(split)
		int ans = Math.min(p1, Math.min(p2, p3));
		for (int split = l; split < r; split++) { //每个划分线都枚举一遍
			ans = Math.min(ans, process(s, l, split, dp) + process(s, split + 1, r, dp));
		}
		dp[l][r] = ans;
		return ans;
	}

}
