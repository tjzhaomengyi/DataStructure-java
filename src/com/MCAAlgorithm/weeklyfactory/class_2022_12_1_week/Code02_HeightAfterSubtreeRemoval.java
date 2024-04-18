package com.MCAAlgorithm.weeklyfactory.class_2022_12_1_week;

// 给你一棵 二叉树 的根节点 root ，树中有 n 个节点
// 每个节点都可以被分配一个从 1 到 n 且互不相同的值
// 另给你一个长度为 m 的数组 queries
// 你必须在树上执行 m 个 独立 的查询，其中第 i 个查询你需要执行以下操作：
// 从树中 移除 以 queries[i] 的值作为根节点的子树
// 题目所用测试用例保证 queries[i] 不 等于根节点的值。
// 返回一个长度为 m 的数组 answer ，其中 answer[i] 是执行第 i 个查询后树的高度。
// 注意：
// 查询之间是独立的，所以在每个查询执行后，树会回到其 初始 状态。
// 树的高度是从根到树中某个节点的 最长简单路径中的边数 。
// 测试链接 : https://leetcode.cn/problems/height-of-binary-tree-after-subtree-removal-queries/
public class Code02_HeightAfterSubtreeRemoval {

	// 提交时不用提交这个类
	// 思路：好题：1、生成dfn序，外围变量传统套路 2、生成每个节点对应子树的size 3、在递归的时候借助递归的传递性质，直接带出了每个节点对应的深度
	//  4、这些都生成完了，利用deep数组，生成每个点左右和右侧的最大值，然后在查询删除的时候，直接从这两个数组里面查询就行了【左神牛逼】
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	// 提交如下方法
	public static final int MAXN = 100010;
	public static int[] dfn = new int[MAXN];
	public static int[] deep = new int[MAXN];
	public static int[] size = new int[MAXN]; //每个子树i有几个节点，包括i节点
	public static int[] maxl = new int[MAXN]; //统计每个deep点的左侧的最大值
	public static int[] maxr = new int[MAXN]; //统计每个deep点的右侧的最大值
	public static int n;

	public static int[] treeQueries(TreeNode root, int[] queries) {
		n = 0; //使用n对每个节点进行编号
		// 每个val，编号
		// 每个val，深度
		// 每个val的子树，大小
		dfs(root, 0);
		//思路：生成每个点左侧的最大深度，和每个点右侧的最大深度，这个时候就方便后续查询
		for (int i = 1; i <= n; i++) {
			maxl[i] = Math.max(maxl[i - 1], deep[i]);
		}
		maxr[n + 1] = 0;
		for (int i = n; i >= 1; i--) {
			maxr[i] = Math.max(maxr[i + 1], deep[i]);
		}
		//思路：查删除后节点的最大高度，直到删除节点的dfn序号，还有该节点对应子树的大小，把这个整棵子树的节点"抠出去"剩下的最大深度是多少【左神牛逼】！
		int m = queries.length;
		int[] ans = new int[m];
		for (int i = 0; i < m; i++) {
			// queries[i] -> a
			// a -> 编号x
			// a -> 子树大小
			// x ... 子树大小这么多范围 删掉
			int leftMax = maxl[dfn[queries[i]] - 1];//左侧最大是谁？
			//右侧的最大是谁，中间范围"抠掉"了，
			int rightMax = maxr[dfn[queries[i]] + size[dfn[queries[i]]]];
			ans[i] = Math.max(leftMax, rightMax);
		}
		return ans;
	}

	// n = 0 1 2 3 4 5 6 7
	//生成dfn序 + 生成deep深度，生成每个节点对应子树的节点个数
	public static void dfs(TreeNode head, int h) {
		int i = ++n; //dfn序号从1开始，n是一个外层的公共变量，这个写法是生成dfn序的固定写法！
		dfn[head.val] = i;
		// 思路：根节点传进来，高度是0，往左孩子和右孩子走一步，递归调用的时候dfs（head.left, h + 1)这个高度自动往下传过去了
		deep[i] = h; //技巧：h表示当前的高度，每次往下调用这个递归的时候自动就传入这个递归的高度，自动的，自动的，这非常巧，之前没有弄过这个技巧！！！
		size[i] = 1; //第i个编号子树当前只有自己这个节点
		if (head.left != null) {
			dfs(head.left, h + 1); //dfn序号编好
			size[i] += size[dfn[head.left.val]]; //当前i节点子树 + 左子树大小
		}
		if (head.right != null) {
			dfs(head.right, h + 1);
			size[i] += size[dfn[head.right.val]];
		}
	}

}
