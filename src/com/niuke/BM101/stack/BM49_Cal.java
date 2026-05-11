package com.niuke.BM101.stack;

import java.util.Stack;

/**
 * 使用双栈法的标准解法，好理解，处理三个位置的东西
 * 1、处理数字
 * 2、处理左右括号，如果是左括号加入ops栈，如果是右括号，开始处理弹栈并处理栈中的内容
 */
public class BM49_Cal {
    public int solve (String s) {
        // write code here
        Stack<Integer> nums = new Stack<Integer>();
        Stack<Character> ops = new Stack<Character>();

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == ' '){
                i++;
                continue;
            }
            // 1、处理数字
            if(Character.isDigit(c)){
                int num = 0;
                while(i < s.length() && Character.isDigit(s.charAt(i))){
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                nums.push(num);
                i--; //这里i已经被推到数字的下一位去了，这里要把 i 定在数字上，再跳出循环
                continue;
            }
            // 2、处理左括号
            else if(c == '('){
                ops.push(c);
            }
            // 3、处理右括号
            else if(c == ')'){
                while(ops.peek() != '('){ //注意这是while
                    calc(nums, ops);
                }
                ops.pop(); //这里一定要把栈中的（弹出；保证一致性
            }
            // 4、处理运算符
            else {
                while(!ops.isEmpty() && priority(ops.peek()) >= priority(c)){ //注意这也是while
                    calc(nums, ops);
                }
                ops.push(c);
            }
        }
        //把剩余的计算完成
        while(!ops.isEmpty()){
            calc(nums, ops);
        }
        return nums.pop();
    }

    //计算函数
    private void calc(Stack<Integer> nums, Stack<Character> ops){
        int b = nums.pop();
        int a = nums.peek();
        char op = ops.pop();
        int res = 0;
        if(op == '+') res = a + b;
        if(op == '-') res = a - b;
        if(op == '*') res = a * b;
        if(op == '/') res = a / b;
        nums.push(res);
    }

    //优先级函数
    private int priority(char op){
        if(op == '+' || op == '-') return 1;
        if(op == '*' || op == '/') return 2;
        return 0;
    }
}
