package com.huaweiOD.nowcoder;

import java.util.*;
public class TrainStationByString{
  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int n=in.nextInt();
    //定义代入队列
    Queue<String> queue=new ArrayDeque<>();

    for(int i=0;i<n;i++){
      queue.add(String.valueOf(in.nextInt()));
    }

    //定义栈,后进先出
    Stack<String> stack=new Stack<>();
    //定义出序列,记录从栈中出去的顺序
    List<String> list=new ArrayList<>();
    //定义pop=""为了递归结束后传入出序列里面.
    String pop="";
    //递归上面定义的三个
    res(stack,queue,list,pop);
    //for循环输出list里面的数值
    Collections.sort(list);

    for(String res:list){
      String[] strr=res.split(" {1,}");
      for(int i=0;i<= strr.length-1;i++){
        if(i!=strr.length-1)
          System.out.print(strr[i]+" ");
        else System.out.println(strr[i]);
      }
    }
  }

  public static void res(Stack<String> stack,Queue<String> queue,List<String> list,String pop){
    //如果当前某个子问题的待出栈以及待入队列全是空的就说明没有可以出的了,结束递归,把出栈序列pop存入list里面
    if(queue.isEmpty()&&stack.isEmpty()){
      list.add(pop);
      return;
    }
    //给当前文字划分两个子问题,是入栈还是出栈,这个if我给的是入栈
    if(!queue.isEmpty()){
      Queue<String> queue1=new ArrayDeque<>(queue);
      Stack<String> stack1=(Stack<String>) stack.clone();
      stack1.push(queue1.poll());
      res(stack1,queue1,list,pop);
    }
    //给当前文字划分两个子问题,是入栈还是出栈,这个if我给的是出栈
    if(!stack.isEmpty()){
      Queue<String> queue1=new ArrayDeque<>(queue);
      Stack<String> stack1=(Stack<String>) stack.clone();
      pop=pop+stack1.pop()+" ";
      res(stack1,queue1,list,pop);
    }
  }

}

