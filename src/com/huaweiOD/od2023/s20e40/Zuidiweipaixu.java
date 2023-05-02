package com.huaweiOD.od2023.s20e40;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-24 19:56
 * @Description:
 */
public class Zuidiweipaixu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String[] str = in.nextLine().split(",");
      TreeMap<Integer, List<String>> map = new TreeMap<>();
      for(int i = 0; i < str.length; i++){
        String strNum = str[i];
        int num = Integer.valueOf(str[i].replace("-",""));
        int yushu = num % 10;
        if(map.get(yushu) == null){
          List<String> lst = new LinkedList<>();
          lst.add(strNum);
          map.put(yushu, lst);
        } else {
          LinkedList<String> lst = (LinkedList<String>) map.get(yushu);
          lst.add(strNum);
          map.put(yushu, lst);
        }
      }

      StringBuilder sb = new StringBuilder();
      for(Map.Entry e : map.entrySet()){
        LinkedList<String> q = (LinkedList<String>) e.getValue();
        for(int i = 0; i < q.size(); i++){
          sb.append(q.get(i) + ",");
        }
      }
      System.out.println(sb.substring(0, sb.length() - 1).toString());
    }
  }

}
