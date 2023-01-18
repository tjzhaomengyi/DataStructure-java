package com.hots100.math.katelanshu;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-05 11:22 上午
 * @Description:
 */
public class Code0096_NumSearchTrees {
  //数学结论：卡特兰数的使用
  // 卡特兰数的使用：直接上公式，因为怎么计算都是 F = G(i-1)*G(n-i)
  public int numTrees(int n) {
    long C = 1;
    for(int i = 0; i < n; i++){
      C = C * 2 * (2 * i + 1) / (i + 2);
    }
    return (int)C;

  }

}
