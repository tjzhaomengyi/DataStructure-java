package com.huaweiOD.od2023.s120e138;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-17 14:20
 * @Description:
 */
public class Transport {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      int weight = Integer.valueOf(in.nextLine());
      int[] arr = new int[str.split(",").length];
      for(int i = 0; i < str.split(",").length; i++){
        arr[i] = Integer.parseInt(str.split(",")[i]);
      }
      System.out.print(transport(arr, weight));
    }
  }

  public static int transport(int[] arr, int weight){
    Arrays.sort(arr);
    int sum = Arrays.stream(arr).sum();
    int allSum = 0;
    for(int i = arr.length - 1; i >= 0; i--){
      if(sum - allSum < weight){ //前缀和
        return i + 1;
      } else {
        allSum += arr[i];
      }
    }
    return 0;
  }

}
