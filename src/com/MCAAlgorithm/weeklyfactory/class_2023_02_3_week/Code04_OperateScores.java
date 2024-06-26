package com.MCAAlgorithm.weeklyfactory.class_2023_02_3_week;

import java.util.Map.Entry;
import java.util.TreeMap;

// 来自TikTok美国笔试
// 给定一个长度为N的一维数组scores, 代表0~N-1号员工的初始得分
// scores[i] = a, 表示i号员工一开始得分是a
// 给定一个长度为M的二维数组operations,
// operations[i] = {a, b, c}
// 表示第i号操作为 : 
// 如果a==1, 表示将目前分数<b的所有员工，分数改成b，c这个值无用
// 如果a==2, 表示将编号为b的员工，分数改成c
// 所有操作从0~M-1, 依次发生
// 返回一个长度为N的一维数组ans，表示所有操作做完之后，每个员工的得分是多少
// 1 <= N <= 10的6次方
// 1 <= M <= 10的6次方
// 0 <= 分数 <= 10的9次方
public class Code04_OperateScores {
	// 思路：整体这道题整体分析出来，是一道非常好的题目，有双向链表有有序表，时间的优化不错，还有两个dummy node的挖掘，非常好@！！
    // [4,3,5,7,2] , 操作[1,4, X],1类型的操作，小于4的数全改成4；[2,3,3],2类型的操作，第3号员工改成3分，返回最后数组
	// 思路：1、分桶【3 2 4 1 3 6】，
	// 桶号：   1		2	    3       4         5         6
	// 员工：   3	    1      0\4      2                   5
	// 把所有小于2 改成2，把所有小于3分的改成3，O(n^2)，这样移动的话不行，时间复杂度太大了
	// 员工作为一个链表节点，有一个pre和next指针，如果把七号桶的元素a->b->c->d->e 移动到 8号桶f->g,只需要迁移头节点的指向，
	// 连接到8号桶节点的头节点，这样避免了每个桶中所有元素的移动。这样的时间复杂度O(n),一定要把这个时间复杂拉下来，才能搞
	// 思路：2、使用有序表，存放分值的key，value维持桶，如果key中的桶没有元素了，直接把这个key从有虚表里面删除
	//  并且一个2类型的操作，最多带来一个新桶！时间复杂度O(m)
	// 思路：3、精髓
	// 暴力方法
	// 为了验证
	public static int[] operateScores1(int[] scores, int[][] operations) {
		int n = scores.length;
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = scores[i];
		}
		for (int[] op : operations) {
			if (op[0] == 1) {
				for (int i = 0; i < n; i++) {
					ans[i] = Math.max(ans[i], op[1]);
				}
			} else {
				ans[op[1]] = op[2];
			}
		}
		return ans;
	}

	// 正式方法
	// 时间复杂度O((N + M) * logN)
	public static int[] operateScores2(int[] scores, int[][] operations) {
		int n = scores.length;
		Node[] nodes = new Node[n];
		// 得分 -> 桶
		TreeMap<Integer, Bucket> scoreBucketMap = new TreeMap<>();
		for (int i = 0; i < n; i++) {
			// 0号人，0号Node
			// i号人，i号Node
			nodes[i] = new Node(i);//建立每个人的节点连理解
			// 17分 -> 桶 ，当前人得17分，建立初始桶，每个桶中的员工，串成双向链表
			if (!scoreBucketMap.containsKey(scores[i])) {
				scoreBucketMap.put(scores[i], new Bucket());
			}
			scoreBucketMap.get(scores[i]).add(nodes[i]);//串成双向链表
		}
		// m次操作
		for (int[] op : operations) {
			if (op[0] == 1) { //1类型的操作
				// op[] = {1, 70, X}
				// < 70
				// merge 进 70号桶！
				// <= 69 且最近的
				// 13 45 53 65
				// 65 -> 70 65X
				// <= 69
				// 13 45 53
				// 53 -> 70 53X
				// 13 45
				// <= 69 记录没有了！
				// <= 69 有没有记录！
				Integer floorKey = scoreBucketMap.floorKey(op[1] - 1);
				// 1)有 <= 69的记录！ 70号得分的桶却没有，有记录但是没有更新70的桶
				if (floorKey != null && !scoreBucketMap.containsKey(op[1])) {
					scoreBucketMap.put(op[1], new Bucket());//加桶
				}
				while (floorKey != null) { //小于70号桶merge进70号桶，直到小于这个记录的桶没有了
					scoreBucketMap.get(op[1]).merge(scoreBucketMap.get(floorKey));
					scoreBucketMap.remove(floorKey); //把移动的桶干掉
					floorKey = scoreBucketMap.floorKey(op[1] - 1);//再往下找
				}
			} else {
				// 2类型
				// op = [2, 17号人，改成80分]
				// cur就是当前人的Node
				Node cur = nodes[op[1]];//目标人节点
				// 链接last  cur   next
				// 把last 和 next 串起来！cur去往新的桶
				cur.conectLastNext();//只需要把cur节点的pre和next节点处理好，把17扔到80号桶
				if (!scoreBucketMap.containsKey(op[2])) {//如果没有更新的桶
					scoreBucketMap.put(op[2], new Bucket());
				}
				scoreBucketMap.get(op[2]).add(cur);
			}
		}
		//还原并设置最终数组的得分
		int[] ans = new int[n];
		for (Entry<Integer, Bucket> entry : scoreBucketMap.entrySet()) {
			int score = entry.getKey();
			Bucket bucket = entry.getValue();
			Node cur = bucket.head.next;
			while (cur != bucket.tail) {
				ans[cur.index] = score;
				cur = cur.next;
			}
		}
		return ans;
	}

	// 桶，得分在有序表里！桶只作为有序表里的value，不作为key
	public static class Bucket {

		// 注意！
		// 头和尾都是假点！
		// 头和尾中间的节点才是真实节点
		// 头为假 ...中间节点才是有用的数据... 尾为假
		// 为什么这么实现，很关键！
		// 课上讲！
		public Node head = null;
		public Node tail = null;

		public Bucket() {
			//思路：这个就是链表的那个dummyhead和dummytail，因为每次拿节点的时候只知道拿的节点的信息，不知道整条链表的信息，所以
			// 维护这两个dummy部，可以免掉整条链表的结构！！！！
			head = new Node(-1);//做假头，这样避免头尾指针为空的判断，这样不需要遍历的
			tail = new Node(-1);
			head.next = tail;
			tail.last = head;
		}

		public void add(Node node) {
			node.last = tail.last;
			node.next = tail;
			tail.last.next = node;
			tail.last = node;
		}

		//两个桶合并在一起，
		// 不用每个Bucket的"假头"和"假尾"，只移动中间部分的东西
		public void merge(Bucket join) {
			if (join.head.next != join.tail) {
				tail.last.next = join.head.next;
				join.head.next.last = tail.last;
				join.tail.last.next = tail;
				tail.last = join.tail.last;
				join.head.next = join.tail;
				join.tail.last = join.head;
			}
		}

	}

	public static class Node {
		public int index;
		public Node last = null;
		public Node next = null;

		public Node(int i) {
			index = i;
		}

		public void conectLastNext() {
			last.next = next;
			next.last = last;
		}

	}

	// 为了测试
	public static int[] randomSocres(int n, int v) {
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = (int) (Math.random() * v);
		}
		return ans;
	}

	// 为了测试
	public static int[][] randomOperations(int n, int m, int v) {
		int[][] ans = new int[m][3];
		for (int i = 0; i < m; i++) {
			if (Math.random() < 0.5) {
				ans[i][0] = 1;
				ans[i][1] = (int) (Math.random() * v);
			} else {
				ans[i][0] = 2;
				ans[i][1] = (int) (Math.random() * n);
				ans[i][2] = (int) (Math.random() * v);
			}
		}
		return ans;
	}

	// 为了测试
	public static boolean isEqual(int[] arr1, int[] arr2) {
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// 为了测试
	public static void main(String[] args) {
		int N = 1000;
		int M = 1000;
		int V = 100000;
		int testTimes = 100;
		System.out.println("功能测试开始");
		for (int i = 0; i < testTimes; i++) {
			int n = (int) (Math.random() * N) + 1;
			int m = (int) (Math.random() * M) + 1;
			int[] scores = randomSocres(n, V);
			int[][] operations = randomOperations(n, m, V);
			int[] ans1 = operateScores1(scores, operations);
			int[] ans2 = operateScores2(scores, operations);
			if (!isEqual(ans1, ans2)) {
				System.out.println("出错了！");
			}
		}
		System.out.println("功能测试结束");

		System.out.println("性能测试开始");
		int n = 1000000;
		int m = 1000000;
		int v = 1000000000;
		int[] scores = randomSocres(n, v);
		int[][] operations = randomOperations(n, m, v);
		System.out.println("总人数 : " + n);
		System.out.println("操作数 : " + n);
		System.out.println("值范围 : " + v);
		long start = System.currentTimeMillis();
		operateScores2(scores, operations);
		long end = System.currentTimeMillis();
		System.out.println("运行时间 : " + (end - start) + " 毫秒");
		System.out.println("性能测试结束");
	}

}
