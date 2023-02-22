package com.point2offerspecial.fifteen_graph.all_path;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-20 19:43
 * @Description:找到最后等于graph.len的所有路径
 */
public class PTOS110_AllPath {
  public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    List<List<Integer>> ans = new LinkedList<>();
    List<Integer> path = new LinkedList<Integer>();
    dfs(0, graph, (LinkedList<Integer>) path, ans);
    return ans;
  }

  private void dfs(int start, int[][] graph, LinkedList<Integer> path, List<List<Integer>> ans){
    path.add(start);
    if(start == graph.length - 1){
      ans.add(new LinkedList<>(path));
    } else {
      for(int next : graph[start]){
        dfs(next, graph, path, ans);
      }
    }
    //帮人家深度完了，自己还得出去
    path.removeLast();
  }
}
