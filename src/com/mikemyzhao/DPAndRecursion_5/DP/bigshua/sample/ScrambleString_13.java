package com.mikemyzhao.DPAndRecursion_5.DP.bigshua.sample;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-24 11:45
 * https://leetcode.com/problems/scramble-string/
 * 扰乱字符串问题
 */
public class ScrambleString_13 {
  //每次枚举第一刀的位置看看左右两侧能不能恢复,说白了，每次变化的正确解都在这一刀上，这一刀保证左右两侧都是有效顺序
  public static boolean isScramble(String s1,String s2){
    if((s1 == null && s2 != null) || (s1 != null && s2 == null)){
      return false;
    }
    if(s1 == null && s2 == null){
      return true;
    }
    if(s1.equals(s2)){
      return true;
    }
    char[] str1 = s1.toCharArray();
    char[] str2 = s2.toCharArray();
    //首先要保证两个字符串的种类和个数一样
    if(!sameTypeSameNumber(str1,str2)){
      return false;
    }
    return process0(str1,0,str1.length-1,str2,0,str2.length-1);
  }

  //str1[l1.r1]区间和str2[l2,r2]区间是否是幻变串，这两个区间长度一样
  public static boolean process0(char[] str1, int L1, int R1, char[] str2, int L2, int R2){
    if(L1 == R1){
      return str1[L1] == str2[L2];
    }
    for(int leftEnd = L1; leftEnd < R1; leftEnd++){
      boolean p1 = process0(str1, L1, leftEnd, str2, L2, L2 + (leftEnd - L1))
          && process0(str1,leftEnd + 1, R1, str2, L2 + (leftEnd - L1) + 1, R2);
      boolean p2 = process0(str1, L1, leftEnd, str2, R2- (leftEnd - L1),R2)
          && process0(str1, leftEnd +1 , R1, str2, L2, R2 - (leftEnd - L1) - 1);
      if(p1 || p2){
        return true;
      }
    }
    return false;
  }


  //process0的四个参数可以改成三个参数L1L2和size
  public static boolean process1(char[] str1, char[] str2, int L1, int L2, int size){
    if (size == 1) {
      return str1[L1] == str2[L2];
    }
    // 枚举每一种情况，有一个计算出互为旋变就返回true。都算不出来最后返回false
    for (int leftPart = 1; leftPart < size; leftPart++) {
      if (
        // 如果1左对2左，并且1右对2右
          (process1(str1, str2, L1, L2, leftPart)
              && process1(str1, str2, L1 + leftPart, L2 + leftPart, size - leftPart)) ||
              // 如果1左对2右，并且1右对2左
              (process1(str1, str2, L1, L2 + size - leftPart, leftPart)
                  && process1(str1, str2, L1 + leftPart, L2, size - leftPart))) {
        return true;
      }
    }
    return false;
  }

  public static boolean isScramble2(String s1, String s2) {
    if ((s1 == null && s2 != null) || (s1 != null && s2 == null)) {
      return false;
    }
    if (s1 == null && s2 == null) {
      return true;
    }
    if (s1.equals(s2)) {
      return true;
    }
    char[] str1 = s1.toCharArray();
    char[] str2 = s2.toCharArray();
    if (!sameTypeSameNumber(str1, str2)) {
      return false;
    }
    int N = s1.length();
    int[][][] dp = new int[N][N][N + 1];
    //dp[i][j][k]：0没算过，1：true; -1：false
    return process2(str1, str2, 0, 0, N, dp);
  }
  public static boolean process2(char[] str1, char[] str2, int L1, int L2, int size, int[][][] dp){
    if(dp[L1][L2][size] != 0){
      return dp[L1][L2][size] == 1;
    }
    boolean ans = false;
    if(size == 1){
      ans = str1[L1] == str2[L2];
    }else{
      for(int leftPart = 1;leftPart < size; leftPart++){
        if(((process2(str1,str2,L1,L2,leftPart,dp))
            && (process2(str1,str2,L1 + leftPart,L2 + leftPart,size - leftPart,dp)))
            ||
            ((process2(str1,str2,L1,L2 + size - leftPart, leftPart,dp))
            && (process2(str1,str2,L1+leftPart,L2,size - leftPart,dp)))){
            ans = true;
            break;
        }
      }
    }
    dp[L1][L2][size] = ans ? 1 : -1;
    return ans;
  }
  public static boolean sameTypeSameNumber(char[] s1, char[] s2){
    if(s1.length != s2.length){
      return false;
    }
    int[] map = new int[256];
    for(int i = 0; i < s1.length; i++){
      map[s1[i]]++;
    }
    for(int i = 0; i < s2.length; i++){
      map[s2[i]]--;
      if(map[s2[i]] < 0){
        return false;
      }
    }
    return true;
  }
}
