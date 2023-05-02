package com.huaweiOD.nowcoder;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-10 11:46
 * @Description:
 */
public class MP3List {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 注意 hasNext 和 hasNextLine 的区别
    while (in.hasNext()) { // 注意 while 处理多个 case
      int len = in.nextInt();
      String ops = in.next();
      char[] ops_arr = ops.toCharArray();
      int ops_len = ops_arr.length;
      int cur_pos = 1;
      ArrayList nowShow = new ArrayList();
      for(int i = 1; i <= 4; i++){
        nowShow.add(i);
      }
      for(int i = 0; i < ops_len; i++){
        int[] pre_cur = move(len, cur_pos, String.valueOf(ops_arr[i]));
        cur_pos = pre_cur[1];
        nowShow = showList(len, pre_cur, nowShow);
      }
      for(int i = 0; i < 4; i++){
        System.out.println(i + " ");
      }
      System.out.println();
      System.out.print(cur_pos);
    }
  }

  public static ArrayList showList(int len, int[] pre_cur, ArrayList<Integer> nowShow){
    ArrayList<Integer> showList = new ArrayList<>();
    int pre = pre_cur[0];
    int cur = pre_cur[1];
    if(pre == len && cur == 1){
      for(int i = 1; i <= 4; i++){
        showList.add(i);
      }
    } else if(pre == 1 && cur == len){
      for(int i = len - 4 + 1; i <= len; i++){
        showList.add(i);
      }
    } else {
      if(!nowShow.contains(cur)){
        if(pre > cur){
          for(int i = cur; i <= cur + 3; i++){
            showList.add(i);
          }
        } else if(pre < cur){
          for(int i = cur - 3; i <= cur; i++){
            showList.add(i);
          }
        }
      } else {
        showList = nowShow;
      }
    }
    return showList;
  }

  public static int[] move(int len, int cur_pos, String dir){
    int next_pos = 0;

    if(dir.equals("D")){
      if(cur_pos == len){
        next_pos = 1;
      } else {
        next_pos = cur_pos + 1;
      }
    } else if(dir.equals("U")){
      if(cur_pos == 1){
        next_pos = len;
      } else {
        next_pos = cur_pos - 1;
      }
    }

    return new int[]{cur_pos, next_pos};
  }
}
