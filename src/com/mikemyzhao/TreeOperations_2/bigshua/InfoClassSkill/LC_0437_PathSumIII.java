package com.mikemyzhao.TreeOperations_2.bigshua.InfoClassSkill;

import com.MCAAlgorithm.bigshua.class37.Problem_0437_PathSumIII;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-19 12:15
 * @Description:
 */
public class LC_0437_PathSumIII {
  public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
  }

  public static int pathSum(TreeNode root, int sum){
    HashMap<Integer, Integer> preSumMap = new HashMap<>();//技巧:DFS记录走过的路径
    preSumMap.put(0, 1);//这个特别重要
    return process(root, sum, 0, preSumMap);
  }
  //preAll表示前缀和 All = sum + find
  public static int process(TreeNode x, int sum, int preAll, HashMap<Integer, Integer> preSumMap){
    if(x == null){
      return 0;
    }
    int all = preAll + x.val;//preAll记录之前的和 all表示当前x结尾的前缀和，所以用all - sum = find如果find找到说明有记录这个的前缀和
    int ans = 0;
    if(preSumMap.containsKey(all - sum)){ //all - sum 表示以当前x结尾
      ans = preSumMap.get(all - sum);
    }
    if(!preSumMap.containsKey(all)){
      preSumMap.put(all, 1);
    } else {
      preSumMap.put(all, preSumMap.get(all) + 1);
    }
    ans += process(x.left, sum, all, preSumMap);
    ans += process(x.right, sum, all, preSumMap);
    //技巧:DFS把当前这个节点删了
    if(preSumMap.get(all) == 1){
      preSumMap.remove(all);
    } else {
      preSumMap.put(all, preSumMap.get(all) - 1);
    }
    return ans;
  }
}
