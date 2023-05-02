package com.huaweiOD.od2023.s20e40;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-24 14:53
 * @Description:
 */
public class YonghuDiaodu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int number = in.nextInt();
      int[][] nums = new int[number][3];
      for(int i = 0; i < number; i++){
        for(int j = 0; j < 3; j++){
          nums[i][j] = in.nextInt();
        }
      }

      //贪心，其实这里应该用递归全排列来做
      int ans = solve(nums);
      System.out.println(ans);
    }
  }

  public static int solve(int[][] nums){
    int ans = 0;
    int[] visited = new int[3];
    for(int i = 0; i < nums.length; i++){
      int[] line = nums[i];
      int minVal = Integer.MAX_VALUE;
      int markIndex = -1;
      if(i == 0){
        for(int j = 0; j < 3; j++){
          if(line[j] <= minVal){
            minVal = line[j];
            markIndex = j;
          }
        }
        visited[markIndex] = 1;
      } else {
        for(int j = 0; j < 3; j++){
          if(line[j] <= minVal && visited[j] != 1){
            minVal = line[j];
            markIndex = j;
          }
        }
        Arrays.fill(visited, 0);
        visited[markIndex] = 1;
      }
      ans += minVal;
    }
    return ans;
  }

  public static boolean canChoose(int x, int y, int[][] visited){
    for(int i = 0; i < visited.length; i++){
      if(i == x) continue;
      if(visited[i][y] == 1){
        return false;
      }
    }
    return true;
  }
}
