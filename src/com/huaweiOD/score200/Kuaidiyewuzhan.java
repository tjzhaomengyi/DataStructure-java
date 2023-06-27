package com.huaweiOD.score200;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-24 10:47
 * @Description:
 */
public class Kuaidiyewuzhan {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int n = in.nextInt();
      int[][] matrix = new int[n][n];
      for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
          matrix[i][j] = in.nextInt();
        }
      }
      Set<Integer> visited = new HashSet<>();
      int count = 0;
      int i = 0;
      while(i < n){
        if(visited.contains(i)){
          i++;
          continue;
        }
        Set<Integer> connectedNodes = new HashSet<>();
        connectedNodes.add(i);
        trace(connectedNodes, i, matrix);
        visited.addAll(connectedNodes);
        count++;
        i++;
      }
      System.out.println(count);
    }
  }


  private static void trace(Set<Integer> connectedNodes, int n,int[][] matrix){
    for(int i = 0; i < matrix.length; i++){
      if(connectedNodes.contains(i)){
        continue;
      }
      if(n != i && matrix[n][i] == 1){
        connectedNodes.add(i);
        trace(connectedNodes, i, matrix);//深度递归下去
      }
    }
  }
}
