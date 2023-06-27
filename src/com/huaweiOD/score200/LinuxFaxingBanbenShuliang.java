package com.huaweiOD.score200;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-06-01 13:39
 * @Description:
 */
public class LinuxFaxingBanbenShuliang {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int n = in.nextInt();
      int[][] nums = new int[n][n];
      for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
          nums[i][j] = in.nextInt();
        }
      }

      int[][] visited = new int[n][n];
      for(int i = 0; i < n; i++){
        visited[i][i] = 1;
      }
      List<Integer> ans = new ArrayList<>();
      for(int i = 0; i < n; i++){
        int tmp = dfs(i, visited, nums, ans);
        ans.add(tmp);
      }
      System.out.println(Collections.max(ans));
    }
  }

  //深度优先遍历
  public static int dfs(int index, int[][] visited,int[][] nums, List<Integer> ans){
    List<Integer> items = new ArrayList<>();
    for(int i = 0; i < visited.length; i++){
      if(index != i && nums[index][i] == 1){
        if(visited[index][i] == 0){
          visited[index][i] = 1;
          visited[i][index] = 1;
          int tmp = dfs(i, visited, nums, ans);
          items.add(tmp);
        }
      }
    }
    int max = 0;
    if(!items.isEmpty()){
      max = Collections.max(items);
    }
    return max + 1; //1是加上自己
  }
}
