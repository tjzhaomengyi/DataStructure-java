package com.mikemyzhao.DPAndRecursion_5.DP.bigshua.shuweiDP;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-03 11:14
 * @Description:给定一个数返回
 */
public class OneNumerInRange {
  /**
   数学结论：给定一个数，在这个范围中的所有正数，1的个数为：
    (1)第一位为1，所有1的个数 = rest + 1 + (k - 1) * 10 ^(k - 2) 【rest是1后面剩下数】
    (2)第一位不是1，所有1的个数 = 10^(k - 1) + (k - 1) * x * 10 ^ (k - 2)
   *
   */
  //思路：先把高位的割掉，12631
  // 2632-12631
  // 632 - 2631
  // 32 - 631
  // 1 - 31
  public static int get1Nums(int num){
    if(num < 1){
      return 0;
    }
    //(1)获取数字长度
    int len = getLenOfNum(num);
    if(len == 1){
      return 1;
    }
    //12631
    //10000
    int base = powerBaseOf10(len - 1);//凑个底数
    //拿到最高位
    int first = num / base;
    int firstPartSum = first == 1 ? num % base + 1 : base;
    int other = (len - 1) * first * powerBaseOf10(len - 2);
    return firstPartSum + other + get1Nums(num % base);
  }
  public static int getLenOfNum(int num){
    int len = 0;
    while(num != 0){
      len++;
      num /= 10;
    }
    return len;
  }
  public static int powerBaseOf10(int base){
    return (int) Math.pow(10,base);
  }
}