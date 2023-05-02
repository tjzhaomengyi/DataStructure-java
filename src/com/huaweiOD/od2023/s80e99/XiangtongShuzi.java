package com.huaweiOD.od2023.s80e99;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-19 18:43
 * @Description:
 */
public class XiangtongShuzi {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int len = Integer.valueOf(in.nextLine());
      HashMap<Integer, ArrayList<Integer>> index_dict = new HashMap<>();
      for(int i = 0; i < len; i++){
        int num = Integer.valueOf(in.nextLine());
        if(index_dict.get(num) == null){
          ArrayList<Integer> indexes = new ArrayList<>();
          indexes.add(i);
          index_dict.put(num, indexes);
        } else {
          ArrayList<Integer> indexes = index_dict.get(num);
          indexes.add(i);
          index_dict.put(num, indexes);
        }
      }
      int max = -1;
      for(int key : index_dict.keySet()){
        ArrayList<Integer> list = index_dict.get(key);
        if(list.size() > 1){
          max = Math.max(max, list.get(list.size() - 1) - list.get(0));
        }
      }
      System.out.println(max);
    }
  }
}
