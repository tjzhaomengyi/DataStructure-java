package com.huaweiOD.nowcoder;

import java.util.Scanner;


// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class BigSum {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 注意 hasNext 和 hasNextLine 的区别
    while (in.hasNext()) { // 注意 while 处理多个 case
      String a = in.next();
      String b = in.next();
      System.out.println(getSum(a,b));
    }
  }

  public static String getSum(String a, String b){
    String ar = new StringBuilder(a).reverse().toString();
    String br = new StringBuilder(b).reverse().toString();
    int lena = ar.length();
    int lenb = br.length();
    String l = lena >= lenb ? ar : br;
    String s = l == ar ?  br : ar;

    char[] ans = new char[l.length()];
    int carry = 0;
    for(int i = 0; i < l.length(); i++){
      int m = Character.getNumericValue(l.charAt(i));
      int n = i <= s.length() - 1 ? Character.getNumericValue(s.charAt(i)) : 0;
      int temp = (m + n + carry) % 10;
      carry = (m + n + carry) / 10;
      ans[i] = String.valueOf(temp).charAt(0);
    }
    String ans_tmp = new StringBuilder(new String(ans)).reverse().toString();
    if(carry == 1){
      return "1" + ans_tmp;
    }
    return ans_tmp;
  }


}