package com.mikemyzhao.SomeSkills;

import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-13 20:40
 * @Description:
 */
public class ModIndex {
  /**1、求a的数组指数比如
   * a^[1,5,6,4]=a^4*a^[1,5,6,0]=a^4*(a^[1,5,6])^10
   * 计算a的k次方框架
   *
   * int myPow(int a,int k)
   * int superPow(int a,int[] b){
   *   if b.empty return 1;
   *   //取出最后一个数
   *   int last = b.back()
   *   b.pop_back();
   *   //将原问题化简,缩小规模递归求解
   *   int part1 = myPow(a,last)
   *   int part2 = myPow(superPow(a,b),10);
   *   //合并结果
   *   return part1*part2
   * }
   *
   * 2、求模
   *  (a*b)%k = (a%k)(b%k)%k
   *
   *
   * 3、求模加速
   * a^b = a*a^(b-1) ,b为奇数
   *    *    = (a^(b/2))^2 b为偶数
   *    *    这样把算法复杂度定义到log级别
   * **/
  int base = 1337;
  int mypow(int a,int k){//求a^k，然后和base取模
    //对因子求模
    a%=base;
    int res = 1;
    for(int _=0;_<k;_++){
      //这里有乘法，是潜在的溢出点
      res *= a;
      //对乘法结果求模
      res %= base;
    }
    return res;
  }

  int superPow(int a, List<Integer> b){
    if(b.size()==0) return 1;
    int last = b.get(-1);
    b.remove(-1);

    int part1 = mypow(a,last);
    int part2 = mypow(superPow(a,b),10);//这里进行递归
    return (part1 * part2)%base;//这里使用(a*b)%k=(a%k)(b%k)%k

  }

  /**
   * 高效求幂,一定要写这个
   * a^b = a*a^(b-1) ,b为奇数
   *    = (a^(b/2))^2 b为偶数
   *    这样把算法复杂度定义到log级别
   * **/

  int myPowFastAndGetMod(int a,int k){
    if(k==0) return 1;
    a%=base;

    if(k%2==1){
      return (a*myPowFastAndGetMod(a,k-1))%base;
    }else{
      int sub = myPowFastAndGetMod(a,k/2);
      return (sub*sub)%base;
    }

  }

}