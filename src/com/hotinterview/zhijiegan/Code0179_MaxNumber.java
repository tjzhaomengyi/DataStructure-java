package com.hotinterview.zhijiegan;

import com.MCAAlgorithm.base.class13.Code05_LowestLexicography;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-27 12:17 下午
 * @Description:
 */
public class Code0179_MaxNumber {
  public static class MyComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
      return (o2 + o1).compareTo(o1 + o2);
    }
  }
  public String largestNumber(int[] nums) {
    //思路：就是返回最大的字典序的字符串,借助一个比较器直接干，把字典序大的放在前面
    if(nums == null || nums.length == 0){
      return "";
    }
    String[] strs = new String[nums.length];
    int cnt0 = 0;
    for(int i = 0; i < nums.length; i++){
      if(nums[i] == 0) {
        cnt0++;
      }
      strs[i] = String.valueOf(nums[i]);
    }
    if(cnt0 == nums.length) return "0";
    Arrays.sort(strs, new MyComparator());
    String res = "";
    for(int i = 0; i < strs.length; i++){
      res += strs[i];
    }
    if(res.charAt(0) == '0' && res.length() > 1){
      res.replaceFirst("0","");
    }
    return res;
  }
}
