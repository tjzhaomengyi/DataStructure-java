package com.mikemyzhao.simulate_reality_13.bigshua;

import java.util.LinkedList;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-03 15:14
 * @Description:一张牌面有ABC三个属性，求100张随意抽三张可能达标的可能性，达标要求：该列上的属性要不全一样，要不全不一样，
 * A      A
 * A      A
 * C不达标 A达标
 */
public class CardsStyles_19 {
  //技巧：三个面值，先来个三进制的转换，把ABC分别对应012.牌面的种类一种27种
  public static int ways(String[] cards){
    int[] counts = new int[27];//一共二十七种牌面可能
    for(String s : cards){
      char[] str = s.toCharArray();
      counts[(str[0] - 'A') * 9 + (str[1] - 'A') * 3 + (str[2] - 'A')]++;//把当前牌面的计数+1
    }
    int ways = 0;
    //遍历每种牌面
    for(int status = 0; status < 27; status++){
      int n = counts[status];//当前片面有多少张牌，
      if(n > 2){
        //从中选三张的排列组合C(n3)
        ways += n == 3 ? 1 : ((n * (n - 1) * (n - 2)) / 6);//从一样的牌面选出三张,来自一种牌面的情况
      }
    }
    //牌面不一样的情况
    LinkedList<Integer> path = new LinkedList<>();//记录拿过的牌
    for(int i = 0; i < 27; i++){
      if(counts[i] != 0){//如果这种片面出现过
        path.addLast(i);//拿一张
        ways += process(counts, i, path);
        path.pollLast();//还原现场，去拿后面这张牌可能的情况

      }
    }
  return ways;
  }
  //之前拿了一些牌，pre表示上一张拿的，返回拿牌的方法数
  public static int process(int[] counts, int pre, LinkedList<Integer> path){
    if(path.size() == 3){
      //拿完了，算方法数
      return getWays(counts,path);//技巧：牌面不同，如何区分每列
    }
    int ways = 0;
    for(int next = pre + 1; next < 27; next++){
      if(counts[next] != 0){
        path.addLast(next);
        ways += process(counts, next, path);
        path.pollLast();
      }
    }
    return ways;
  }

  public static int getWays(int[] counts, LinkedList<Integer> path){
    int card0 = path.get(0);
    int card1 = path.get(1);
    int card2 = path.get(2);
    //技巧：卡面现在肯定不一样，都是不同的值。如果前两个卡面一样，第三个肯定要一样，
    // 那个已经算在三张一样里面了，这个保证拿不一样的，在不一样的中间怎么保证列还不一样
    for (int i = 9; i > 0; i /= 3){
      //拿每一列
      int cur0 = card0 / i;
      int cur1 = card1 / i;
      int cur2 = card2 / i;
      //剩下的
      card0 %= i;
      card1 %= i;
      card2 %= i;
      if((cur0 != cur1 && cur0 != cur2 && cur1 != cur2) || (cur0 == cur1 && cur1 == cur2)){
        continue;
      }
      return 0;
    }
    //还得还原回去
    card0 = path.get(0);
    card1 = path.get(1);
    card2 = path.get(2);
    return counts[card0] * counts[card1] * counts[card2];

  }
}
