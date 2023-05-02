package com.huaweiOD.od2023.s80e99;

import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-19 12:13
 * @Description:
 */
public class RenshuZuiduoZhandian {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int len = Integer.valueOf(in.nextLine());
      TreeMap<Integer, Integer> statics = new TreeMap<>();
      for(int i = 0; i < len; i++){
        String[] start_end = in.nextLine().split(" ");
        int start = Integer.valueOf(start_end[0]);
        int end = Integer.valueOf(start_end[1]);
        for(int zhan = start; zhan < end; zhan++){
          statics.put(zhan, statics.getOrDefault(zhan, 0) + 1);
        }
      }
      int max_cnt = Collections.max(statics.values());
      for(int k : statics.keySet()){
        if(max_cnt == statics.get(k)){
          System.out.println(k);
        }
      }

    }
  }
}
