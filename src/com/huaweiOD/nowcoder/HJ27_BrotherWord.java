package com.huaweiOD.nowcoder;

import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-07 16:19
 * @Description:
 */
public class HJ27_BrotherWord {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 注意 hasNext 和 hasNextLine 的区别
    while (in.hasNext()) { // 注意 while 处理多个 case
      int len = in.nextInt();
      HashMap<String, List<String>> dict = new HashMap();
      for(int i = 0; i < len; i++){
        String str = in.next();
        String key_str = makeSortString(str);
        if(!dict.containsKey(key_str)){
          List<String> lst = new ArrayList<>();
          lst.add(str);
          dict.put(key_str, lst);
        } else {
          List<String> lst = dict.get(key_str);
          lst.add(str);
          dict.put(key_str, lst);
        }
      }
      //接收后两个参数
      String bro = in.next();
      int nage_bro = in.nextInt() - 1;

      String key_bro = makeSortString(bro);
      List<String> bro_list = dict.get(key_bro);
      int bro_size_ans = 0;
      ArrayList<String> all_bros = new ArrayList<>();
      for(int i = 0; i < bro_list.size(); i++){
        if(isBro(bro, bro_list.get(i))){
          bro_size_ans++;
          all_bros.add(bro_list.get(i));
        }
      }
      Collections.sort(all_bros);
      System.out.println(bro_size_ans);
      if(nage_bro < all_bros.size()){
        System.out.println(all_bros.get(nage_bro));
      }
    }
  }

  public static String makeSortString(String str){
    char[] arr = str.toCharArray();
    Arrays.sort(arr);
    return new String(arr);
  }

  public static boolean isBro(String t, String b){
    if(makeSortString(t).equals(makeSortString(b)) && !t.equals(b)){
      return true;
    }
    return false;

  }
}
