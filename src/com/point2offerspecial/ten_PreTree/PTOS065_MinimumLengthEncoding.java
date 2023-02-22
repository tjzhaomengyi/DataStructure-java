package com.point2offerspecial.ten_PreTree;

import com.MCAAlgorithm.bigshua.class21.TreeChainPartition;
import com.sun.xml.internal.xsom.impl.SchemaImpl;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-13 12:21
 * @Description:
 */
public class PTOS065_MinimumLengthEncoding {
  //对于后缀词的省略编码,把字符反转过来，倒着构建字典书
  public class TrieNode{
      TrieNode[] children;
      boolean isWord;

      public TrieNode(){
        children = new TrieNode[26];
      }
  }

  private TrieNode buildTree(String[] words){
    TrieNode root = new TrieNode();
    //用一个HashMap统计每个叶子节点的长度
    HashMap<TrieNode, Integer> map = new HashMap<>();
    for(String word : words){
      TrieNode node = root;
      char[] w = word.toCharArray();

      for(int i = w.length - 1; i >= 0; i--){
        char ch = w[i];
        if(node.children[ch - 'a'] == null){
          node.children[ch - 'a'] = new TrieNode();
        }
        node = node.children[ch - 'a'];
      }
      node.isWord = true;
    }
    return root;
  }

  public int minimumLengthEncoding(String[] words) {
    TrieNode root= buildTree(words);
    int[] total = new int[]{0};
    dfs(root, 1, total);
    return total[0];
  }

  //直接记住吧：使用dfs统计每个叶子结点的长度，就是最后的计算长度,这样不用再构建树的过程中添加其他逻辑,可以改成Info信息那种
  private void dfs(TrieNode root, int length, int[] total){
    boolean isLeaf = true;
    for(TrieNode child : root.children){
      if(child != null){
        dfs(child, length + 1, total); //注意：这里还有个技巧点就是每次都把root那个空节点加进去充当题目中的分隔符井号使用
      }
    }
    if(isLeaf){
      total[0] += length;
    }
  }




}
