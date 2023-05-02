package com.huaweiOD.od2023.s60e79;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-21 10:35
 * @Description:
 */
public class JisuanWangluoXinhao {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      int m = in.nextInt();
      int n = in.nextInt();
      int[][] nums = new int[m][n];
      int[] cur = new int[2];
      int cur_pow = 0;
      for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
          nums[i][j] = in.nextInt();
          if(nums[i][j] > 0){
            cur[0] = i;
            cur[1] = j;
            cur_pow = nums[i][j];
          }
        }
      }
      int query_x = in.nextInt();
      int query_y = in.nextInt();
      //使用BFS，先构建一个队列
      bfs(nums,  cur, cur_pow);
      System.out.println(nums[query_x][query_y]);
      for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
          System.out.print(nums[i][j] + " ");
        }
        System.out.println();
      }

    }
  }


  //个人总结:BFS在实现上并不是使用递归，而是在bfs代码内部使用一个栈记录当前节点，然后把相邻遍历过的塞进去，本质是一个循环下去，
  // 所以并不用考虑递归时候的容器清空啊，主要是情况容器的问题，还有参数怎么加在函数中这些问题，就一个 while(队列不等于空) 挨个遍历即可
  public static void bfs(int[][] nums, int[] cur, int cur_pow){
    //如果是-1,不传播，如果是0传播
    LinkedList<int[]> queue = new LinkedList<int[]>();
    int m = nums.length;
    int n = nums[0].length;
    int cur_x = cur[0];
    int cur_y = cur[1];
    queue.add(new int[]{cur_x, cur_y});

    //上下左右进行递归
    while(!queue.isEmpty()){
      int[] pos = queue.poll();
      int x = pos[0];
      int y = pos[1];

      //每个格子的值根据转移过来的起始值决定
      if(nums[x][y] > 0) {
        if (x - 1 >= 0 && nums[x - 1][y] == 0) {
          nums[x - 1][y] = nums[x][y] - 1;
          queue.add(new int[]{x - 1, y});
        }
        if (x + 1 < m && nums[x + 1][y] == 0) {
          nums[x + 1][y] = nums[x][y] - 1;
          queue.add(new int[]{x + 1, y});
        }
        if (y - 1 >= 0 && nums[x][y - 1] == 0) {
          nums[x][y - 1] = nums[x][y] - 1;
          queue.add(new int[]{x, y - 1});
        }
        if (y + 1 < n && nums[x][y + 1] == 0) {
          nums[x][y + 1] = nums[x][y] - 1;
          queue.add(new int[]{x, y + 1});
        }
      }
    }
  }

}
