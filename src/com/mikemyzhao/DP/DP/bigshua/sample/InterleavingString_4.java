package com.mikemyzhao.DP.DP.bigshua.sample;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-17 17:32
 * @Description:https://leetcode.com/problems/interleaving-string/
 * 样本对应模型:这里是拿几个不是拿序号了！
 */

public class InterleavingString_4 {
//dp[i][j]表示从str1中只拿前i个，str2中只拿前j个，是否能组成str总[i+j]
  public static boolean isInterLeave(String s1, String s2, String s3){
    if(s1 == null || s2 == null || s3 == null){
      return  false;
    }
    char[] str1 = s1.toCharArray();
    char[] str2 = s2.toCharArray();
    char[] str3 = s3.toCharArray();
    if(str3.length != str1.length + str2.length){
      return  false;
    }
    boolean[][] dp = new boolean[str1.length+1][str2.length + 1];
    dp[0][0] = true;
    //只要只拿str1有一样的就可以继续标记为true，如果不一样就不行了，break，str2同理
    for(int i = 1; i <= str1.length; i++){
      if(str1[i-1] != str3[i-1]){
        break;
      }
      dp[i][0] =true;
    }
    for(int j = 1; j <= str2.length; j++){
      if(str2[j-1] != str3[j-1]){
        break;
      }
      dp[0][j] = true;
    }
    for(int i = 1; i <= str1.length; i++){
      for(int j = 1; j <= str2.length; j++){
        if((str1[i - 1] == str3[i + j - 1] && dp[i - 1][j]) || (str2[j - 1] == str3[i + j -1] && dp[i][j - 1])){
          dp[i][j] = true;
        }
      }
    }
    return dp[str1.length][str2.length];
  }
}
