package com.book.zuoshen.InterviewGuide.chpt1.stack;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/11 09:55
 * @Description:https://leetcode.cn/problems/min-stack/
 */
public class Stack_getMin {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public Stack_getMin() {
        this.stackData = new Stack<Integer>();
        this.stackMin = new Stack<Integer>();
    }

    public void push(int newNum){
        if(this.stackMin.isEmpty()){
            this.stackMin.push(newNum);
        } else if(newNum < this.getMin()){
            this.stackMin.push(newNum);
        } else {
            int newMin = this.stackMin.peek();
            this.stackMin.push(newMin);
        }
        this.stackData.push(newNum);
    }

    public int getMin(){
        if(this.stackMin.isEmpty()){
            throw new RuntimeException("Your stack is empty");
        } else {
            return this.stackMin.peek();// 不弹出栈
        }
    }

    public int pop(){
        if(this.stackData.isEmpty()){
            throw new RuntimeException("Your stack is empty");
        } else {
          this.stackMin.pop();
          return this.stackData.pop();
        }
    }

    public int top(){
        if(this.stackData.isEmpty()){
            throw new RuntimeException("Your stack is empty");
        } else {
            return this.stackData.peek();
        }
    }

    public static void main(String[] args) {
        Stack_getMin stack = new Stack_getMin();
        stack.push(-1);
        int top = stack.top();

    }
}
