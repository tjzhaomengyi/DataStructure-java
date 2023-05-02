package com.huaweiOD.od2023.s60e79;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-20 12:26
 * @Description:
 */
public class ShijianTuisong {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      int m = in.nextInt();
      int n = in.nextInt();
      int R = in.nextInt();
      int[] A = new int[m];
      int[] B = new int[n];

      for(int i = 0; i < m; i++){
        A[i] = in.nextInt();
      }
      for(int j = 0; j < n; j++){
        B[j] = in.nextInt();
      }

      int index = 0;
      List<int[]> resultList = new ArrayList<>();
      for(int j : A){
        int[] values = new int[2];
        while(index < B.length){
          if(B[index] >= j && B[index] - j <= R){
            values[0] = j;
            values[1] = B[index];
            resultList.add(values);
            break;
          }
          index++;
        }
      }
      resultList.forEach(arr -> System.out.println(arr[0] + " " + arr[1]));

    }
  }


}
