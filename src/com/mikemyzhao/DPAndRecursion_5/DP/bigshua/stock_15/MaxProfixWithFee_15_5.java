package com.mikemyzhao.DPAndRecursion_5.DP.bigshua.stock_15;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-25 16:15
 * @Description:有手续费,一笔带一个手续费，买卖缴纳一次即可
 */
public class MaxProfixWithFee_15_5 {
  public static int maxProfit(int[] prices, int fee){
    if(prices.length < 2 || prices == null){
      return 0;
    }
    int N = prices.length;
    int bestBuy = -prices[0] - fee;
    int bestSell = 0;
    for(int i = 1; i < N; i++){
      //如果在i位置买入
      int curbuy = bestSell - prices[i] - fee;
      int cursell = bestBuy + prices[i] ;
      bestBuy = Math.max(bestBuy, curbuy);
      bestSell = Math.max(bestSell,cursell);
    }
    return bestSell;
  }
}
