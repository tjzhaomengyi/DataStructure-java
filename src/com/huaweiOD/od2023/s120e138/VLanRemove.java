package com.huaweiOD.od2023.s120e138;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-17 17:46
 * @Description:
 */
public class VLanRemove {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String[] vlans = str.split(",");
      int vLanNeed = Integer.valueOf(in.nextLine());
      //字符串所有包含的数字加入到List
      List<Integer> lst = new ArrayList<>();
      for(String vlan : vlans){
        String[] tmp = vlan.split("-");
        if(tmp.length > 1){
          for(int i = Integer.parseInt(tmp[0]); i <= Integer.parseInt(tmp[1]); i++){
            lst.add(i);
          }
        } else {
          lst.add(Integer.parseInt(tmp[0]));
        }
      }

      for(int i = 0; i < lst.size(); i++){
        if(lst.get(i) == vLanNeed){
          lst.remove(i);
        }
      }

      Collections.sort(lst);
      StringBuilder stringBuilder = new StringBuilder();
      int i = 0;
       while(i < lst.size()){
         int firstnum = lst.get(i);
         int j = 1;
         while(j <= lst.size() - 1 - i){
           if(lst.get(i) + j == lst.get(i + j)){
             j++;
           } else {
             break;
           }
         }
         if(j == 1){
           stringBuilder.append(firstnum);
           stringBuilder.append(",");
           ++i;
         } else {
           stringBuilder.append(String.valueOf(firstnum) + "-" + String.valueOf(firstnum + j - 1) + ",");
           i = i + j;
         }
       }
       System.out.println(stringBuilder.toString().substring(0, stringBuilder.length() - 1));
    }
  }


}
