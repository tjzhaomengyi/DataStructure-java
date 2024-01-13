package com.zhousai.zhousai346;


import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-21 11:16
 * @Description:
 */
public class Chengfashu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int n  = in.nextInt();
      int ans = punishmentNumber(n);
      System.out.println(ans);
    }
  }
  public static int punishmentNumber(int n) {
    int ans = 0;
    for(int i = 1; i <= n; i++){
      if(isChengfashu(i)){
        ans += i;
      }
    }
    return ans;
  }

  public static boolean isChengfashu(int target){
    int pow = target * target;
    String str = String.valueOf(pow);
    int[] nums = new int[str.length()];
    for(int i = 0; i < str.length(); i++){
      nums[i] = Character.getNumericValue(str.charAt(i));
    }
    int len = String.valueOf(target).length();
    return process(target, len, nums, 0, 0);
  }

  public static boolean process(int target,int len, int[] nums, int index, int tmp){
    if(tmp == target && index == nums.length){
      return true;
    }
    if(tmp != target && index == nums.length){
      return false;
    }
    int add = nums[index];
    boolean ans =  process(target, len, nums, index + 1, tmp + add);//只要一个
    for(int i = 1; i < len; i++){
      add += add * 10 + nums[i];
      if(add > target){
        break;
      } else {
        ans = ans || (process(target, len, nums, index + i, tmp + add) &&
            process(target, len, nums, index + i + 1, tmp + add));
      }
    }
    return ans;
  }
}
