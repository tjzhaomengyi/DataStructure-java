package com.huaweiOD.od2023.s40e59;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-21 15:50
 * @Description:
 */
public class Sushuzhiji {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
       int n = Integer.parseInt(in.nextLine());
       int a1 = -1;
       int a2 = -1;
       for(int i = 2; i * i < n; i++){
         if(n % i == 0 && isPrime(i) && isPrime(n / i)){
           a1 = i;
           a2 = n / i;
           break;
         }
       }
       System.out.println(a1 + " " + a2);
    }
  }

  public static boolean isPrime(int n){
    for(int i = 2; i * i < n; i++){
      if(n % i == 0){
        return false;
      }
    }
    return true;
  }

}

