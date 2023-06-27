package com.huaweiOD.score200;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-15 10:06
 * @Description:
 */
public class QuchuDuoyuKongge {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String[] indexs = in.nextLine().split(",");
      int[] index_arr = new int[indexs.length * 2];
      for(int i = 0; i < indexs.length; i++){
        String index = indexs[i];
        index_arr[2 * i] = Integer.valueOf(index.split(" ")[0]);
        index_arr[2 * i + 1] = Integer.valueOf(index.split(" ")[1]);
      }

      int cnt_space = 0;
      boolean is_yinhao = false;
      StringBuilder sb = new StringBuilder();
      for(int i = 0; i < str.length(); i++){
        if(i >= 1 && is_yinhao == false && str.charAt(i) == ' ' && str.charAt(i - 1) == ' '){
          cnt_space++;
          xiugaiweizhi(index_arr, cnt_space, i);
          continue;
        } else if(is_yinhao == false && str.charAt(i) == '\''){
          is_yinhao = true;
        } else if(is_yinhao == true && str.charAt(i) == '\''){
          is_yinhao = false;
        }
        sb.append(str.charAt(i));
      }
      System.out.println(sb.toString());
      for(int i = 0; i < indexs.length; i++){
        System.out.print("[" + index_arr[2 * i] + "," + index_arr[2 * i + 1] + "]");
      }
      System.out.println();
    }
  }

  public static void xiugaiweizhi(int[] index_arr, int cnt_space, int index){
    for(int i = 0; i < index_arr.length; i++){
      if(index_arr[i] > index){
        index_arr[i] = index_arr[i] - cnt_space;
      }
    }
  }
}
