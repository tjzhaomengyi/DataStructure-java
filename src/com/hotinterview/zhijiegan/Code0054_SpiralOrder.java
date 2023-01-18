package com.hotinterview.zhijiegan;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-21 7:57 下午
 * @Description:
 */
public class Code0054_SpiralOrder {
  public List<Integer> spiralOrder(int[][] matrix) {
    if(matrix == null) return null;
    List<Integer> ans = new ArrayList<>();
    int tR = 0;
    int tC = 0;
    int eR = matrix.length - 1;
    int eC = matrix[0].length - 1;
    while(tR <= eR && tC <= eC) {
      printEdge(matrix, tR++, tC++, eR--, eC--, ans);
    }
    return ans;
  }

  public void printEdge(int[][] m, int tR, int tC, int eR, int eC, List<Integer> ans){
    if(tR == eR){
      for(int i = tC; i <= eC; i++){
        ans.add(m[tR][i]);
      }
    } else if(tC == eC){
      for(int i = tR; i <= eR; i++){
        ans.add(m[i][tC]);
      }
    } else {
      int curR = tR;
      int curC = tC;
      while(curC < eC){
        ans.add(m[tR][curC]);
        curC++;
      }
      while(curR < eR){
        ans.add(m[curR][eC]);
        curR++;
      }
      while(curC > tC){
        ans.add(m[eR][curC]);
        curC--;
      }
      while(curR > tR){
        ans.add(m[curR][tC]);
        curR--;
      }
    }
  }

  public static void main(String[] args) {
//    int[][] raw = new int[3][3];
//    raw[0] = new int[]{1,2,3};
//    raw[1] = new int[]{4,5,6};
//    raw[2] = new int[]{7,8,9};
    int[][] matrix = { { 1, 2, 3, 4 },
        { 5, 6, 7, 8 },
        { 9, 10, 11, 12 },
        { 13, 14, 15, 16 } };
    List<Integer> ans = new Code0054_SpiralOrder().spiralOrder(matrix);
    System.out.println(ans);
  }
}
