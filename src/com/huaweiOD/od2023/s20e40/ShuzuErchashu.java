package com.huaweiOD.od2023.s20e40;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-24 16:32
 * @Description:
 */
public class ShuzuErchashu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String[] str = in.nextLine().split(" ");
      int[] nums = new int[str.length + 1];//多一位0，从1开始
      for(int i = 0; i < str.length; i++){
        nums[i + 1] = Integer.valueOf(str[i]);//
      }
      ArrayList<Integer> ans = getPath(nums);
      for(int i = ans.size() - 1; i >=0; i--){
        System.out.print(ans.get(i) + " ")  ;
      }
      System.out.println();
    }
  }
  public static ArrayList<Integer> getPath(int[] nums){
    ArrayList<Integer> path = new ArrayList<>();
    TreeMap<Integer, Integer> record = new TreeMap<>();//记录叶子节点:key是值，val是位置
    for(int i = 1; i < nums.length ; i++){
      if(isLeaf(nums, i)){
        record.put(nums[i], i);
      }
    }
    int minValIndex = record.get(record.firstKey());
    while(minValIndex != 0){
      path.add(nums[minValIndex]);
      minValIndex = minValIndex / 2;
    }
    return path;
  }
  public static boolean isLeaf(int[] nums, int index){
    int len = nums.length;
    if(index * 2 >= nums.length && nums[index] != -1){
      return true;
    }
    if(nums[index] != -1 && nums[index * 2] == -1 && nums[index * 2 + 1] == -1){
      return true;
    }

    return false;
  }


}
