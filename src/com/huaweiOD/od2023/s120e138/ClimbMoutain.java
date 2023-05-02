package com.huaweiOD.od2023.s120e138;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-16 10:33
 * @Description:
 */
public class ClimbMoutain {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int step = in.nextInt();
      System.out.println(getCount(step));
    }
  }
  public static int getCount(int step){
    if(step < 3){
      return 1;
    }
    return getCount(step - 1) + getCount(step - 3);
  }

}

