package com.point2offerspecial.ten_PreTree;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-13 15:45
 * @Description:
 */
public class PTOS067_MaxXOR {
  /**
   * 如果使用字典树，一定要脑子里有字典树的结构，并且要注意的是（1）字典树的头结点是一个没有值的结点
   * （2）【这点在这道题上非常有感觉】然后每次观察传进来的值的时候，是站在当前这位的上一层的字典树来观察（
   * 比如这道题就是站在root节点来观察数字最高位的情况root.children[^bit]是否存在，
   * 但是根本无需判断root.childeren[bit]因为这一位在构建字典树的时候已经加到字典树了，有点废话），
   * （3）并且使用了字典树基本不会做相等的判断，通常是判断这个点在这一层是否存在，就可以进行解题。
   * 有了这个框子，这些字典树的题目可以直接构建字典树，并且不需要再字典树中加过多逻辑，生成字典树后，
   * 直接求题目要求，一般后续跟进的方法无非是递归（其他一些题目）或者像这道题是一个贪心 + 数位的处理。代码：参考原书解法
   */
  static class TrieNode{
    public TrieNode[] children;

    public TrieNode(){
      children = new TrieNode[2];
    }
  }

  //建立特殊字典树
  public TrieNode buildTree(int[] nums){
    TrieNode root = new TrieNode();
    for(int n : nums){
      TrieNode node = root;
      for(int i =  31; i >= 0; i--){
          int bit = (n >> i) & 1;
          if(node.children[bit] == null){
            node.children[bit] = new TrieNode();
          }
          node = node.children[bit];
      }
    }
    return root;
  }


  public int findMaximumXOR(int[] nums) {
    //贪心从字典树的高位往下选择，优先选择位高且不同的bit位
    TrieNode root = buildTree(nums);
    int max = 0;
    for(int num : nums){
      int xor = 0;
      TrieNode node = root;
      for(int i = 31; i >= 0; i--){
        int bit = (num >> i) & 1;
        if(node.children[1- bit] != null){
          //技巧：此时这个位上有异同，可以给XOR + 1
          xor = (xor << 1) + 1;
          node = node.children[1 - bit];//技巧：选择不同的走bit相反这边
        } else {
          xor = xor << 1;
          node = node.children[bit];//技巧：都是相同的，走bit那边
        }
      }
      max = Math.max(xor, max);
    }
    return max;
  }
}
