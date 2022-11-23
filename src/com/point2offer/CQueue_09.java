package com.point2offer;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-04 18:18
 * @Description:
 */
public class CQueue_09 {
  private Stack inStack;
  private Stack outStack;

  public CQueue_09() {
    this.inStack = new Stack();
    this.outStack = new Stack();
  }

  public void appendTail(int value) {
    this.inStack.push(Integer.valueOf(value));
  }

  public int deleteHead() {

    if (outStack.isEmpty()) {
      if(inStack.isEmpty()){
        return -1;
      }
      while (!inStack.isEmpty()) {
        this.outStack.push(inStack.pop());
      }
    }
    return (int) outStack.pop();
  }
}

