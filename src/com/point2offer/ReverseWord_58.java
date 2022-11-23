package com.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-09 22:21
 * @Description:将一个句子反转，开头和末尾的空格不要，中间两个空格变一个空格
 */
public class ReverseWord_58 {
  public String reverseWords(String s) {
    String[] strs = s.trim().split(" ");
    StringBuilder res = new StringBuilder();
    for(int i= strs.length-1;i>=0;i--){
      if(strs[i].equals(""))continue;
      else{
        res.append(strs[i]).append(" ");
      }
    }
    return res.toString().trim();
  }

  public String leftReverseWord(String s,int n){
    //return s.substring(n,s.length())+s.substring(0,n);
    String res="";
    for(int i=n;i<n+s.length();i++){
      res+=s.charAt(i%s.length());
    }
    return res;
  }

}
