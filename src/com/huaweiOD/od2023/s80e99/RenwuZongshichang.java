package com.huaweiOD.od2023.s80e99;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-18 20:39
 * @Description:
 */
public class RenwuZongshichang {
  static Set<Integer> ans = new HashSet<>();
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String[] arr = str.split(",");
      int a = Integer.valueOf(arr[0]);
      int b = Integer.valueOf(arr[1]);
      int total = Integer.valueOf(arr[2]);
      getAllTimes(a, b , total, 0, 0);
      ArrayList<Integer> tmp = new ArrayList<>(ans);
     Collections.sort(new ArrayList<>(ans));
     System.out.print("[");
     for(int i = 0; i < tmp.size(); i++){
        if(i == tmp.size() - 1){
          System.out.print(tmp.get(i));
        }else{
          System.out.print(tmp.get(i) + ", ");
        }
      }
     System.out.println("]");
    }
  }

  public static int getAllTimes(int a, int b , int total, int cnt, int sum){
    if(cnt == total){
      ans.add(sum);
      return 1;
    }
    int p1 =
        getAllTimes(a, b, total , cnt + 1, sum + a);
     int p2  =
         getAllTimes(a, b, total, cnt + 1, sum + b);
    return p1 + p2;
//    returnd;
  }


}
