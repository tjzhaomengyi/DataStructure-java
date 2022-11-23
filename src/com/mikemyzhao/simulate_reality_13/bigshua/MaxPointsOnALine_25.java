package com.mikemyzhao.simulate_reality_13.bigshua;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-07 16:15
 * @Description:给定一个二维数组N*2，分别代表一个点的x和y位置，求一条线最多可以压中多少个点
 */
public class MaxPointsOnALine_25 {
  //技巧：用字符串表示一条线的斜率;或者用map表示斜率，分子做key，分母做value的一部分
  public static int maxPoints(int[][] points){
    if(points == null){
      return 0;
    }
    if(points.length <= 2){
      return points.length;
    }
    //key为斜率的分子 key = 3
    //value为斜率的分母和该斜率压点个数
    // value = {7, 10}斜率
    //       = {5, 15}
    Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
    int result = 0;
    for(int i = 0; i < points.length; i++){
      map.clear();//技巧：每个点算完清理一次
      //技巧：两种共线、共点和共斜线
      int samePosition = 1;//统计同一位置的个数
      int sameX = 0;//同一行
      int sameY = 0;//同一列
      int line = 0; //同一斜线
      for (int j = i + 1; j < points.length; j++){
        int x = points[j][0] - points[i][0];
        int y = points[j][1] - points[i][1];
        if(x == 0 && y == 0){
          samePosition++;
        } else if(x == 0){
          sameX++;
        } else if(y == 0){
          sameY++;
        } else {
          //求一下最大公约数
          int gcd = gcd(x, y);
          x /= gcd;
          y /= gcd;
          if(!map.containsKey(x)){
            map.put(x,new HashMap<Integer, Integer>());
          }
          if(!map.get(x).containsKey(y)){
            map.get(x).put(y, 0);
          }
          map.get(x).put(y,map.get(x).get(y) + 1);
          line = Math.max(line, map.get(x).get(y));//每个点
        }
      }
      result = Math.max(result,Math.max(Math.max(sameX, sameY), line) + samePosition);//技巧：加上同一个点
    }
    return result;
  }

  public static int gcd(int a, int b){
    return b == 0 ? a: gcd(b,a % b);
  }
}
