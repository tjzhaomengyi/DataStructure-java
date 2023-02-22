package com.mikemyzhao.TreeClassicSytle.pretree.bigshua;

import java.util.HashSet;


/**
 * @Author: zhaomengyi
 * @Date: 2022-03-19 15:06
 * @Description:给定一个字符串，给定一个字典数组，字典中每个词可以无限使用来组成该字符串，求一共多少方法
 */
public class WaysToBuildString_7_sameas_0139 {
  //方法1：使用递归。每次从i开始做前缀一直到end，然后对该前缀进行字典中查询
  public static int ways(String str, String[] arr){
    HashSet<String> set  = new HashSet<>();
    for(String candidate : arr){
      set.add(candidate);
    }
    return process(str, 0, set);
  }

  public static int process(String str, int i, HashSet<String> set){
    if(i == str.length()){
      return 1;//如果i到了length长度，说明找到了一种方法
    }
    int ways = 0;
    //注意这里有循环尝试，每次都要对前缀进行查询看字典里有没有这个字符串
    for(int end = i; end < str.length(); end++){
      String pre = str.substring(i, end + 1);
      if(set.contains(pre)){
        ways += process(str, end + 1, set);
      }
    }
    return ways;
  }


  //太难了~直接记住例子吧 使用前缀树的方法，减少查询过程
  public static class Node{
    public boolean end;
    public Node[] nexts;

    public Node(){
      end = false;
      nexts = new Node[26];
    }
  }
  public static int ways1(String str, String[] arr){
    if(str == null || str.length() == 0 || arr == null || arr.length == 0){
      return 0;
    }
    //todo:构建字典类型的前缀树
    Node root = new Node();
    for(String s : arr){
      char[] chs = s.toCharArray();
      Node node = root;
      int index = 0;
      for(int i = 0; i < chs.length; i++){
        index = chs[i] - 'a';
        if(node.nexts[index] == null){
          node.nexts[index] = new Node();
        }
        node = node.nexts[index];
      }
      node.end = true;
    }
    return g(str.toCharArray(),root,0);
  }

  public static int g(char[] str, Node root, int i){
    if(i == str.length){
      return 1;
    }
    int ways = 0;
    Node cur = root;
    for(int end = i; end < str.length; end++){
      int path = str[end] - 'a';
      if(cur.nexts[path] == null){
        break;
      }
      cur = cur.nexts[path];
      if(cur.end){
        ways += g(str,root,end+1);
      }
    }
    return ways;
  }

  //字典树加动态规划
  public static int ways4(String s, String[] arr) {
    if (s == null || s.length() == 0 || arr == null || arr.length == 0) {
      return 0;
    }
    Node root = new Node();
    for (String str : arr) {
      char[] chs = str.toCharArray();
      Node node = root;
      int index = 0;
      for (int i = 0; i < chs.length; i++) {
        index = chs[i] - 'a';
        if (node.nexts[index] == null) {
          node.nexts[index] = new Node();
        }
        node = node.nexts[index];
      }
      node.end = true;
    }
    char[] str = s.toCharArray();
    int N = str.length;
    int[] dp = new int[N + 1];
    dp[N] = 1;//从N位置开始拿，这个已经不用了，说明前面已经都走过是一种拿的方法了
    for (int i = N - 1; i >= 0; i--) {
      Node cur = root;
      for (int end = i; end < N; end++) {
        int path = str[end] - 'a';
        if (cur.nexts[path] == null) {
          break;
        }
        cur = cur.nexts[path];
        if (cur.end) {
          dp[i] += dp[end + 1];//dp[i]会有多种end结尾的可能所以要加
        }
      }
    }
    return dp[0];
  }
}
