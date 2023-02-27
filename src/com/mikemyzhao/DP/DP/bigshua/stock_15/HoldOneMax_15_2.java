package com.mikemyzhao.DP.DP.bigshua.stock_15;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-25 7:55
 * @Description:一个时间手里只能有一只股票,可以多次交易k次
 */
public class HoldOneMax_15_2 {
  public static int maxProfit(int[] prices){
    if(prices == null || prices.length == 0){
      return 0;
    }
    int ans = 0;
    for(int i = 1; i < prices.length; i++){
      ans += prices[i] - prices[i - 1] > 0 ? prices[i] - prices[i - 1] : 0;
    }
    return ans;
  }

}
