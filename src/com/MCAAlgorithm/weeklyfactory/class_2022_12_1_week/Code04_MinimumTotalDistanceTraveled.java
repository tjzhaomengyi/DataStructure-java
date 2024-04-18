package com.MCAAlgorithm.weeklyfactory.class_2022_12_1_week;

import java.util.Arrays;
import java.util.List;

// X轴上有一些机器人和工厂。给你一个整数数组robot，其中robot[i]是第i个机器人的位置
// 再给你一个二维整数数组factory，其中 factory[j] = [positionj, limitj]
// 表示第 j 个工厂的位置在 positionj ，且第 j 个工厂最多可以修理 limitj 个机器人
// 每个机器人所在的位置 互不相同。每个工厂所在的位置也互不相同
// 注意一个机器人可能一开始跟一个工厂在相同的位置
// 所有机器人一开始都是坏的，他们会沿着设定的方向一直移动
// 设定的方向要么是 X 轴的正方向，要么是 X 轴的负方向
// 当一个机器人经过一个没达到上限的工厂时，这个工厂会维修这个机器人，且机器人停止移动
// 任何时刻，你都可以设置 部分 机器人的移动方向
// 你的目标是最小化所有机器人总的移动距离
// 请你返回所有机器人移动的最小总距离
// 注意：
// 所有机器人移动速度相同
// 如果两个机器人移动方向相同，它们永远不会碰撞
// 如果两个机器人迎面相遇，它们也不会碰撞，它们彼此之间会擦肩而过
// 如果一个机器人经过了一个已经达到上限的工厂，机器人会当作工厂不存在，继续移动
// 机器人从位置 x 到位置 y 的移动距离为 |y - x|
// 1 <= robot.length, factory.length <= 100
// factory[j].length == 2
// -10^9 <= robot[i], positionj <= 10^9
// 0 <= limitj <= robot.length
// 测试数据保证所有机器人都可以被维修
// 测试链接 : https://leetcode.cn/problems/minimum-total-distance-traveled/
public class Code04_MinimumTotalDistanceTraveled {

