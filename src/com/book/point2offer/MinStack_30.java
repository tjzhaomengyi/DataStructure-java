package com.book.point2offer;


import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-06 23:03
 * @Description:不要考虑那么多，跟着题目的例子走就行，所以辅助栈B就是帮着A存比当前最小的即可
 */
public class MinStack_30 {
  /**
   * initialize your data structure here.
   */
  private Stack A;
  private Stack B;
  public MinStack_30() {
    A=new Stack();
    B=new Stack();
  }

  public void push(int x) {
    if(A.isEmpty() || x<=(int)B.peek()){
      B.push(x);
    }
    A.push(x);
  }

  public void pop() {
    if(A.isEmpty()) return;
    if(A.peek().equals(B.peek())){
      B.pop();
    }
    A.pop();
  }

  public int top() {
    return (int) A.peek();
  }

  public int min() {
    return (int)B.peek();
  }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */