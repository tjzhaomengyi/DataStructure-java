package com.mikemyzhao.TreeClassicSytle.pretree.bigshua;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-17 18:04
 * @Description:
 */
public class LC_0140_WordBreakII_FindBreakChars_ByDFS {
  public static class Node {
    public String path;
    public boolean end;
    public Node[] nexts;

    public Node() {
      path = null;
      end = false;
      nexts = new Node[26];
    }
  }

  public static Node getTrie(List<String> words){
    Node root = new Node();
    for(String word : words){
      char[] str = word.toCharArray();
      Node cur = root;
      int index = 0;
      for(int i = 0; i < str.length; i++){
        index = str[i] - 'a';
        if(cur.nexts[index] == null){
          cur.nexts[index] = new Node();
        }
        cur = cur.nexts[index];
      }
      cur.path = word;
      cur.end = true;
    }
    return root;
  }

  public static boolean[] getdp(String s, Node root){
    char[] str = s.toCharArray();
    int N = str.length;
    boolean[] dp = new boolean[N + 1];
    dp[N] = true;
    for(int i = N - 1; i > 0; i--){
      Node cur = root;
      for(int end = i; end < N; end++){
        cur = cur.nexts[str[end] - 'a'];
        if(cur == null){
          break;
        }
        if(cur.end){
          dp[i] |= dp[end + 1];
        }
        if(dp[i]){
          break;
        }
      }
    }
    return dp;
  }

  public static List<String> wordBreak(String s,List<String> wordDict){
    char[] str = s.toCharArray();
    Node root = getTrie(wordDict);
    boolean[] dp = getdp(s,root);
    ArrayList<String> path = new ArrayList<>();//技巧:记录DFS的path
    List<String> ans = new ArrayList<>();
    process(str, 0, root, dp, path, ans);
    return ans;

  }

  //str[index...是要搞定的字符串]默认前面的已经搞定了
  public static void process(char[] str, int index, Node root, boolean[] dp, ArrayList<String> path,
                             List<String> ans){
    if(index == str.length){
      StringBuilder builder = new StringBuilder();
      for(int i = 0; i < path.size() - 1; i++){
        builder.append(path.get(i) + " ");
      }
      builder.append(path.get(path.size() - 1));
      ans.add(builder.toString());
    } else {
      Node cur = root;
      for(int end = index; end < str.length; end++){
        int road = str[end] - 'a';
        if(cur.nexts[road] == null){
          break;
        }
        cur = cur.nexts[road];
        if(cur.end && dp[end + 1]){
          path.add(cur.path);
          process(str, end + 1, root, dp, path, ans);
          path.remove(path.size() - 1);
        }
      }
    }
  }

}
