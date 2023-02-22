package com.point2offerspecial.fifteen_graph.topology;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-21 17:29
 * @Description:
 */
public class PTOS115_SequenceReconstruction {
  //思路：又是一道类字典序的问题，就是找有没有唯一的拓扑排序HashMap<Integeer,Node> Node(Integer,in,nexts) + Queue拓扑排序

  public class Node{
    int val;
    int in;
    HashSet<Integer> nexts;

    public Node(int val){
      this.val = val;
      nexts = new HashSet<>();
    }
  }

  public class Graph{
    HashMap<Integer, Node> nodes;

    public Graph(){
      nodes = new HashMap<>();
    }
  }

  public boolean sequenceReconstruction(int[] nums, int[][] sequences){
    Graph graph = new Graph();
    HashMap<Integer, Node> nodes = graph.nodes;

    for(int num : nums){
      nodes.put(num, new Node(num));
    }

    for(int[] seq : sequences){
//      for(int num : seq){
//        if(nodes.containsKey(num)){
//          nodes.put(num, new Node(num));
//        }
//      }

      for(int i = 0; i < seq.length - 1; i++){
        int num1 = seq[i];
        int num2 = seq[i + 1];
        Node num1Node = nodes.get(num1);
        Node num2Node = nodes.get(num2);
        if(!num1Node.nexts.contains(num2)){
          num1Node.nexts.add(num2);
          num2Node.in++;
        }
      }
    }

    Queue<Integer> queue = new LinkedList<>();
    for(int key : nodes.keySet()){
      if(nodes.get(key).in == 0){
        queue.add(key);
      }
    }

    List<Integer> ans = new LinkedList<>();
    while(queue.size() == 1){ //这个贱东西不知道啥意思
      int num = queue.poll();
      ans.add(num);
      for(int next : nodes.get(num).nexts){
        Node nextNode = nodes.get(next);
        nextNode.in--;
        if(nextNode.in == 0){
          queue.add(next);
        }
      }
    }

    int[] result = new int[ans.size()];
    result = ans.stream().mapToInt(i->i).toArray();
    return Arrays.equals(result, nums);
  }

  public static void main(String[] args) {
    boolean ans = new PTOS115_SequenceReconstruction().sequenceReconstruction(
        new int[]{1,2,3}, new int[][]{{1,2},{1,3}}
    );

    System.out.println(ans);
  }
}
