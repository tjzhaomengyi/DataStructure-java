package com.mikemyzhao.Tanxin_2_3.bigshua;

import com.MCAAlgorithm.bigshua.class06.Code02_MaximumXorOfTwoNumbersInAnArray;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-18 15:13
 * @Description:
 */
public class FindTwoNumMaxXor_6 {
  public static class Node {
    public Code02_MaximumXorOfTwoNumbersInAnArray.Node[] nexts = new Code02_MaximumXorOfTwoNumbersInAnArray.Node[2];
  }

  public static class NumTrie {
    public Code02_MaximumXorOfTwoNumbersInAnArray.Node head = new Code02_MaximumXorOfTwoNumbersInAnArray.Node();

    public void add(int newNum) {
      Code02_MaximumXorOfTwoNumbersInAnArray.Node cur = head;
      for (int move = 31; move >= 0; move--) {
        int path = ((newNum >> move) & 1);
        cur.nexts[path] = cur.nexts[path] == null ? new Code02_MaximumXorOfTwoNumbersInAnArray.Node() : cur.nexts[path];
        cur = cur.nexts[path];
      }
    }

    public int maxXor(int sum) {
      Code02_MaximumXorOfTwoNumbersInAnArray.Node cur = head;
      int res = 0;
      for (int move = 31; move >= 0; move--) {
        int path = (sum >> move) & 1;
        int best = move == 31 ? path : (path ^ 1);
        best = cur.nexts[best] != null ? best : (best ^ 1);
        res |= (path ^ best) << move;
        cur = cur.nexts[best];
      }
      return res;
    }
  }
  public int findMaximumXOR(int[] nums){
    if(nums == null || nums.length == 0){
      return 0;
    }
    int max = Integer.MIN_VALUE;
    NumTrie numTrie = new NumTrie();
    numTrie.add(nums[0]);
    for(int i = 0; i < nums.length; i++){
      max = Math.max(max,numTrie.maxXor(nums[i]));
      numTrie.add(nums[i]);
    }
    return max;
  }
}
