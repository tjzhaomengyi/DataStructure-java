package com.book.zuoshen.InterviewGuide.chpt1.stack;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/11 11:17
 * @Description:
 */
public class ReverseStackWithRecursion {
    //技巧：【这个理解递归挺好，见图1-4】递归函数1：将栈的栈底元素返回并移除
    public static int getAndRemoveLastEle(Stack<Integer> stack){
        int result = stack.pop();
        //用递归拿到最底下的栈底元素
        if (stack.isEmpty()){
            return result;
        } else {
            int last = getAndRemoveLastEle(stack); //真正的栈底元素
            stack.push(result);//把已经拿到的栈顶元素再放回栈中
            return last;
        }
    }

    //使用递归逆序一个栈
    public static void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        int i = getAndRemoveLastEle(stack); //栈底元素
        reverse(stack);//递归中间部分进行逆序
        stack.push(i);//把栈底元素放在最上面
    }
}
