package com.huaweiOD.score200;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-06-01 9:52
 * @Description: 这题没看懂
 */
public class ChazhaoshuZhongdeYuansu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int n = Integer.valueOf(in.nextLine());

      List<Integer[]> nodesInfo = new ArrayList<>();
      for(int i = 0; i < n; i++){
        String[] line = in.nextLine().split(" ");
        Integer[] tmp = new Integer[line.length];
        for(int j = 0; j < tmp.length; j++){
          tmp[j] = Integer.valueOf(line[j]);
        }
        nodesInfo.add(tmp);
      }
      String pos = in.nextLine();
      int x = Integer.valueOf(pos.split(" ")[0]);
      int y = Integer.valueOf(pos.split(" ")[1]);
      String ans = getResult(nodesInfo, x, y);
      System.out.println(ans);
    }
  }

  public static String getResult(List<Integer[]> nodes, int x, int y){
    if(x < 0 || y < 0) return "{}";
    Map<String, List<Integer>> memo = new HashMap<>();
    List<Integer> list = calc(nodes, 0, x, memo);
    if(y >= list.size()){
      return "{}";
    }
    return "{" + list.get(y) + "}";
  }


  private static List<Integer> calc(List<Integer[]> nodes, int index, int n, Map<String, List<Integer>> memo){
    String key = index + "," + n;
    //技巧:使用一个computeIfAbsent
    Integer[] node = nodes.get(index);
    List<Integer> list = new ArrayList<>();
    if(n == 0){
      list.add(node[0]);
    } else if(node.length > 1){
      for(int i = 1; i < node.length; i++){
        List<Integer>  tmp = calc(nodes, node[i], n - 1, memo);
        list.addAll(tmp);
      }
      memo.put(key, list);
    }
    return list;
  }

}
