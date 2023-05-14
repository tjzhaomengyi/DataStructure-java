package com.huaweiOD.score200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-12 10:52
 * @Description:
 */
public class TianranXushuichi {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      ArrayList<Integer> heights = new ArrayList<>();
      String[] heightstr = in.nextLine().split(" ");
      for(int i = 0; i < heightstr.length; i++){
        heights.add(Integer.valueOf(heightstr[i]));
      }
      int minHeight = Collections.min(heights);
      int maxArea = 0;
      int maxLeftIndex = 0;
      int maxRightIndex = 0;
      for(int i = 0; i < heights.size() - 2; i++){
        int leftHeight = heights.get(i);
        if(leftHeight == minHeight){
          continue;
        }
        int[] result = findMaxArea(heights, minHeight, i);
        if(result[0] > maxArea){
          maxArea = result[0];
          maxLeftIndex = result[1];
          maxRightIndex = result[2];
        }
      }
      System.out.println(maxArea == 0 ? 0 : maxLeftIndex + " " + maxRightIndex + ":" + maxArea);
    }
  }

  private static int[] findMaxArea(ArrayList<Integer> heights, int minHeight, int i){
    int maxArea = 0;
    int maxLeftIndex = 0;
    int maxRightIndex = 0;
    for(int j = i + 2; j < heights.size(); j++){
      int rightHeight = heights.get(j);
      if(rightHeight == minHeight){
        continue;
      }
      int height = Math.min(heights.get(i), rightHeight);
      int area = 0;
      for(int k = i + 1; k < j; k++){
        int heightDiff = height - heights.get(k);
        if(heightDiff > 0){
          area += heightDiff;
        }
      }
      if(area > maxArea){
        maxArea = area;
        maxLeftIndex = i;
        maxRightIndex = j;
      } else if(area == maxArea && (maxRightIndex - maxLeftIndex) > (j - i)){
        maxLeftIndex = i;
        maxRightIndex = j;
      }
    }
    return new int[] {maxArea, maxLeftIndex, maxRightIndex};
  }

}
