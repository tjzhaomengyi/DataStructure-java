package com.mikemyzhao.TreeOperations_2.bigshua.InfoClassSkill;


/**
 * @Author: zhaomengyi
 * @Date: 2022-04-19 10:33
 * @Description:二叉树最大同值路径问题
 */
public class LC_0687_LongestSameValPath {
  public static class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int v) {
      val = v;
    }
  }

  // 建设以x节点为头的树，返回两个信息
  public static class Info {
    // 在一条路径上：要求每个节点通过且只通过一遍
    public int len; // 技巧:路径必须从x出发且只能往下走的情况下，路径的最大距离
    public int max; // 技巧:路径不要求必须从x出发的情况下，整棵树的合法路径最大距离

    public Info(int l, int m) {
      len = l;
      max = m;
    }
  }
  public static int longestUnivaluePath(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return process(root).max - 1;
  }
  private static Info process(TreeNode x){
    if(x == null){
      return new Info(0, 0);
    }
    TreeNode l = x.left;
    TreeNode r = x.right;
    Info linfo = process(l);
    Info rinfo = process(r);

    //必须从x出发的情况下，往下的最大路径
    int len = 1;
    if(l != null && l.val == x.val){
      len = linfo.len + 1;
    }
    if(r != null && r.val == x.val){
      len = Math.max(len, rinfo.len + 1);
    }
    //不从x出发最大路径
    int max = Math.max(Math.max(linfo.max, rinfo.max), len);
    if(l != null && r != null && l.val == x.val && r.val == x.val){
      max = Math.max(max, linfo.len + rinfo.len + 1);
    }
    return new Info(len, max);
  }
}
