package com.mikemyzhao.DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-07 19:53
 * @Description:
 */
public class DropEgg {
/**普通dp解法
 * K个鸡蛋，N层楼，求最坏情况下的最少扔鸡蛋次数
 *
 * @return**/
  int dropEgg(int K, int N){
    if(K==1){return N;}
    if(N==0){return 0;}
    int[][] dp = new int[N+1][K+1];//i层楼j个鸡蛋
    for(int i=0;i<=N;i++){
      Arrays.fill(dp[i],Integer.MAX_VALUE);//因为是最小值，所以要先最大化
    }
    for(int i=0;i<=N;i++){
      dp[i][1]=i;//n层楼1个鸡蛋
      dp[i][0]=0;//n层楼没有鸡蛋
    }
    //0层楼,k个蛋
    for(int j=0;j<=K;j++){
      dp[0][j]=0;
    }

    //DP里面唯一的三循环到现在
    for(int i=1;i<=N;i++){ //从第一层开始
      for(int j=2;j<=K;j++){//从2个鸡蛋开始
        for(int x=1;x<=i;x++){ //控制再做实验的层
          /**在第i层的时候鸡蛋没有碎，那么dp[K][N-i]，那么还有K个鸡蛋N-i层楼测试，向上走;如果碎了还有K-1个鸡蛋，i-1层楼测试，向下走**/
          dp[i][j]=Math.min(dp[i][j],Math.max(dp[i-x][j],dp[x-1][j-1])+1);//蛋没坏;蛋坏了
        }
      }
    }
    return dp[N][K];
  }

  /**二分修改**/
  int dropEggDivide(int K,int N){
    if(K==1){return N;}
    if(N==0){return 0;}
    int[][] dp = new int[N+1][K+1];
    for(int i=0;i<=N;i++){
      Arrays.fill(dp[i],Integer.MAX_VALUE);//因为是最小值，所以要先最大化
    }
    for(int i=0;i<=N;i++){
      dp[i][1]=i;//n层楼1个鸡蛋
      dp[i][0]=0;//n层楼没有鸡蛋
    }
    //0层楼,k个蛋
    for(int j=0;j<=K;j++){
      dp[0][j]=0;
    }

    for(int i=1;i<=N;i++){
      for(int j=2;j<=K;j++){
        //在区间[1,i]里确定一个最优值
        int left = 1;
        int right = i;
        while(left<right){
          int mid = left+(right-left+1)/2;
          int breakcount = dp[mid-1][j-1];//碎了
          int notbreakcount = dp[i-mid][j];//没碎
          //todo:这里要调整区间了,这里不太懂，关键
          if(breakcount>notbreakcount){
            //碎了的次数大于没碎的次数,这不行了，得让right楼层降低往mid以下走
            //向下区间【left,mid-1】
            right = mid-1;
          }else{
            //没碎的次数多余碎了的次数，这蛋牛逼，让left向上走
            //向上区间【mid,right】
            left = mid;
          }
        }
        //最后寻找left，就是最好的楼层数
        dp[i][j] = Math.max(dp[left-1][j-1],dp[i-left][j])+1;
      }
    }
    return dp[N][K];
  }

  public int superEggDrop(int K, int N) {
    // dp[i][j]：一共有 i 层楼梯的情况下，使用 j 个鸡蛋的最少仍的次数
    int[][] dp = new int[N + 1][K + 1];

    // 初始化
    for (int i = 0; i <= N; i++) {
      Arrays.fill(dp[i], i);
    }
    for (int j = 0; j <= K; j++) {
      dp[0][j] = 0;
    }

    dp[1][0] = 0;
    for (int j = 1; j <= K; j++) {
      dp[1][j] = 1;
    }
    for (int i = 0; i <= N; i++) {
      dp[i][0] = 0;
      dp[i][1] = i;
    }

    // 开始递推
    for (int i = 2; i <= N; i++) {
      for (int j = 2; j <= K; j++) {
        // 在区间 [1, i] 里确定一个最优值
        int left = 1;
        int right = i;
        while (left < right) {
          // 找 dp[k - 1][j - 1] <= dp[i - mid][j] 的最大值 k
          int mid = left + (right - left + 1) / 2;

          int breakCount = dp[mid - 1][j - 1];
          int notBreakCount = dp[i - mid][j];
          if (breakCount > notBreakCount) {
            // 排除法（减治思想）写对二分见第 35 题，先想什么时候不是解
            // 严格大于的时候一定不是解，此时 mid 一定不是解
            // 下一轮搜索区间是 [left, mid - 1]
            right = mid - 1;
          } else {
            // 这个区间一定是上一个区间的反面，即 [mid, right]
            // 注意这个时候取中间数要上取整，int mid = left + (right - left + 1) / 2;
            left = mid;
          }
        }
        // left 这个下标就是最优的 k 值，把它代入转移方程 Math.max(dp[k - 1][j - 1], dp[i - k][j]) + 1) 即可
        dp[i][j] = Math.max(dp[left - 1][j - 1], dp[i - left][j]) + 1;
      }
    }
    return dp[N][K];
  }


  public static void main(String[] args) {
//    System.out.println(new DropEgg().dropEgg(8,10000));
    System.out.println(new DropEgg().dropEggDivide(8,10000));
    System.out.println(new DropEgg().superEggDrop(8,10000));

  }
}
