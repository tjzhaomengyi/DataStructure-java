package com.book.point2offerspecial.ten_PreTree;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-13 14:27
 * @Description:
 */
public class PTOS066_MapSum {
  static class TrieNode{
    TrieNode[] children;
    int value;

    public TrieNode(){
      children = new TrieNode[26];
      value = 0;
    }

  }
  private TrieNode root;
  /** Initialize your data structure here. */
  public PTOS066_MapSum() {
    root = new TrieNode();
  }

  public void insert(String key, int val) {
    TrieNode node = root;
    char[] keyStr = key.toCharArray();
    for(int i = 0; i < key.length(); i++){
      char c = keyStr[i];
      if(node.children[c - 'a'] == null){
        node.children[c - 'a'] = new TrieNode();
      }
      node = node.children[c - 'a'];
    }
    node.value = val;//存储单词前缀树的个数
  }

  public int sum(String prefix) {
    TrieNode node = root;
    char[] str = prefix.toCharArray();
    for(int i = 0; i < prefix.length(); i++){
      char c = str[i];
      if(node.children[c - 'a'] == null){
        return 0;
      } else {
        node = node.children[c - 'a'];
      }
    }
    return getAllSum(node);//如果该单词后面还有字符，就把后续结果加上
  }

  public int getAllSum(TrieNode node){
    if(node == null){
      return 0;
    }
    int ans = node.value;
    for(TrieNode child : node.children){
      ans += getAllSum(child);
    }
    return ans;
  }
}
