package com.hots100.structure;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-16 5:47 下午
 * @Description:
 */
public class Code0030_MinStack {
  private Stack<Integer> stackData;
  private Stack<Integer> stackMin;
  public Code0030_MinStack() {
    this.stackData = new Stack<>();
    this.stackMin = new Stack<>();
  }

  public void push(int val) {
    if(this.stackMin.isEmpty()){
      this.stackMin.push(val);
    } else if (val <= this.getMin()){
      this.stackMin.push(val);
    }
    this.stackData.push(val);
  }

  public void pop() {
    if(this.stackData.isEmpty()){
      throw new RuntimeException("Stack is empty");
    }
    int val = this.stackData.pop();
    if(val == this.getMin()){
      this.stackMin.pop();
    }
  }

  public int top() {
    if(this.stackData.isEmpty()){
      throw new RuntimeException("Stack is empty");
    }
    int val = this.stackData.peek();
    return val;
  }

  public int getMin() {
    if(this.stackMin.isEmpty()){
      throw new RuntimeException("Stack is empty");
    }
    return this.stackMin.peek();
  }
}
