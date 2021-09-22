package com.mikemyzhao.Stack;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-14 14:44
 * @Description:
 */
public class ValidParenthesses {

  boolean isValid(String str){
    Stack<Character> s=new Stack<>();
    for(char c:str.toCharArray()){
      if(c=='('||c=='['||c=='{'){
        s.add(c);
      }else{
        if(!s.empty() && leftOfC(c)==s.peek()){
          s.pop();
        }else{
          return false;
        }
      }
    }
    return s.empty();//匹配好就都没了
  }

  char leftOfC(char c){
    if(c=='}') return '{';
    if(c==')') return '(';
    return '[';
  }
}
