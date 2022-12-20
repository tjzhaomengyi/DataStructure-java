package com.hots100.huiwen;


/**
 * @Author: zhaomengyi
 * @Date: 2022-12-08 4:56 下午
 * @Description:
 */
public class Code0647_CountSubstrings {
  //manacher变种题,技巧：使用了最长回文直径的"巧劲"，回文直径有多长，那么该中心点可以扩充的点就有直径点+1个
  public int countSubstrings(String s) {
    if(s == null || s.length() == 0) return 0;
    int[] dp = mancher(s);
    int ans = 0;
    for(int i = 0; i < dp.length; i++){
      ans += dp[i] >> 1;
    }
    return ans;
  }

  public int[] mancher(String s){
    if(s.length() == 0 || s == null) return null;
    char[] str = transform(s);
    int C = -1;
    int R = -1;
    int[] pArr = new int[str.length];//记录转换字符串的长度
    for(int i = 0; i < str.length; i++){
      pArr[i] = i < R ? Math.min(R - i, pArr[2 * C - i]) : 1;
      while(i + pArr[i] < str.length  && i - pArr[i] > -1){
        if(str[i + pArr[i]] == str[i - pArr[i]]){
          pArr[i]++;
        } else {
          break;
        }
      }
      if(i + pArr[i] > R){
        R = i + pArr[i];
        C = i;
      }
    }
    return pArr;
  }

  public char[] transform(String s){
    StringBuilder tmp = new StringBuilder();
    for(char ch : s.toCharArray()){
      tmp.append("#"+ch);
    }
    tmp.append("#");
    return tmp.toString().toCharArray();
  }
}
