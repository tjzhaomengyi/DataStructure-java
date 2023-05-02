package com.huaweiOD.od2023.s20e40;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-24 17:05
 * @Description:
 */
public class XunzhaoXiangtongzichuan {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String t = in.nextLine();
      String p = in.nextLine();
      if(!t.contains(p)){
        System.out.println("No");
      } else {
        int index = t.indexOf(p);
        System.out.println(index + 1);
      }
    }
  }


}
