package com.point2offerspecial.fifteen_graph.topology;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-21 14:27
 * @Description: 编辑部排版出错问题也是这个【字典序 + 拓扑图生成】,
 * 这个是按照规矩写的Graph里面有HashMap<Character,Node>,Node里面属性：char，in【拓扑排序只入度】，out
 */
public class PTOS114_AlienOrder {
  //技巧：通过题目给定的字母顺序，得到字母之间的拓扑关系,如果是某个字母不是拓扑排序的最后一个字母，
  // 那么，他肯定出现在给出字符串的最后字符的前面
  public class Node{
    public char c;
    public int in;
    public int out;
    public Set<Character> nexts;

    public Node(char c){
      this.c = c;
      nexts = new HashSet<Character>();
    }
  }

  public class Graph{
    public HashMap<Character, Node> nodes;
    public Graph(){
      this.nodes = new HashMap<Character, Node>();
    }
  }

  public String alienOrder(String[] words){
    Graph graph = new Graph();
    HashMap<Character, Node> nodes = graph.nodes;

    //技巧：先放节点，这个是规矩
    for(String word : words){
      char[] str = word.toCharArray();
      for(int i = 0; i < str.length; i++){
       //先放进去有啥事后续再说
        nodes.put(str[i], new Node(str[i]));
      }
    }

    //技巧：从上到下看字典序，如果相邻两个字符串，相同位置的字符
    for(int i = 0; i < words.length - 1; i++){
      char[] cur = words[i].toCharArray();
      char[] next = words[i + 1].toCharArray();
      int len = Math.min(cur.length, next.length);//字典序比到最短的即可
      //技巧：两个相邻的字符串，相同位置的字符，cur[i]在next[i]的前面，
      // 说明cur[i]这个字符的字典序在next[i]的前面，后面的字符就不用比较了（字典序就看第一个不一样的）
      int j = 0;
      //abc ab
      for(; j < len; j++){
        if(cur[j] != next[j]){//技巧：一旦有相同位置的字符不一样了
          if(!nodes.get(cur[j]).nexts.contains(next[j])){
            nodes.get(cur[j]).nexts.add(next[j]);//相邻结点
            nodes.get(cur[j]).out++;//出度+1
            nodes.get(next[j]).in++;//入度+1
          }
          break;//j位置后面的东西不用看了
        }
      }
      if(j < cur.length && j == next.length){
        return "";//技巧：特殊情况：前缀问题，例子：abcde,abc【d的字典序比空还大，这就不对了，直接返回""（非正常字典序）】
      }
    }

    //技巧：执行拓扑排序
    StringBuilder ans = new StringBuilder();
    Queue<Character> queue = new LinkedList<>();
    for(Character key : graph.nodes.keySet()){
      if(graph.nodes.get(key).in == 0){
        queue.offer(key);
      }
    }
    while(!queue.isEmpty()){
      char cur = queue.poll();
      ans.append(cur);
      for(char next : graph.nodes.get(cur).nexts){
        //cur已经出来了，所以cur相邻结点的入度in-1；
        Node nextNode = graph.nodes.get(next);
        nextNode.in--;
        if(nextNode.in == 0){
          queue.offer(next);
        }
      }
    }
    return ans.length() == graph.nodes.size() ? ans.toString() : "";
  }

  public static void main(String[] args) {
    String ans = new PTOS114_AlienOrder().alienOrder(new String[]{"abc","ab"});
    System.out.println(ans);
  }
}
