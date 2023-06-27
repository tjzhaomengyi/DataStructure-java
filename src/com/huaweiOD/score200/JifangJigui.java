package com.huaweiOD.score200;

import java.util.Scanner;
import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-22 14:30
 * @Description: 这题啥意思没看懂
 */
public class JifangJigui {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      System.out.println(solveMethod(str));
    }
  }

  public static int solveMethod(String line){
    int n = line.length();
    Stack<int[]> stack = new Stack<>();
    boolean stick = false;

    for(int i = 0; i < n; i++){
      if(line.charAt(i) == 'M'){
        boolean left = i - 1 < 0 || line.charAt(i - 1) == 'M';
        boolean right = i + 1 >= n || line.charAt(i + 1) == 'M';
        if(left && right){
          return -1;
        }
        int[] range = {Math.max(0, i - 1), Math.min(n - 1, i + 1)};
        if(!stack.isEmpty() && !stick){
          int[] prev = stack.peek();
          int end1 = prev[1];
          int start2 = range[0];
          if(end1 == start2){
            stack.pop();
            stick = true;
          }
        } else {
          stick = false;
        }
        stack.add(range);
      }
    }
    return stack.size();
  }
}
