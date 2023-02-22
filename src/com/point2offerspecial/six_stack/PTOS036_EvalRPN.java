package com.point2offerspecial.six_stack;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-07 11:33
 * @Description:注意tokens就是逆波兰式的顺序，直接使用一个栈处理即可，里面也没有括号
 */
public class PTOS036_EvalRPN {
  public int evalRPN(String[] tokens) {
    Stack<Integer> store = new Stack();
    for(String t : tokens){
      if(t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/")){
        compute(store, t);
      } else {
        store.push(Integer.valueOf(t));
      }
    }
    return store.peek();
  }

  public void compute(Stack<Integer> store, String token){
    int ans = 0;
    int n1 = store.pop();
    int n2 = store.pop();
    if(token.equals("+")){
      ans = n1 + n2;
    } else if(token.equals("-")){
      ans = n2 - n1;
    } else if(token.equals("*")){
      ans = n1 * n2;
    } else if(token.equals("/")){
      ans = n2 / n1;
    }
    store.push(ans);
  }
}
