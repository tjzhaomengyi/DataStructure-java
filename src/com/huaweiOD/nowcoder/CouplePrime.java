package com.huaweiOD.nowcoder;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-14 20:10
 * @Description:
 */
public class CouplePrime {
  static int max = 0;

  public static void main(String[] args) {
    //标准输入
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      //输入正偶数
      int n = in.nextInt();
      //用于记录输入的n个整数
      int[] arr = new int[n];
      //用于存储所有的奇数
      ArrayList<Integer> odds = new ArrayList<>();
      //用于存储所有的偶数
      ArrayList<Integer> evens = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        arr[i] = in.nextInt();
        if (arr[i] % 2 == 1) {
          odds.add(arr[i]);
        } else if (arr[i] % 2 == 0) {
          evens.add(arr[i]);
        }
      }

      //下标对应已经匹配的偶数的下标，值对应这个偶数的伴侣
      int[] matcheven = new int[evens.size()];
      int count = 0;
      for (int j = 0; j < odds.size(); j++) {
        //用于标记对应的偶数是否查找过
        boolean[] v = new boolean[evens.size()];
        if (find(odds.get(j), matcheven, evens, v)) {
          count++;
        }
      }
      System.out.println(count);
    }

  }

  public static boolean find(int x, int[] matcheven, ArrayList<Integer> evens,
                             boolean[] v){
    for (int i = 0; i < evens.size(); i++){
      //该位置没有访问过，且与x组成素数
      if(isPrime(x+evens.get(i)) && v[i] == false){
        v[i] = true;
        //如果i位置偶数没有伴侣，则与x组成伴侣，如果已经有伴侣，并且这个伴侣能重新找到新的伴侣
        // 则把原来的伴侣给别人，自己与x组成伴侣
        if(matcheven[i] == 0 || find(matcheven[i], matcheven, evens, v)){
          matcheven[i] = x;
          return true;
        }
      }
    }
    return false;
  }


  public static boolean isPrime(int num) {
    if (num <= 2) {
      return true;
    }
    for (int i = 2; i * i <= num; i++) {
      if (num % i == 0) {
        return false;
      }
    }
    return true;
  }
}