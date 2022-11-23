package com.mikemyzhao.SomeSkills_0.ShuWeiWenTi;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 9:35
 * @Description:
 */
public class LC_0204_CountZhiShu {
  public static int countPrimes(int n){
    //计算质数
    if(n < 3){
      return n;
    }
    boolean[] f= new boolean[n];
    int count = n / 2;
    for(int i = 3; i * i < n; i += 2){//i = i + 2跳过所有的偶数
      if(f[i]){
        continue;
      }
      for(int j = i * i; j < n; j += 2 *i){
        if(!f[j]){
          --count;
          f[j] = true;
        }
      }
    }
    return count;
  }
}
