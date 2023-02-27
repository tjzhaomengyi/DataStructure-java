package com.point2offerspecial.nine_heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-12 17:09
 * @Description:
 */
public class PTOS060_TopKFrequent {
  public class Node {
    int num;
    int cnt;
    public Node(int num) {
      this.num = num;
      this.cnt = 1;
    }
  }

  public  class NodeCompator implements Comparator<Node>{

    @Override
    public int compare(Node o1, Node o2) {
      return o1.cnt - o2.cnt;//小根堆
    }
  }
  public int[] topKFrequent(int[] nums, int k) {
    HashMap<Integer, Node> map = new HashMap<>();
    for(int n : nums){
      if(map.containsKey(n)){
         map.get(n).cnt++;
      } else {
        map.put(n, new Node(n));
      }
    }
    PriorityQueue<Node> minHeap = new PriorityQueue(new NodeCompator());
    int[] ans = new int[k];
    for(Node node : map.values()) {
      if (minHeap.size() < k) {
        minHeap.offer(node);
      } else if(minHeap.peek().cnt < node.cnt){
        minHeap.poll();
        minHeap.offer(node);
      }
    }
    for(int i = 0; i < k; i++){
      ans[i] = minHeap.poll().num;
    }
    return ans;
  }
}
