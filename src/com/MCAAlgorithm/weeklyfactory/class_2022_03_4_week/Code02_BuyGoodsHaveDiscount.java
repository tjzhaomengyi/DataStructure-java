package com.MCAAlgorithm.weeklyfactory.class_2022_03_4_week;

// 来自字节内部训练营【非常好非常好的题】
// 某公司游戏平台的夏季特惠开始了，你决定入手一些游戏。现在你一共有X元的预算。
// 平台上所有的 n 个游戏均有折扣，标号为 i 的游戏的原价a_i元，现价只要b_i元
// 也就是说该游戏可以优惠 a_i - b_i，并且你购买该游戏能获得快乐值为 w_i
// 由于优惠的存在，你可能做出一些冲动消费导致最终买游戏的总费用超过预算，
// 只要满足 : 获得的总优惠金额不低于超过预算的总金额
// 那在心理上就不会觉得吃亏。
// 现在你希望在心理上不觉得吃亏的前提下，获得尽可能多的快乐值。
// 测试链接 : https://leetcode-cn.com/problems/tJau2o/
// 提交以下的code，将主类名字改成"Main"
// 可以直接通过
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;


public class Code02_BuyGoodsHaveDiscount {

	//这道题非常有意思，不是花给的真钱，而是用一个心里价位来买，然后变成背包问题
	// 转化成心里价位的过程：
	// 商品价格10元 | 真实价格6元 | 折扣4元 | 买家心里预估10元
	// 这个情况买家直接买5件最爽，获得的总优惠5 * 4 = 20， 超出心里预估5*6 - 10 = 20，总有花大于等于超过的预算，
	// 但是如果买了6件那么不行，总优惠6*4 = 24，超出心里预估价格6*6-10 = 26，那么总优惠小于了超出预算，
	// 转化过程（1）用优惠价-折扣价，就是心里价位 4-6 = -2，消耗了心里预估10元中的两元
	// (2)如果优惠价-折扣价 > 0,那么心里价位增加，比如真实价格4元，折扣价格6元，那么10+2=12，并且这样的东西必买！！！
	//转换好心里价位剩下的就计算背包问题
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			int n = (int) in.nval;
			in.nextToken();
			int money = (int) in.nval;
			int[] costs = new int[n];
			long[] values = new long[n];
			int size = 0;
			long ans = 0;
			for (int i = 0; i < n; i++) {
				// 打折前
				in.nextToken();
				int pre = (int)in.nval;
				// 打折后
				in.nextToken();
				int pos = (int)in.nval;
				// 满足度
				in.nextToken();
				int happy = (int)in.nval;
				// 节省的钱(save) = 打折前(pre) - 打折后(pos)
				int save = pre - pos;
				// 带来的好处(well) = 节省的钱 - 打折后(pos)
				int well = save - pos;
				// 比如，一件"一定要买的商品":
				// 预算 = 100，商品原价 = 10，打折后 = 3
				// 那么好处 = (10 - 3) - 3 = 4
				// 所以，这件商品把预算增加到了104，一定要买
				// 接下来，比如一件"需要考虑的商品"，预算 = 104，商品原价 = 10，打折后 = 8
				// 那么好处 = (10 - 8) - 8 = -6
				// 这件商品，就花掉6元！
				// 也就是说，以后花的不是打折后的值，是"坏处"
				int cost = -well;
				if (well >= 0) {
					money += well;
					ans += happy;
				} else {
					costs[size] = cost;
					values[size++] = happy;
				}

			}
			long[][] dp = new long[size + 1][money + 1];
			for (int a = 0; a <= size; a++) {
				for (int b = 0; b <= money; b++) {
					dp[a][b] = -2;
				}
			}
			ans += process(costs, values, size, 0, money, dp);
			out.println(ans);
			out.flush();
		}
	}

	public static long process(int[] costs, long[] values, int size, int i, int money, long[][] dp) {
		if (money < 0) {
			return -1;
		}
		if (i == size) {
			return 0;
		}
		if (dp[i][money] != -2) {
			return dp[i][money];
		}
		long p1 = process(costs, values, size, i + 1, money, dp);
		long p2 = -1;
		long next = process(costs, values, size, i + 1, money - costs[i], dp);
		if (next != -1) {
			p2 = values[i] + next;
		}
		long ans = Math.max(p1, p2);
		dp[i][money] = ans;
		return ans;
	}

}