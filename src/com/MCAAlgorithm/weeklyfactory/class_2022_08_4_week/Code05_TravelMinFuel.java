package com.MCAAlgorithm.weeklyfactory.class_2022_08_4_week;

// 来自微软
// 给定两个数组A和B，比如
// A = { 0, 1, 1 }
// B = { 1, 2, 3 }
// A[0] = 0, B[0] = 1，表示0到1有双向道路
// A[1] = 1, B[1] = 2，表示1到2有双向道路
// A[2] = 1, B[2] = 3，表示1到3有双向道路
// 给定数字N，编号从0~N，所以一共N+1个节点
// 题目输入一定保证所有节点都联通，并且一定没有环
// 默认办公室是0节点，其他1~N节点上，每个节点上都有一个居民
// 每天所有居民都去往0节点上班
// 所有的居民都有一辆5座的车，也都乐意和别人一起坐车
// 车不管负重是多少，只要走过一条路，就耗费1的汽油
// 比如A、B、C的居民，开着自己的车来到D居民的位置，一共耗费3的汽油
// D居民和E居民之间，假设有一条路
// 那么D居民可以接上A、B、C，4个人可以用一辆车，去往E的话，就再耗费1的汽油
// 求所有居民去办公室的路上，最少耗费多少汽油
// 测试链接 : https://leetcode.cn/problems/minimum-fuel-cost-to-report-to-the-capital/
import java.util.ArrayList;

//给出的graph是一个无向图的表示，使用dfn序号防止重复遍历，也可以用set防止重复遍历
//其实这个三个数组就是把dfn，size和cost从多叉数递归套路拆了，可以用多叉树递归套路
public class Code05_TravelMinFuel {

	public static int cnt = 0;

	public static int minFuel(int[] a, int[] b, int n) {
		// 先建图
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < a.length; i++) {
			//两边都链接，无向图
			graph.get(a[i]).add(b[i]);
			graph.get(b[i]).add(a[i]);
		}
		// 建图完毕
		// 根据题目描述，办公室一定是0号点
		// 所有员工一定是往0号点汇聚

		//dfn和size数组都是为了生成cost数组服务
		// a 号，dfn[a] == 0 没遍历过！
		// dfn[a] != 0 遍历过！
		int[] dfn = new int[n + 1]; //dfn序号数组
		// a为头的树，一共有10个节点
		// size[a] = 0
		// size[a] = 10
		int[] size = new int[n + 1]; //以任何一个节点以自己为头，有多少个子节点，包括自己
		// 所有居民要汇总吗？
		// a为头的树，所有的居民是要向a来汇聚
		// cost[a] : 所有的居民要向a来汇聚，总油量的耗费
		int[] cost = new int[n + 1];
		cnt = 0; //开始分配dfn，把序号归0
		dfs(graph, 0, dfn, size, cost);//从办公室0开始往下遍历，cur是本来的编号，不是dfn序号
		return cost[0]; //最后大家都要去到0（办公室）
	}

	// 图 ： graph
	// 当前的头，原来的编号，不是dfn序号！ : cur
	// 从cur开始，请遍历
	// 遍历完成后，请把dfn[cur]填好！size[cur]填好！cost[cur]填好
	public static void dfs(ArrayList<ArrayList<Integer>> graph, int cur, int[] dfn, int[] size, int[] cost) {
		dfn[cur] = ++cnt; //cnt从1开始，把当前的节点执行dfn编号
		size[cur] = 1; //当前节点的子节点（要把自己包括进来）有多少个，当前只有自己，所以先设置为1
		//使用孩子节点填写出来
		for (int next : graph.get(cur)) {
			if (dfn[next] == 0) { //如果在dfn的序号不为0，说明这个节点已经填写过了，没有填写dfn序号的节点才是没有遍历过的，才是cur节点的子节点
				dfs(graph, next, dfn, size, cost);
				size[cur] += size[next];
				cost[cur] += cost[next]; // cur -> next -> a,b,c,cur节点先要"继承"next节点之前的花费！！！所以要+cost[next]
				cost[cur] += (size[next] + 4) / 5; //+4/5就是向上取整，是最后一下的花费,size[next]+4)/5（表示next到cur到底有几趟车）,是纯next自己到cur节点的花费
			}
		}
	}

	// 找到了这个题的在线测试链接 :
	// https://leetcode.cn/problems/minimum-fuel-cost-to-report-to-the-capital/
	// 如下方法提交了可以直接通过
	public static long minimumFuelCost(int[][] roads, int seats) {
		int n = roads.length;
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		for (int[] r : roads) {
			graph.get(r[0]).add(r[1]);
			graph.get(r[1]).add(r[0]);
		}
		int[] size = new int[n + 1];
		long[] cost = new long[n + 1];
		dfs(0, -1, seats, graph, size, cost);
		return cost[0];
	}

	public static void dfs(int cur, int father, int seats, ArrayList<ArrayList<Integer>> graph, int[] size,
			long[] cost) {
		size[cur] = 1;
		for (int next : graph.get(cur)) {
			if (next != father) {
				dfs(next, cur, seats, graph, size, cost);
				size[cur] += size[next];
				cost[cur] += cost[next];
				cost[cur] += (size[next] + seats - 1) / seats;
			}
		}
	}

}
