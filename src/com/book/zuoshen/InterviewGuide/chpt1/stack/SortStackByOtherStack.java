package com.book.zuoshen.InterviewGuide.chpt1.stack;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/11 12:42
 * @Description: 用一个栈实现另一个栈的排序,从大到小排序
 */
public class SortStackByOtherStack {
    private Stack<Integer> stack;
    public SortStackByOtherStack(){
        this.stack = new Stack<Integer>();
    }

    public void push(int val){
        this.stack.push(val);
        sortStackByStackSmall(this.stack);
    }

    public void pop(){
        if(isEmpty()){
            return;
        }
        this.stack.pop();
    }

    public int peek(){
        if(isEmpty()){
            return -1;
        }
      return this.stack.peek();
    }

    public boolean isEmpty(){
        return this.stack.isEmpty();
    }

    //保证原栈是从大到小
    public static void sortStackByStack(Stack<Integer> stack) {
        Stack<Integer> help = new Stack<Integer>();
        //注意：help里面由栈顶到栈底是从小到大，这样返回给stack才是由栈顶到栈底是从大到小
        while (!stack.isEmpty()){
            int cur = stack.pop();
            while(!stack.isEmpty() && help.peek() < cur){
                stack.push(help.pop());
            }
            help.push(cur);
        }
        while(!help.isEmpty()){
            stack.push(help.pop());
        }
    }

    //保证原栈是从小到大
    public static void sortStackByStackSmall(Stack<Integer> stack){
        Stack<Integer> help = new Stack<Integer>();
        while(!stack.isEmpty()){
            int cur = stack.pop();
            while(!help.isEmpty() && cur < help.peek()){
                stack.push(help.pop());
            }
            help.push(cur);
        }
        while(!help.isEmpty()){
            stack.push(help.pop());
        }
    }



}
