package com.huaweiOD.od2023.s80e99;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-19 19:09
 * @Description:
 */
public class WenjianSaomiao {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int cache_cost = Integer.valueOf(in.nextLine());
      String idStr = in.nextLine();
      String sizeStr = in.nextLine();
      int res = getMin(cache_cost, idStr, sizeStr);
      System.out.println(res);
    }
  }


  public static int getMin(int cache_cost, String idStr, String sizeStr) {
    Map<Integer, Integer> idCostMap = new HashMap<>();
    Map<Integer, Integer> idSizeMap = new HashMap<>();
    String[] ids = idStr.split(" ");
    String[] sizes = sizeStr.split(" ");

    for (int i = 0; i < ids.length; i++) {
      int id = Integer.parseInt(ids[i]);
      int size = Integer.parseInt(sizes[i]);
      idCostMap.put(id, idCostMap.getOrDefault(id, 0) + 1);
      idSizeMap.put(id, size);
    }
    int sum = 0;
    for (int k : idCostMap.keySet()) {
      int total = idCostMap.get(k) * idSizeMap.get(k);
      idCostMap.put(k, Math.min(total, cache_cost + idSizeMap.get(k)));
      sum += idCostMap.get(k);
    }
    return sum;
  }

}
