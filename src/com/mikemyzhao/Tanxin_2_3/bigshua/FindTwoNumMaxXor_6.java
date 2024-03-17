package com.mikemyzhao.Tanxin_2_3.bigshua;


/**
 * @Author: zhaomengyi
 * @Date: 2022-03-18 15:13
 * @Description:找出数组中最大的异或和子数组的值，这题不是很好，使用异或完全就是两条路，先让前缀树把为0的路径全占领了，然后从0到i有节点可以异或出1就加进去
 */
public class FindTwoNumMaxXor_6 {
  // 前缀树节点结构nexts[0]是0方向的路，nexts[1]是1方向的路
  // nexts[0]=null 0方向没路，nexts[0]!=null 可以跳下一个节点，同理nexts[1]
  public static class Node {
    public Node[] nexts = new Node[2];
  }

  public static class NumTrie {
    public Node head = new Node();//数字一定挂在路上

    //把某个数加入到这个前缀树中
    public void add(int newNum) {
      Node cur = head;
      for (int move = 31; move >= 0; move--) { //整形最大31位，
        int path = ((newNum >> move) & 1); //提取出newNum第move位的状态
        cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];//当前节点的path方向是null建立新节点，如果不是空复用旧的即可
        cur = cur.nexts[path]; //往下跳即可
      }
    }

    //该结构之前收集了一票数字，并建立好了前缀树，num和谁^最大的结果（把结果返回）
    public int maxXor(int num) {
      Node cur = head;
      int res = 0;
      for (int move = 31; move >= 0; move--) {
        int path = (num >> move) & 1; //sum的move位状态
        //best是要贪心的结果
        int best = move == 31 ? path : (path ^ 1); //best表示贪心期待遇到的东西，有符号整数最高31位我们只希望是一样的，0^0或者1^1,因为如果是1的话就负数了
        best = cur.nexts[best] != null ? best : (best ^ 1); //best会改成实际遇到的东西，如果期待的best存在best过去，如果不存在只能走反向
        //
        res |= (path ^ best) << move; //贪心拿结果，当前path ^ best[贪心拿到的结果]，然后移动move位放回
        cur = cur.nexts[best];//cur到下一个
      }
      return res;
    }
  }
  public int findMaximumXOR(int[] nums){
    if(nums == null || nums.length == 0){
      return 0;
    }
    int max = Integer.MIN_VALUE;
    //因为异或和的前缀树记录了这个路径到过或者没有到过，所以可以从0到i一直记录，只要在maxXor选择到过的那条路径就可以了，
    // 这样就不用考虑每个子数组从中间开始的情况了，记录的是每一位的情况
    int xor = 0;//从0到i的整体异或和，这个树一旦生成记录所有到达每个节点的可能
    NumTrie numTrie = new NumTrie();//对整体异或0构建
    numTrie.add(0);//表示一个数都没有异或和为0，这里已经把32位所有为0的路径都填充了
    for(int i = 0; i < nums.length; i++){
      xor ^= nums[i];//把位置数异或进来
      max = Math.max(max, numTrie.maxXor(xor)); //xor和谁结合可以变成最大
      numTrie.add(xor); //把新的xor加进去
    }
    return max;
  }
}
