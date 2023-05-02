package com.huaweiOD.od2023.s60e79;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-20 12:04
 * @Description:
 */
public class LianxuShulie {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      int s = in.nextInt();
      int n = in.nextInt();
      getSubList(s, n);
    }
  }

  public static void getSubList(int s, int n){
    List<Integer> list = new ArrayList<>();
    if(2 * s % n != 0 || (2 * s / n - n) % 2 == 0){//公式判断
      System.out.print(-1);
      return ;
    }

    int num = (2 * s / n + 1 - n) / 2;
    IntStream.range(num , num + n).forEach(list::add);
    for(int i = 0; i < list.size(); i++){
      System.out.print(list.get(i));
      if(i != list.size() - 1){
        System.out.print(" ");
      }
    }

  }


}
