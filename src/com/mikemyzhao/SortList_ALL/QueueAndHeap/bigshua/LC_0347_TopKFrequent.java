package com.mikemyzhao.SortList_ALL.QueueAndHeap.bigshua;

import com.MCAAlgorithm.base.class33.Hash;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-19 8:44
 * @Description:
 */
public class LC_0347_TopKFrequent {
  public static class  Node{
    public int num;
    public int count;

    public Node(int k){
      num = k;
      count = 1;
    }
  }

  public static class CountComparator implements Comparator<Node>{

    @Override
    public int compare(Node o1, Node o2) {
      //数学结论:topK直接想小根堆
      return o1.count - o2.count;
    }
  }
  public int[] topKFrequent(int[] nums, int k) {
    HashMap<Integer, Node> map = new HashMap<>();
    for(int num : nums){
      if(!map.containsKey(num)){
        map.put(num, new Node(num));
      } else  {
        map.get(num).count++;
      }
    }
    PriorityQueue<Node> heap = new PriorityQueue<>(new CountComparator());
    for(Node node : map.values()){
      if(heap.size() < k || node.count > heap.peek().count){
        heap.add(node);//技巧:把大的放进来
        if(heap.size() > k){
          heap.poll();//技巧:小的在队头，把前面小的推出去
        }
      }
    }
    int[] ans = new int[k];
    for(int i = 0; i < k;i++){
      ans[i] = heap.poll().num;
    }
    return ans;
  }
}
