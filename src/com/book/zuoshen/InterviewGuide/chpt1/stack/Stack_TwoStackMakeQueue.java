package com.book.zuoshen.InterviewGuide.chpt1.stack;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/11 10:22
 * @Description:要有add()，poll()和peek()功能
 */
public class Stack_TwoStackMakeQueue {
    //注意：在popStack中有数据的时候，不要从StackPush中倒入数据。如果要向stackPop数据中倒入数据，就一次性倒入
    public Stack<Integer> stackPush;
    public Stack<Integer> stackPop;

    public Stack_TwoStackMakeQueue() {
        this.stackPush = new Stack<Integer>();
        this.stackPop = new Stack<Integer>();
    }

    //push栈向pop栈倒入数据
    private void pushToPop(){
        //技巧：这个位置的判断非常讨巧，避免了混乱的判断
        if (stackPop.isEmpty()){ //注意：这里防止：在Stackpop中有元素还加入的问题，这样在peek的时候保证是正确的队头即可
            while(!stackPush.isEmpty()){
                stackPop.push(stackPush.pop());
            }
        }
    }

    //在这三个动态调整队列的方法中，要处理两个栈中的东西
    public void push(int pushInt){
        stackPush.push(pushInt);
        pushToPop();
    }

    public int poll(){
        if (stackPop.isEmpty() && stackPush.isEmpty()){
            throw new RuntimeException("Queue is empty.");
        }
        pushToPop();
        return stackPop.pop();
    }

    public int peek(){
        if(stackPop.empty() && stackPush.empty()){
            throw new RuntimeException("Queue is empty");
        }
        pushToPop();
        return stackPop.peek();
    }

    public boolean empty(){
        if(stackPop.empty() && stackPush.empty()){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Stack_TwoStackMakeQueue q = new Stack_TwoStackMakeQueue();
        for(int i=0; i < 10; i++){
            q.push(i);//注意：这里在添加元素的时候，stackPop里面一直就只有一个元素，这样可以保证可以正常取到peek
        }
        int peek = q.peek();
        System.out.println(peek);
    }
}
