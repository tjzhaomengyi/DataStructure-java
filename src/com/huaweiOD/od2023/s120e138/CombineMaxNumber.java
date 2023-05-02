package com.huaweiOD.od2023.s120e138;


import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-16 15:47
 * @Description:
 */
public class CombineMaxNumber {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      String str = in.next();
      System.out.print(maxStr(str));
    }
  }

  public static String maxStr(String str){
    String[] arr = str.split(",");
    PriorityQueue<String> q = new PriorityQueue<String>((o1, o2) -> {
      char[] arr1 = o1.toCharArray();
      char[] arr2 = o2.toCharArray();
      int minlen = Math.min(arr1.length , arr2.length);
      for(int i = 0; i < minlen; i++){
        char c1 = arr1[i];
        char c2 = arr2[i];
        if(c2 != c1){
          return c2 - c1;
        }
      }
      return arr2.length - arr1.length;
    });

    for(int i = 0; i < arr.length; i++){
      q.offer(arr[i]);
    }
    StringBuilder sb = new StringBuilder();
    while(!q.isEmpty()){
      sb.append(q.poll());
    }
    return sb.toString();
  }

}
