package com.mikemyzhao.DP.DP.bigshua.sample;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-18 11:17
 * @Description:将字符串str1删除为str2，最少删除多少个字符
 */
public class DeleteStr1ToStr2_5 {
  //解法1：使用编辑距离问题求解，把删除设置为1，其他操作设置为MAX
  public static int minCost1(String s1,String s2){
    if(s1.length() == 0 || s2.length() == 0){
      return s2.length();
    }
    int ans = Integer.MAX_VALUE;
    char[] str2 = s2.toCharArray();
    for(int start = 0; start < s1.length(); start++){
      for(int end = start + 1; end <= s1.length();end++){
        //两个循环生成所有子序列
        ans = Math.min(ans,distance(str2,s1.substring(start,end).toCharArray()));
      }
    }
    return ans == Integer.MAX_VALUE ? s2.length() : ans;
  }
  public static int distance(char[] str2, char[] s1sub){
    int row = str2.length + 1;
    int col = s1sub.length + 1;
    int[][] dp = new int[row][col];
    for(int i = 0; i < row; i++){
      for(int j = 0; j < col; j++){
        dp[i][j] = Integer.MAX_VALUE;
      }
    }
    dp[0][0] = 0;
    for(int i = 1; i <= row; i++){
      dp[i][0] = i;
    }
    for(int xlen = 1;xlen <= row; xlen++){
      for(int ylen = 1;ylen <= Math.min(col,xlen);ylen++){
        if(dp[xlen-1][ylen] != Integer.MAX_VALUE){
          dp[xlen][ylen] = dp[xlen - 1][ylen] + 1;
        }
        if(str2[xlen - 1] == s1sub[ylen - 1] && dp[xlen - 1][ylen - 1] != Integer.MAX_VALUE){
          dp[xlen][ylen] = Math.min(dp[xlen][ylen],dp[xlen - 1][ylen - 1]);
        }
      }
    }
    return dp[row][col];
  }

  //如果s2待删除的字符串长度比较小可以用子串全排列，然后从大到小挨个和s1比对的方式
  public static int minCost2(String s1, String s2){
    List<String> s2subs = new ArrayList<>();
    process(s2.toCharArray(), 0, "", s2subs);
    s2subs.sort(new LenComp());
    for(String str : s2subs){
      if(s1.indexOf(str) != -1){//直接在s1中找s2的索引，底层就是KMP
        return s2.length() - str.length();
      }
    }
    return s2.length();
  }

  //递归方式生成全部子序列
  public static void process(char[] str2, int index, String path, List<String> list){
    if(index == str2.length){
      list.add(path);
      return;
    }
    process(str2,index + 1, path, list);//用index位置字符
    process(str2, index + 1, path + str2[index], list);//不用index位置的字符
  }

  public static class LenComp implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
      return o2.length() - o1.length();//从大到小排列
    }
  }

  //把s1中相同同字符的开头位置存成链表，然后在遍历s2的时候和每个链表进行比对，借助一个辅助map完成整体比对O(N*M)
  public static int minCost4(String s1,String s2){
    char[] str1 = s1.toCharArray();
    char[] str2 = s2.toCharArray();
    HashMap<Character, ArrayList<Integer>> map1 = new HashMap();
    for(int i = 0; i < str1.length; i++){
      ArrayList<Integer> list = map1.getOrDefault(str1[i],new ArrayList<Integer>());
      list.add(i);
      map1.put(str1[1], list);
    }
    int ans = 0;
    //假设删除后的str2以i开头，找str1中也是以i开头的开始对比删除
    for (int i = 0; i < str2.length; i++){
      if(map1.containsKey(str2[i])){
        ArrayList<Integer> keyList = map1.get(str2[i]);
        for(int j = 0; j < keyList.size(); j++){
          int cur1 = keyList.get(j) + 1;//在str1上卡住开头
          int cur2 = i + 1;//在str2上的开始往后捋
          int count = 1;//在map1中存在说明找到了一个字符相等
          for (int k = cur2; k < str2.length && cur1 < str1.length; k++) {//注意这个遍历的写法
            if (str2[k] == str1[cur1]) {
              cur1++;
              count++;
            }
          }
          ans = Math.max(ans,count);
        }
      }
    }
    return s2.length() - ans;
  }

}
