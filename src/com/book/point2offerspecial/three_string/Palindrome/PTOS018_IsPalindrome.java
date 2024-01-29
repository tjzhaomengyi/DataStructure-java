package com.book.point2offerspecial.three_string.Palindrome;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-03 15:50
 * @Description:
 */
public class PTOS018_IsPalindrome {
  public boolean isPalindrome(String s){
    if(s == null || s.equals(" ") || s.length() == 0) return true;
    char[] str = s.toCharArray();
    int i = 0, j = str.length - 1;
    while(i < j){
      if(!Character.isLetterOrDigit(str[i])){
        i++;
      }else if(!Character.isLetterOrDigit(str[j]) ){
        j--;
      } else {
        char si = Character.toLowerCase(str[i]);
        char sj = Character.toLowerCase(str[j]);
        if (si != sj) {
          return false;
        } else {
          i++;
          j--;
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    boolean ans = new PTOS018_IsPalindrome().isPalindrome("0P");
    System.out.println(ans);
  }
}
