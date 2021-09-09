package com.mikemyzhao.DP;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-07 16:59
 * @Description:四种操作：输入A，全选、复制和粘贴，给定步数让输入最长
 * @LC650类似，只有两个输入
 */
public class FourKeyBoard {
  /**
   * dp[i]表示i次操作后最多能显示多少个A
   * 最后一次按键要么是A，要么是c-v。这样就将选择减少为2个。这个程序最后肯定以c-v结束，但是前提是保证输入了足够多的A。
   * 对于第i次按键，如果是A，比i-1屏幕多一个A：dp[i]=dp[i-1]+1
   * 如果第i次按键，如果是c-v，取决于c-a c-c之前的数量dp[i]=dp[i-2]*2
   * 这里，还要添加另一个下标j，表示i之前的操作
   *
   *
   * aaaaaa||||ca     CC  cv cv CV
   *          j-2     j          i
   */
  int fourKeyBoard(int N){
    int[] dp = new int[N+1];
    dp[0]=0;
    for(int i=1;i<=N;i++){
      //如果按键是A
      dp[i]=dp[i-1]+1;
      for(int j=2;j<i;j++){
        //全选并复制dp[j-2],连续粘贴i-j次
        //屏幕上共dp[j-2]*(i-j+1)个A
        dp[i]=Math.max(dp[i],dp[j-2]*(i-j+1));
      }
    }
    return dp[N];
  }

  public int twoKeyBoard(int n){
    /**
     * 只有一个字符A通过n补获得最多的A，在记事本输出n个A，并且用最少的步骤
     *  dp[i]长度为i的时候最少需要多少步骤
     *  trick:如果i可以被j除尽，说明j个A可以通过复制得到i个A，复制粘贴次数为i/j
     *
     */
    int[] dp = new int[n+1];
    Arrays.fill(dp,Integer.MAX_VALUE);
    dp[1]=0;
    for(int i=2;i<=n;i++){
      for(int j=1;j<=i/2;j++){
        if(i%j==0) {
          dp[i] = Math.min(dp[i], dp[j]+i/j);
        }
      }
    }
    return dp[n];
  }

  public static void main(String[] args) {
    System.out.println(new FourKeyBoard().twoKeyBoard(6));
    System.out.println(new FourKeyBoard().fourKeyBoard(5));
  }
}
