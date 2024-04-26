package com.MCAAlgorithm.weeklyfactory.class_2023_01_2_week;

// 线段树上的拓扑排序，难死了，想都想不到
// 一条单向的铁路线上，火车站编号为1~n
// 每个火车站都有一个级别，最低为 1 级。
// 现有若干趟车次在这条线路上行驶，
// 每一趟都满足如下要求：
// 如果这趟车次停靠了火车站 x，则始发站、终点站之间所有级别大于等于火车站x的都必须停靠。
//（注意：起始站和终点站自然也算作事先已知需要停靠的站点）
// 现有 m 趟车次的运行情况（全部满足要求），
// 试推算这n个火车站至少分为几个不同的级别。
// 测试链接 : https://www.luogu.com.cn/problem/P1983
// 线段树建边
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下所有代码，把主类名改成Main，可以直接通过
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
// 例子：1 5 7 车站停了，经过1234567车站，每个车站有不同等级。条件：没停靠的车站等级比停靠车站的等级小，告诉你停靠的车站和等级，猜一下没停靠车站的等级能有几个？
// 等级	6	3	7	3	7
// 站号	1	2	3	4	5
// 上面的例子说明2、3、4等级不如1和5，
// 数学结论：这个关系都连成了得到一个拓扑排序，就是求拓扑排序的最大深度是多少？通过广播来更新每个节点的深度
// 假设1-5个车站，停了1、5两个车站，说明2、3、4不如1，2、3、4不如5，又停了一个4车站， 2、3不如4，这样不如的描述连接一个箭头
// a->d->b,d在（2）的时候向b又广播了一次b从（2）变成（3）
// a --> b(3) --> e
//	.    .
//    .
//		 .
// c---->d (2)
// 注意：车站最大1000量，每辆车都连1000个，边和点的数量巨大，不行了。需要其他优化，建立线段树，只需要利用线段树那个范围生成每个节点的id，其他操作都没有。。
// 把车站建立线段树：12345678,开始正常从上往下建立，如果停靠车站是{2,7},建立虚拟点x，让虚拟点连2的点，2到7的中间是3456，让3-4和5-6连x这个虚拟点
//        1-8 a
//   1-4 b	    5-8 c
//1-2d  3-4e   5-6f	7-8p
//1  2x	        5y    7k
// e这个点不如x，f也不如y，生成脑洞去吧，生成一个M点，这个M点表示e和f不如M，但是M不如x和k
// 本质意思就是假如1-5不如6，现在在1-5中间加个虚拟点x，1-5不如x，x不如6，线段树从下往上看1->[1-2]->[1-5]->x->6,1不如6，怎么求这个深度【虚拟点和范围点不算】这个深度就是2，这个叫线段树建边
// 过程：拿着线段树的每个点去建图，一旦处理了一个停靠就新加一个虚拟点x，如果这个点是3，那么就把相应的点（1-3）去连x，然后，连完求深度，碰到单个点算，范围点和虚拟点不算
// 例子：以上面1-8为例子，如果【2，7】停放，生成虚拟点x，然后x->7表示x不如7，x连上2，表示x不如2， 中间的3456的区间表示【3-4】【5-6】就可以连上
public class Code05_BusStationsMinLevelNumbers {

	public static final int maxn = 100001;
	// 1 500 600 1000
	// stops[1,500,600,1000]
	// 停靠车站
	public static int[] stops = new int[maxn];
	// 一段线段树范围的id编号
	// id[rt] = x，rt背后的范围这一段，给它的点编号是x
	// rt -> 线段树的某个范围的固有属性，l~r,rt
	public static int[] id = new int[maxn << 2]; //编号后的点
	
	
	
