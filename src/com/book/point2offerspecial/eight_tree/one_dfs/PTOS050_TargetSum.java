package com.book.point2offerspecial.eight_tree.one_dfs;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-10 11:35
 * @Description:
 */
public class PTOS050_TargetSum {
  public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }
  //太难了~直接记住例子吧，就是用HashMap记录出现和的次数
//  public int pathSum(TreeNode root, int targetSum) {
//    Map<Integer, Integer> preSumMap = new HashMap<>();//标记走过的路径，key表示target大小，value表示得到该和的路径数
//    preSumMap.put(0, 1);//解决一个节点的问题，如果是8，那么反推回去是有一个路径的
//    return dfs(root,targetSum,0,preSumMap);
//  }
//
//  public int dfs(TreeNode node,int targetSum, int path, Map<Integer, Integer> preSum){
//    if(node == null){
//      return 0;
//    }
//    int all = path + node.val;
//    int count = preSum.getOrDefault(all - targetSum,0);
//    preSum.put(all, preSum.getOrDefault(all, 0) + 1);
//
//    count += dfs(node.left, targetSum, all, preSum);
//    count += dfs(node.right, targetSum, all, preSum);
//
//    preSum.put(all, preSum.get(all) - 1);
//
//    return count;
//  }

  public int pathSum(TreeNode root, int sum){
    Map<Long, Integer> map = new HashMap<>();
    map.put(Long.valueOf(0), 1);
    return dfs(root, Long.valueOf(sum), map, Long.valueOf(0));
  }

  public int dfs(TreeNode root, Long sum, Map<Long, Integer> map, Long path){
    if(root == null){
      return 0;
    }
    path += root.val;
    int count = map.getOrDefault(path - sum, 0);
    map.put(path, map.getOrDefault(path, 0) + 1);
    count += dfs(root.left, sum, map, path);
    count += dfs(root.right,sum, map, path);
    map.put(path, map.get(path) - 1);
    return count;
  }

}
