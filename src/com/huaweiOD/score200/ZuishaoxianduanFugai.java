package com.huaweiOD.score200;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-18 11:53
 * @Description: 这道题可以和base里面的CoverMax一起看一下，左神高频面试题也讲了
 */
public class ZuishaoxianduanFugai {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int n = Integer.valueOf(in.nextLine());
      int[][] points = new int[n][2];
      for(int i = 0; i < n; i++){
        String str = in.nextLine();
        points[i] = new int[]{Integer.valueOf(str.split(",")[0]),  Integer.valueOf(str.split(",")[1])};
      }
      int res = solution(n, points);
      System.out.println(res);
    }
  }
    private static int solution(int n, int[][] points){
      HashMap<Integer, Integer> line = new HashMap<>();
      addPointsToMap(points, line);
      int del = 0;
      for(int i = 0; i < n; i++){
        int[] point = points[i];
        if(isDeletable(point, line)){
          removePointFromMap(point, line);
          del++;
        }
      }
      return n - del;
    }

    private static void addPointsToMap(int[][] points, HashMap<Integer, Integer> line){
      for(int i = 0; i < points.length; i++){
        int[] point = points[i];
        for(int j = point[0]; j <= point[1]; j++){
          line.put(j, line.getOrDefault(j, 0) + 1);
        }
      }
    }

    private static boolean isDeletable(int[] point, HashMap<Integer, Integer> line){
      for(int j = point[0]; j <= point[1]; j++){
        if(line.get(j) <= 1){
          return false;
        }
      }
      return true;
    }
    private static void removePointFromMap(int[] point, Map<Integer, Integer> line){
      for(int j = point[0]; j <= point[1]; j++){
        line.put(j, line.get(j) - 1);
      }
    }
}
