package com.mikemyzhao.simulate_reality_13.bigshua;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-17 19:25
 * @Description:
 */
public class LC_0150_NiBolanShi {
  public static int evalRPN(String[] tokens) {
    Stack<Integer> stack = new Stack();
    for(String str : tokens){
      if(str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")){
        compute(stack, str);
      } else {
        stack.push(Integer.valueOf(str));
      }
    }
    return stack.peek();
  }

  public static void compute(Stack<Integer> stack, String op){
    int num2 = stack.pop();
    int num1 = stack.pop();
    int ans = 0;
    switch (op){
      case "+":
        ans = num1 + num2;
        break;
      case "-":
        ans = num1 - num2;
        break;
      case "*":
        ans = num1 * num2;
        break;
      case "/":
        ans = num1 / num2;
        break;
    }
    stack.push(ans);
  }
}
