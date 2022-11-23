package com.mikemyzhao.TreeClassicSytle.pretree.bigshua;

import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-17 16:26
 * @Description:能否用字典的词把原字符串拼出来
 */
public class LC_0139_WordBreak {
  //技巧:构建前缀树,前缀树使用一般肯定配合 动态规划的从左到右模型 使用
  public static class Node {
    public String path;
    public boolean end;
    public Node[] nexts;

    public Node(){
      path = null;
      end = false;
      nexts = new Node[26];
    }
  }

  //数学结论:如何构建前缀树
  public static Node getTrie(List<String> wordDict){
    Node root = new Node();
    for(String str : wordDict){
      char[] chs = str.toCharArray();
      Node node = root;//技巧:拿头
      int index = 0;
      for(int i = 0; i < chs.length; i++){
        index = chs[i] - 'a';
        if(node.nexts[index] == null){
          node.nexts[index] = new Node();//技巧:如果没有赶紧建立出来
        }
        node = node.nexts[index];//技巧:往下继续查，把节点向下推！！！！
      }
      node.path = str;//技巧:在最后节点把path记录在最后节点
      node.end = true;
    }
    return root;
  }

  //j
  public static boolean wordBreak(String s, List<String> wordDict){
    Node root = getTrie(wordDict);//生成字典树
    int N = s.length();
    char[] str = s.toCharArray();
    boolean[] dp = new boolean[N + 1];
    //dp表示从i到结尾位置能不能分出词俩
    dp[N] = true;//数学结论:只要是这种字符串能不能凑的肯定是N结尾N+1长度的dp。到了字符串末尾肯定成功
    for(int i = N - 1; i >=0; i--){
      Node cur = root;//技巧:拿头，||注意位置
      for(int end = i; end < N; end++){
        cur = cur.nexts[str[end] - 'a'];//技巧:从前缀树开始查往下查
        //技巧:只看前缀树后面有没有合适的下一个
        if(cur == null){
          break;
        }
        if(cur.end){
          //技巧:i到end可以查到，看看之前的end+1能不能推出字来
          dp[i] = dp[i] | dp[end + 1];
        }
        if(dp[i]){
          break;//技巧:卡点了，只要成功就往前推
        }
      }
    }
    return dp[0];
  }
}
