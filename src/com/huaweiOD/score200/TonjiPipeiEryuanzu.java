package com.huaweiOD.score200;

import com.MCAAlgorithm.bigshua.class05.Hash;
import org.omg.CORBA.INTERNAL;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-22 17:50
 * @Description:
 */
public class TonjiPipeiEryuanzu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int m = Integer.valueOf(in.nextLine());
      int n = Integer.valueOf(in.nextLine());
      int[] arr1 = new int[m];
      int[] arr2 = new int[n];
      String[] str1 = in.nextLine().split(" ");
      String[] str2 = in.nextLine().split(" ");
      HashMap<Integer, Integer> map1 = new HashMap<>();
      HashMap<Integer, Integer> map2 = new HashMap<>();
      for(int i = 0; i < arr1.length; i++){
        arr1[i] = Integer.valueOf(str1[i]);
        map1.put(arr1[i], map1.getOrDefault(arr1[i], 0) + 1);
      }
      for(int i = 0; i < arr2.length; i++){
        arr2[i] = Integer.valueOf(str2[i]);
        map2.put(arr2[i], map2.getOrDefault(arr2[i], 0) + 1);
      }
      HashMap<Integer, Integer> s_map = n <= m ? map2 : map1;
      HashMap<Integer, Integer> l_map = s_map == map1 ? map2 : map1;
      int ans = getCount(s_map, l_map);
      System.out.println(ans);

    }
  }

  public static int getCount(HashMap<Integer, Integer> s_map, HashMap<Integer, Integer> l_map){
    int ans = 0;
    Set<Integer> l_set = l_map.keySet();
    for(Map.Entry<Integer, Integer> entry : s_map.entrySet()){
      int key = entry.getKey();
      int val = entry.getValue();
      if(l_set.contains(key)){
        ans += val * l_map.get(key);
      }
    }
    return ans;
  }
}
