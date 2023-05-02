package com.huaweiOD.nowcoder;

import org.w3c.dom.ls.LSLoadEvent;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-12 17:23
 * @Description:
 */
public class EnglishNumber {
  static String[] spk_bmt = {"","thousand","million","billion"};
  static String[] spk_nums_ge = {"","one","two","three","four","five","six",
  "seven","eight","nine"};
  static String[] spk_nums_one_shi = {"ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen", "eighteen", "nineteen"};
  static String[] spk_nums_shi = {"","","twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 注意 hasNext 和 hasNextLine 的区别
    while (in.hasNextLong()) { // 注意 while 处理多个 case
      Long num = in.nextLong();
      System.out.println(speakNum(num));
    }
  }

  public static String speakNum(Long num){
    String spk_ans = "";
    String str = String.valueOf(num);
    int len = str.length();
    if(len == 1){
      return spk_nums_ge[Integer.valueOf(str)];
    }

    for(int i = 0; i < str.length(); i++){
      char c = str.charAt(i);
      int bit_num = Character.getNumericValue(c);

      String speak_num = "";
      int part = (str.length() - 1 - i) / 3;
      if((str.length() - 1 - i) % 3 == 0 ){ // 个位
        if(i-2 >= 0 && str.charAt(i-1) == '0' && str.charAt(i - 2) == '0' && str.charAt(i) == '0'){
          continue;
        }
        speak_num = spk_nums_ge[bit_num];
        speak_num = speak_num +" "+ spk_bmt[part] + " ";

      } else if((str.length() - 1 - i) % 3 == 2){ // 百位
        speak_num = spk_nums_ge[bit_num];
        if(str.charAt(i) != '0') {
          speak_num = speak_num + " hundred ";
          if (str.charAt(i + 1) != '0' || str.charAt(i + 2) != '0') {
            speak_num = speak_num + "and ";
          }
        }

      } else if((str.length() - 1 - i) % 3 == 1) { //十位
        if (str.charAt(i) == '1') {
          speak_num = spk_nums_one_shi[Character.getNumericValue(str.charAt(i + 1))];
          i++;
        } else {
          speak_num = spk_nums_shi[bit_num] + " ";
        }
      }
      spk_ans =spk_ans + speak_num;
    }
    return spk_ans.replaceAll("  ", " ");
  }


}
