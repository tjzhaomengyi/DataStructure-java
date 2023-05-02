package com.huaweiOD.od2023.s120e138;

import java.util.Scanner;
import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-16 15:08
 * @Description:
 */
public class XiaoXiaoLe {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      System.out.println(xiaoxiaole(str));
    }
  }

  public static int xiaoxiaole(String str){
    Stack<String> stack = new Stack<>();
    int head = -1;
    for(int i = 0; i < str.length(); i++){
      if(stack.size() > 0){
        if(stack.peek().equals(String.valueOf(str.charAt(i)))){ //字符和字符串比较
          stack.pop();
          head--;
          continue;
        }
      }
      stack.push(String.valueOf(str.charAt(i)));
      head++;
    }
    return stack.size();
  }

}
