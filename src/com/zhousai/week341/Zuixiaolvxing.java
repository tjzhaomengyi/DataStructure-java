package com.zhousai.week341;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-22 11:45
 * @Description:
 */
public class Zuixiaolvxing {
  public static void main(String[] args) {
    int n = 4;
    int[][] edges = new int[][]{{0, 1}, {1, 2}, {1, 3}};
    int[] prices = new int[]{2, 2, 10, 6};
    int[][] trips = new int[][]{{0, 3}};//{2,1},{2,3}
    int ans = minimumTotalPrice(4, edges, prices, trips);
    System.out.println(ans);
  }

  public static int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
    int trip_n = trips.length;
    int ans = 0;
    for (int i = 0; i < trip_n; i++) {
      ans += bfs(trips[i][0], trips[i][1], edges, price);
    }
    return ans;
  }

  public static int bfs(int s, int e, int[][] edges, int[] price) {
    LinkedList<Integer> lst = new LinkedList<>();
    HashMap<ArrayList<Integer>, Integer> map = new HashMap<>();
    lst.add(s);
    int cost = 0;
    int final_cost = 0;
    if (xianglin(s, e, edges)) {
      cost = price[s] + price[e];
      return cost;
    } else {
      cost = (price[s] + price[e]) / 2;
      final_cost = cost;
    }


    while (!lst.isEmpty()) {
      int start = lst.poll();
      for (int i = 0; i < edges.length; i++) {
        if (start == edges[i][0] && edges[i][1] != e) {

          lst.add(edges[i][1]);
          cost += price[edges[i][1]];
        } else if (start == edges[i][0] && edges[i][1] == e) {
          cost += price[edges[i][1]];
          final_cost = cost;
        }
      }
    }
    return final_cost;
  }

  public void recodPathCost(HashMap<List<Integer>, Integer> map, int s, int e, int[] price) {
    if (map.isEmpty()) {
      map.put(Arrays.asList(s,e),price[s] + price[e]);
    }
    for(List<Integer> key : map.keySet()){
      if(key.get(key.size() - 1) == s){
        int p = map.get(key) + price[e];
        //todo:
//        map.put()
      }
    }
  }

  public static boolean xianglin(int s, int e, int[][] edges) {
    for (int i = 0; i < edges.length; i++) {
      if (edges[i][0] == s && edges[i][1] == e) {
        return true;
      }
    }
    return false;
  }

}
