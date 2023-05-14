package com.huaweiOD.score200;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-12 17:23
 * @Description:
 */
public class ZuhechuZuixiaoshu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String[] arr = str.split(" ");
      StringBuilder sb = new StringBuilder();
      ArrayList<String> no_zero_arr = new ArrayList<>();
      ArrayList<String> zero_arr = new ArrayList<>();
      for(int i = 0; i < arr.length; i++){
        String s = arr[i];
        if(s.startsWith("0")){
          zero_arr.add(s);
        } else {
          no_zero_arr.add(s);
        }
      }
      Collections.sort(no_zero_arr);
      Collections.sort(zero_arr);
      if(no_zero_arr.size() == 0){
        for(int i = 0; i < zero_arr.size(); i++){
          sb.append(zero_arr.get(i));
        }
        System.out.println(sb.toString().substring(1));
      } else if(zero_arr.size() == 0){
        for(int i = 0; i < no_zero_arr.size(); i++){
          sb.append(no_zero_arr.get(i));
        }
        System.out.println(sb.toString());
      } else {
        sb.append(no_zero_arr.get(0));
        for(int i = 0; i < zero_arr.size(); i++){
          sb.append(zero_arr.get(i));
        }
        for(int j = 1; j < no_zero_arr.size(); j++){
          sb.append(no_zero_arr.get(j));
        }
        System.out.println(sb.toString());
      }


    }
  }
}
