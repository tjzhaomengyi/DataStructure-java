package com.mikemyzhao.TrackInTime.recursion;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-19 22:14
 * @Description:给定str字符串、一个二维矩阵matrix，在matrix中找出是否存在路径可以组成str
 */
public class FindWordInMatrix_8 {
  //从m[i][j]出发能不能找到str[k...]这个后缀字符串
  public static boolean canLoop(char[][] m, int i, int j,char[] str, int k){
    if(k == str.length){
      return true;
    }
    if(i == -1 || i == m.length || j == -1 || j == m[0].length || m[i][j] != str[i]){
      return false;
    }

    boolean ans = false;
    if(canLoop(m,i+1,j,str,k+1)|| canLoop(m, i - 1, j, str, k + 1) || canLoop(m, i, j + 1, str, k + 1)
        || canLoop(m, i, j - 1, str, k + 1)) {
      ans = true;
    }
    return ans;
  }

  public static boolean noLoop(char[][] m, int i, int j, char[] str, int k) {
    if (k == str.length) {
      return true;
    }
    if (i == -1 || i == m.length || j == -1 || j == m[0].length || m[i][j] != str[k]) {
      return false;
    }
    // 不越界！也不是回头路！m[i][j] == str[k] 也对的上！
    m[i][j] = 0;
    boolean ans = false;
    if (noLoop(m, i + 1, j, str, k + 1) || noLoop(m, i - 1, j, str, k + 1) || noLoop(m, i, j + 1, str, k + 1)
        || noLoop(m, i, j - 1, str, k + 1)) {
      ans = true;
    }
    m[i][j] = str[k];
    return ans;
  }
}
