package com.huaweiOD.nowcoder;

import java.util.*;
import java.io.*;
public class Can24{
  static int[] nums = new int[4];
  static boolean[] visit = new boolean[4];
  static int flag = 0;

  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    while(in.hasNext()){
      String[] a = in.nextLine().split(" ");
      for(int i = 0; i < 4; i ++)
        nums[i] = Integer.parseInt(a[i]);
      dfs(0, 0);
      System.out.println( flag == 1 );
    }

  }

  // tmp是前面n个数字运算结果，u表示已经使用了多少个数字
  static boolean dfs(int u,float tmp){
    // 递归终止条件：已经使用了数组四个元素，同时结果为24，满足题意
    if(tmp == 24 && u == 4){
      flag = 1;
      return true;
    }

    for(int i = 0; i < 4; i ++){
      if(visit[i] == false){
        visit[i] = true;
        // 加减乘除当前数字num[i]
        if( dfs(u + 1, tmp + nums[i]) ||
            dfs(u + 1, tmp - nums[i])  ||
            dfs(u + 1,tmp * nums[i]) ||
            dfs(u + 1, tmp / nums[i])){
          return true;
        }
        // 相当于回溯
        visit[i] = false;
      }
    }
    return false;
  }
}


