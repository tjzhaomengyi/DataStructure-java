package com.book.zuoshen.InterviewGuide.chpt5;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/19 11:11
 * @Description:使用递归的方案从左往右计算式子，这个递归比较傻逼，半截返回一个计算结果，还有一个当前递归到的位置
 */
public class CalculateStrFormula {
    //只有一个原则遇到"("，就找对应的")",进入递归过程
    public int getValue(String exp){
        return value(exp.toCharArray(), 0)[0];
    }

    public int[] value(char[] chars, int i){
        Deque<String> deq = new LinkedList<String>(); //双端队列存中间结果
        int pre = 0;
        int[] bra = null;//一个二维数组，获取后面结果（数值和后面递归结束位置）
        while(i < chars.length && chars[i] != ')'){
            if(chars[i] >= '0' && chars[i] <= '9'){ //数字
                pre = pre * 10 + chars[i++] - '0';
            } else if(chars[i] != '('){ //如果是算数符号
                addNum(deq, pre);
                deq.addLast(String.valueOf(chars[i++]));
                pre = 0;
            } else { //如果是左括号进入递归
                bra = value(chars, i + 1);
                pre = bra[0];
                i = bra[1] + 1;

            }
        }
        //最后到了字符串长度，或者右括号，把pre值放入deq，最后计算结果
        addNum(deq, pre);
        return new int[] {getNum(deq), i};
    }

    //遇到算数符号的话把递归拉来的数值放入deq中，并进行计算，换成stack也行
    public void addNum(Deque<String> deq, int num){
        if(!deq.isEmpty()){
            int cur = 0;
            String top = deq.pollLast();
            if(top.equals("+") || top.equals("-")){ //如果是加减符号，不做任何操作，把操作符还原放回
                deq.addLast(top);
            } else { //如果是乘法或者除法
                cur = Integer.valueOf(deq.pollLast());//把上面的数拉出来
                num = top.equals("*") ? (cur * num) : (cur / num);
            }
        }
        deq.addLast(String.valueOf(num));
    }

    //把deq或者stack中的容器中的数据拿出来计算结果
    public int getNum(Deque<String> deq){
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while(!deq.isEmpty()){
            cur = deq.pollFirst();
            if(cur.equals("+")){
                add = true;
            } else if (cur.equals("-")){
                add = false;
            } else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }
}
