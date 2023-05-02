package com.huaweiOD.od2023.s100e119;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-18 10:35
 * @Description:
 */
public class MaxStringArea {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String[] lines = str.split(",");
      int maxValue = 0;
      for(int i = 0; i < lines.length; i++){
        for(int j = i + 1; j < lines.length; j++){
          if(!checkSingle(lines[i], lines[j])){
            maxValue = Math.max(maxValue, lines[j].length() * lines[i].length());
          }
        }
      }
      System.out.println(maxValue);
    }
  }

  public static boolean checkSingle(String str1, String str2){
    char[] arr1 = str1.toCharArray();
    char[] arr2 = str2.toCharArray();
    Arrays.sort(arr1);
    Arrays.sort(arr2);
    int index1 = 0;
    int index2 = 0;
    while(index2 < arr2.length && index1 < arr1.length){
      if(arr1[index1] == arr2[index2]){
        return true;
      } else if(arr1[index1] > arr2[index2]){
        index2++;
      } else if(arr1[index1] < arr2[index2]){
        index1++;
      }
    }
    return false;
  }


}
