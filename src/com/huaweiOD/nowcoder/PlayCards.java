package com.huaweiOD.nowcoder;

import com.MCAAlgorithm.base.class33.Hash;

import java.util.Scanner;
import java.util.*;
/**
 * @Author: zhaomengyi
 * @Date: 2023-04-12 20:03
 * @Description:
 */
public class PlayCards {
  static HashMap<String, Integer> bigDict = new HashMap<>();
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 注意 hasNext 和 hasNextLine 的区别
    bigDict.put("J", 11);
    bigDict.put("Q", 12);
    bigDict.put("K", 13);
    bigDict.put("A", 14);
    bigDict.put("2", 15);
    bigDict.put("joker", 16);
    bigDict.put("JOKER", 17);
    while (in.hasNextLine()) { // 注意 while 处理多个 case
      String str = in.nextLine();
      String[] arr = str.split("-");
      String part0 = arr[0];
      String part1 = arr[1];
      String ans = canWin(part0, part1);
      if(ans.equals("w")){
        System.out.println(part0);
      } else if(ans.equals("l")){
        System.out.println(part1);
      } else {
        System.out.println(ans);
      }
    }
  }

  public static  String canWin(String p0, String p1){
    Set<String> bigKeys = bigDict.keySet();
    int[] p0_arr = transPoker(p0);
    int[] p1_arr = transPoker(p1);
    int len0 = p0_arr.length;
    int len1 = p1_arr.length;
    //个子
    //对子
    //三个
    if((len0 == 1 && len1 == 1) || (len0 == 2 && len1 == 2) || (len0 == 3 && len1 == 3)){
      return p0_arr[0]  > p1_arr[0] ? "w" : "l";
    } else if(len0 == 5 && len1 == 5){ //顺子
      return p0_arr[0] > p1_arr[0] ? "w" : "l";
    } else if(len0 == 4){//炸弹
      if (len1 == 2 && p1_arr[0] == 16){
        return "l";
      } else if(len1 == 4){
        return p0_arr[0] > p1_arr[0] ? "w" : "l";
      } else {
        return "w";
      }
    } else if(len0 == 2 && p0_arr[0] == 16 ){
      return "w";
    } else if(len1 == 2 && p1_arr[0] == 16){
      return "l";
    } else if(len1 == 4 && len0 != 4){
      return "l";
    }
    return "ERROR";
  }
  public static int[] transPoker(String p){
    Set<String> bigKeys = bigDict.keySet();
    String[] pks = p.split(" ");
    int[] arr = new int[pks.length];
    for(int i = 0; i < pks.length; i++){
      if(bigKeys.contains(pks[i])){
        arr[i] = bigDict.get(pks[i]);
      } else {
        arr[i] = Integer.valueOf(pks[i]);
      }
    }
    return arr;
  }



}
