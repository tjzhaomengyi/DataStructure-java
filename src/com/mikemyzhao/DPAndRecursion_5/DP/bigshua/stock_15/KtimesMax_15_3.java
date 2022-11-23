package com.mikemyzhao.DPAndRecursion_5.DP.bigshua.stock_15;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-25 14:54
 * @Description:允许买卖k笔交易(一只股票买进来再卖空算一笔交易)，买卖完成才能买第二次，一个时间内持有一只股票
 */
public class KtimesMax_15_3 {
  public static int maxProfit(int K, int[] prices){
    if(prices == null || prices.length == 0){
      return 0;
    }
    int N = prices.length;
    if(K >= N / 2){
      return allTrans(prices);//只要是利润都可以搞定，把低位到高位都买到
    }
    int[][] dp = new int[prices.length][K + 1];
    //dp[i][j]表示到第i个prices最多做了j比交易的最大收益
    //第一行表示[0]这个钱做n笔交易，有啥用啊，就这一个逼价格买了卖卖了买，收益0
    //第一列表示各种各样的价格，你不交易，挣个鸡毛啊，所以都是0
    //如果dp[i][j]不是最后一笔交易那么，都在前面操作完了，等于dp[i-1][j]
    //然后考虑dp[i][j]，如果第j位置是最后一笔交易，那他肯定是卖出去:
    //dp[i][j] = dp[i][j-1] + [j] - [j]，j买j卖;
    //         = dp[i-1][j-1] + [j] - [i-1]
    //。。。。。= dp[0][j-1] + [j] - [0]
    //这些值求max
    //todo:这个填表是从列固定，然后从上往下,这个写法比价特殊
    for (int j = 1; j <= K; j++){
      //而且第一行特殊可以先确定
      int p1 = dp[0][j];//第一次没有参与交易
      //int p2 = Math.max(dp[1][j - 1] + prices[j] - prices[j],dp[0][j-1] + prices[j] - prices[0]);//
      //优化一下
      int best = Math.max(dp[1][j - 1] - prices[1], dp[0][j - 1] - prices[0]);
      dp[1][j] = Math.max(p1, best + prices[1]);
      for(int i = 2; i < prices.length; i++){
        p1 = dp[i - 1][j];
        //这一次的新的只需要和上一次的比就能更新出来，这个更新可以省略好多
        int newP = dp[i][j - 1] - prices[i];
        best = Math.max(best, newP);
        dp[i][j] = Math.max(p1, best + prices[i]);
      }
    }
    return dp[N - 1][K];
  }

  public static int allTrans(int[] prices){
    int ans = 0;
    for(int i = 1; i < prices.length; i++){
      ans += Math.max(0,prices[i] - prices[i - 1]);
    }
    return ans;
  }

}
