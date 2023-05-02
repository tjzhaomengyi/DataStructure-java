package com.huaweiOD.od2023.s40e59;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-23 18:50
 * @Description:
 */
public class ShujuFenlei {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String[] str = in.nextLine().split(" ");
      int[] nums = new int[10];
      int c = Integer.valueOf(str[0]); //3
      int b = Integer.valueOf(str[1]); // 4
      for(int i = 0; i < 10; i++){
        nums[i] = Integer.valueOf(str[i + 2]);
      }
      int ans = fenlei(nums, c, b);
      System.out.println(ans);
    }
  }

  //小于c， 模b
  public static int fenlei(int[] nums, int c, int b){
    int maxCnt = 0;
    HashMap<Integer, Integer> youxiaoDict = new HashMap<>();
    for(int i = 0; i < nums.length; i++){
      int num = nums[i];
      String num_bin = Integer.toBinaryString(num);
      int bin_sum = 0;
      while(num_bin.length() > 8){
        String tmp = num_bin.substring(num_bin.length() - 8);
        bin_sum += Integer.parseInt(tmp);
        num_bin = num_bin.substring(0, num_bin.length() - 8);
      }
      bin_sum += Integer.parseInt(num_bin);//最后不足8位的还要加一下
      int yushu = bin_sum % b;
      if(yushu < c){
        youxiaoDict.put(yushu, youxiaoDict.getOrDefault(yushu, 0) + 1);
      }
    }

    ArrayList<Map.Entry<Integer, Integer>> youxiaoList = new ArrayList<>();
    for(Map.Entry<Integer, Integer> e : youxiaoDict.entrySet()){
      youxiaoList.add(e);
    }
    Collections.sort(youxiaoList, (o1, o2) -> {
      return o2.getValue() - o1.getValue();
    });
    maxCnt = youxiaoList.get(0).getValue();
    return maxCnt;
  }
}
