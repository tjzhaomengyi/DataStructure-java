package com.mikemyzhao.SomeSkills_0.tricks;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-16 20:14
 * @Description:
 */
public class LC_0062_UniquePath {
  //技巧:求C这种问题的解法
  public int uniquePaths(int m, int n){
    if(m == 1 || n == 1){
      return 1;
    }
    int right = n - 1;
    int all = m + n - 2;
    long o1 = 1;
    long o2 = 1;
    //计算C(m+n-2,n-1)
    for(int up = all,down = 1;down <= right;up--,down++){
      o1 *= up;
      o2 *= down;
      long gcd = gcd(o1, o2);
      o1 /=gcd;
      o2 /= gcd;
    }
    return (int) o1;
  }

  public static long gcd(long a,long b){
    return b == 0 ? a : gcd(b, a % b);
  }


  public int uniquePathsbest(int m, int n) {
    long ans = 1;
    for (int x = n, y = 1; y < m; x++, y++) {
      ans = ans * x / y;
    }
    return (int) ans;
  }

}
