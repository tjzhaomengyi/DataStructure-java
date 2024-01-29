package com.book.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-04 15:43
 * @Description:
 */
public class ReplaceSpace_05 {
  public String replaceSpace(String s) {
    if(s==null){
      return null;
    }
    StringBuilder sb = new StringBuilder();
    for(int i=0;i<s.length();i++){
      if(String.valueOf(s.charAt(i)).equals(" ")){
        sb.append("%20");
      }else{
        sb.append(s.charAt(i));
      }
    }
    return sb.toString();
  }
}