	public static long minimumTotalDistance1(List<Integer> robot, int[][] factory) {
		int n = robot.size();
		int m = factory.length;
		robot.sort((a, b) -> a - b);
		Arrays.sort(factory, (a, b) -> a[0] - b[0]);
		long[][] dp = new long[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				dp[i][j] = -1;
			}
		}
		return process1(robot, factory, n - 1, m - 1, dp);
	}

	public static long process1(List<Integer> robot, int[][] factory, int i, int j, long[][] dp) {
		if (i < 0) {
			return 0;
		}
		if (j < 0) {
			return Long.MAX_VALUE;
		}
		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		long ans = process1(robot, factory, i, j - 1, dp);
		long distance = 0;
		for (int l = i, num = 1; l >= 0 && num <= factory[j][1]; l--, num++) {
			long curAns = process1(robot, factory, l - 1, j - 1, dp);
			distance += Math.abs(robot.get(l) - factory[j][0]);
			if (curAns != Long.MAX_VALUE) {
				ans = Math.min(ans, curAns + distance);
			}
		}
		dp[i][j] = ans;
		return ans;
	}

	//
	public static long minimumTotalDistance2(List<Integer> robot, int[][] factory) {
		int n = robot.size();
		int m = factory.length;
		robot.sort((a, b) -> a - b);
		Arrays.sort(factory, (a, b) -> a[0] - b[0]);
		//dp[i][j]表示0到i号机器人一定要进0到j号工厂，一个标准的从左往右的尝试
		// 思路：j号工厂要么没有机器人，要不就放入1到工厂容量个机器人，遍历尝试获得每个尝试的最小距离
		// 可能性：所有机器人就不进j号工厂，只有一个机器人进j号工厂，只有两个机器人进j号工厂，直到达到j号工厂的容量
		long[][] dp = new long[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// ans = dp[i][j - 1] -> 0...i -> 0...j-1
				long ans = j - 1 >= 0 ? dp[i][j - 1] : Long.MAX_VALUE;//0到i号机器人不放入j号工厂
				long distance = 0;
				//这个for循环的大小就是工厂的容量
				for (int l = i, num = 1; l >= 0 && num <= factory[j][1]; l--, num++) {//
					long curAns = l - 1 < 0 ? 0 : (j - 1 < 0 ? Long.MAX_VALUE : dp[l - 1][j - 1]);//让l以及l以后的机器人进入j工厂，0到l-1进0到j-1的工厂
					distance += Math.abs(robot.get(l) - factory[j][0]); //计算l号机器人到j号工厂的距离
					if (curAns != Long.MAX_VALUE) {
						ans = Math.min(ans, curAns + distance);
					}
				}
				dp[i][j] = ans;
			}
		}
		return dp[n - 1][m - 1];
	}

	// 卧槽，看看左神对dp问题枚举的优化，绝了，绝了，到头了，如果没有左神能优化的枚举，基本就没了
	// 最优解O(N*M)，目前所有题解都没有达到这个程度的，
	// 借助窗口，不太好理解，上面的dp解慢是因为有枚举，怎么优化这个枚举,把迪桑for循环优化掉
	// 思路：1、定义P(i）函数，来到0位置机器人，来到j号工厂，我们把0号机器人只放入到0到j-1位置的工厂，j号工厂不用，我们把这个动作记作P(0),那么，我们来到5号机器人，
	//  P（5）表示0到5号机器人只放入到0到j-1号工厂中，j号工厂不放入0到5号的机器人，P(i)就表示0到x机器人只进前j-1个工厂。
	//  思路：2、假设目标工厂的容量是3 ，然后根据0到2个物品进行递推归纳
	//   	（1）在只有0号机器人的时候，来到j工厂，第一种可能性P(0) ,第二种可能0号机器人就放入到j，消耗距离j+0（0表示之前没有机器人，j暂时表示0号机器人走到j的距离）
	//   	（2）在只有0和1号机器人的时候，来到j工厂，第一种可能性 P(1)【01根本不进j工厂】 第二种选择：1进j号工厂 + P（0）【1号进入j工厂，0号不进j工厂】
	// 			第三种可能：1进j工厂 + 0进j工厂 + 0
	//      （3）在只有0、1和2机器人的时候，来到j工厂，第一种可能性P（2） 第二种可能性：2进j工厂+P（1） 第三种可能性：2进j工厂+1进j工厂+P（0）
	// 			第四种可能性：2进j工厂 + 1进j工厂 + 0进j工厂 + 0
	// 思路：3、窗口内最小值的更新结构，新建窗口里面只有一个【0】,distance(i,j),表示i号机器人到j工厂移动多少步。重新回顾下上面的内容，
	// 	3-1 来到0号机器人需要什么 （1）dp[0][j-1] 就是P（0） （2）0 到 j的移动距离，计作add=distance(0,j) （3）【这里这里就这里就是窗口最小值关注的东西】再加一个0，那个表示之前没有移动的机器人，这三部分决策出来最小填入dp表中！
	//  然后窗口中放入什么东西，注意了，左神就是在这里优化的【0，P(0)-distance(0,j)】
	//  3-2 【此时窗口中内容：【0,P(0)-distance(0,j)】】来到0，1号机器人，add累加变成distance（0，j） + distance（1，j），
	//  终点来了思路2的（2）步骤整理一下三个部分分别是：P（1） 、1->j + P(0)【add + 窗口中P(0) + 1->j】 、1->j + 0->j + 0 【这部分可以通过add + 窗口中的0得到】
	//  【得到结论】：窗口中的最小值 + add变量即可，就是最优解！！！！！！
	//  3-3  【此时窗口内容：【0，P(0)- distance(0,j), P(1) - distance(0,j) - distance(1,j)】】,add变成=distance（0，j） + distance（1，j） + distance(2, j)
	//  思路2-（3）的四种情况 ，最后想知道哪种情况最好：
	//  P（2）、
	//  2->j + P(1)【窗口里的第二项 + add】 、
	//  2->j + 1->j + P(0) 、 【窗口里的第一项 + add】
	//  2->j + 1->j + 0->j + 0 【窗口里的0 + add】
	// 【得到结论】：窗口中的最小值 + add变量即可，这就是就是最优解！！！！！！
	//  思路：4、窗口如何移动，挪动窗口左右边界
	//  例子，对容量3个的j工厂，0个机器人到2号机器人，一旦达到3个以上机器人，缩窗口左边界
	public static long minimumTotalDistance3(List<Integer> robot, int[][] factory) {
		int n = robot.size();
		int m = factory.length;
		robot.sort((a, b) -> a - b);
		Arrays.sort(factory, (a, b) -> a[0] - b[0]);
		long[][] dp = new long[n][m];
		long[][] deque = new long[n + 1][2]; //窗口，看下面代码对deque的解释，左神又来了，咱们怂逼就来个双端队列就完了
		int l = 0;
		int r = 0;
		// 最外的for循环一定是枚举工厂，因为思路4，所以外层是工厂，里面是机器人，把机器人在每个工厂都尝试了
		for (int j = 0; j < m; j++) {
			long add = 0;
			long limit = factory[j][1];
			l = 0;
			r = 1;
			// deque[l][0] : 加入时的下标，用来判断过期
			deque[l][0] = -1;
			// deque[l][1] : 加入时的指标，窗口选出最小指标 去加上当前add！
			deque[l][1] = 0;
			for (int i = 0; i < n; i++) {
				long p1 = j - 1 >= 0 ? dp[i][j - 1] : Long.MAX_VALUE; //思路里面的P(i)
				add += Math.abs((long) robot.get(i) - (long) factory[j][0]); //add每次往上累加当前这个机器人到factory[j]的距离！！
				if (deque[l][0] == i - limit - 1) { //如果来到当前位置i，最左的位置不能让当前的factory接纳了，那么就把l扔了，左边窗口缩
					l++;
				}
				long p2 = Long.MAX_VALUE;
				if (l < r) { //干掉枚举行为！！！！
					long best = deque[l][1];//窗口内最小值的更新结构中，最小值永远在最左侧，所以直接拿到最小值，塞给add【枚举就在这优化了】
					if (best != Long.MAX_VALUE) {
						p2 = add + best;
					}
				}
				dp[i][j] = Math.min(p1, p2);
				long fill = p1 == Long.MAX_VALUE ? p1 : (p1 - add); //往窗口中加入的东西
				while (l < r && deque[r - 1][1] >= fill) {//窗口内容不如fill，让窗口原来的内容滚，怎么滚让r缩窗口啊
					r--;
				}
				deque[r][0] = i; //下标进来
				deque[r++][1] = fill; //窗口新的填充物
			}
		}
		return dp[n - 1][m - 1];
	}

}
