package com.point2offerspecial.five_map.two_yingyong;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-07 10:18
 * @Description:
 */
public class PTOS034_IsAlienAnagram {
  public boolean isAlienSorted(String[] words, String order){
    int[] orderArray = new int[order.length()];
    char[] orderStr = order.toCharArray();
    for(int i = 0; i < order.length(); i++){
      orderArray[orderStr[i] - 'a'] = i;
    }
    for(int i = 0; i < words.length - 1; i++){
      if(!isSorted(words[i] , words[i + 1], orderArray)){
        return false;
      }
    }
    return true;
  }

  private boolean isSorted(String word1, String word2, int[] order){
    int i = 0;
    for(; i < word1.length() && i < word2.length(); i++){
      char c1 = word1.charAt(i);
      char c2 = word2.charAt(i);
      if(order[c1 - 'a'] < order[c2 - 'a']){
        return true;
      }
      if(order[c1 - 'a'] > order[c2 - 'a']){
        return false;
      }
    }
    return i == word1.length();
  }
}
