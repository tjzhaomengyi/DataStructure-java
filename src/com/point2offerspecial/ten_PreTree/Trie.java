package com.point2offerspecial.ten_PreTree;


/**
 * @Author: zhaomengyi
 * @Date: 2023-02-12 20:08
 * @Description:
 */
public class Trie {
  //太难了~直接记住例子吧 ： 前缀树模板
  static class TrieNode{
    TrieNode children[];
    boolean isWord;

    public TrieNode(){
      children = new TrieNode[26];
    }
  }
  private  TrieNode root;

  public Trie() {
    root = new TrieNode();
  }//技巧：【注意】前缀树的第一个节点就是空的

  public void insert(String word){
    TrieNode node = root;
    for(char ch : word.toCharArray()){
      if(node.children[ch - 'a'] == null){
        node.children[ch - 'a'] = new TrieNode();
      }
      node = node.children[ch - 'a'];
    }
    node.isWord = true;
  }

  public boolean search(String word){
    TrieNode node = root;
    char[] str = word.toCharArray();
    for(char c : str){
      if(node.children[c - 'a'] == null){
        return false;
      }
      node = node.children[c - 'a'];
    }
    return node.isWord;//判断最后是否是结尾
  }

  public boolean startsWith(String prefix){
    TrieNode node = root;
    char[] str = prefix.toCharArray();
    for(char c : str){
      if(node.children[c - 'a'] == null){
        return false;
      }
      node = node.children[c - 'a'];
    }
    return true;
  }

  public static void main(String[] args) {
    Trie trie = new Trie();
    trie.insert("apple");
    trie.search("apple");   // 返回 True
    trie.search("app");     // 返回 False
    trie.startsWith("app"); // 返回 True
    trie.insert("app");
    trie.search("app");     // 返回 True
  }
}
