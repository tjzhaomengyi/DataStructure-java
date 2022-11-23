package com.mikemyzhao.DPAndRecursion_5.BackTracking.bigshua;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-22 9:13
 * @Description:一个字符串切多少刀可以得到所有的子串都是回文子串
 */
public class OneStringSplitToPalindrome_11 {
  //先存储回文串，每个位置的情况，后续可以直接查该表
  public static boolean[][] createCheckMap(char[] str, int N){
    boolean[][] ans = new boolean[N][N];//从i到j是否是回文
    for(int i = 0; i < N - 1; i++){
      ans[i][i] = true;
      ans[i][i + 1] = str[i] == str[i + 1];
    }
    ans[N - 1][N - 1] = true;
    for(int i = N - 3; i >= 0; i--){
      for(int j = i + 2; j < N; j++){
        ans[i][j] = str[i] == str[j] && ans[i + 1][j - 1]; //当前ij的字符相等并且中间的也是回文
      }
    }
    return ans;
  }

  public static int minCut(String s){
    if(s == null || s.length() < 2){
      return 0;
    }
    char[] str = s.toCharArray();
    int N = str.length;
    boolean[][] checkMap = createCheckMap(str,N);
    int[] dp = new int[N];
    dp[N - 1] = 0;
    for(int i = N - 1; i >= 0; i--){
      if(checkMap[i][N - 1]){
        dp[i] = 1;
      }else{
        int next = Integer.MAX_VALUE;
        for(int j = i; j < N; j++){
          if(checkMap[i][j]){
            next = Math.min(next, dp[j +1]);
          }
        }
        dp[i] = next + 1;//前面划分出来的，在加上当前这组
      }
    }
    return dp[0] - 1;
  }

  // 本题第二问，返回其中一种结果
  public static List<String> minCutOneWay(String s) {
    List<String> ans = new ArrayList<>();
    if (s == null || s.length() < 2) {
      ans.add(s);
    } else {
      char[] str = s.toCharArray();
      int N = str.length;
      boolean[][] checkMap = createCheckMap(str, N);
      int[] dp = new int[N + 1];
      dp[N] = 0;
      for (int i = N - 1; i >= 0; i--) {
        if (checkMap[i][N - 1]) {
          dp[i] = 1;
        } else {
          int next = Integer.MAX_VALUE;
          for (int j = i; j < N; j++) {
            if (checkMap[i][j]) {
              next = Math.min(next, dp[j + 1]);
            }
          }
          dp[i] = 1 + next;
        }
      }
      // dp[i]  (0....5) 回文！  dp[0] == dp[6] + 1
      //  (0....5)   6
      for (int i = 0, j = 1; j <= N; j++) {
        if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
          ans.add(s.substring(i, j));
          i = j;
        }
      }
    }
    return ans;
  }

  // 本题第三问，返回所有结果
  public static List<List<String>> minCutAllWays(String s) {
    List<List<String>> ans = new ArrayList<>();
    if (s == null || s.length() < 2) {
      List<String> cur = new ArrayList<>();
      cur.add(s);
      ans.add(cur);
    } else {
      char[] str = s.toCharArray();
      int N = str.length;
      boolean[][] checkMap = createCheckMap(str, N);
      int[] dp = new int[N + 1];
      dp[N] = 0;
      for (int i = N - 1; i >= 0; i--) {
        if (checkMap[i][N - 1]) {
          dp[i] = 1;
        } else {
          int next = Integer.MAX_VALUE;
          for (int j = i; j < N; j++) {
            if (checkMap[i][j]) {
              next = Math.min(next, dp[j + 1]);
            }
          }
          dp[i] = 1 + next;
        }
      }
      process(s, 0, 1, checkMap, dp, new ArrayList<>(), ans);
    }
    return ans;
  }

  // s[0....i-1]  存到path里去了
  // s[i..j-1]考察的分出来的第一份
  public static void process(String s, int i, int j, boolean[][] checkMap, int[] dp,
                             List<String> path,
                             List<List<String>> ans) {
    if (j == s.length()) { // s[i...N-1]
      if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
        path.add(s.substring(i, j));
        ans.add(copyStringList(path));
        path.remove(path.size() - 1);
      }
    } else {// s[i...j-1]
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

}
