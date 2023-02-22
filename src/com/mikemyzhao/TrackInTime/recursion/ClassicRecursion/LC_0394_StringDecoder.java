package com.mikemyzhao.TrackInTime.recursion.ClassicRecursion;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-19 11:51
 * @Description:
 */
public class LC_0394_StringDecoder {
  //经典递归解法
  public String decodeString(String s) {
    char[] str = s.toCharArray();
    return process(str, 0).ans;
  }

  public static class Info{
    public String ans;
    public int stop;

    public Info(String a, int e){
      ans = a;
      stop = e;
    }
  }

  //s[i...]什么时候遇到']'或者遇到s的终止位置，停止，返回Info(1)串(2)算到了哪
  public static Info process(char[] s, int i){
    StringBuilder ans = new StringBuilder();
    int count = 0;
    while(i < s.length && s[i] != ']'){
      if((s[i] >= 'a' && s[i] <= 'z') || (s[i] >= 'A' && s[i] <= 'Z')){
        ans.append(s[i++]);
      } else if(s[i] >= '0' && s[i] <= '9'){
        count = count * 10 + s[i++] - '0';
      } else { //str[i] = '['
        Info next = process(s, i + 1);//遇到了[，收集括号中的内容
        ans.append(timeString(count, next.ans));
        count = 0;
        i = next.stop + 1;
      }
    }
    return new Info(ans.toString() , i);
  }

  public static String timeString(int times, String str){
    StringBuilder ans = new StringBuilder();
    for(int i = 0; i < times; i++){
      ans.append(str);
    }
    return ans.toString();
  }
}
