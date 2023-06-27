package com.huaweiOD.score200;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-30 10:04
 * @Description:
 */
public class ZuichaChanpin {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int win_len =Integer.valueOf(in.nextLine());
      String[] str = in.nextLine().split(",");
      int[] nums = new int[str.length];
      for(int i = 0; i < str.length; i++){
        nums[i] = Integer.valueOf(str[i]);
      }
      int L = 0;
      int R = L + win_len - 1;
      ArrayList<Integer> ans = new ArrayList<>();
      int[] tmp_min = new int[2];
      while(R < str.length){
        int min = Integer.MAX_VALUE;
        for(int i = L; i <= R; i++){
          min = Math.min(min, nums[i]);
        }
        ans.add(min);
        L++;
        R++;
      }
      for(int i = 0; i < ans.size(); i++){
        System.out.print(ans.get(i) + ",");
      }
      System.out.println();
    }
  }


}
