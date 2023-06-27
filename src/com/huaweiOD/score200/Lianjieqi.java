package com.huaweiOD.score200;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-22 11:18
 * @Description:
 */
public class Lianjieqi {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String lines = in.nextLine();
      String lianjieqi_str = in.nextLine();
      String[] lines_tmp = lines.substring(1, lines.length() - 1).split("],\\[");
      int[][] lines_arr = new int[lines_tmp.length][2];
      for(int i = 0; i < lines_tmp.length; i++){
        int start = Integer.valueOf(lines_tmp[i].split(",")[0]);
        int end = Integer.valueOf(lines_tmp[i].split(",")[1]);
        lines_arr[i] = new int[]{start, end};
      }
      String[] lianjieqi_tmp = lianjieqi_str.substring(1, lianjieqi_str.length() - 1).split(",");
      LinkedList<Integer> lianjieqis_arr = new LinkedList();
      for(int i = 0; i < lianjieqi_tmp.length; i++){
        lianjieqis_arr.addLast(Integer.valueOf(lianjieqi_tmp[i]));
      }
      System.out.println(getMInLinks(lines_arr, lianjieqis_arr));
    }
  }

  public static int getMInLinks(int[][] lines, LinkedList<Integer> connects){
    Arrays.sort(lines, ((o1, o2) -> o1[0] - o2[0]));
    LinkedList<int[]> linesList = new LinkedList<>();//队列
    linesList.add(lines[0]);
    LinkedList<Integer> gaps = new LinkedList<>();
    for(int i = 1; i < lines.length; i++){
      int[] last_line = linesList.getLast();
      int slast = last_line[0];
      int elast = last_line[1];

      //
      int scur = lines[i][0];
      int ecur = lines[i][1];
      if(scur <= elast){
        linesList.removeLast();
        linesList.addLast(new int[]{slast, Math.max(elast, ecur)});
      } else {
        gaps.addLast(scur - elast);
        linesList.addLast(lines[i]);
      }
    }
    gaps.sort((o1, o2) -> o2 - o1);
    connects.sort((o1, o2) -> o2 - o1);
    int ans = 0;
    while(connects.size() > 0 && !gaps.isEmpty()){
      if(connects.remove(connects.size() - 1) >= gaps.getLast()){ // 这里用了贪心
        gaps.removeLast();
      }
    }
    return gaps.size() + 1;
  }
}
