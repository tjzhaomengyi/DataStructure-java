package com.huaweiOD.od2023.s120e138;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-17 10:47
 * @Description:
 */
public class XiaopengyouFenban {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      PriorityQueue<Integer> q0 = new PriorityQueue<>();
      PriorityQueue<Integer> q1 = new PriorityQueue<>();
      int pre = 0;
      String[] strs = str.split(" ");
      for(int i = 0; i < strs.length; i++) {
        int index = Integer.valueOf(strs[i].split("/")[0]);
        String isSame = strs[i].split("/")[1];
        if(i == 0){
          q0.offer(index);
          pre = 0;
        } else {
          if(isSame.equals("Y") && pre == 0){
            q0.offer(index);
          } else if(isSame.equals("Y") && pre == 1){
            q1.offer(index);
          } else if(isSame.equals("N") && pre == 0){
            q1.offer(index);
            pre = 1;
          } else if(isSame.equals("N") && pre == 1){
            q0.offer(index);
            pre = 0;
          }
        }
      }
      while(!q0.isEmpty()){
        System.out.print(q0.poll() + " " );
      }
      System.out.println();
      while (!q1.isEmpty()){
        System.out.print(q1.poll() + " ");
      }
    }


  }




}
