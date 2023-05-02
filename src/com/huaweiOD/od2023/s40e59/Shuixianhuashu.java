package com.huaweiOD.od2023.s40e59;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-21 14:07
 * @Description:
 */
public class Shuixianhuashu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      int n = in.nextInt();
      int index = in.nextInt();
      ArrayList<Integer> ans = getShuixianhua(n);
      if(ans.size() == 0){
        System.out.println(-1);
      } else if(index > ans.size() - 1){
        System.out.println(index * ans.get(ans.size() - 1));
      } else{
        System.out.println(ans.get(index));
      }
    }
  }

  public static ArrayList<Integer> getShuixianhua(int n){
    ArrayList<Integer> ans = new ArrayList<>();
    int max = (int)Math.pow(10, n) - 1;
    int min = (int)Math.pow(10, n - 1);
    for(int i = min; i <= max; i++){
      int tmp = i;
      double total = 0;
      while(tmp / 10 != 0){
        int weishu = tmp % 10;
        total += Math.pow(weishu, n);
        tmp = tmp / 10;
      }
      total += Math.pow(tmp, n);
      if((int)total == i){
        ans.add(i);
      }
    }
    return  ans;
  }
}
