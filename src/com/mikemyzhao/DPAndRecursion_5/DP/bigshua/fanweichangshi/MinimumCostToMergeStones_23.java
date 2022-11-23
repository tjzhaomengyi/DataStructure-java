package com.mikemyzhao.DPAndRecursion_5.DP.bigshua.fanweichangshi;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-06 10:27
 * @Description:给定数组arr，把相邻p个数求和，可以选择合并的前后范围，但是必须保证每次合并的个数是k，
 * 求如何让合并的代价最小。“代价” 等于 每次中间合并的和的累加和
 * // 本题测试链接 : https://leetcode.com/problems/minimum-cost-to-merge-stones/
 */
public class MinimumCostToMergeStones_23 {
  //数学结论： 先用一个数学结论过滤一下数组如果(n - 1) % (k - 1) = 0就可以满足题目中凑的方法
  public static int mergeStones1(int[] stones, int K){
    int n = stones.length;
    if((n - 1) % (K - 1) > 0){
      return -1;//没有办法合并
    }
    int[] presum = new int[n + 1];
    for(int i = 0; i < n; i++){
      presum[i + 1] = presum[i] + stones[i];
    }
    return process1(0, n - 1, 1, stones, K, presum);
  }

  //在[L..R]上,找出连续K个，并且弄成P分的最小代价，
  public static int process1(int L, int R, int P, int[] arr, int K, int[] presum){
    if(L == R){
      return P == 1 ? 0 : -1;//base case:只有一个值，还得弄1分
    }
    if(P == 1){//技巧：合成目标是1份
      //原始目标f(L,R,K),从L到R给我搞出K分。如果要求0-7，k=3，初始问题是f(0,7,1)->f(0,7,3)搞出3份最后合成1分
      //最后弄成1份,那么最后就弄出K分来，然后最后K个连续合并成1分
      int next = process1(L, R, K, arr, K, presum);
      if(next == -1){
        //这么搞不出来
        return -1;
      } else {
        //next这样可以搞出来
        //[L...R]->到R的累加和 减去 到 L-1的累加和，因为累加和数组下标对应是+1的
        return next + (presum[R + 1] - presum[L]);//技巧：最后一下大合并的值也要加上
      }
    } else {//技巧：如何合成不是1份
      int ans = Integer.MAX_VALUE;//先把结果干成最大
      //L..mid是第一块，剩下的是part-1块
      for (int mid = L; mid < R; mid += K - 1){
        //L..mid(一份) mid+1...R(part - 1份)
        int next1 = process1(L, mid, 1, arr, K, presum);
        int next2 = process1(mid + 1, R, P - 1, arr, K, presum);
        if(next1 != -1 && next2 != -1){
          ans = Math.min(ans, next1 + next2);
        }
      }
      return ans;
    }
  }

  //纯递归会超时，加1个dp表
  public static int mergeStones(int[] stones, int K){
    int n = stones.length;
    if((n - 1) % (K - 1) > 0){
      return -1;
    }

    int[] presum = new int[n + 1];
    for(int i = 0; i < stones.length; i++){
      presum[i + 1] = presum[i] + stones[i];
    }
    int[][][] dp = new int[n][n][K + 1];//这里变化的只有L、R和P
    return process2(0,stones.length -  1, 1, stones ,K, presum, dp);
  }

  public static int process2(int L, int R, int P, int[] arr, int K, int[] presum, int[][][] dp){
    if(dp[L][R][P] != 0){
      return dp[L][R][P];
    }
    if(L == R){
      return  P == 1 ? 0 : -1;
    }
    if(P == 1){
      int next = process2(L, R, K, arr, K, presum, dp);
      if(next == -1){
        dp[L][R][P] = -1;
        return -1;
      } else {
        dp[L][R][P] = next + presum[R + 1] - presum[L];
        return next + presum[R + 1] - presum[L];
      }
    } else {
      int ans = Integer.MAX_VALUE;
      for(int mid = L; mid < R; mid += K - 1){
        int next1 = process2(L, mid, 1, arr, K, presum, dp);
        int next2 = process2(mid + 1, R, P - 1, arr, K, presum, dp);
        if(next1 == -1 || next2 == -1){
          dp[L][R][P] = -1;
          return  -1;
        } else {
          ans = Math.min(ans, next1 + next2);
        }
      }
      dp[L][R][P] = ans;
      return ans;
    }
  }
}
