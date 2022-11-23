package com.mikemyzhao.Disassemble;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-13 17:51
 * @Description:一个拆分问题的例子，计算器
 */
public class Calculator {
  int calculate(String s){
    Stack stack = new Stack();
    char sign = '+';
    int num=0;
    for(int i=0;i<s.length();i++) {
      char c = s.charAt(i);
      /**1、如果该字符是数字**/
      if(Character.isDigit(c)){
        /**java细节char转int**/
        num = 10*num+Character.getNumericValue(c);//注意：进来一位，原来的数字乘以10，再加该数字
      }
      /**2判断是否是括号，如果是括号需要进行递归**/
      if (c=='('){
        calculate(s.substring(i+1,s.length()-1));//如果i位是括号，从下一位开始进行递归
      }
      if(c==')'){//如果是反括号，结束递归
        break;
      }
      /**注意！！！：1、sign初始为+，每次遇见c是符号的时候，之前的sign已经更改了，所以直接放入当前num
       * 2、到结尾的时候一定要收口，要不最后一个没有参与计算！！！
       * **/
      if((!Character.isDigit(c) && c!=' ') || (i==s.length()-1)){
        if(sign=='+'){
          stack.add(num);
        }else if(sign=='-'){
          stack.add(-num);
        }else if(sign=='*'){
          Integer peek = (Integer) stack.pop();
          stack.add(peek*num);
        }else if(sign=='/'){
          Integer peek = (Integer)stack.pop();
          stack.add(peek/num);
        }
        //这个时候要记录下一个num，并保留这个c作为sign
        num=0;
        sign=c;
        continue;
      }
    }
    int res = Arrays.asList(stack.toArray()).stream().
        map(String::valueOf).map(Integer::valueOf).reduce(Integer::sum).orElse(0);
    return res;
  }

  public static void main(String[] args) {
    String s = "3/2";
    System.out.println(new Calculator().calculate(s));
  }
}
