package com.huaweiOD.od2023.s120e138;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-17 15:40
 * @Description:
 */
public class HighLowHigh {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String[] boys = str.split(" ");
      int[] arr = new int[boys.length];
      for (int i = 0; i < boys.length; i++) {
        arr[i] = Integer.valueOf(boys[i]);
      }
      int index = 0;
      while (index + 1 < arr.length) {
        if ((index + 1) % 2 == 0) { //index应该是矮， index+1应该是高
          if (arr[index] > arr[index + 1]) {
            int tmp = arr[index + 1];
            arr[index + 1] = arr[index];
            arr[index] = tmp;
          }
        } else { //index应该是高 index + 1应该是矮
          if (arr[index] < arr[index + 1]) {
            int tmp = arr[index + 1];
            arr[index + 1] = arr[index];
            arr[index] = tmp;
          }
        }
        index++;
      }

        for (int i = 0; i < arr.length; i++) {
          System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


  }
}
