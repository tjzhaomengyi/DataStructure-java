package com.book.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-06 11:37
 * @Description:使用公式求解，避免动态规划的极值问题，其实就是尽量让绳子按每个都为3切即可，
 * 如果剩下的话就是剩下1或者2，剩1:3^(a-1)*4;剩下2:3^a.2
 */
public class CutLine_14_2 {
  public int cuttingRope(int n) {
    if(n <= 3) return n - 1;
    int b = n % 3, p = 1000000007;

    long rem = 1, x = 3 ,a = n / 3;
    //直接套循环求余公式
    for(int i = 0; i < ((b == 1)?a-1:a); i++) { //b == 1代表余数为1的时候，需要单独取出一个3出来凑成2*2达到最大值效果
      rem = (rem * x) % p;
    }
    if(b == 0) return (int)(rem % p);
    if(b == 1) return (int)(rem * 4 % p);
    return (int)(rem * 2 % p);
  }


  // 求 (x^a) % p —— 循环求余法。固定搭配建议背诵
  public long  remainder(int x,int a,int p){  //x为底数，a为幂，p为要取的模
    long rem = 1 ;
    for (int i = 0; i < a; i++) {
      rem = (rem * x) % p ;
    }
    return rem;
  }

}
