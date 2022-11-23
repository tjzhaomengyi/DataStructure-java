package com.mikemyzhao.SomeSkills_0.ShuWeiWenTi;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 8:34
 * @Description:
 */
public class LC_0171_WordToInteger {
  //用A表示1，B表示2，....这是一个类26进制问题
  //数学结论:每一位遍历*26 然后当前为减一下+1
  public static int titleToNumber(String s){
    char[] str = s.toCharArray();
    int ans = 0;
    for(int i = 0; i < str.length; i++){
      ans = ans * 26 + (str[i] - 'A') + 1;//技巧:注意是伪26进制所以这里要加1
    }
    return ans;
  }
}
