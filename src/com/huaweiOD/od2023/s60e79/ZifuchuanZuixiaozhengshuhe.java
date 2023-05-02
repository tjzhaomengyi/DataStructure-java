package com.huaweiOD.od2023.s60e79;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-20 12:55
 * @Description:
 */
public class ZifuchuanZuixiaozhengshuhe {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      String str = in.next();
      System.out.println(solve(str));
    }
  }

  //如果不是负数直接放入链表中，如果是负数先放在temp中等遇到其他结束符在放入链表
  public static int solve(String values) {
    boolean fuNumber = false;
    String tempNumber = "-";
    List<Integer> myList = new ArrayList<>();
    for (int j = 0; j < values.length(); j++) {
      char tmp = values.charAt(j);
      if (tmp == '-') {
        fuNumber = true;
        continue;
      }
      if (Character.isDigit(tmp)) {
        if (fuNumber) {
          tempNumber += tmp;
        } else {
          myList.add(Integer.parseInt(String.valueOf(tmp)));
        }
      } else {
        if(tempNumber != "-"){
          myList.add(Integer.parseInt(tempNumber));
          tempNumber = "-";
        }
        fuNumber = false;
      }
    }
    int sum = 0;
    for(int i = 0; i < myList.size(); i++){
      sum += myList.get(i);
    }
    return sum;
  }

}
