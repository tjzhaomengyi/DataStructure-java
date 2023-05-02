package com.huaweiOD.od2023.s20e40;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-24 14:21
 * @Description:
 */
public class QiuZuidaShuzi {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      String num = in.next();
      if(null == num || num.length() < 1 || num.length() > 10000){
        System.out.println("input error");
        return;
      }
      HashMap<Character, Integer> numHashMap = new HashMap<>();
      LinkedList<Character> myLinkList = new LinkedList<>();
      char[] chars = num.toCharArray();
      for(char c : chars){
        numHashMap.put(c, numHashMap.getOrDefault(c, 0) + 1);
        myLinkList.add(c);
      }
      for(Map.Entry<Character, Integer> entry : numHashMap.entrySet()){
        Character cVal = entry.getKey();
        Integer cnt = entry.getValue();
        for(int j = 0; j < myLinkList.size() && cnt > 2; j++){
          if(cVal == myLinkList.get(j)){
            //技巧(1)减掉最后
            if(myLinkList.size() - 1 == j){
              myLinkList.removeLast();
              cnt--;
            } else if(cVal < myLinkList.get(j + 1)){ //技巧(2)减掉后面比它大的
              myLinkList.remove(j);
              j--;
              cnt--;
            }
          }
        }
      }

      StringBuilder sb = new StringBuilder();
      for(char c : myLinkList){
        sb.append(c);
      }
      System.out.println(sb.toString());
    }
  }
  //技巧:贪心，(1)在最后的删除掉 (2)后面比它大的删除掉


}
