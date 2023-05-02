package com.huaweiOD.od2023.s120e138;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-17 10:08
 * @Description: https://dream.blog.csdn.net/article/details/129143332
 */
public class KaoQinXinxi {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      System.out.println(areYouOk(str));
    }
  }

  public static boolean areYouOk(String str){
    Stack<String> stack = new Stack<>();
    LinkedList<String> queue = new LinkedList<>();
    String[] strs = str.split(" ");
    int absent = 0;
    int myCount = 0;
    for(int i = 0; i < strs.length; i++){
      String cur = strs[i];
      if(cur.equals("absent")){
        if(++absent > 1) return  false; //缺勤不超过一次
      }
      if(cur.equals("leaveearly") || cur.equals("late")){
        if(stack.peek().equals("late") || stack.peek().equals("leaveearly")){
          return false;
        } else {
          myCount++;
        }
      }
      stack.push(cur);
      queue.offer(cur);
      if(queue.size() == 7 && myCount >3){
        return false;
      } else {
         String poll = queue.poll();
         if(poll.equals("late") || poll.equals("leaveearly")){
           myCount--;
         }
      }
    }
    return true;
  }

}
