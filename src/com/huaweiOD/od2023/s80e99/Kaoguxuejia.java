package com.huaweiOD.od2023.s80e99;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-19 10:48
 * @Description:
 */
public class Kaoguxuejia {
  static ArrayList<ArrayList<String>> ans = new ArrayList<>();
  static int len;
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int n = Integer.valueOf(in.nextLine());
      len = n;
      String str = in.nextLine();
      String[] splits = str.split(" ");
      int[] visited = new int[n];
      ArrayList<String> path = new ArrayList<>();
      process(splits, visited, path, ans);
      HashSet<String> tmp = new HashSet<>();
      for(int i = 0; i < ans.size(); i++){
        List<String> p = ans.get(i);
        StringBuilder sb = new StringBuilder();
        for(int j = 0; j < p.size(); j++){
          sb.append(p.get(j));
        }
        tmp.add(sb.toString());
      }
      Iterator itr = tmp.iterator();
      while(itr.hasNext()){
        System.out.println(itr.next());
      }
    }
  }

  public static void process(String[] stones, int[] visited, ArrayList<String> path, ArrayList<ArrayList<String>> ans){
    if(path.size() == len && isAllVisted(visited) && !ans.contains(path)){
      ans.add((ArrayList<String>) path.clone()); //技巧:重要的事情说三遍:使用递归进行复制的时候一定要进行拷贝操作，使用递归对容器进行赋值的时候一定要拷贝，使用递归对容器进行复制的时候一定要用拷贝
      return;
    }
    for(int i = 0; i < len; i++){
      if(visited[i] == 0) {
        visited[i] = 1;
        path.add(stones[i]);
        process(stones, visited, path, ans);
        visited[i] = 0;
        path.remove(path.size() - 1);
      }
    }
    return;
  }

  public static boolean isAllVisted(int[] visited){
    for(int i = 0; i < visited.length; i++){
      if(visited[i] == 0) return false;
    }
    return true;
  }
}
