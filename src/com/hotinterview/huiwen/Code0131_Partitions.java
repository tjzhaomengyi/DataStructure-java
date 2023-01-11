package com.hotinterview.huiwen;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-22 5:19 下午
 * @Description:
 */
public class Code0131_Partitions {
  //得到字符串从i到j是否能组成回文串，返回数组ans
  public boolean[][] createCheckMap(char[] str, int N) {
    boolean[][] ans = new boolean[N][N];//从i到j是否是回文
    for (int i = 0; i < N - 1; i++) {
      ans[i][i] = true;
      ans[i][i + 1] = str[i] == str[i + 1];
    }
    ans[N - 1][N - 1] = true;
    for (int i = N - 3; i >= 0; i--) {
      for (int j = i + 2; j < N; j++) {
        ans[i][j] = str[i] == str[j] && ans[i + 1][j - 1];//这两个位置字符相等，且内部是回文
      }
    }
    return ans;
  }

  //这个方法是根据最优解得到分解方法，左神
  public List<List<String>> partition(String s) {
    if (s == null || s.length() == 0) return null;
    List<List<String>> ans = new ArrayList<>();
    if (s == null || s.length() < 2) {
      List<String> cur = new ArrayList<>();
      cur.add(s);
      ans.add(cur);
    } else {
      char[] str = s.toCharArray();
      int N = str.length;
      boolean[][] checkMap = createCheckMap(str, N);//第一个动态规划找到字符串中的子回文串
      int[] dp = new int[N + 1];//字符串长为N的时候能够拆出多少个回文子串
      dp[N] = 0;
      for (int i = N - 1; i >= 0; i--) {//从dp[N]往前推，
        if (checkMap[i][N - 1]) { //如果i到N-1是回文整体就是回文
          dp[i] = 1;
        } else { // 如果i到N-1本身不是回文，那么就判断i到j内部是否是回文,dp[i]要依赖i+1后面的结果
          int next = Integer.MAX_VALUE;
          for (int j = i; j < N; j++) {
            //太难了~直接记住例子吧：aaeeffcff-dp是[3322122110]
            if (checkMap[i][j]) { //如果i到j是回文
              next = Math.min(next, dp[j + 1]);//思路：收集j+1到结束可以分多少段！
            }
          }
          dp[i] = 1 + next;//无论怎么样都得加上自己这个1
        }
      }
      //从0到1开始检查，然后一点点向后递归
      process(s, 0, 1, checkMap, dp, new ArrayList<>(), ans);
      for(int i = 0; i < dp.length; i++){
        System.out.print(dp[i] + ",");
      }
      System.out.println();
    }
    return ans;
  }

  //这里的i和j表示位置
  //0 ~ i-1已经放在了path中
  //要考察 i 到 j-1 怎么存放
  //太难了~直接记住例子吧：aaeeffcff dp[3322122110]
  public void process(String s, int i, int j, boolean[][] checkMap, int[] dp, List<String> path, List<List<String>> ans) {
    if (j == s.length()) {//遍历结束
      //思路：只要dp[i] = dp[j] + 1,前面等于后面+1这部分就是可以拆出来的回文
      if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
        path.add(s.substring(i, j));
        ans.add(copyStringList(path));
        path.remove(path.size() - 1);
      }
    } else {
      if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
        path.add(s.substring(i, j));
        process(s, j, j + 1, checkMap, dp, path, ans);
        path.remove(path.size() - 1);
      }
      process(s, i, j + 1, checkMap, dp, path, ans);
    }

  }
  public static List<String> copyStringList(List<String> list) {
    List<String> ans = new ArrayList<>();
    for (String str : list) {
      ans.add(str);
    }
    return ans;
  }

  //这个方法是使用dfs给出暴力解，是131的题解
  public List<List<String>> partition_131(String s){
    if(s.length() == 0 || s == null){
      return null;
    }
    List<List<String>> ans = new ArrayList<>();
    List<String> path = new ArrayList<>();
    char[] str = s.toCharArray();
    int N = str.length;
    boolean[][] checkMap = createCheckMap(str, N);
    dfs(s,0, N, checkMap, path, ans);
    return ans;
  }

  public void dfs(String s, int index, int len, boolean[][] dp, List<String> path, List<List<String>> ans){
    if(index == len){
      ans.add(new ArrayList<>(path));
      return;
    }
      for(int i = index; i < len; i++){
        if(dp[index][i]){//思路：只要这个位置到i是回文就开始深度优先遍历，跟傻逼赛的
          path.add(s.substring(index, i + 1));
          dfs(s, i + 1, len, dp, path, ans);
          path.remove(path.size() - 1);
        }
      }

  }

  public static void main(String[] args) {
    List<List<String>> res = new Code0131_Partitions().partition("fcfffcffcc");
    List<List<String>> res131 = new Code0131_Partitions().partition_131("efe");
    System.out.println(res);
    for (List<String> way : res) {
      for (String str : way) {
        System.out.print(str + " ");
      }
      System.out.println();
    }
    System.out.println(res131);
    for(List<String> way : res131){
      for(String str : way) {
        System.out.print(str + " ");
      }
    }
  }


}
