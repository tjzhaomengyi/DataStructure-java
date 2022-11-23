package com.mikemyzhao.SomeSkills_0.ShuWeiWenTi;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-14 18:06
 * @Description:
 */
public class LC008_StringToInteger {
  public static int myAtoi(String s){
    if(s == null || s.equals("")){
      return 0;
    }
    s = removeHeadZero(s.trim());
    if(s == null || s.equals("")){
      return 0;
    }
    char[] str = s.toCharArray();
    if(!isValid(str)){
      return 0;
    }

    //str符合正常书写，用负数转
    boolean posi = str[0] == '-' ? false : true;
    //技巧：负数转正数的关键
    int minq = Integer.MIN_VALUE / 10;
    int maxr = Integer.MIN_VALUE % 10;
    int res = 0;
    int cur = 0;
    for(int i = (str[0] == '-' || str[0] == '+') ? 1 : 0; i < str.length; i++){
      //把每位数都变成负数
      cur = '0' - str[i];
      //技巧：这里是用负数转各种正数的关键
      if((res < minq) || (res == minq && cur < maxr)){
        return posi ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }
      res = 10 * res + cur;
    }
    if(posi && res == Integer.MIN_VALUE){
      return Integer.MAX_VALUE;
    }
    return posi ? -res : res;
  }
  public static String removeHeadZero(String str){
    boolean r = (str.startsWith("+") || str.startsWith("-"));
    int s= r ? 1 : 0;
    for(; s < str.length(); s++){
      if(str.charAt(s) != '0'){
        break;
      }
    }
    //s到了第一个不是0的位置
    int e = -1;
    //左 <- 右
    for(int i = str.length() - 1; i >= (r ? 1 : 0); i--){
      if(str.charAt(i) < '0' || str.charAt(i) > '9'){
        e = i;
      }
    }
    return (r ? String.valueOf(str.charAt(0)) : "") + str.substring(s, e == -1 ? str.length() : e);
  }

  public static boolean isValid(char[] chas){
    if(chas[0] != '-' && chas[0] != '+' && (chas[0] < '0' || chas[0] > '9')){
      return false;
    }
    if((chas[0]=='-' || chas[0] == '+') && chas.length == 1){
      return false;
    }
    for(int i = 1; i < chas.length; i++){
      if(chas[i] < '0' || chas[i] > '9'){
        return false;
      }
    }
    return true;
  }
}
