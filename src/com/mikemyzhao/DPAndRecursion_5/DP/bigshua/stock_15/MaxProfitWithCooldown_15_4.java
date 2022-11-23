package com.mikemyzhao.DPAndRecursion_5.DP.bigshua.stock_15;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-25 15:39
 * @Description:带有cooldown问题，每笔交易后要有一天的间隔，也就是说在卖出后要隔一天才能再买入
 */
public class MaxProfitWithCooldown_15 {
  //递归，就是抱着当前行为是否是买入行为，当前持有的买入价格为buyPrice
  public static int process1(int[] prices, boolean buy, int index, int buyPrice){
    if(index >= prices.length){
      return 0;
    }
    if(buy){
      //如果当前已经买入，买入价格buyPrices
      int noSell = process1(prices, true,index + 1,buyPrice);
      //在当前节点卖出去了,剩余价格为0往后面扔吧
      int yesSell = prices[index] - buyPrice + process1(prices, false, index + 2, 0);
      return Math.max(noSell, yesSell);
    } else {
      //当前节点没有持有股票
      //1.不买
      int noBuy = process1(prices, false, index + 1, 0);
      int yesBuy = process1(prices, true, index + 1, prices[index]);
      return Math.max(noBuy, yesBuy);
    }
  }
  //动态规划拆成两个表sell和buy
  public static int maxProfit2(int[] prices){
    if(prices.length < 2){
      return 0;
    }
    int N = prices.length;
    int[] buy = new int[N];
    int[] sell = new int[N];
    buy[1] = Math.max(-prices[0], -prices[1]);//开始买入肯定是挑选最大的因为是负数要从结果中减掉
    sell[1] = Math.max(0, prices[1] - prices[0]);
    for(int i = 2; i < N; i++){
      buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);//i时刻买入或者不买
      sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);//在这个点卖出，或者不卖出
    }
    return sell[N - 1];
  }

  //从上面来看，每次只依赖buy[i-1],sell[i-2]和sell[i-1]
  //
  public static int maxProfit3(int[] prices) {
    if (prices.length < 2) {
      return 0;
    }
    int buy1 = Math.max(-prices[0], -prices[1]);
    int sell1 = Math.max(0, prices[1] - prices[0]);
    int sell2 = 0;
    for (int i = 2; i < prices.length; i++) {
      int tmp = sell1;
      sell1 = Math.max(sell1, buy1 + prices[i]);
      buy1 = Math.max(buy1, sell2 - prices[i]);
      sell2 = tmp;
    }
    return sell1;
  }
}
