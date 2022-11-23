package com.mikemyzhao.SomeSkills_0.bigshua.tricks;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-27 12:58
 * @Description:几个人围一圈，数到m崩死一个，最后剩下谁
 */
public class JosephusCycleProblems_16 {
  //反着推,m = 3数到3的死
  // 杀前的序号：1 2 3 4 5 6 7 8
  // 杀后的序号：6 7 x 1 2 3 4 5
  //用一个剃刀函数：杀前 = f(杀后)
  public static int getLive(int n, int m){
    if(n == 1){
      return 1;
    }
    //杀死前的序号=(杀死后的序号 + M - 1) % n + 1
    return(getLive(n - 1, m) + m - 1) % n + 1;
  }

}
