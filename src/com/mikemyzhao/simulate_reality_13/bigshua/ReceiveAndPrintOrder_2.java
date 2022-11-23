package com.mikemyzhao.simulate_reality_13.bigshua;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-15 11:10
 * @Description:信息流不按照顺序接收，但是要求按照顺序打印
 */
public class ReceiveAndPrintOrder_2 {
  public  static class Node{
    public String info;
    public Node next;

    public Node(String str){ info = str;}
  }

    //把消息先缓存到消息盒子中,用headmap和tailmap记录头尾信息
    public static class MessageBox {
      private HashMap<Integer, Node> headMap;
      private HashMap<Integer, Node> tailMap;
      private int waitPoint;

      public MessageBox(){
        headMap = new HashMap<Integer, Node>();
        tailMap = new HashMap<Integer, Node>();
        waitPoint = 1;
      }

      public void receive(int num, String info){
        if(num < 1){
          return;
        }
        Node cur = new Node(info);
        headMap.put(num, cur);
        tailMap.put(num, cur);
        //查询有没有某个区间以num-1结尾
        if(tailMap.containsKey(num-1)){
          tailMap.get(num-1).next = cur;
          tailMap.remove(num-1);
          headMap.remove(num);
        }
        //查询有没有某个连续区间以num+1开头
        if(headMap.containsKey(num + 1)){
          cur.next = headMap.get(num + 1);
          tailMap.remove(num);
          headMap.remove(num + 1);
        }
        if(num == waitPoint){
          print();
        }
      }

      private void print(){
        Node node = headMap.get(waitPoint);
        headMap.remove(waitPoint);
        while (node != null){
          System.out.println(node.info + " ");
          node = node.next;
          waitPoint++;
        }
      }
    }



}
