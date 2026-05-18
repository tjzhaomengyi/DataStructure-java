package com.niuke.BM101.dp;

public class DM81_Stock {
    /**
     * 股票买卖2：可以连续做t
     */
    // 方法1 贪心,有利就走
    public int maxProfit(int[] prices){
        int n = prices.length;
        int profit = 0;
        for(int i = 1; i < n; i++){
            if(prices[i] > prices[i - 1]){
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
    // 方法2 使用动态规划，规矩一点
    public int maxProfitStandard(int[] prices){
        //定义状态
        // dp[i][0] 第i天结束后不持有股票的最大收益
        // dp[i][1] 第i天结束后结束后持有股票的最大收益
        // 状态转移
        // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + price[i]) //今天卖出 或者 什么都不做
        // dp[i][1] = max(dp[i-1][1], dp[i-1][0] - price[i]) //今天买入 或者 什么都不做
        int n = prices.length;
        if(n == 0) return 0;

        int dp0 = 0;     //不持有股票
        int dp1 = -prices[0]; //持有股票

        for(int i = 1; i < n; i++){
            int new_dp0 = Math.max(dp0, dp1 + prices[i]); // 什么都不做 或者 卖出
            int new_dp1 = Math.max(dp1, dp0 - prices[i]); // 什么都不做 或者 买入
            dp0 = new_dp0;
            dp1 = new_dp1;
        }
        return dp0;
    }

    /**
     * 要求只要两次交易
     */
    // dp定义
    // dp[i][k][0 or 1] 表示第i天，最多允许k次交易， 并且0不持股，1 持股 的最大收益。
    // 状态转移
    // dp[i][k][0] = max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]) 今天不操作或者卖出
    // dp[i][k][1] = max(dp[i - 1][k][1], dp[i - 1][k-1][0] - prices[i]) 今天不操作或者买入，并且用掉一次交易机会
    // 流程就是标准的滚动轮算
    public int maxProfitTwice(int[] prices){
        if(prices == null || prices.length == 0) return 0;
        int firstBuy = -prices[0];
        int firstSell = 0;
        int secondBuy = -prices[0];
        int secondSell = 0;

        for(int i = 1; i < prices.length; i++){
            firstBuy = Math.max(firstBuy, -prices[i]); //买便宜的
            firstSell = Math.max(firstSell, firstBuy + prices[i]);
            secondBuy = Math.max(secondBuy, firstSell - prices[i]);
            secondSell = Math.max(secondSell, secondBuy + prices[i]);
        }
        return secondSell;
    }
}