	// id点是否为单点
	// a 单点 范围 虚拟点？
	// 70~90 rt = 60 -> 17 single[17] ? 
	public static boolean[] single = new boolean[maxn << 3];
	
	
	// id点的入度
	public static int[] inDegree = new int[maxn << 3];
	// id点拓扑排序统计的最大深度(只算路径上的单点数量)
	public static int[] singleDeep = new int[maxn << 3];
	// 链式前向星建图用
	public static int[] head = new int[maxn << 3];
	public static int[] to = new int[maxn << 3];
	public static int[] next = new int[maxn << 3];
	// 拓扑排序用
	public static int[] queue = new int[maxn << 3];
	// n为车站个数、nth为线段树上范围的编号计数、eth为边的计数
	public static int n, nth, eth;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			n = (int) in.nval;
			in.nextToken();
			int m = (int) in.nval;
			nth = 0;
			eth = 0;
			Arrays.fill(single, 0, (n << 2) + m + 1, false);
			Arrays.fill(inDegree, 0, (n << 2) + m + 1, 0);
			Arrays.fill(singleDeep, 0, (n << 2) + m + 1, 0);
			build(1, n, 1);
			for (int i = 0; i < m; i++) {
				in.nextToken();
				int k = (int) in.nval;
				for (int j = 0; j < k; j++) {
					in.nextToken();
					stops[j] = (int) in.nval;
				}
				int curVirtual = ++nth;
				// 虚点向停靠车站连边
				for (int j = 0; j < k; j++) {
					vLinkStop(curVirtual, stops[j], 1, n, 1);
				}
				// 不停靠的连续车站向虚点连边
				for (int j = 1; j < k; j++) {
					if (stops[j] > stops[j - 1] + 1) {
						rangeLinkV(stops[j - 1] + 1, stops[j] - 1, curVirtual, 1, n, 1);
					}
				}
			}
			out.println(topoSort());
			out.flush();
		}

	}

	//rt是来到第几个车站，l到r是线段树节点的范围
	public static void build(int l, int r, int rt) {
		id[rt] = ++nth;//给线段树点编号
		if (l == r) { //单点
			single[id[rt]] = true;
		} else {
			int m = (l + r) / 2;
			build(l, m, rt << 1);
			build(m + 1, r, rt << 1 | 1);
			addEdge(id[rt << 1], id[rt]); //左孩子拉一条边连向父节点，使用id编号，
			addEdge(id[rt << 1 | 1], id[rt]); //右孩子拉一条边连向父节点
		}
	}

	//虚拟点连范围点
	public static void rangeLinkV(int L, int R, int vid, int l, int r, int rt) {
		if (L <= l && r <= R) {
			addEdge(id[rt], vid);
		} else {
			int m = (l + r) / 2;
			if (L <= m) {
				rangeLinkV(L, R, vid, l, m, rt << 1);
			}
			if (R > m) {
				rangeLinkV(L, R, vid, m + 1, r, rt << 1 | 1);
			}
		}
	}

	// 17 17~17 虚拟点连单独点的停靠站
	public static void vLinkStop(int vid, int stop, int l, int r, int rt) {
		if (l == r) {
			addEdge(vid, id[rt]);
		} else {
			int m = (l + r) / 2;
			// 1~100
			// 想去的车站是70 70~70
			// 1~50 51~100
			if (stop <= m) {
				vLinkStop(vid, stop, l, m, rt << 1);
			} else {
				vLinkStop(vid, stop, m + 1, r, rt << 1 | 1);
			}
		}
	}

	public static void addEdge(int fid, int tid) {
		inDegree[tid]++;
		to[++eth] = tid;
		next[eth] = head[fid];
		head[fid] = eth;
	}

	public static int topoSort() {
		int l = 0;
		int r = 0;
		for (int i = 1; i <= nth; i++) {
			if (inDegree[i] == 0) {
				queue[r++] = i;
				if (single[i]) {
					singleDeep[i] = 1;
				}
			}
		}
		int ans = 0;
		while (l < r) {
			int curNode = queue[l++];
			ans = Math.max(ans, singleDeep[curNode]);
			for (int edgeIndex = head[curNode]; edgeIndex != 0; edgeIndex = next[edgeIndex]) {
				int child = to[edgeIndex];
				singleDeep[child] = Math.max(singleDeep[child], singleDeep[curNode] + (single[child] ? 1 : 0));
				if (--inDegree[child] == 0) {
					queue[r++] = child;
				}
			}
		}
		return ans;
	}

}