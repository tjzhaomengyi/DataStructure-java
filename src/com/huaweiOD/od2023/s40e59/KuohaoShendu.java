package com.huaweiOD.od2023.s40e59;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-23 17:03
 * @Description:
 */
public class KuohaoShendu {
  //技巧:初始化Arrays
  public static ArrayList<Character> leftDict = new ArrayList<Character>(Arrays.asList('(','[','{'));
  public static ArrayList<Character> rightDict = new ArrayList<Character>(Arrays.asList(')',']','}'));
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      int ans = shendu(str);
      System.out.println(ans);
    }
  }

  public static int shendu(String str){
    char[] arr = str.toCharArray();
    Stack<Character> stack = new Stack<>();
    int max_level = 0;
    for(int i = 0; i < arr.length; i++){
      if(leftDict.contains(arr[i])){
        stack.push(arr[i]);
      } else if(rightDict.contains(arr[i]) && !stack.isEmpty()) {
        char peek = stack.peek();
        if(pipei(peek, arr[i])){
          max_level = Math.max(max_level, stack.size());
          stack.pop();
        }
      }
    }
    return max_level;
  }

  public static boolean pipei(char a, char b){
    if(leftDict.indexOf(a) == rightDict.indexOf(b)){
      return true;
    }
    return false;
  }
}
