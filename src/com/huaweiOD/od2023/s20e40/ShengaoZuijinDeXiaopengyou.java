package com.huaweiOD.od2023.s20e40;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-24 13:41
 * @Description:
 */
public class ShengaoZuijinDeXiaopengyou {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int xioaming = in.nextInt();
      int n = in.nextInt();
      TreeMap<Integer, PriorityQueue<Integer>> xiaopengyou = new TreeMap<>();
      for(int i = 0; i < n; i++){
        int xpy = in.nextInt();
        int delta = Math.abs(xioaming - xpy);
        if(xiaopengyou.get(delta) == null){
          PriorityQueue<Integer> q = new PriorityQueue();
          q.offer(xpy);
          xiaopengyou.put(delta, q);
        } else {
          PriorityQueue q = xiaopengyou.get(delta);
          q.offer(xpy);
          xiaopengyou.put(delta, q);
        }
      }
      for(int k : xiaopengyou.keySet()){
        PriorityQueue<Integer> q = xiaopengyou.get(k);
        while(!q.isEmpty()){
          System.out.print(q.poll() + " ");
        }
      }
      System.out.println();
    }
  }
}
