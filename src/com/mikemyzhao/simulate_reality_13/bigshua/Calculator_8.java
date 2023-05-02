package com.mikemyzhao.simulate_reality_13.bigshua;



import java.util.LinkedList;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-19 17:17
 * @Description:给定一个字符串计算结果 ,相似类型的题目:化学表达式的化学元素，字符串压缩，都是这种问题一样的
 * 处理括号嵌套的问题:套路就是 扩这个是括号嵌套问题
 */
public class Calculator_8 {
  //遇到左括号，使用递归往下迭代括号部分，通过左括号划分为每个部分，每个部分有自己的小栈，这样可以解决大部分带括号的问题
  public static int calculate(String str){
    return f(str.toCharArray(),0)[0];//f函数返回该部分计算结果和，结束位置
  }

  public static int[] f(char[] str, int i){
    LinkedList<String> que = new LinkedList();//这里每一层在递归的时候有自己的小队列，然后最上层是自己的队列
    int cur = 0;//记录当前碰到的数字可以组成的数
    int[] bra = null;//这个是最后返回的二维数组结果，计算值和计算结束位置
    while (i < str.length && str[i] != ')'){
      if(str[i] >= '0' && str[i] <= '9'){//遇到数字
        cur = cur * 10 + str[i++] - '0';
      } else if(str[i] != '('){ // 遇到运算符号
        addNum(que,cur);//先算一下
        que.addLast(String.valueOf(str[i++]));
        cur = 0;
      } else {//遇到左括号，懒运算，它只想靠着当前进行迭代
        bra = f(str,i+1);//重复调用当前的递归
        cur = bra[0];
        i = bra[1] + 1;
      }
    }
    addNum(que, cur);//如果碰上的是右括号或者结束就要把当前值先加进去计算出结果
    return new int[]{getNum(que),i};
  }

  public static void addNum(LinkedList<String> que,int num){
    if(!que.isEmpty()){
      int cur = 0;
      String top = que.pollLast();
      if(top.equals("+") || top.equals("-")){
        que.addLast(top);//如果是+-不动，放回
      }else{
        cur = Integer.valueOf(que.pollLast());
        num = top.equals("*") ? (cur * num) : (cur / num);
      }
    }
    que.addLast(String.valueOf(num));
  }

  public static int getNum(LinkedList<String> que){
    int res = 0;
    boolean add = true;
    String cur = null;
    int num = 0;
    while(!que.isEmpty()){
      cur = que.pollFirst();
      if(cur.equals("+")){
        add = true;
      }else if(cur.equals("-")){
        add = false;
      }else {
        num = Integer.valueOf(cur);
        res += add ? num : (-num);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    String s = "3*5+8-0*3-6+0+0".replaceAll("[\\{\\[]","(").replaceAll("[\\}\\]]",")");
    System.out.println(calculate(s));
  }
}
