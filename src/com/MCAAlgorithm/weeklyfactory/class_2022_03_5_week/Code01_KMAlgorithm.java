// 注意本文件中，graph不是邻接矩阵的含义，而是一个二部图
// 在长度为N的邻接矩阵matrix中，所有的点有N个，matrix[i][j]表示点i到点j的距离或者权重
// 而在二部图graph中，所有的点有2*N个，行所对应的点有N个，列所对应的点有N个
// 而且认为，行所对应的点之间是没有路径的，列所对应的点之间也是没有路径的！

package com.MCAAlgorithm.weeklyfactory.class_2022_03_5_week;

import java.util.Arrays;

// km算法
// O(N^3)，最大匹配问题，最优的解！
public class Code01_KMAlgorithm {

	// 暴力解
	public static int right(int[][] graph) {
		int N = graph.length;
		int[] to = new int[N];
		for (int i = 0; i < N; i++) {
			to[i] = 1;
		}
		return process(0, to, graph);
	}

	public static int process(int from, int[] to, int[][] graph) {
		if (from == graph.length) {
			return 0;
		}
		int ans = 0;
		for (int i = 0; i < to.length; i++) {
			if (to[i] == 1) {
				to[i] = 0;
				ans = Math.max(ans, graph[from][i] + process(from + 1, to, graph));
				to[i] = 1;
			}
		}
		return ans;
	}

	public static int km(int[][] graph) {
		int N = graph.length;
		int[] match = new int[N];//当前匹配的主，N表示公主，match[i]=爷们，如果为0说明还没有配对
		int[] lx = new int[N]; //爷们的预期
		int[] ly = new int[N]; //暂时成功配对的公主
		// dfs过程中，碰过的点！
		boolean[] x = new boolean[N]; //表示王子是否被遍历过
		boolean[] y = new boolean[N]; //表示公主是否配对成功
		// 降低的预期！
		// 公主上，打一个，降低预期的值，只维持最小！
		int[] slack = new int[N]; //记录预期降低值，维持每个点降低预期的最小值,只在公主身上打这个分，并且这个值只更新已经考虑过的公主和王子再加上当前要参与进来的王子
		int invalid = Integer.MAX_VALUE;
		//1、初始化王子和公主预期值
		for (int i = 0; i < N; i++) {
			match[i] = -1;
			lx[i] = -invalid;
			for (int j = 0; j < N; j++) {
				lx[i] = Math.max(lx[i], graph[i][j]);//lx[i]王子的预期
			}
			ly[i] = 0;//公主的预期都是0
		}
		//2、一个王子解决匹配问题
		for (int from = 0; from < N; from++) {
			for (int i = 0; i < N; i++) {//这个在KM-code02里面讲解的
				slack[i] = invalid; //每个王子过来，所有公主的期望值先设置成系统最大值，每个公主目的是要维持一个小的期望值
			}
			Arrays.fill(x, false);//所有王子都没尝试过
			Arrays.fill(y, false);//所有公主都没有配对成功
			// dfs() : from王子，能不能不降预期，匹配成功！
			// 能：dfs返回true！
			// 不能：dfs返回false！
			while (!dfs(from, x, y, lx, ly, match, slack, graph)) {
				// 刚才的dfs，失败了！
				// 需要拿到，公主的slack里面，预期下降幅度的最小值！这个slack值在dfs过程中进行调整
				int d = invalid;//遍历过和当前王子要降低的预期
				for (int i = 0; i < N; i++) {
					if (!y[i] && slack[i] < d) { //y[i]公主没有被成功配对，并且这公主的slack[i]小于降低预期值
						d = slack[i]; //降低预期值
					}
				}
				// 按照最小预期来调整预期
				for (int i = 0; i < N; i++) {
					if (x[i]) {
						lx[i] = lx[i] - d; //所有王子预期减小
					}
					if (y[i]) {
						ly[i] = ly[i] + d; //所有配对成功的公主预期增加
					}
				}
				//为了下一个王子，公主把自己的
				Arrays.fill(x, false);
				Arrays.fill(y, false);
				// 然后回到while里，再次尝试
			}
		}
		//最后获取答案，每个公子和公主都配好对了，预期就是结果值，求和即可
		int ans = 0;
		for (int i = 0; i < N; i++) {
			ans += (lx[i] + ly[i]);
		}
		return ans;
	}

	// from, 当前的王子
	// x，王子碰没碰过
	// y, 暂时成功配对好的公主，尝试连接过并且在王子期望值下允许的为true；
	// 		如果公主哪个都没有别选过，全为false，此时计算的下降预期值都为lx[i]-0;
	//		一旦，王子期望值+公主期望值-礼金值=0，那么这个王子满足了，赶紧和公主建立连接，把公主调整为已经连接状态
	// 		没有王子期望值为9，连接值为10，那么这个尝试连就是false【注意y数组的解释！！！！】
	// lx，所有王子的预期
	// ly, 所有公主的预期
	// match，所有公主，之前的分配，之前的爷们！
	// slack，连过，但没允许的公主，最小下降的幅度
	// map，报价，所有王子对公主的报价
	// 返回，from号王子，不降预期能不能配成！
	public static boolean dfs(int from, boolean[] x, boolean[] y, int[] lx, int[] ly, int[] match, int[] slack,
			int[][] map) {
		int N = map.length;
		x[from] = true; //把当前进来的王子设置为遍历过
		for (int to = 0; to < N; to++) { //to表示王子尝试把公主都尝试一遍
			if (!y[to]) { // 如果没有到过或者没有成功过,都为false
				//下面要通过d值判断公主和王子是否能凑成一对，
				int d = lx[from] + ly[to] - map[from][to]; //计算一下要降低的预期值
				if (d != 0) {//如果等于0说明是刚进来，如果不是0说明要更新了，要根据这个公主的要求调整slack期望值幅度
					slack[to] = Math.min(slack[to], d);//把此时降低的预期值赋值给slack[to],停止往下执行，调整完幅度值，不需要往下递归了
				} else { //如果这个值等于0，说明此时找到了王子的最大期望值，让y[to]被找到
					y[to] = true;//此时，礼金路径值正好满足王子和公主的期望，把二者建立连接
					//如果这个公主没有人拿直接交给该王子，如果有人拿过这个公主让那个人尝试选选别的公主，如果原配王子可以找到一个新的公主，那么当前from王子获得to公主
					if (match[to] == -1 || dfs(match[to], x, y, lx, ly, match, slack, map)) {
						match[to] = from; //把公主赋值
						return true; //这个王子找到了公主结束！
					}
				}
			}
		}
		return false;
	}

	// 为了测试
	public static int[][] randomGraph(int N, int V) {
		int[][] graph = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				int num = (int) (Math.random() * V);
				graph[i][j] = num;
				graph[j][i] = num;
			}
		}
		return graph;
	}

	// 为了测试
	public static void main(String[] args) {
		int N = 10;
		int V = 20;
		int testTime = 100;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[][] graph = randomGraph(N, V);
			int ans1 = right(graph);
			int ans2 = km(graph);
			if (ans1 != ans2) {
				System.out.println("Oops!");
				for (int r = 0; r < graph.length; r++) {
					for (int c = 0; c < graph.length; c++) {
						System.out.print(graph[r][c] + " ");
					}
					System.out.println();
				}
				System.out.println(ans1);
				System.out.println(ans2);
			}
		}
		System.out.println("测试结束");
	}

}
