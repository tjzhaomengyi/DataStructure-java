package com.book.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-06 14:51
 * @Description:给一个字符串，判断是不是合理数字，这是个状态转移的问题
 * 正确：["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
 * 错误：["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
 *
 */
public class IsNumber_20 {
  public boolean isNumber(String s) {
    if(s==null||s.length()==0) return false;//没什么可说的
    boolean isNum = false;//是否遇到数位
    boolean isDot = false;//是否遇到小数点
    boolean ise_or_E = false;//是否遇到e
    char[] str = s.trim().toCharArray();
    for(int i=0;i<str.length;i++){
      if(str[i]>='0' && str[i]<='9') isNum=true;//判断当前的数位
      else if(str[i]=='.'){
        if(isDot||ise_or_E){
          return false;
        }
        isDot = true;
      }
      else if(str[i]=='e' || str[i]=='E') {
        if(!isNum||ise_or_E){
          return false;
        }
        ise_or_E = true;
        isNum = false;
      }
      else if(str[i]=='+'||str[i]=='-'){
        if(i!=0 && str[i-1]!='e' && str[i-1] !='E')return false;
      }
      else{
        return false;
      }
    }
    return isNum;
  }
}
