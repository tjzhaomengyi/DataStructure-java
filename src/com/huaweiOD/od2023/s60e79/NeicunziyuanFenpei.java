package com.huaweiOD.od2023.s60e79;

import org.omg.CORBA.INTERNAL;

import java.util.Scanner;
import java.util.TreeMap;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-21 9:20
 * @Description:
 */
public class NeicunziyuanFenpei {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str_mem = in.nextLine();
      String str_use = in.nextLine();
      String[] mems = str_mem.split(",");
      String[] uses = str_use.split(",");
      String ans = canuse(mems, uses);
      System.out.println(ans);
    }
  }

  public static String canuse(String[] mems, String[] uses){
    TreeMap<Integer, Integer> memMap = new TreeMap<>();
    int[] use_arr = new int[uses.length];
    String ans = "";
    for(int i = 0; i < mems.length; i++){
      int mem_daxiao = Integer.valueOf(mems[i].split(":")[0]);
      int mem_kuaishu = Integer.valueOf(mems[i].split(":")[1]);
      memMap.put(mem_daxiao, mem_kuaishu);
    }
    for(int i = 0; i < uses.length; i++){
      use_arr[i] = Integer.valueOf(uses[i]);
    }
    for(int use : use_arr){
      String tmp_ans = "false";
      for(int key : memMap.keySet()){
        if(use <= key){
          int val = memMap.get(key) - 1;
          tmp_ans = "true";
          if(val == 0){
            memMap.remove(key);
          } else {
            memMap.put(key, val);
          }
          break;
        }
      }
      ans += tmp_ans + ",";
    }
    return ans.substring(0, ans.length() - 1);
  }

}
