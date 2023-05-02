package com.huaweiOD.od2023.s1e19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-26 17:01
 * @Description:
 */
public class
PingjunLanqiusaiMVP {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      int len = in.nextInt();
      int[] arr = new int[len];
      for(int i = 0; i < len; i++){
        arr[i] = in.nextInt();
      }
      Arrays.sort(arr);
      int result = 0;
      List<Integer> list = new ArrayList<>();
      for(int p : arr){
        result += p;
        list.add(p);
      }
      for(int j = arr.length; j > 0; j--){
        if(result % j == 0 && check(list, result / j, result / j)){
          System.out.println(result / j);
          return;
        }
      }
    }
  }

  static boolean check(List<Integer> list, int number, int beforeNum){
    if(list == null){
      return true;
    }
    if(number == beforeNum && list.size() == 0){
      return true;
    }
    boolean statu = false;
    for(int i = 0; i < list.size(); i++){
      List<Integer> mySubList = new ArrayList<>();
      for(int j = 0; j < list.size(); j++){
        if(i != j){
          mySubList.add(list.get(j));
        }
      }
      if(list.get(i) == number){
        statu = statu || check(mySubList, beforeNum, beforeNum);
      } else if(list.get(i) < number){
        statu = statu || check(mySubList, number - list.get(i), beforeNum);
      }
    }
    return statu;
  }
}
