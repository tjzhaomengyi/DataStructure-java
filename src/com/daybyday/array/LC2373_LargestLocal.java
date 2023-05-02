package com.daybyday.array;

/**
 * @Author: zhaomengyi
 * @Date: 2023-03-01 20:44
 * @Description:
 */
public class LC2373_LargestLocal {
  public int[][] largestLocal(int[][] grid) {
     //池化后图像尺寸 = (图像尺寸 - 池化尺寸) / 步长 + 1 = n - 3 + 1 = n - 2
    int N = grid.length;
    int K = N - 2;
    int[][] ans = new int[K][K];
    int last_max = Integer.MIN_VALUE;
//    for(int i = 0; i < 3; i++){
//      for(int j = 0; j < 3; j++){
//        last_max = Math.max(last_max, grid[i][j]);
//      }
//    }
    ans[0][0] = last_max;
    for(int i = 0; i < K; i++){
      for(int j = 0; j < K; j++){
        for (int x = i; x < i + 3; x++) {
          for (int y = j; y < j + 3; y++) {
            ans[i][j] = Math.max(ans[i][j], grid[x][y]);
          }
        }
      }
    }
    return ans;
  }
}
