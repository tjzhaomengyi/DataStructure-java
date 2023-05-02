package com.huaweiOD.od2023.s80e99;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-18 19:33
 * @Description:
 */
public class QuanpailieDeDinge {
  static ArrayList<String> ans = new ArrayList<>();
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int n = Integer.valueOf(in.nextLine());
      int k = Integer.valueOf(in.nextLine());
      int[] nums = new int[n];
      int[] visited = new int[n];
      for(int i = 0; i < n; i++){
        nums[i] = i + 1;
      }
      process(nums,  visited, "");
//      for(int i = 0; i < ans.size(); i++){
//        System.out.println(ans.get(i));
//      }
      Collections.sort(ans);
      System.out.println(ans.get(k - 1));
    }
  }

  public static void process(int[] nums, int[] visited, String path){
    int len = nums.length;
    if(path.length() == len){
      ans.add(path);
    }
    for(int i = 0; i < nums.length; i++) { //技巧:注意是全排列，所以这里要for
      if (visited[i] == 0) {
        visited[i] = 1;
        process(nums,  visited, path + nums[i]);
        process(nums, visited, path);
        visited[i] = 0;
      }
    }
  }


}
