package com.hotinterview.digui;

import com.hotinterview.zhijiegan.Code0179_MaxNumber;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-27 4:28 下午
 * @Description:
 */
public class Code0227_CaculateString {

  //这个题不考虑括号，用栈解。思路：用一个前置运算符辅助计算当前的运算法
  public int calculate(String s) {
    Stack<Integer> stack = new Stack<>();
    int num = 0;
    int n = s.length();
    char presign = '+';
    for(int i = 0; i < n; i++){
      if(Character.isDigit(s.charAt(i))){
        num = num * 10 + s.charAt(i) - '0';
      }
      //当前是运算符把前一个运算符清了,到头也要清
      if(!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == s.length() - 1){
        if(presign == '+'){
          stack.push(num);
        } else if(presign == '-'){
          stack.push(-num);
        } else if(presign == '*'){
          stack.push(stack.pop() * num);
        } else if(presign == '/'){
          stack.push(stack.pop() / num);
        }
        presign = s.charAt(i);
        num = 0;
      }
    }
    int ans = 0;
    while(!stack.isEmpty()){
      ans += stack.pop();
    }
    return ans;
  }

  public static void main(String[] args) {
    int ans = new Code0227_CaculateString().calculate("2+2*3");
    System.out.println(ans);
  }
}
