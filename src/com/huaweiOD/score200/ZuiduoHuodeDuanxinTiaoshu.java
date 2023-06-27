package com.huaweiOD.score200;

import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-30 20:24
 * @Description:这是一道数学题,先买那些一元能买最多的，然后不够了用少的来补
 */
public class ZuiduoHuodeDuanxinTiaoshu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int limit = Integer.valueOf(in.nextLine());
      String[] line = in.nextLine().split(" ");
      int[] money_cnt_arr = new int[line.length];
      for(int i = 0; i < money_cnt_arr.length; i++){
        money_cnt_arr[i] = Integer.valueOf(line[i]);
      }

      System.out.println(getMoney(limit, money_cnt_arr));
    }
  }

  public static int getMoney(int limit, int[] money_cnt){ //包含index，最多可以弄多少钱
    List<Duanxin> duanxin_arr = new ArrayList<>();
    for(int i = 0; i < money_cnt.length; i++){
      int price = i + 1;
      int count = money_cnt[i];
      Double rating = count * 1.0 / (i + 1); //技巧:每种短信付费方式，一条短信花多少钱
      duanxin_arr.add(new Duanxin(price, count, rating));
    }

    Collections.sort(duanxin_arr, (o1, o2) -> o2.rating.compareTo(o1.rating));

    int max = 0;
    for(int i = 0; limit > 0; i++){
      Duanxin duanxin = duanxin_arr.get(i);
      if(duanxin.price <= limit){
        max += duanxin.count;
        limit -= duanxin.price;
      }
      if(i == duanxin_arr.size() - 1){
        i = 0;//如果limit还有剩余，把剩余的用干净
      }
    }
    return max;
  }

  public static class Duanxin{
    int price;
    int count;
    Double rating;
    public Duanxin(int price, int count, Double rating){
      this.price = price;
      this.count = count;
      this.rating = rating;
    }
  }

}
