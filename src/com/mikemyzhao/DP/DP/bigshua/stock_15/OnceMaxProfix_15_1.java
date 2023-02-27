package com.mikemyzhao.DP.DP.bigshua.stock_15;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-25 7:49
 * @Description:
 */
public class OnceMaxProfix_15_1 {
  public static int maxProfit(int[] prices){
    if(prices == null || prices.length == 0){
      return 0;
    }
    //假如在i时刻卖掉，求此时最大
    int ans = 0;
    int min = prices[0];//买入的最小值
    for(int i = 1; i < prices.length; i++){
      min = Math.min(min,prices[i]);
      ans = Math.max(ans, prices[i] - min);
    }
    return ans;
  }
}
