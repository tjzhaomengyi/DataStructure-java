package com.huaweiOD.od2023.s40e59;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-24 10:29
 * @Description:
 */
public class Xiangduikaiyinjie {
//  static char[] yuanyins = new char[]{'a','e','i','o','u'};
  static ArrayList<Character> yuanyins = new ArrayList<Character>(Arrays.asList('a','e','i','o','u'));
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      int kaiyinjie = chazhaoKaiyinjie(str);
      System.out.println(kaiyinjie);
    }
  }

  public static int chazhaoKaiyinjie(String str){
    String[] words = str.split(" ");
    int cnt = 0;
    for(int i = 0; i < words.length; i++){
      String word = words[i];
      String panduan_word = word.replaceAll("[^a-zA-z]", "");
      if(panduan_word.length() < 4){
        continue;
      }
      if(panduan_word.length() == word.length()){
        word = new StringBuilder(word).reverse().toString();
      }
      char[] arr = word.toCharArray();
      for(int j = 0; j <= arr.length - 4; j++){
        if(Character.isLetter(arr[j]) && Character.isLetter(arr[j+1]) && Character.isLetter(arr[j+2]) && Character.isLetter(arr[j+3])){
          if(!yuanyins.contains(arr[j]) && yuanyins.contains(arr[j + 1]) &&
              !yuanyins.contains(arr[j + 2]) && arr[j + 2] != 'r' && arr[j + 3] == 'e'){
            cnt++;
          }
        }
      }
    }
    return cnt;
  }

}
