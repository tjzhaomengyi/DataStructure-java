package com.huaweiOD.od2023.s20e40;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-25 9:07
 * @Description:
 */
public class ZhaopengyouShengao {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int n = Integer.parseInt(in.nextLine());
      String[] heights = in.nextLine().split(" ");
      solve(n, heights);
    }
  }

  public static void solve(int n, String[] heights){
    int[] results = new int[n];
    for(int i = 0; i < heights.length; i++){
      int index = 0;
      for(int j = i + 1; j < heights.length; j++){
        if(Integer.parseInt(heights[j]) > Integer.parseInt(heights[i])){
          index = j;
          break;
        }
      }
      results[i] = index;
    }
    for(int j = 0; j < results.length; j++){
      if(j != results.length - 1){
        System.out.print(results[j] + " ");
      } else {
        System.out.println(results[j]);
      }
    }

  }
}

class XiaoPengYou{
  int index;
  int height;
  int higherXiaopengyou = 0;


  public XiaoPengYou(int index, int height){
    this.index = index;
    this.height = height;
  }
}
