package com.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-06 12:18
 * @Description:二进制中1的个数
 */
public class BinaryOnes_15 {
  public int hammingWeight(int n) {
    int cnt=0;
    while(n!=0){
      n=n&(n-1); //思路：将二进制最右侧的1变为0
      cnt++;
    }
    return cnt;
  }
}
