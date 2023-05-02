package com.huaweiOD.od2023.s20e40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-24 20:24
 * @Description:
 */
public class ZifuchuanShaixuanPaixu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      int top = Integer.valueOf(in.nextLine());
      char[] arr = str.toCharArray();
      Arrays.sort(arr);
      char c = arr[top - 1];
      System.out.println(str.indexOf(c));
    }
  }
}
