package com.huaweiOD.score200;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-19 11:48
 * @Description:
 */
public class QiuZuidaShuzi {
  static List<Integer> resList = new ArrayList<>();
  static List<Character> inputList = new ArrayList<>();
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      for(int i = 0; i < str.length(); i++){
        char inputChar = str.charAt(i);
        inputList.add(inputChar);
      }
      Map<Character, Integer> cnt_map = new HashMap<>();
      for(Character c : inputList){
        cnt_map.put(c, cnt_map.getOrDefault(c, 0) + 1);
      }
      List<Character> lis = new ArrayList<>();
      for(Map.Entry<Character, Integer> entry : cnt_map.entrySet()){
        char c = entry.getKey();
        lis.add(c);
        if(entry.getValue() >= 2){
          lis.add(c);//如果出现次数至少两次，那么就再加一次
        }
      }
      swapAndCombine(lis, 0, lis.size() - 1);
      Collections.sort(resList);
      System.out.println(resList.get(resList.size() - 1));
    }
  }

  public static void swapAndCombine(List<Character> strs, int start, int end){
    if(start == end){
      String numString = "";
      for(int i = 0; i < strs.size(); i++){
        numString += strs.get(i);
      }
      if(!resList.contains(Integer.parseInt(numString)) && isChild(strs)){
        resList.add(Integer.parseInt(numString));
      }
    } else {
      for(int i = start; i < strs.size(); i++){
        if(i != start && strs.get(i) == strs.get(start)){
          continue;
        }
        Collections.swap(strs, i, start);
        if(strs.get(0).equals("0")){
          continue;
        }
        swapAndCombine(strs, start + 1, end);
        Collections.swap(strs, i , start);
      }
    }
  }

  public static boolean isChild(List<Character> child){
    int index = 0;
    int count = 0;
    for(int i = 0; i < child.size(); i++){
      Character str = child.get(i);
      for(int j = index; j < inputList.size(); j++){
        Character tem = inputList.get(j);
        if(tem == str){
          index = j + 1;
          count++;
          break;
        }
        if(j == inputList.size() - 1){
          return false;
        }
      }
    }
    return count == child.size();
  }
}
