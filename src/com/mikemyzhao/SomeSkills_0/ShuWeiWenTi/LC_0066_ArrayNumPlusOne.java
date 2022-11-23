package com.mikemyzhao.SomeSkills_0.ShuWeiWenTi;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-16 20:54
 * @Description:
 */
public class LC_0066_ArrayNumPlusOne {
  public static int[] plusOne(int[] digits){
    int n = digits.length;
    for(int i = n - 1; i >= 0; i--){
      if(digits[i] < 9){
        digits[i]++;
        return digits;
      }
      digits[i]=0;
    }
    //如果不是上面的那样，直接构建新的数字数组
    int[] ans = new int[n + 1];
    ans[0] = 1;
    return ans;
  }
}
