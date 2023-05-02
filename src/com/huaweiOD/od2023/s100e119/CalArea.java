package com.huaweiOD.od2023.s100e119;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-17 20:37
 * @Description: https://dream.blog.csdn.net/article/details/129191407
 */
public class CalArea {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String[] line = str.split(" ");
      int allCount = Integer.parseInt(line[0]);
      int maxX1 = Integer.parseInt(line[1]);
      int sqValue = 0;
      int tempX = 0;
      int tempY = 0;
      for(int i = 0; i < allCount; i++){
        String[] strs = in.nextLine().split(" ");
        int x1 = Integer.parseInt(strs[0]);
        sqValue += Math.abs(tempY * (x1 - tempX));
        tempY += Integer.parseInt(strs[1]);
        tempX = x1;
      }
      sqValue += (maxX1 - tempX) * tempY;
      System.out.println(sqValue);
    }
  }


}
