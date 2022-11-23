package com.mikemyzhao.DP_5.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-15 9:22
 * @Description:有A、B两地，安排2N个司机，每个司机安排在AB两地的收入是arr[a,b],求两地平均分配司机后，如何让司机收入最大
 */
public class Drive_02 {
  public static int maxMoney1(int[][] incomes){
    if(incomes == null || incomes.length < 2 || (incomes.length & 1) != 0) {
      return 0;
    }
    int N = incomes.length;
    int M = N >> 1;
    return process1(incomes,0,M);
  }

  //当前分配到index，A去还剩多少待分配
  public static int process1(int[][] incomes, int index, int rest){
    if(index == incomes.length){
      return 0;
    }
    if(incomes.length-index == rest){
      return incomes[index][0] + process1(incomes,index + 1, rest - 1);
    }
    if(rest == 0){
      return incomes[index][1] + process1(incomes,index+1, rest);//从这看出要从下往上填表。依赖下方的表格。
    }
    //当前司机可以去A 可以去B
    int A = incomes[index][0] + process1(incomes, index + 1, rest - 1);
    int B = incomes[index][1] + process1(incomes , index + 1, rest);
    return Math.max(A,B);
  }

  //从递归到动态规划
  public static int maxMoney2(int[][] incomes){
    if(incomes == null || incomes.length < 2 || (incomes.length & 1) != 0) {
      return 0;
    }
    int N = incomes.length;
    int M = N >> 1;
    int[][] dp = new int[N+1][M+1];//N-i-表示当前到哪个司机分配，M-j-表示A地区还剩多少位置
    //从上面的递归过程看出，dp[i][j]只依赖i+1,和rest-1，rest这些并且两个变量index[即N和i]和rest[M和j]即可控制
    for(int i = N - 1; i >= 0; i--){
      for(int j = 0; j <= M; j++){
        if(N - i == j){
          dp[i][j] = incomes[i][0] + dp[i + 1][j - 1];
        }else if(j == 0){
          dp[i][j] = incomes[i][1] + dp[i+1][0];
        }else{
          int p1 = incomes[i][0] + dp[i+1][j-1];
          int p2 = incomes[i][1] + dp[i+1][j];
          dp[i][j] = Math.max(p1,p2);
        }
      }
    }
    return dp[0][M];
  }


}
