package com.mikemyzhao.SomeSkills_0.tricks.HuiWenXiLie;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-17 13:29
 * @Description:
 */
public class LC_0125_ValidPalindrome {
  //忽略空格、大小写，数字不在忽略大小写的范围
  public static boolean isPalindrome(String s){
    if(s == null || s.length() == 0){
      return true;
    }
    char[] str = s.toCharArray();
    int L = 0;
    int R = str.length - 1;
    while(L < R){
      if(validChar(str[L]) && validChar(str[R])){
        if(!equal(str[L],str[R])){
          return false;
        }
        L++;
        R--;
      } else {
        //空字符串和其他特殊字符的处理,技巧:如果是正常字符就不动，等不正常的动好再变
        L += validChar(str[L]) ? 0 : 1;
        R -= validChar(str[R]) ? 0 : 1;
      }
    }
    return true;
  }

  //技巧:1判断字符是否合法
  public static boolean validChar(char c){
    return isLetter(c) || isNumber(c);
  }
  public static boolean isLetter(char c){
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
  }
  public static boolean isNumber(char c){
    return (c >= '0' && c <= '9');
  }

  //技巧:2判断字符是否相等
  public static boolean equal(char c1, char c2){
    if(isNumber(c1) || isNumber(c2)){//数学结论:如何比两个数字字符，只要有一个是数字就行
      return c1 == c2;
    }
    //数学结论:大小写相差32！！！！
    return (c1 == c2) || (Math.abs(c1 - c2) == 32);
  }
}
