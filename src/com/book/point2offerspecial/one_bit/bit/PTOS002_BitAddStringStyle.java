package com.book.point2offerspecial.one_bit.bit;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-11 10:33 上午
 * @Description:
 */
public class PTOS002_BitAddStringStyle {
  public String addBinary(String a, String b) {
    StringBuilder ans = new StringBuilder();
    int carry = 0;
    int i = a.length() - 1;
    int j = b.length() - 1;
    while(i >=0 || j >= 0){
      int digA = i >= 0 ? a.charAt(i--) - '0' : 0;
      int digB = j >= 0 ? b.charAt(j--) - '0' : 0;
      int sum = digA + digB + carry;
      carry = sum >= 2 ? 1 : 0;
      sum = sum >= 2 ? sum - 2 : sum;
      ans.append(sum);
    }
    if(carry == 1){
      ans.append(1);
    }
    return ans.reverse().toString();
  }
}
