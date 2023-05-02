package com.huaweiOD.od2023.s120e138;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-17 18:14
 * @Description:https://dream.blog.csdn.net/article/details/129191224
 * todo:这道题用前缀和合适
 */
public class PortAvgGood {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      int minAvg = Integer.parseInt(str);
      String[] strs = in.nextLine().split(" ");
      List<Integer> listValues = new ArrayList<>();
      for(int i = 0; i < strs.length; i++){
        listValues.add(Integer.parseInt(strs[i]));
      }
      getPeriod(minAvg, listValues);
      if(!resultList.isEmpty()){
        for(int i = 0; i < resultList.size(); i++){
          System.out.print(resultList.get(i) + " ");
        }
      }
    }
  }

  private static List<String> resultList = new ArrayList<>();
  private static int currentLen = 0;
  private static int getListSum(List<Integer> list, int start, int end){
    int sum = 0;
    for(int i = start; i <= end; i++){
      sum += list.get(i);
    }
    return sum;
  }
  private static void getPeriod(int minAvg, List<Integer> list){
    resultList.clear();
    currentLen = 0;
    for(int start = 0; start < list.size(); start++){
      for(int end = start + 1; end < list.size(); end++){
        int sum = getListSum(list, start, end);
        String result = "";
        int length = end - start + 1;
        if(sum == 0 && minAvg >= 0){
          result =start + "-" + end;
        } else {
          if(sum <= minAvg + length){
            result = start + "-" + end;
          }
        }
        if(result.contains("-")){
          if(length == currentLen){
            resultList.add(result);
          }
          if(length > currentLen){
            resultList.clear();
            resultList.add(result);
            currentLen = length;
          }
        }
      }
    }
  }
}
