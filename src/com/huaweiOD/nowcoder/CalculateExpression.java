package com.huaweiOD.nowcoder;


import java.util.Scanner;
import java.util.*;
/**
 * @Author: zhaomengyi
 * @Date: 2023-04-14 15:13
 * @Description: 计算表达式,这道题一共三个板子，(1)括号嵌套问题 (2)计算问题的入栈处理 (3)入栈结果表达式的最终计算
 */
public class CalculateExpression {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 注意 hasNext 和 hasNextLine 的区别
    while (in.hasNextLine()) { // 注意 while 处理多个 case
      String exp = in.nextLine().replaceAll("[\\{\\[]","(").replaceAll("[\\}\\]]",")");
      System.out.println(calculte(exp, 0)[0]);
    }
  }

  public static int[] calculte(String str, int i){
    Stack<String> stack = new Stack<>();//
    int num = 0;
    int[] bra = null;
    while(i < str.length() && str.charAt(i) != ')'){
      //如果是数字
      char c = str.charAt(i);
      if(Character.isDigit(c)){
        num  = num * 10 + Character.getNumericValue(c);
        i++;
      } else if(c == '+' || c == '-' || c == '*' || c == '/'){
        addNumToStack(stack, num);//先把当前num加进去再加符号
        stack.add(String.valueOf(c));
        i++;//往下走
        num = 0;
      } else if(c == '('){
        bra = calculte(str, i + 1);
        num = bra[0];
        i = bra[1] + 1;
      }
    }
    //如果为长度或者是遇到右括号，退出当前递归或者整个程序
    addNumToStack(stack, num); //把当前的值放入，最终计算结果
    return new int[]{getStackRes(stack), i};
  }



  public static void addNumToStack(Stack<String> stack, int num){
    if (!stack.isEmpty()) {
      int cur = 0;
      //技巧:注意这里因为每次添加符号的时候才添加数字，并且每次在添加符号的时候先添加当前的数字，在添加符号，所以stack的顶部总是符号
      String top = stack.pop();
      if (top.equals("+") || top.equals("-")) {
        stack.add(top);//放回去没事了
      } else {
        cur = Integer.valueOf(stack.pop());
        num = top.equals("*") ? (cur * num) : (cur / num);
      }
    }
    stack.push(String.valueOf(num));
  }

  public static int getStackRes(Stack<String> stack){
   int sum = 0;
   boolean add = true;
   for(int i = 0; i < stack.size(); i++){
      String cur = stack.get(i);
      if(cur.equals("+")){
        add = true;
      } else if(cur.equals("-")){
        add = false;
      } else {
        int num = Integer.valueOf(cur);
        sum += add ? num : (-num);
      }
   }
      return sum;
  }
}
