package com.huaweiOD.od2023.s40e59;

import com.MCAAlgorithm.bigshua.class05.Hash;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-21 20:44
 * @Description:
 */
public class YanseTuse {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int n = Integer.valueOf(in.nextLine());
      ArrayList<Integer> nums = new ArrayList<>();
      String[] strs = in.nextLine().split(" ");
      for(int i = 0; i < n; i++){
        nums.add(Integer.valueOf(strs[i]));
      }
      Collections.sort(nums);
      int[] cnts = new int[nums.size()];
      Arrays.fill(cnts, -1);//么有颜色
      for(int i = 0; i < nums.size(); i++){
        int base = nums.get(i);
        for(int j = i + 1; j < nums.size(); j++){
          if(cnts[j] != -1){
            continue;
          }
          if(nums.get(j) % base == 0 && cnts[j] == -1){
            cnts[j] = i;
            if(cnts[i] == -1){
              cnts[i] = i;//让i也把颜色补上
            }
          }
        }
      }
      int cnt = 0;
      HashSet<Integer> tmp = new HashSet<>();
      for(int i = 0; i < cnts.length; i++){
        if(cnts[i] == -1){
          cnt++;
        } else {
          tmp.add(cnts[i]);
        }
      }
      System.out.println(cnt + tmp.size());

    }
  }


}
