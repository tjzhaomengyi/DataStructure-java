package com.mikemyzhao.Tanxin_2_3.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-18 18:02
 * @Description: https://leetcode.com/problems/maximum-xor-with-an-element-from-array/
  查询给出数小于m点在arr中的最大异或和
 */
public class MaxXorwithAnElementFromArr_6 {
  //使用前缀树，有num进来进行add方法，然后求maxOr
  public static class Node {
    //这个前缀树只有两个方向0和1，表示进来的数的各个位的二进制方向，一个有符号的数最多32位
    public Node[] nexts = new Node[2];
    public int min;//节点记录最小值

    public Node() {
      min = Integer.MAX_VALUE;
      nexts = new Node[2];
    }
  }


  //基于本题，定制前缀树的实现
  public static class NumTrie {
    public Node head = new Node();

    //把一个数添加到前缀树中
    public void add(int newNum) {
      Node cur = head;
      head.min = Math.min(head.min,newNum);
      for (int move = 31; move >= 0; move--) {
        int path = ((newNum >> move) & 1);//move位置是0还是1,path只会是0或者1
        cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
        cur = cur.nexts[path];
        cur.min = Math.min(cur.min,newNum);//每个节点更新当前的min值
      }
    }

    public int maxXorWithBehindM(int x, int m){
      if(head.min > m){
        return -1;
      }
      Node cur = head;
      int ans = 0;
      for(int move = 30; move >= 0; move--){
        int path = (x >> move) & 1;
        //期待遇到的东西
        int best = (path ^ 1);
        //如果想要的best是null或者best的min值都大于了m就不要了这个期待的best，赶紧与1异或变回去
        best ^= (cur.nexts[best] == null || cur.nexts[best].min > m) ? 1 : 0;
        //best要变成实际遇到的
        ans |= (path ^ best) << move;
        cur = cur.nexts[best];
      }
      return ans;
    }
  }


}
