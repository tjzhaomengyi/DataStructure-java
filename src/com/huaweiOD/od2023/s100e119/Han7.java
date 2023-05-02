package com.huaweiOD.od2023.s100e119;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-17 20:53
 * @Description:
 */
public class Han7 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String[] lines = str.split(" ");
      int[] arr = new int[lines.length + 1];//技巧:从1开始，假设多一个0，忽略不计，结果从1输出
      int len = lines.length;
      int sum  =0 ;
      for(int i = 0; i < lines.length; i++){
        sum += Integer.parseInt(lines[i]);//这里限制喊的次数
      }
      int tmp = 0;
      for(int a = 1; a < 200; a++){
        if(check7(a)){
          arr[a % len]++;
          tmp++;
        }
        if(sum <= tmp){
          break;
        }
      }
      for(int i = 1; i < arr.length; i++){
        System.out.print(arr[i] + " ");
      }
      System.out.println();
    }
  }

  public static boolean check7(int a){
    return a % 7 == 0 || a % 10 == 7;
  }
}
