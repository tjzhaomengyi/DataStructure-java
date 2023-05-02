package com.huaweiOD.od2023.s80e99;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-19 9:39
 * @Description:
 */
public class ChuzucheJifei {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str_price = in.nextLine();
      int price = Integer.valueOf(str_price);
      int ans = price;
      int tmp = 0;
      int i = 0;
      int j = 1;
      while(price > 0){
        if(price % 10 > 4){
          //判断个位上是否跳过了4，如果个位是5-9，就先tmp = 1
          tmp += i * (price % 10 - 1) + j;
        } else {
          tmp += i * (price % 10);
        }
        //这里表示跳了多少次
        i = 9 * i + j;
        //这里j代表位数
        j *= 10;
        //将price右移一位
        price = price / 10;
      }
      System.out.println(ans - tmp);
    }
  }


}
