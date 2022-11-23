package com.mikemyzhao.Tanxin_2_3.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-18 14:28
 * @Description:求子数组的最大异或和
 */
public class MaxSubarrXor_5 {
  //使用前缀树，有num进来进行add方法，然后求maxOr
  public static class Node {
    //这个前缀树只有两个方向0和1，表示进来的数的各个位的二进制方向，一个有符号的数最多32位
    public Node[] nexts = new Node[2];
  }

  //基于本题，定制前缀树的实现
  public static class NumTrie {
    public Node head = new Node();
    //把一个数添加到前缀树中
    public void add(int newNum){
      Node cur = head;
      for(int move = 31; move >= 0; move--){
        int path = ((newNum >> move) & 1);//move位置是0还是1,path只会是0或者1
        cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
        cur = cur.nexts[path];
      }
    }
    //该结构之间收集了一票数字，并且构建好了对应前缀树，num和谁最大的结果，并返回
    public int maxXor(int num){
      Node cur = head;
      int ans = 0;
      for(int move = 31; move >= 0; move--){
        //取出num中第move位的状态，path只有0和1两种值
        int path = (num >> move) & 1;
        //期待遇到的东西
        //如果是31位为0表示正数，如果是1表示负数，正负数都是希望符号为和当前一样，正数为正数，负数变正数
        //如果是31位就是path保持一致，如果没有就变
        int best = move == 31 ? path : (path ^ 1);
        ans |= (path ^ best) << move;
        cur = cur.nexts[best];
      }
      return ans;
    }
  }

  public static int maxXorSubarray(int[] arr){
    if(arr == null || arr.length == 0){
      return 0;
    }
    int max = Integer.MIN_VALUE;
    int xor = 0;
    NumTrie numTrie = new NumTrie();
    numTrie.add(0);
    for(int i = 0; i < arr.length; i++){
      xor ^= arr[i];
      max = Math.max(max, numTrie.maxXor(xor));
      numTrie.add(xor);
    }
    return max;
  }
}
