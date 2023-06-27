package com.zhousai.zhousai345;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-14 10:32
 * @Description:
 */
public class Lunzhuanyouxishujia {
  public static void main(String[] args) {
   int[] ans = circularGameLosers(5, 2);
   for(int i = 0; i < ans.length; i++){
     System.out.println(ans[i]);
   }
  }

  public static int[] circularGameLosers(int n, int k) {
    int[] friends = new int[n];
    for(int i = 0; i < n; i++){
      friends[i] = i + 1;
    }
    int[] record_index = new int[n];
    record_index[0] = 1;
    int beishu = 1;
    int start = 0;
    int next = (start + beishu * k) % n;
    while(record_index[next] == 0){
      record_index[next] = 1;
      beishu++;
      start = next;
      next = (start + beishu * k) % n;
    }
    int index = 0;
    ArrayList<Integer> tmp = new ArrayList<>();
    for(int i = 0; i < record_index.length; i++){
      if(record_index[i] == 0){
        tmp.add(i + 1);
      }
    }
    int[] ans = new int[tmp.size()];
    for(int i = 0; i < tmp.size(); i++){
      ans[i] = tmp.get(i);
    }

    return ans;
  }
}
