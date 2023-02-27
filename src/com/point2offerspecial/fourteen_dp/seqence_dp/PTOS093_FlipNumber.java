package com.point2offerspecial.fourteen_dp.seqence_dp;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-16 18:32
 * @Description:
 */
public class PTOS093_FlipNumber {
  public int minFlipsMonoIncr(String s) {
    if(s == null || s.length() == 1) return 0;
    int len = s.length();
    int[][] dp = new int[len][2];
    char[] str = s.toCharArray();
    dp[0][0] = str[0] == '0' ? 0 : 1;
    dp[0][1] = str[0] == '1' ? 0 : 1;

    for(int i = 1; i < len; i++){
      char c = str[i];
      int pre0 = dp[i - 1][0];
      int pre1 = dp[i - 1][1];
      //技巧：如果当前要变成0那么前面必须都是0
      dp[i][0] = pre0 + (c == '0' ? 0 : 1);
      dp[i][1] = Math.min(pre0, pre1) + (c == '1' ? 0 : 1);//前面谁小跟哪个
    }

    return Math.min(dp[s.length() - 1][0], dp[s.length() - 1][1]);
  }
}
