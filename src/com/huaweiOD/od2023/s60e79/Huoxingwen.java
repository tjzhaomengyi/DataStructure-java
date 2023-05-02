package com.huaweiOD.od2023.s60e79;

import java.util.Scanner;
import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-20 14:23
 * @Description:
 */
public class Huoxingwen {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      int ans = calculate(str);
      System.out.println(ans);
    }
  }

  public static int calculate(String str) {
    int tmp = 0;
    Stack<String> stack = new Stack<>();
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (Character.isDigit(c)) {
        tmp = tmp * 10 + Character.getNumericValue(c);
      } else {
        if (!stack.empty()) {
          if (stack.peek().equals("$")) {
            stack.pop();
            tmp = op1(Integer.valueOf(stack.pop()), tmp);
          }
        }
        stack.push(String.valueOf(tmp));
        stack.push(String.valueOf(c));
        tmp = 0;
      }
      if(i == str.length() - 1) {
        if(stack.peek().equals("$")){
          stack.pop();
          tmp = op1(Integer.valueOf(stack.pop()), tmp);
        }
        stack.push(String.valueOf(tmp));
      }
    }

    //计算结果
    int ans = 0;
    for (int i = 0; i < stack.size(); i++) {
      String ele = stack.get(i);
      if(i == 0){
        ans = Integer.valueOf(stack.get(0));
        continue;
      }
      if (!ele.equals("#")){
        ans = op2(ans, Integer.valueOf(ele));
      }
    }
    return ans;
  }

  //"#"
  public static int op2(int a, int b) {
    return 2 * a + 3 * b + 4;
  }

  //$
  public static int op1(int a, int b) {
    return 3 * a + b + 2;
  }


}
