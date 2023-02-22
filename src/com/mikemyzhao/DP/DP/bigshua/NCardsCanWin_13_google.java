package com.mikemyzhao.DP.DP.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-24 10:30
 * @Description:
 */
public class NCardsCanWin_13_google {
  // 谷歌面试题
  // 面值为1~10的牌组成一组，
  // 每次你从组里等概率的抽出1~10中的一张
  // 下次抽会换一个新的组，有无限组
  // 当累加和<17时，你将一直抽牌
  // 当累加和>=17且<21时，你将获胜
  // 当累加和>=21时，你将失败
  // 返回获胜的概率
  public static double f1(){
    return p1(0);
  }
  //当来到cur累加和时候获胜的概率是多少
  //每次到新的组里抽牌的时候每个牌得点/10
  public static double p1(int cur){
    if(cur >= 17 && cur < 21){
      return 1;
    }
    if(cur >= 21){
      return 0.0;
    }
    double w = 0.0;
    for(int i = 1; i <= 10; i++){//可以优化的枚举
      w += p1(cur + i);
    }
    return w / 10;
  }
  //把这个过程普遍化
  public static double p2(int cur, int N, int a, int b){
    if(cur >= a && cur < b){
      return 1;
    }
    if(cur >= b){
      return 0.0;
    }
    double w = 0.0;
    for(int i = 1; i <= N; i++){//可以优化的枚举
      w += p2(cur + i,N, a,b);
    }
    return w / N;
  }

  //对普遍优化可以减少迭代
  public static double p3(int cur, int N, int a, int b) {
    if (cur >= a && cur < b) {
      return 1.0;
    }
    if (cur >= b) {
      return 0.0;
    }
    if (cur == a - 1) {
      return 1.0 * (b - a) / N;
    }
    double w = p3(cur + 1, N, a, b) + p3(cur + 1, N, a, b) * N;
    if (cur + 1 + N < b) {
      w -= p3(cur + 1 + N, N, a, b);
    }
    return w / N;
  }

  //改动态规划
  public static double f4(int N, int a, int b){
    if(N < 1 || a >= b || a < 0 || b < 0){
      return 0.0;
    }
    if(b - a >= N){
      return 1.0;
    }
    double[] dp = new double[b];
    for(int i = a; i < b; i++){ //大于a小于b的直接赢
      dp[i] = 1.0;
    }
    if (a - 1 >= 0){//特殊位置，在硬的前一个位置
      dp[a - 1] = 1.0 * (b - a) / N;//计算赢的概率
    }
    for (int cur = a - 2; cur >= 0; cur--) {
      double w = dp[cur + 1] + dp[cur + 1] * N;
      if (cur + 1 + N < b) {
        w -= dp[cur + 1 + N];
      }
      dp[cur] = w / N;
    }
    return dp[0];
  }
}
