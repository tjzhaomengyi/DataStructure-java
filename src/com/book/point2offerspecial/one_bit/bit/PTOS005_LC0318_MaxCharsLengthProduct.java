package com.book.point2offerspecial.one_bit.bit;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-13 6:29 下午
 * @Description:
 */
public class PTOS005_LC0318_MaxCharsLengthProduct {
  public int maxProduct(String[] words) {
    int[] flag = new int[words.length];//用一个整形32位中的26位来代表每个字符串中某个26字符是否存在
    for(int i = 0; i < words.length; i++){
      char[] cs = words[i].toCharArray();
      for(char c : cs){
        flag[i] |= 1 << (c - 'a');
      }
    }
    int ans = 0;
    for(int i = 0; i < words.length; i++){
      for(int j = i + 1; j < words.length; j++){
        if((flag[i] & flag[j]) == 0){
          ans = Math.max(words[i].length() * words[j].length(),ans);
        }
      }
    }
    return ans;
  }
}
