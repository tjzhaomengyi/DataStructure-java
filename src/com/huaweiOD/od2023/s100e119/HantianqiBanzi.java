package com.huaweiOD.od2023.s100e119;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-17 19:48
 * @Description:
 */
public class HantianqiBanzi {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      solveMethod(str);
    }
  }

  public static void solveMethod(String str){
    String[] strs = str.split(",");
    long[] ls = Arrays.stream(strs).mapToLong(Long::parseLong).toArray();

    long result = 0;
    for(int i = 0; i < strs.length; i++){
      for(int j = i + 1; j < strs.length; j++){
        long a = (j - i) * Math.min(ls[i], ls[j]);
        result = Math.max(result, a);
      }
    }

    System.out.println(result);
  }
}
