package com.MCAAlgorithm.bigshua.class37;

import java.util.HashMap;

//繁衍题:前缀和 变 树形结构,这个也是套路题，用hashmap记录前缀和的个数
public class Problem_0437_PathSumIII {

	public class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	public static int pathSum(TreeNode root, int sum) {
		HashMap<Integer, Integer> preSumMap = new HashMap<>();
		preSumMap.put(0, 1); //技巧:这里要提前把开头没有和为0的放进去
		return process(root, sum, 0, preSumMap);
	}

	// 返回方法数
	public static int process(TreeNode x, int sum, int preAll, HashMap<Integer, Integer> preSumMap) {
		if (x == null) {
			return 0;
		}
		int all = preAll + x.val; //先求presum与当前节点的总和
		int ans = 0;
		if (preSumMap.containsKey(all - sum)) { //再看总和减去sum是否出现在map 中
			ans = preSumMap.get(all - sum); //
		}
		if (!preSumMap.containsKey(all)) {
			preSumMap.put(all, 1);
		} else {
			preSumMap.put(all, preSumMap.get(all) + 1);
		}
		ans += process(x.left, sum, all, preSumMap);
		ans += process(x.right, sum, all, preSumMap);
		//技巧:dfs当前这里要恢复现场，线索信息要擦干净，
		if (preSumMap.get(all) == 1) {
			preSumMap.remove(all);
		} else {
			preSumMap.put(all, preSumMap.get(all) - 1);
		}
		return ans;
	}

}
