package com.huaweiOD.od2023.s120e138;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-16 14:23
 * @Description:手里n块糖，每次均分，如果不足偶数可以拿一块或者放回一块
 */
public class Candy {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int n = in.nextInt();
      System.out.println(candy(n));
    }
  }

  public static int candy(int n){
    int index = 0;
    for(int i = n ; i != 1; i /= 2, index++){
      if(n == 3){
        return index += 2;
      }
      if(i % 2 != 0){
        if((i + 1)  % 2 == 0){
          i++;
        } else {
          i--;
        }
        index++;
      }
    }
    return index;
  }
}
