package com.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-12 15:41
 * @Description:
 */
public class StockProfit_63 {
  public int maxProfit(int[] prices) {
    int cost = Integer.MAX_VALUE, profit = 0;
    for(int price : prices) {
      cost = Math.min(cost, price);
      profit = Math.max(profit, price - cost);
    }
    return profit;
  }

}
