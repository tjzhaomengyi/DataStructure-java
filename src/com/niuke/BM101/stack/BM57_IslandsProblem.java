package com.niuke.BM101.stack;

import java.util.Stack;

public class BM57_IslandsProblem {
    public int numIslands(int[][] grid){
        if(grid == null || grid.length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    count++;
                    dfsWithStack(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfsWithStack(int[][] grid, int i, int j){
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{i, j});
        grid[i][j] = 0; // 先淹掉

        int[][] directions = {
                {1, 0}, //下
                {-1, 0}, //上
                {0, 1}, //右
                {0, -1} //左
        };

        while(!stack.isEmpty()){
            int[] cur = stack.pop();

            for(int[] dir: directions){
                int newX = cur[0] + dir[0];
                int newY = cur[1] + dir[1];

                if(newX >= 0 && newX < grid.length
                        && newY >= 0 && newY < grid[0].length
                        &&grid[newX][newY] == 1){
                    stack.push(new int[]{newX, newY});
                    grid[newX][newY] = 0; //标记访问
                }
            }
        }
    }


}
