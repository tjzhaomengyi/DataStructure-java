package com.huaweiOD.score200;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-18 17:07
 * @Description:
 */
public class ExcelDanyuangeJisuan {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      int m = in.nextInt();
      int n = in.nextInt();
      int[][] grids = new int[m][n];
      Map<int[], String> tmps = new HashMap<>();
      for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
          String input = in.next();
          if(input.charAt(0) == '='){
            tmps.put(new int[]{i, j}, input);
            grids[i][j] = -1;
          } else {
            grids[i][j] = Integer.valueOf(input);
          }
        }
      }
      fillExcel(grids, tmps);
      String sum_pos = in.next();
      String str_start = sum_pos.split(":")[0];
      String str_end = sum_pos.split(":")[1];
      int start_x = getPos(str_start)[0];
      int start_y = getPos(str_start)[1];
      int end_x = getPos(str_end)[0];
      int end_y = getPos(str_end)[1];
      int ans = 0;
      for(int i = start_x; i <= end_x; i++){
        for(int j = start_y; j <= end_y; j++){
          ans += grids[i][j];
        }
      }
      System.out.println(ans);
    }
  }
  public static void fillExcel(int[][] grids, Map<int[], String> tmps){
    for(int[] pos : tmps.keySet()){
      int x = pos[0];
      int y = pos[1];
      String str_format = tmps.get(pos);
      grids[x][y] = getVal(str_format, grids);
    }
  }

  public static int getVal(String str_format, int[][] grids){
    String format = str_format.substring(1);
    int ans = 0;
    boolean is_pos = false;
    boolean is_add = true;
    String tmp = "";
    for(int i = 0; i < format.length(); i++){
      if(format.charAt(i) == '+' || format.charAt(i) == '-'){
        if(is_pos){
          int[] pos = getPos(tmp);
          ans += is_add ? grids[pos[0]][pos[1]] : -grids[pos[0]][pos[1]];
          is_pos = false;
        } else {
          ans += is_add ? Integer.valueOf(tmp) : -Integer.valueOf(tmp);
        }
        tmp = "";
        is_add = format.charAt(i) == '+' ? true : false;
      } else if(Character.isLetter(format.charAt(i))){
        is_pos = true;
        tmp += format.charAt(i);
      } else {
        tmp += format.charAt(i);
      }
    }
    //最后剩下的加上
    if(is_pos){
      int[] pos = getPos(tmp);
      ans += is_add ? grids[pos[0]][pos[1]] : -grids[pos[0]][pos[1]];
    } else {
      ans += is_add ? Integer.valueOf(tmp) : -Integer.valueOf(tmp);
    }
    return ans;
  }

  public static int[] getPos(String str_pos){
    int col = str_pos.charAt(0) - 'A' ;
    int row = Integer.valueOf(str_pos.substring(1)) - 1;
    return new int[]{row, col};
  }
}
