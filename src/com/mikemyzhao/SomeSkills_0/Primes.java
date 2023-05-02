package com.mikemyzhao.SomeSkills_0;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-13 20:18
 * @Description:寻找素数，给一个数n看里面有多少素数
 */
public class Primes {
  /**判断一个数是否是素数**/
  boolean isPrime(int n){
    /**12=2*6
     * 12=3*4
     * 12=sqrt(12)*sqrt(12),以此为分界线，所以判断i*i<n即可
     * 12=4*3
     * 12=6*2
     * **/
    for(int i=2;i*i<=n;i++){
      if(n%i==0){
        return false;
      }
    }
    return true;

  }

  //埃拉托色尼筛选法
  int countPromises(int n){
    boolean[] isPrimes = new boolean[n];
    Arrays.fill(isPrimes,true);
    //素数从2开始计算
    for(int i=2;i*i<n;i++){//for(int i=2;i<n;i++){因为是相乘到sqrt(i)*sqrt(i)<n即可
      if(isPrime(i)){
        //如果i是素数，那么i的倍数就不是素数了
        for(int j=i*i;j<n;j+=i){//for(int j=2*i;j<n;j+=i){对应上面这里也优化
          isPrimes[j]=false;
        }
      }
    }
    int count=0;
    for(int i=2;i<n;i++){//for(int i=2;i<n;i++){
      if(isPrimes[i]) count++;
    }
    return count;
  }
}
