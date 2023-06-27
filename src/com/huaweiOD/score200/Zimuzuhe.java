package com.huaweiOD.score200;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-26 14:42
 * @Description:
 */
public class Zimuzuhe {
  public static Map<Integer, List<Character>> DIC = new HashMap<>();

  public static void main(String[] args) {
    int offset = 0;
    for (int i = 0; i < 10; i++) {
      List<Character> lst = new ArrayList<>();
      int len = i < 6 ? 3 : 2;
      for (int l = 0; l < len; l++) {
        char c = (char) ('a' + offset);
        offset++;
        lst.add(c);
      }
      DIC.put(i, lst);
    }
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String input_str = in.nextLine();
      String word = in.nextLine();
      LinkedList<String> ans = new LinkedList<>();
      for(int i = 0; i < input_str.length(); i++){
        int input_key = Character.getNumericValue(input_str.charAt(i));
        List<Character> lst = DIC.get(input_key);
        if(ans.isEmpty()){
          for(char c : lst){
            ans.offer(String.valueOf(c));
          }
        } else {
            while(ans.peekFirst().length() == i){
              String tmp = ans.pollFirst();
              for(char c : lst){
                String tianchong = tmp + String.valueOf(c);
                if(word.equals(tianchong) || tianchong.contains(word)){
                  continue;
                }
                ans.offer(tianchong);
              }
            }
        }
      }
      for(int i = 0; i < ans.size(); i++){
        System.out.print(ans.get(i) + ",");
      }
      System.out.println();
    }
  }
}
