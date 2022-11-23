package com.mikemyzhao.SortList_ALL.Queue.bigshua;

import java.util.PriorityQueue;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-05 12:40
 * @Description: 使用DFS + 优先队列PriorityQueue
 */
public class TrappingRainInMatrix_21 {
  //技巧：把矩阵中的每个位置看成一个节点
  public static class Node {
    public int value;
    public int row;
    public int col;

    public Node(int v, int r, int c){
      value = v;
      row = r;
      col = c;
    }
  }

  //技巧：从外围找到最小的节点，然后从这个节点开始往三个方向走，起始是一个dfs
  // 标记走过的节点，然后从queue中每次弹出最小的接出最大的雨水，最后返回整体的雨水数量就是最大
  public static int trapRainWater(int[][] heightMap){
    if(heightMap == null || heightMap.length == 0 || heightMap[0] == null || heightMap[0].length == 0){
      return 0;
    }
    int N = heightMap.length;
    int M = heightMap[0].length;
    boolean[][] isEnter = new boolean[N][M];
    PriorityQueue<Node> heap = new PriorityQueue<>((a,b) -> a.value - b.value);//默认根据value从小到大
    //技巧：注意这个遍历，每个方向不能到头，给垂直的方向留着。。。
    for(int col = 0; col < M - 1; col++){
      isEnter[0][col] = true;
      heap.add(new Node(heightMap[0][col], 0, col));
    }
    for(int row = 0; row < N - 1; row++){
      isEnter[row][M - 1] = true;
      heap.add(new Node(heightMap[row][M - 1], row, M - 1));
    }
    for(int col = M - 1; col > 0; col--){
      isEnter[N - 1][col] = true;
      heap.add(new Node(heightMap[N - 1][col], N - 1, col));
    }

    for(int row = N - 1; row > 0; row--){
      isEnter[row][0] = true;
      heap.add(new Node(heightMap[row][0], row , 0));
    }

    int water = 0;
    int max = 0;
    while(!heap.isEmpty()){
      Node cur = heap.poll();
      max = Math.max(max, cur.value);
      int r = cur.row;
      int c = cur.col;
      //上下左右四个方向,
      //技巧：如果这个方向水深加上，标记遍历，节点加入堆
      if(r > 0 && !isEnter[r - 1][c]){
        water += Math.max(0, max - heightMap[r - 1][c]);
        isEnter[r - 1][c] = true;
        heap.add(new Node(heightMap[r - 1][c], r - 1, c));
      }
      if(r < N - 1 && !isEnter[r + 1][c]){
        water += Math.max(0, max - heightMap[r + 1][c]);
        isEnter[r + 1][c] = true;
        heap.add(new Node(heightMap[r + 1][c], r + 1, c));
      }
      if(c > 0 && !isEnter[r][c - 1]){
        water += Math.max(0, max - heightMap[r][c - 1]);
        isEnter[r][c - 1] = true;
        heap.add(new Node(heightMap[r][c - 1], r, c - 1));
      }
      if(c < M - 1 && !isEnter[r][c + 1]){
        water += Math.max(0, max - heightMap[r][c + 1]);
        isEnter[r][c + 1] = true;
        heap.add(new Node(heightMap[r][c + 1], r, c + 1));
      }
    }
    return water;
  }
}
