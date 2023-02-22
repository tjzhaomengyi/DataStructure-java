package com.point2offerspecial.fifteen_graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-20 16:28
 * @Description:
 */
public class PTOS106_PainColor {
  //把图分成两段，那么两段的信息肯定不同
  public boolean isBipartite(int[][] graph) {
    //技巧：graph中的i表示第i个节点，对应的数组[a,b,c]表示i相邻结点的序号
    int size = graph.length;
    int[] colors = new int[size];
    Arrays.fill(colors, -1);
    for(int i = 0; i < size; i++) {//取出结点
      if (colors[i] == -1){
        if(!setColor(graph, colors, i, 0)){
          return false;
        }
      }
    }
    return true;
  }

  //技巧：每次从队列中选出一个节点v，该节点再添加到队列的时候已经被涂上颜色，其颜色保存在colors[v]中，
  // 如果相邻的结点还没有着色（color[neighbor] == -1),就按照二分图给neighbor涂上不同的颜色，
  // 即 1 - colors[v]。如果相邻结点已经被涂上颜色，判断color[neighbor] 是否等于 color[v]。
  // 如果二者相同，那么违反了题目规定，直接返回false
  private boolean setColor(int[][] graph, int[] colors, int i , int color){
    Queue<Integer> queue = new LinkedList<>();
    queue.add(i);
    colors[i] = color;
    while(!queue.isEmpty()){
      int v = queue.poll();
      for(int neighbor : graph[v]){
        if(colors[neighbor] >= 0){
          if(colors[neighbor] == colors[v]){
            return false;
          }
        } else {
          queue.add(neighbor);
          colors[neighbor] = 1 - colors[v];
        }
      }
    }
    return true;
  }
}
