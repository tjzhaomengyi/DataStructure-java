package com.huaweiOD.od2023.s100e119;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-18 11:12
 * @Description:
 */
public class GouguYuanzu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int val1 = in.nextInt();
      int val2 = in.nextInt();
      List<List<Integer>> myList = new ArrayList<>();
      for(int i = val1; i <= val2; i++){
        for(int j = i + 1; j <= val2; j++){
          if(check(i, j, val2) ){
            List<Integer> tmp = new ArrayList<>();
            tmp.add(i);
            tmp.add(j);
            tmp.add((int)Math.sqrt(i * i + j * j));
            Collections.sort(tmp);
            if(!checkGougYue(tmp.get(0), tmp.get(1), tmp.get(2))){
              myList.add(tmp);
            }
          }
        }
      }

      for(int i = 0; i < myList.size(); i++){
        List<Integer> tmp = myList.get(i);
        System.out.println(tmp.get(0) + " " + tmp.get(1) + " " + tmp.get(2));
      }

    }

  }

  //判断是否是勾股数
  public static boolean check(int i, int j, int max){
    int mul = i * i + j * j;
    double res = Math.sqrt(mul);
    int sub = (int) res;
    //技巧:
    return res * res == sub * sub && sub <= max && sub > j;
  }

  public static boolean checkGougYue(int i , int j , int k){
    for(int y = 2; y < k; y++){
      if(i % y == 0 && j % y == 0 && k % y == 0){
        return true;
      }
    }
    return false;
  }

}
