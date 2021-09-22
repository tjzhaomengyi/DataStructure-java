package com.mikemyzhao.SomeSkills;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-14 17:04
 * @Description:一行代码解决问题
 */
public class OneLineSolve {
  /**1、一堆石子，轮流拿，一次至少拿1颗，最多拿3颗，谁拿最后一个谁赢
   * 可以动态规划，但是复杂
   * 创建场景，如果要赢，最后石子必须剩下1-3颗，如果对方手拿时候只剩4颗石子，
   *  选择5-7颗
   *  只要4的倍数就赢
   * **/
  boolean canWin(int n){
    //如果赶上了4的倍数，认输；否则把对方控制在4的倍数
    return n%4!=0;
  }

  /**
   * 我和hommie拿一堆石子，piles[i]表示第i堆有多少颗石子，轮流拿，一次拿一堆，但是只能从最左边或者最右边开始拿，都拿完，谁多谁赢
   * 石子有偶数堆，所以每人拿走堆数相同，石子总数是奇数，
   * 割了一下hommie的韭菜
   * **/
  boolean stoneGames(int[] piles){
    return true;
  }

  /**
   * 开关灯,比如6和16盏灯
   * 第一次全打开，第二次拨动2,4,6，8.。。。的开关，第三次拨动3,6,9,12开关......
   * 6盏灯时候，现在求一下第六6盏灯，最后被拨动多少次,1，2,3,6四次 6=1*6=2*3
   * 16盏灯时候，第16盏灯拨动16=1*16=2*8=4*4,拨动了留下
   * 最后得到sqrt(n)表示最后亮着的盏数。
   * 分别是第1*1,2*2,3*3,4*4盏灯
   * **/
  int bulbSwitch(int n){
    return (int)Math.sqrt(n);
  }
}
