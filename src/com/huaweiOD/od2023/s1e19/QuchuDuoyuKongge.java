package com.huaweiOD.od2023.s1e19;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-26 12:37
 * @Description:
 */
public class QuchuDuoyuKongge {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String marks = in.nextLine();
      if(str.replaceAll("\\s+", " ").length() == str.length()){
        System.out.println(str);
        System.out.println(marks);
      }
      char[] str_arr = str.toCharArray();
      String[] marks_split = marks.split(",");
      LinkedList<int[]> marks_lst = new LinkedList<>();
      for(int i = 0; i < marks_split.length; i++){
        int[] pos = new int[]{Integer.valueOf(marks_split[i].split(" ")[0]), Integer.valueOf(marks_split[i].split(" ")[1])};
        marks_lst.add(pos);
      }

      char[] ans1 = new char[str.length()];
      LinkedList<int[]> ans2 = new LinkedList<>();

      int[] first = marks_lst.poll();
      int start = first[0];
      int end = first[1];
      boolean s_flg = false;
      int more_space_cnt = 0;
      for(int i = 0, j = 0; i < str_arr.length && j < ans1.length; i++){
        if(!s_flg && i - 1 >= 0 && str_arr[i - 1] == ' ' && str_arr[i] == ' '){
          more_space_cnt++;
          continue;
        } else if(!s_flg && str_arr[i] == '\''){
          s_flg = true;
        } else if(s_flg && str_arr[i] == '\''){
          s_flg = false;
        }
        if(i == start){
          int new_start = start - more_space_cnt;
          int new_end = end - more_space_cnt;
          ans2.add(new int[]{new_start, new_end});
          if(!marks_lst.isEmpty()){
            first = marks_lst.poll();
            start = first[0];
            end = first[1];
          }
        }
        ans1[j++] = str_arr[i];
      }
      System.out.println(String.valueOf(ans1));
      for(int i = 0; i < ans2.size(); i++){
        System.out.print("[" + ans2.get(i)[0] +","+ ans2.get(i)[1] + "]");
      }
      System.out.println();
    }
  }




}
