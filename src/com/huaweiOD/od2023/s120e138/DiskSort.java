package com.huaweiOD.od2023.s120e138;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-17 17:17
 * @Description:
 */
public class DiskSort {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int n = Integer.valueOf(in.nextLine());

      int[][] nums = new int[n][2];
      Map<Integer, String> hashMap = new HashMap<>();
      for(int i = 0; i < n; i++){
        int sum = 0;
        int index = -1;
        String value = in.nextLine();
        hashMap.put(i + 1, value);
        for(int j = 0; j < value.length(); j++){
          if('M' == value.charAt(j)){
            sum += Integer.parseInt(value.substring(index + 1, j));
            index = j;
          } else if('G' == value.charAt(j)){
            sum += Integer.parseInt(value.substring(index + 1, j)) * 1024;
            index = j;
          } else if('T' == value.charAt(j)){
            sum += Integer.parseInt(value.substring(index + 1, j)) * 1024 * 1024;
            index = j;
          }
        }
        nums[i][0] = i + 1;
        nums[i][1] = sum;
      }
      Arrays.sort(nums, (e1, e2) -> (e1[1] == e2[1] ? (e1[0] - e2[0]) : (e1[1] - e2[1])));
      for(int i = 0; i < n; i++){
        if(i == n - 1){
          System.out.print(hashMap.get(nums[i][0]));
        } else {
          System.out.println(hashMap.get(nums[i][0]));
        }
      }
    }

  }


}
