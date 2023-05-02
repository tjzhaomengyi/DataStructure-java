package com.huaweiOD.od2023.s80e99;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-18 21:11
 * @Description:
 */
public class BaiXiangzi {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String[] str = in.nextLine().split(" ");
      String boxes = str[0];
      int len = boxes.length();
      int m = Integer.valueOf(str[1]);
      int n = (len + m - 1) / m;
      char[][] ans = new char[m][n];

      int index = 0;
      for(int j = 0 ; j < n; j++){
        for(int i = 0; i < m; i++){
          if(index == len){
            break;
          }
          if(j % 2 == 0){
            ans[i][j] = boxes.charAt(index++);
          }else if(j % 2 == 1){
            ans[m - 1 - i][j] = boxes.charAt(index++);
          }
        }
      }
      for(int i = 0; i < m; i++){
        System.out.println(String.valueOf(ans[i]));
      }
    }
  }


}
